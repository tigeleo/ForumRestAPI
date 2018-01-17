package com.theforum.api;

import java.net.HttpURLConnection;
import java.util.Date;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.theforum.api.utils.DateUtils;
import com.theforum.db.Rolls;
import com.theforum.db.User;
import com.theforum.dbm.ConnectionManager;


/**
 * @author David
 */
@Path("/comment")
public class CommentRestApi {

	@GET
	@Produces("application/json")
	public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.append("message", "Male and Female users add");

		String result = "@The forum API: " + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@Path("/add")
	@GET
	@Produces("application/json")
	public Response add(
			@QueryParam("name") final String name)
			throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		if (name == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("name parameter is mandatory").build());
		}
		

		return Response.status(200).entity(jsonObject.toString()).build();
	}


}