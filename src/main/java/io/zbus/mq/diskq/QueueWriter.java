package io.zbus.mq.diskq;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * 
 * @author Rushmore
 *
 */
public class QueueWriter { 
	private final Index index;
	private Block writeBlock;
	private final Lock writeLock = new ReentrantLock();  
	
	public QueueWriter(Index index) throws IOException {
		this.index = index;
		writeBlock = index.createWriteBlock();
	}
	
	public void write(byte[] data) throws IOException{
		writeLock.lock();
		try{ 
			int count = writeBlock.write(data); 
			if(count <= 0){
				writeBlock.close();
				writeBlock = index.createWriteBlock();
			}
		}
		finally {
			writeLock.unlock();
		}
	} 
}