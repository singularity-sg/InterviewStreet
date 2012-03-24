package kingdomconnectivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {

	InputStream is;
	String[][] input = new String[2][];

	String[] output;

	Integer N;
	Integer M;
	Integer[][] paths;
	Integer[] nodeCount;

	Integer count;
	
	private static final Integer INFINITE_LOOP = -999;
	
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

		this.count = oneMoreIdea(paths, 1, false);
		
		if (!count.equals(INFINITE_LOOP)) {
			output = new String[] { String.valueOf(count % 1000000000) };
		} else {
			output = new String[] { "INFINITE PATHS" };
		}

	}
	
	private int oneMoreIdea(Integer[][] graph, Integer node, boolean looped) {
		
		Integer[] adjacentNodes = graph[node-1];
		int count = 0;
		boolean loop = looped; 
		
		if(node.intValue() == this.N.intValue()) {
			if(loop) {
				return INFINITE_LOOP; //INFINITE LOOP
			} else {
				count = 1;
			}
		} else {
			if(adjacentNodes != null) {
				
				if(this.nodeCount[node-1] == -1) {
					
					Map<Integer, Integer> adjacentNodesMap = new HashMap<Integer, Integer>();

					for(int i=0;i<adjacentNodes.length;i++) {
						Integer curNode = adjacentNodes[i];
						Integer curCount = adjacentNodesMap.get(curNode);
						if(curCount != null) {
							adjacentNodesMap.put(curNode, ++curCount);
						} else {
							adjacentNodesMap.put(curNode, 1);
						}
					}
					
					Set<Entry<Integer,Integer>> set = adjacentNodesMap.entrySet();
					Iterator<Entry<Integer,Integer>> it = set.iterator();
					
					while(it.hasNext()) {
						
						Entry<Integer, Integer> cur = it.next();
						
						if(cur.getKey() < node) {
							loop = true;
							continue;
						}
						
						int curCount = oneMoreIdea(graph, cur.getKey(), loop);
						
						if(curCount != INFINITE_LOOP) {
							count += curCount * cur.getValue();
						} else {
							return curCount;
						}
						
					}
					
					this.nodeCount[node-1] = count;
				} else {
					count = this.nodeCount[node-1];
				}
			}
		}
		
		return count;
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
		this.paths = new Integer[N][];
		this.nodeCount = new Integer[N];
		
		Arrays.fill(this.nodeCount, -1);
		Arrays.sort(this.input[1]);

		List<Integer> currentCityPaths = new ArrayList<Integer>();
		int prevCity = -1;

		for (int i = 0; i < M; i++) {

			String[] line = this.input[1][i].split(" ");
			int x = Integer.valueOf(line[0]);
			int y = Integer.valueOf(line[1]);

			if (x == prevCity || prevCity == -1) {
				currentCityPaths.add(y);
			} else {
				this.paths[prevCity - 1] = currentCityPaths
						.toArray(new Integer[] {});
				currentCityPaths = new ArrayList<Integer>();
				currentCityPaths.add(y);
			}

			prevCity = x;
		}

		this.paths[prevCity - 1] = currentCityPaths.toArray(new Integer[] {});

	}

	private void readInput() throws Exception {
		List<String> lines = new ArrayList<String>();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				this.is));
		if ((line = reader.readLine()) != null) {
			this.input[0] = new String[] { line };
		}
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}

		reader.close();
		this.input[1] = lines.toArray(new String[] {});
	}

	public String[] getOutput() {
		return output;
	}

}
