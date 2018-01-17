package com.theforum.api;

/**
 * @author David
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
 
@Path("/test")
public class TestTheForumApi {
 
	  @GET
	  @Produces("application/json")
	  public Response convertFtoC() throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
 
		jsonObject.append("message", "hello world!!");
		
		String result = "@The forum API: " + jsonObject;
		return Response.status(200).entity(result).build();
	  }
 
	  @Path("{un}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("un") String userName) throws JSONException {
 
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("message", "hello world!!"); 
		jsonObject.put("user", userName);
 
		return Response.status(200).entity(jsonObject).build();
	  }
}