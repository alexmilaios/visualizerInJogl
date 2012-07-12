package graphics;

import graph.Connection;
import graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.vecmath.Vector3f;

import sort.SenderReceiverPairs;


public class Model {
	private int levels, numOfNodes;

	private List<List<Vector3f>> layers;
	private List<Connection> connections;
	private List<List<Node>> sortedGraph;
	private Vector<SenderReceiverPairs> messages;
	
	public Model(int levels, int numOfNodes,List<Connection> connections, 
			List<List<Node>> sortedGraph, Vector<SenderReceiverPairs> messages){
		this.levels = levels;
		this.numOfNodes = numOfNodes;
		layers = new ArrayList<List<Vector3f>>();
		this.connections = connections;
		this.sortedGraph = sortedGraph;
		this.messages = messages;
		computePoints();
	}
	
	private void computePoints () {
		for(int i = 0; i < levels; i++) {
			List<Vector3f> layer = new ArrayList<Vector3f>();
			for(int j = 0; j < numOfNodes; j++) {
				layer.add(new Vector3f((float) ( 3 * Math.cos((2*Math.PI/numOfNodes)*(j) + Math.PI/4 )), 
						(float) (levels-i-1 -(levels-1)/2),(float) (3 * Math.sin((2*Math.PI / numOfNodes)*(j) + Math.PI/4 ))));
			}
			if(i == 0) {
				layer.add(new Vector3f(0.0f, (float) (levels-i-1 -(levels-1)/2), 0.0f));
			}
			layers.add(layer);
		}
	}
	
	public void drawPoints(GLU glu, GL2 gl) {
	
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		GLUquadric quadric = glu.gluNewQuadric();
		glu.gluQuadricNormals(quadric, GL.GL_TRUE);
		gl.glColor3f(1.0f,1.0f,1.0f);
		
		for(List<Vector3f> layer : layers) {
			for(Vector3f point : layer) {

				gl.glLoadIdentity();
				gl.glTranslatef(point.x, point.y, point.z);
				
				glu.gluSphere(quadric, 0.07, 8, 8);
			}
		}
	}
	
	public void drawGraph(GL2 gl) {
		ListIterator<Connection> litr = connections.listIterator();
		
		while(litr.hasNext()) {
			Connection connection = litr.next();
			drawLine(gl,layers.get(0).get(connection.from), layers.get(0).get(connection.to));
		}
	}
	
	public void drawTimeLines(GL2 gl) {
		for(int i = 0; i < numOfNodes; i++) {
			drawLine(gl,layers.get(0).get(i), layers.get(levels-1).get(i));
		}
	}
	
	public void drawMessages(GL2 gl) {
		for(int i = 1; i < sortedGraph.size(); i++) {
			List<Node> level = sortedGraph.get(i);
			for(Node node : level) {
				Vector3f end = layers.get(i).get(node.pair.getNode()-1);
				Node sender = getSender(node);
				int senderId = (sender.pair.getNode() == 0) ? numOfNodes : sender.pair.getNode()-1;
				Vector3f start = layers.get(getSenderLevel(sender)).get(senderId);
				drawLine(gl, start, end);
			}
		}
	}
	
	private int getSenderLevel(Node sender) {
		for(int i = 0; i < sortedGraph.size(); i++){
			for(Node n : sortedGraph.get(i)){
				if(n.toString().equals(sender.toString())){
					return i;
				}
			}
		}
		return -1;
	}
	
	private Node getSender(Node receiver){
		for(int i = 0; i < messages.size(); i++) {
			SenderReceiverPairs pair = messages.elementAt(i);
			if(pair.getReceiver().toString().equals(receiver.toString())) {
				return pair.getSender();
			}
		}
		return null;
	}
	
	private void drawLine(GL2 gl, Vector3f start, Vector3f end) {
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glLineWidth(1.0f);
		gl.glDisable(GL2.GL_LINE_STIPPLE);
		gl.glEnable(GL.GL_LINE_SMOOTH);
		
		gl.glBegin(GL.GL_LINES);
			gl.glVertex3f(start.x, start.y, start.z);
			gl.glVertex3f(end.x, end.y, end.z);
		gl.glEnd();
	}
}
