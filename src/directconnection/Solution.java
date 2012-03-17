package directconnection;
/* Enter your code here. Read input from STDIN. Print output to STDOUT *//* Enter your code here. Read input from STDIN. Print output to STDOUT */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Solution {
   
    
    private class City implements Comparable<City> {
        int population;
        int coordinate;
        
        public City(int p, int c) {
            this.population = p;
            this.coordinate = c;
        }
        
        public int getPopulation() {
            return this.population;
        }
        
        public int getCoordinate() {
            return this.coordinate;
        }
        
        public int compareTo(City other) {
            if(this.population < other.getPopulation()) {
                return 1;
            } else if(this.population == other.getPopulation()) {
                return 0;
            } else {
                return -1;
            }
        }
    }
    
    private void processScenario(BufferedReader in) throws Exception {
        String n,p,c;
        int N = 0;
        List<City> cities = null;
        
        if((n = in.readLine()) != null) {
            N = Integer.parseInt(n.trim());
        }
        
        if((c = in.readLine()) != null && (p = in.readLine()) != null) {
            cities = parseValues(N, p, c);
        }
    
        
        System.out.println(solveProblem(cities)%1000000007);
    }

    private List<City> parseValues(int N, String p, String c) {
        String[] pop = p.split("\\s+");
        String[] coord = c.split("\\s+");
        List<City> cities = new ArrayList<City>();    
        
        for(int i=0;i<N;i++) {
            int population = Integer.parseInt(pop[i].trim());
            int coordinate = Integer.parseInt(coord[i].trim());
            cities.add(new City(population, coordinate));
        }
        
        return cities;
    }

    private long solveProblem(List<City> cities) {
        
        Collections.sort(cities);
        
        City other = null;
        long totalCableRequired = 0;
        
        long prevCalc = 0;
        
        while(cities.size() > 0) {
            City curCity = cities.remove(0);
            
            Iterator<City> it = cities.iterator();
            long totalSumForOneCity = 0;
            
            while(it.hasNext()) {
                other = it.next();
                totalSumForOneCity += Math.abs(curCity.getCoordinate() - other.getCoordinate());
            }
            
            totalCableRequired += totalSumForOneCity * curCity.getPopulation();           
        }
        
        return totalCableRequired;
   
    }

    public static void main(String[] arg) throws Exception {
        
    	long start = System.currentTimeMillis();
    	
        Solution solution = new Solution();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int noOfTests = 0;
        String s = null;
        
        if((s = in.readLine()) != null && s.length() != 0) {
            noOfTests = Integer.parseInt(s);
        }
        
        for(int i=0;i<noOfTests;i++) {
            solution.processScenario(in);
        }
        
        in.close();
        
        System.out.println("Execution time : " + (System.currentTimeMillis() - start));
        
    }
   
}