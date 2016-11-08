package service;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Simple class to parse the response into
 * a JSON object in order to return it to 
 * the client.
 */

public class Jason {
	public static String create(int depid, int arrid, boolean found) throws JSONException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("dep_sid", depid); 
		jsonObject.put("arr_sid", arrid);
		jsonObject.put("direct_bus_route", found);

		return jsonObject.toString();
	}
}
