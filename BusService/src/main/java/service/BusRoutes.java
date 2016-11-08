package service;


import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This is the class that holds all the routes information
 * as an ArrayList of TreeSets, each representing an individual
 * bus route, as well as an ArrayList holding the ID's of
 * each route.
 */

public class BusRoutes {
	
	public ArrayList<TreeSet<Integer>> routes;
	public ArrayList<Integer> idList;
		
	public void createTree(ArrayList<TreeSet<Integer>> tset){
			this.routes=tset;
	}
	
	public void createIdList(ArrayList<Integer> idlist){
		this.idList=idlist;
	}
	
	/**
	 * This method searches the data and returns true
	 * if both the parameters passed, i.e. the bus stations
	 * given by the client are both present in any route
	 */
	
	public boolean searchStations(int idstart, int idend){
		ArrayList<Integer> intlist=new ArrayList<Integer>();
		intlist.add(idstart);
		intlist.add(idend);

		for (TreeSet<Integer> t: this.routes){
			if (t.containsAll(intlist)){
				return true;
			}
		}
		return false;
	}
}
