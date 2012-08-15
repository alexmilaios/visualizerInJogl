package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import graph.Node;
import graph.Topology;
import graphics.Universe;
import javax.swing.JFrame;

import sort.Layers;
import sort.SenderReceiverPairs;

public class Visualization {
	public static void main(String [] args) {
		
		File trace = new File("trace1.txt");
		//Layers layers = new Layers(new File("trace-thmr.txt"));
		Layers layers = new Layers(trace);
		//Layers layers = new Layers(new File("trace_lsp.txt"));
		
		List<List<Node>> levels = layers.layers; 
		Vector<SenderReceiverPairs> messages = layers.messages; 
				
	  
	  	//Iterator<List<Node>> itr1 = levels.iterator();
		
	/*	Iterator<SenderReceiverPairs> itr3 = messages.iterator();
		while(itr3.hasNext()){
			System.out.println(itr3.next());
		}*/
		
		/*while(itr1.hasNext()) {
			Iterator<Node> itr2 = itr1.next().iterator();
			while(itr2.hasNext()) {
				Node node = itr2.next();
				System.out.print(node + " : ");
			}
			System.out.println();
		}*/
		
		String[] persistentPredicates = {"stored_incoming_route","stored_outgoing_route","stored_selected_route"};
		String[] transportPredicates = {"receive_new_route_message","receive_withdraw_route_message",
										"send_new_route_message", "send_withdraw_route_message"};
		
		
		Universe frame = new Universe(levels,messages,(new Topology(messages)).getTopology(),
				5,persistentPredicates,null,transportPredicates,trace);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
