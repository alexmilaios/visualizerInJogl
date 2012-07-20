package sort;

import graph.*;

public class SenderReceiverPairs {

	private Node sender;
	private Node receiver;

	public SenderReceiverPairs(Pair sender, Pair receiver) {
			this.sender =  new Node(sender);
			this.receiver = new Node(receiver); 
	}
	
	public boolean equals(Object pair) {
		return (this.toString().equals(pair.toString()));
	}
	
	public String toString() {
		return sender.toString() + " -> " + receiver.toString();
	}
	
	public void setSender(Node sender) {
		this.sender = sender;
	}

	public Node getSender() {
		return sender;
	}

	public void setReceiver(Node receiver) {
		this.receiver = receiver;
	}

	public Node getReceiver() {
		return receiver;
	}
}