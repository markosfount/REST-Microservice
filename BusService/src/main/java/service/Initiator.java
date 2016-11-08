package service;

import java.io.IOException;


public class Initiator {
	
	/**
	 * This class is responsible for creating a BusRoutes
	 * instance and calling the FileHandler class in order
	 * to parse the contents of the given file. Upon doing
	 * so, it returns an instance of the BusRoutes class
	 */
		public static BusRoutes getRoutes(String filename){
			System.out.println(filename);
			BusRoutes busroutes = new BusRoutes();
			try{
				FileHandler.parseFileContents(filename);
				busroutes.createTree(FileHandler.getRouteList());
				busroutes.createIdList(FileHandler.getIdList());
			} catch (IOException e){
				e.printStackTrace();
			}
			return busroutes;
		}
}