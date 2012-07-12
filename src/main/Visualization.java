package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import graph.Connection;
import graph.Node;
import graphics.Universe;
import javax.swing.JFrame;

import sort.Layers;
import sort.SenderReceiverPairs;

public class Visualization {
	public static void main(String [] args) {
		
		Layers layers = new Layers(new File("trace1.txt"));
		
		List<List<Node>> levels = layers.layers; 
		Vector<SenderReceiverPairs> messages = layers.messages; 
		
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(0, 1));
		connections.add(new Connection(1, 2));
		connections.add(new Connection(2, 3));
		connections.add(new Connection(3, 0));
		
		Universe frame = new Universe(levels,messages,connections);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
