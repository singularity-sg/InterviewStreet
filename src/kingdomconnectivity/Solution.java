package kingdomconnectivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solution {

	InputStream is;
	String[][] input = new String[2][];
	String[] output;
	
	int N;
	int M;
	int[][] paths;
	int count;

	public static void main(String[] args) throws Exception {
		Solution kc = new Solution(System.in);
		kc.go();
		String[] output = kc.getOutput();
		
		for (String string : output) {			
			System.out.println(string);
		}
	}
	
	public Solution(InputStream in) {
		this.is = in;
		count = 0;
	}

	private void solve() {
		
		this.count = 0;
		
		LinkedList<Integer> visited = new LinkedList<Integer>();
		visited.add(1);
		
		breadthFirst(paths, visited, false);
		
		if(count > -1) {
			output = new String[] {String.valueOf(count)};
		} else {
			output = new String[] {"INFINITE PATHS"};
		}
	
	}
	
	private void breadthFirst(int[][] graph, LinkedList<Integer> visited, boolean repeatedBefore) {
		int lastVisited = 1;
		boolean repeat = repeatedBefore;
		
		if(visited.size() > 0) {
			lastVisited = visited.getLast();
		}
		
		int[] adjacentNodes = graph[lastVisited-1];
		
		if(adjacentNodes != null) {
		
			for(int i=0;i<adjacentNodes.length;i++) {
				if(!containsNode(adjacentNodes[i], visited)) {
					visited.add(adjacentNodes[i]);
					if(adjacentNodes[i] == this.N) {
						this.count++;
						if(repeat) {
							count = -1;
							printVisited(visited, repeat);
							return;
						}
						printVisited(visited, repeat);
					} else {
						breadthFirst(graph, visited, repeat);
					}
					visited.removeLast();
				} else {
					repeat = true;
				}
			}
		}
    }
	
	private void printVisited(List<Integer> visited, boolean repeat) {
		Iterator<Integer> it = visited.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.print(", Repeat="+repeat);
		System.out.println("\n");
	}

	private boolean containsNode(int node, List<Integer> visited) {
		
		for (Integer visitedNode : visited) {
			if(node == visitedNode.intValue()) {
				return true;
			}
		}
		
		return false;
	}

	public void go() throws Exception {
		readInput();
		parseInput();
		solve();
	}

	private void parseInput() {
		String[] lineOne = this.input[0][0].split(" ");
		this.N = Integer.valueOf(lineOne[0]);
		this.M = Integer.valueOf(lineOne[1]);
		this.paths = new int[N][];
			
		Arrays.sort(this.input[1]);
		
		List<Integer> currentCityPaths = new ArrayList<Integer>();
		int prevCity = -1;
		
		for(int i=0;i<M;i++) {
			
			String[] line = this.input[1][i].split(" ");
			int x = Integer.valueOf(line[0]);
			int y = Integer.valueOf(line[1]);
			
			if(x == prevCity || prevCity == -1) {
				currentCityPaths.add(y);
			} else {
				this.paths[prevCity-1] = convertIntegerToIntArray(currentCityPaths);
				currentCityPaths = new ArrayList<Integer>();
				currentCityPaths.add(y);
			}
			
			prevCity = x;
		}
		
		this.paths[prevCity-1] = convertIntegerToIntArray(currentCityPaths); 
		
	}

	private int[] convertIntegerToIntArray(List<Integer> currentCityPaths) {
		
		int[] arr = new int[currentCityPaths.size()];
		
		Iterator<Integer> it = currentCityPaths.iterator();
		int i = 0;
		while(it.hasNext()) {
			arr[i] = it.next();
			i++;
		}
		
		return arr;
	}

	private void readInput() throws Exception {
		List<String> lines = new ArrayList<String>();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.is));
		if((line = reader.readLine()) != null) {
			this.input[0] = new String[] { line };
		}
		while((line = reader.readLine()) != null) {
			lines.add(line);
		}
		
		reader.close();
		this.input[1] = lines.toArray(new String[] {});
	}

	public String[] getOutput() {
		return output;
	}
	
}
