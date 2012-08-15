package graphics;

import graph.Node;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sort.LineReader;
import sort.Pair;

public class NodeWindow implements Runnable {

	private AreaOfNode point;
	private List<List<Node>> sortedGraph;
	private Pair pointId;
	private String [] persistentPredicates, transientPredicates, transportPredicates;
	private File trace;
	
	public NodeWindow(AreaOfNode node,List<List<Node>> sortedGraph, String [] persistentPredicates, 
			String [] transienPredicates, String [] transportPredicates, File trace) {
		this.point= node;
		this.sortedGraph = sortedGraph;
		pointId = getOriginalTime();
		this.persistentPredicates = persistentPredicates;
		this.transientPredicates = transienPredicates;
		this.transportPredicates = transportPredicates;
		this.trace = trace;
	}
	
	private Pair getOriginalTime() {
		int level = point.node.pair.getTime() + 1, node = point.node.pair.getNode();
		List<Node> layer = sortedGraph.get(level);
		Iterator<Node> itr = layer.iterator();
		while(itr.hasNext()) {
			Node current = itr.next();
			if(current.pair.getNode() == node){
				return(new Pair(node,current.pair.getTime()));
			}
		}
		return new Pair(node, -2);
	}
		
	@Override
	public void run() {
		if(pointId.getTime() != -2)
			new Window();
	}

	
	@SuppressWarnings("serial")
	class Window extends JFrame implements ActionListener{
		
		JButton search = new JButton("Search");
		String [] types = {"persistent", "transient", "transport"};
		JComboBox typeOfTuple =  new JComboBox();
		JTextArea outputPredicates = new JTextArea(20,30);
		
		public Window() {
			
			super("Predicates for node (" + point.node.pair.getNode() + 
					"," + point.node.pair.getTime() + ")"+" global " + 
					"(" + pointId.getNode() + "," + (pointId.getTime())   + ") local");
			
			setSize(400,400);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel panel = new JPanel();
			JLabel typesLabel = new JLabel("Type of predicates: ");
			panel.add(typesLabel);
			for(int i = 0; i < types.length; i++) {
				typeOfTuple.addItem(types[i]);
			}
			
			search.addActionListener(this);
			FlowLayout flow = new FlowLayout();
			setLayout(flow);
			panel.add(typeOfTuple);
			panel.add(search);
			outputPredicates.setEditable(false);
			JScrollPane scroll = new JScrollPane(outputPredicates);
			add(panel);
			add(scroll);
			setVisible(true);
		}
		
		private boolean isPersistent(String predicate) {
			if(persistentPredicates != null){
				for(int i = 0; i < persistentPredicates.length; i++)
					if(persistentPredicates[i].equals(predicate))
						return true;
			}
			return false;
		}
		
		private boolean isTransient(String predicate) {
			if(transientPredicates != null){
				for(int i = 0; i < transientPredicates.length; i++)
					if(transientPredicates[i].equals(predicate))
						return true;
			}
			return false;
		}
		
		private boolean isTransport(String predicate) {
			if(transportPredicates != null){
				for(int i = 0; i < transportPredicates.length; i++)
					if(transportPredicates[i].equals(predicate))
						return true;
			}
			return false;
		}
		
		
		private String getPredicates(String type) throws Exception {
			BufferedReader in =  new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(trace))));
			String line = "",output = "";
			while((line = in.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line,"(");
				if(type == "persistent" && isPersistent(token.nextToken())){
					Pair tmp = LineReader.getReceiver(line);
					tmp.setTime(tmp.getTime()-1);
					if(pointId.equals(tmp)){
						output += line + "\n";
					}
				}
				else if(type == "transient" && isTransient(token.nextToken())){
					Pair tmp = LineReader.getReceiver(line);
					tmp.setTime(tmp.getTime()-1);
					if(pointId.equals(tmp)){
						output += line + "\n";
					}
				}
				else if (type == "transport" && isTransport(token.nextToken())){
					Pair tmp = LineReader.getReceiver(line);
					if(LineReader.isReceive(line)){
						tmp.setTime(tmp.getTime());
						if(pointId.equals(tmp)){
							output += line + "\n";
						}
					}
					else{
						tmp.setTime(tmp.getTime()-1);
						if(pointId.equals(tmp)){
							output += line + "\n";
						}
					}
				}
			}
			return output;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(typeOfTuple.getSelectedIndex() == 0) {	
					outputPredicates.setText(getPredicates("persistent"));
				} else if(typeOfTuple.getSelectedIndex() == 1) {
					outputPredicates.setText(getPredicates("transient"));
				}else {
					outputPredicates.setText(getPredicates("transport"));
				}
				repaint();
			}catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
	}
}
