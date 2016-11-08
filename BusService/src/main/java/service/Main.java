package service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;
import javax.inject.Singleton;

/** 
 * Main class
 */

@Singleton
public class Main extends ResourceConfig{
	
	public static BusRoutes busroutes;
	// Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8088/api/";
    
	/**
	 * This method calls the Initiator class method "getRoutes" with
	 * the name of the routes file as an argument and returns 
	 * an object "busroutes" which contains the parsed file contents.
	 */
	
	public static void init(String filename){
		
		busroutes=Initiator.getRoutes(filename);
	}
	
    
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {

        final ResourceConfig rc = new ResourceConfig().packages("service");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method that starts and stops the server
     */
    public static void main(String[] args) throws IOException {
    	String filenm=args[0].replace("\\", "\\\\");
    	init(filenm);
        final HttpServer server = startServer();
        System.out.println(String.format("BusService app started!"));
    }
}
