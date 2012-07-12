package sort;

import java.util.StringTokenizer;

public class LineReader {
	
	public static boolean isReceive(String line) {
		StringTokenizer tokens = new StringTokenizer(line,"_");
		if(tokens.nextToken().equalsIgnoreCase("receive")){
			return true;
		}
		return false;
	}
	
	private static String getTokenAt(StringTokenizer tokens,int index) {
		for(int i = 0; i < index; i++){
			tokens.nextToken();
		}
		return tokens.nextToken();
	}
	
	private static int length(String input){
		int count = 0;
		StringTokenizer tmp = new StringTokenizer(input,",");
		while(tmp.hasMoreElements()){
			count++;
			tmp.nextToken();
		}
		return count;
	}
	
	private static String [] toArray(StringTokenizer tokens, int size){
		String array [] = new String[size];
		for(int i = 0; i < size; i++){
			array[i] = tokens.nextToken();	
		}
		return array;
	}
	
	public static Pair getSender(String line) {
		StringTokenizer tokens = new StringTokenizer(line,"(");
		tokens.nextToken();
		String secondPart = getTokenAt(tokens, 0);
		secondPart = secondPart.substring(0,secondPart.length()-1);
		tokens = new StringTokenizer(secondPart, ",");
		String array [] = toArray(tokens,length(secondPart)); 
		return new Pair(Integer.parseInt(array[length(secondPart)-3]), Integer.parseInt(array[length(secondPart)-2])-1);
	}
	
	public static Pair getReceiver(String line) {
		StringTokenizer tokens = new StringTokenizer(line,"(");
		tokens.nextToken();
		String secondPart = getTokenAt(tokens, 0);
		secondPart = secondPart.substring(0,secondPart.length()-1);
		tokens = new StringTokenizer(secondPart, ",");
		String array [] = toArray(tokens,length(secondPart)); 
		return new  Pair(Integer.parseInt(array[0]), Integer.parseInt(array[length(secondPart)-1]));
	}
	
}