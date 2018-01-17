package com.theforum.api;

import java.net.HttpURLConnection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * @author David
 */

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONException;
import org.json.JSONObject;

import com.theforum.api.utils.DateUtils;
import com.theforum.db.Auth;
import com.theforum.db.Rolls;
import com.theforum.db.User;
import com.theforum.dbm.ConnectionManager;


@Path("/login")
public class LoginRestApi {

	@POST
	@Produces("application/json")
	public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.append("message", "Male and Female users add");

		String result = "@The forum API: " + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	
	@Path("/authenticate")
	@GET
	@Produces("application/json")
	public Response authenticate(
			@Context HttpServletRequest requestContext,
			@Context SecurityContext context,
			@QueryParam("login") final String login, 
			@QueryParam("password") final String password)
			throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userip", requestContext.getRemoteAddr());
		jsonObject.put("AuthenticationScheme", context.getAuthenticationScheme());
		
		boolean loginExsists=ConnectionManager.factory().checkLoginExists(login);
		if(loginExsists) {

		
			try {
				Auth au = ConnectionManager.factory().authenticate(login, password);
				if(au!=null) { 
					jsonObject.put("message", "Authentication successfully");
					jsonObject.put("authenticationid", au.getId());
					jsonObject.put("email", au.getEmail());
					jsonObject.put("login", au.getLogin());
				}else {
					jsonObject.put("status", "failed");				
					jsonObject.put("message", "Invalid password.");
		
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				jsonObject.put("status", "failed");
				jsonObject.put("message", e.getMessage());
	
			}
		
		}else {
			jsonObject.put("status", "failed");				
			jsonObject.put("message", "Login not exists.");
			
		}

		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	
	
	@Path("/exit")
	@GET
	@Produces("application/json")
	public Response add(
			@QueryParam("login") final String login)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();
		

		return Response.status(200).entity(jsonObject.toString()).build();
	}

}