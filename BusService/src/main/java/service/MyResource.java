package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.inject.*;
import org.json.*;

/**
 * Our resource class which handles the GET request and
 * returns a JSON object
 */

@Singleton
@Path("/direct")

public class MyResource {
	
	BusRoutes busroutes=Main.busroutes;
	
	@GET
	@Produces("application/json")
	public Response checkStations(@QueryParam("dep_sid") int dep_sid,  @QueryParam("arr_sid") int arr_sid) 
			throws JSONException {

		boolean found=busroutes.searchStations(dep_sid, arr_sid);
		String result = Jason.create(dep_sid,arr_sid,found);
		return Response.status(200).entity(result).build();
	}
}
