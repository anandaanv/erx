package com.erx.service.handler.sms;

public class SMSResponseObject {
	private String status;
	private int balance;
	private int cost;
	private int num_messages;
	private Message message;
	private Messages[] messages;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getNum_messages() {
		return num_messages;
	}

	public void setNum_messages(int num_messages) {
		this.num_messages = num_messages;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Messages[] getMessages() {
		return messages;
	}

	public void setMessages(Messages[] messages) {
		this.messages = messages;
	}

	public static class Message{
		String num_parts;
		String sender;
		String content;
		public String getNum_parts() {
			return num_parts;
		}
		public void setNum_parts(String num_parts) {
			this.num_parts = num_parts;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	}
	
	public static class Messages{
		int id;
		String recipient;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getRecipient() {
			return recipient;
		}
		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}
	}
}
