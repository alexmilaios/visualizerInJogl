package sort;

public class Pair {

	private int node;
	private int time;
	
	public Pair(int node, int time){
		this.node = node;
		this.time = time;
	}
	
	public int compaire(Pair pair){
		return -1;
	}
	
	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public String present() {
		return "pair(" + node + "," + time+")";
	}
	
	public boolean equals(Object obj) {
		Pair tmp = (Pair) obj;
		return present().equals(tmp.present());
	}
}