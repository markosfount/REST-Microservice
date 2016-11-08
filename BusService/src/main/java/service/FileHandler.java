package service;


import java.io.BufferedReader;
import java.io.Reader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class opens the file provided and parses its
 * contents, line by line, in an ArrayList of TreeSets,
 * while at the same time keeping the id of each bus route
 * (first number of each line besides the first) in a 
 * different ArrayList
 */

public class FileHandler {
	
	private static BufferedReader buff=null;
	public static ArrayList<TreeSet<Integer>> all_routes;
	public static ArrayList<Integer> idlist;
	
	public static Reader openFile(String filename) throws IOException{ 
			return new FileReader(new File(filename));
	}
	
	public static void parseFileContents(String filename) throws IOException{
		try{
			buff = new BufferedReader(openFile(filename));
			String line;
			int i=0;
		    
		    while ((line=buff.readLine())!=null){
		    	if (i==0)
		    		parseFirstLine(line);
		    	else
		    		addLine(parseLine(line));
		    	i++;	
		    }
		}
		catch (FileNotFoundException e){
		    System.out.println("File not found");
		    } catch (IOException e) {
		    	e.printStackTrace();
		     } 
		finally{
			closeFile(buff);
		}
	}
	
	public static void closeFile(Reader r) throws IOException{
		try{
			r.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
			
	public static void parseFirstLine(String line){
		Matcher nummatch=matchPattern(line);
		while (nummatch.find()){
			int num_routes=Integer.parseInt(nummatch.group());
			createLists(num_routes);
			break;	//ignore other integers beyond the first in first line
		}
	}
	
	public static TreeSet<Integer> parseLine(String line){
		TreeSet<Integer> tset=new TreeSet<Integer>();
		int i=0;
		Matcher nummatch=matchPattern(line);
		while (nummatch.find()){
			if (i==0)
				addToIdList(idlist, Integer.parseInt(nummatch.group()));
			else
				tset.add(Integer.parseInt(nummatch.group()));
			i++;
		}
		return tset;
	}
	
	public static Matcher matchPattern(String line){
		Pattern numpatt=Pattern.compile("\\d+");
		return(numpatt.matcher(line));
		
	}
	
	public static void createLists(int numr){
		all_routes=new ArrayList<TreeSet<Integer>>(numr);
		idlist=new ArrayList<Integer>(numr);
	}
	
	public static void addLine(TreeSet<Integer> tset){
		all_routes.add(tset);
	}
	
	public static void addToIdList(ArrayList<Integer> idl, int id){
		idl.add(id);
	}
	
	public static ArrayList<TreeSet<Integer>> getRouteList(){
		return all_routes;
	}
	
	public static ArrayList<Integer> getIdList(){
		return idlist;
	}
}
