package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import sort.SenderReceiverPairs;

public class Topology {

	private Vector<SenderReceiverPairs> messages;
	
	public Topology(Vector<SenderReceiverPairs> messages) {
		this.messages = messages;
	}
	
	public List<Connection> getTopology() {
		List<Connection> topology = new ArrayList<Connection>();
		Iterator<SenderReceiverPairs> itr = messages.iterator();
		
		while(itr.hasNext()) {
			SenderReceiverPairs pair = itr.next();
			Node sender = pair.getSender();
			Node receiver = pair.getReceiver();
			Connection temporaryConFront = new Connection(sender.pair.getNode(), receiver.pair.getNode());
			Connection temporaryConBack = new Connection(receiver.pair.getNode(), sender.pair.getNode());
			if((!topology.contains(temporaryConBack)) && (!topology.contains(temporaryConFront))
					&& (temporaryConFront.from != -1) && (temporaryConBack.to != -1)){
				topology.add(temporaryConFront);
			}
		}
		return topology;
	}
}
