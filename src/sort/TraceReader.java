package sort;

import graph.Node;

import java.io.*;
import java.util.Vector;

public class TraceReader {

	private BufferedReader in; 

	public TraceReader(FileInputStream _in){
		in = new BufferedReader(new InputStreamReader(new DataInputStream(_in)));
	}


	private boolean isMember(Vector<Node> list, Pair pair) {

		for(int i = 0; i < list.size(); i++){
			if(pair.present().equals(list.elementAt(i).toString())){
				return true;
			}
		}
		return false;
	}

	public Vector<Node> getPairs() throws IOException{
		Vector<Node> pairs = new Vector<Node>();
		String line;
		Pair sender = null, receiver = null;
		while((line = in.readLine()) != null){
			if(LineReader.isReceive(line)){

				sender = LineReader.getSender(line);
				receiver = LineReader.getReceiver(line);

				if(!isMember(pairs, sender))
					pairs.add(new Node(sender));

				if(!isMember(pairs, receiver))
					pairs.add(new Node(receiver));
			}
		}
		return pairs;
	}

	public Vector<SenderReceiverPairs> getSRPairs() throws IOException{
		Vector<SenderReceiverPairs> pairs = new Vector<SenderReceiverPairs>();
		String line;
		Pair sender = null, receiver = null;
		while((line = in.readLine()) != null){
			if(LineReader.isReceive(line)){
				sender = LineReader.getSender(line);
				receiver = LineReader.getReceiver(line);
				SenderReceiverPairs pair = new SenderReceiverPairs(sender, receiver);
				if(!pairs.contains(pair))
					pairs.add(pair);
			}
		}
		return pairs;
	}

}