package io.zbus.mq.api;

import java.util.HashMap;
import java.util.Map;

public interface MqAdmin{   
	
	MqFuture<Topic> declareTopic(TopicDeclare ctrl); 
	MqFuture<Boolean> removeTopic(TopicRemove ctrl);  
	MqFuture<Topic> queryTopic(TopicQuery ctrl); 
    
	MqFuture<Channel> declareChannel(ChannelDeclare ctrl); 
	MqFuture<Boolean> removeChannel(ChannelRemove ctrl);  
	MqFuture<Channel> queryChannel(ChannelQuery ctrl);   
	
	void configAuth(Auth auth);
	
	public static class Auth{
		public String appId;
		public String token; 
	}
	
	public static class Topic {
		public String name;

		@Override
		public String toString() {
			return "Topic [name=" + name + "]";
		}  
	}
	
	public static class TopicDeclare {
		public String topic;
		public Boolean rpcFlag;
		public Map<String, String> properties = new HashMap<String, String>(); 
	}
	
	public static class TopicQuery {
		public String topic;
		public String appId;
		public Long createdTime; 
	}
	
	public static class TopicRemove {
		public String topic;
		public String appId;
		public Long createdTime; 
	}
	
	
	public static class Channel {
		public String topic;
		public String channel;
		@Override
		public String toString() {
			return "Channel [topic=" + topic + ", channel=" + channel + "]";
		} 
		
	}
	
	public static class ChannelDeclare {
		public String topic;
		public String channel;
		public String tag;
		
		public Boolean deleteOnExit;
		public Boolean exclusive;
		
		public Long consumeStartOffset;
		public Long consumeStartTime;  
	} 
	
	public static class ChannelQuery {
		public String topic;
		public String channel; 
	}
	
	public static class ChannelRemove {
		public String topic;
		public String channel; 
	}
}