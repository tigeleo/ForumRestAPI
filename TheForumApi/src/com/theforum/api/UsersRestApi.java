package com.theforum.api;

import java.net.HttpURLConnection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.theforum.api.utils.DateUtils;
import com.theforum.db.Auth;
import com.theforum.db.Rolls;
import com.theforum.db.User;
import com.theforum.dbm.ConnectionManager;
import com.theforum.json.UserWrapper;


@Path("/users")
public class UsersRestApi {

	@GET
	@Produces("application/json")
	public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.append("message", "Male and Female users add");

		String result = "@The forum API: " + jsonObject;
		return Response.status(200).entity(result).build();
	}

	
	
	
	@Path("/regisration")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response create(UserWrapper uw)
			throws JSONException {
		
		if (uw.getUsername() == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("name parameter is mandatory").build());
		}
		if (uw.getEmail() == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("email parameter is mandatory").build());
		}
		if (uw.getPassword() == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("email parameter is mandatory").build());
		}
		
		boolean loginExsists=ConnectionManager.factory().checkLoginExists(uw.getUsername());
		if(loginExsists) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("login parameter already ussed").build());
			
		}
		

		Rolls rolle = Rolls.USER;

		
		JSONObject jsonObject = new JSONObject();
		
		Auth au = new Auth();
		au.setLogin(uw.getUsername());
		au.setEmail(uw.getEmail());
		au.setRolle(rolle);
		au.setPassword(uw.getPassword());

		Gson gson = new Gson();
		
		try {
			ConnectionManager.factory().registration(au);
			jsonObject.put("status", "success");
			jsonObject.put("message", "Successfully added");
			jsonObject.put("auth", gson.toJson(au));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			jsonObject.put("status", "failed");
			jsonObject.put("message", e.getMessage());
			jsonObject.put("auth", gson.toJson(au));
		}
		

		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	
	
	
	
	
	@Path("/regisration")
	@GET
	@Produces("application/json")
	public Response add(
			 
			@QueryParam("email") final String email, 
			@QueryParam("login") final String login, 
			@QueryParam("password") final String password)
			throws JSONException {
		
		if (login == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("name parameter is mandatory").build());
		}
		if (email == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("email parameter is mandatory").build());
		}
		if (password == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("email parameter is mandatory").build());
		}
		
		boolean loginExsists=ConnectionManager.factory().checkLoginExists(login);
		if(loginExsists) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("login parameter already ussed").build());
			
		}
		

		Rolls rolle = Rolls.USER;

		
		JSONObject jsonObject = new JSONObject();
		
		Auth au = new Auth();
		au.setLogin(login);
		au.setEmail(email);
		au.setRolle(rolle);
		au.setPassword(password);

		Gson gson = new Gson();
		
		try {
			ConnectionManager.factory().registration(au);
			jsonObject.put("status", "success");
			jsonObject.put("message", "Successfully added");
			jsonObject.put("auth", gson.toJson(au));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			jsonObject.put("status", "failed");
			jsonObject.put("message", e.getMessage());
			jsonObject.put("auth", gson.toJson(au));
		}
		

		return Response.status(200).entity(jsonObject.toString()).build();
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
		
		if (login == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("name parameter is mandatory").build());
		}
		if (password == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("email parameter is mandatory").build());
		}
		
		JSONObject jsonObject = new JSONObject();
		
		boolean loginExsists=ConnectionManager.factory().checkLoginExists(login);
		if(loginExsists) {

		
			try {
				Auth au = ConnectionManager.factory().authenticate(login, password);
				if(au!=null) {
					jsonObject.put("status", "success");
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


	@Path("{un}")
	@GET
	@Produces("application/json")
	public Response convertFtoCfromInput(@PathParam("un") String userName,@QueryParam("firstname") final String firstname, 
			@QueryParam("lastname") final String lastname, 
			@QueryParam("birth") final String p_birth,
			@QueryParam("sex") final String p_sex) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		
		Date birth=DateUtils.parseDate(p_birth);
		if(birth==null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("birthday parameter is mandatory date mm/dd/yy").build());
			
		}
		char sex=p_sex.charAt(0);
		if(sex !='M' && sex!='W' && sex != 'm' &&  sex != 'w') {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("sex parameter is mandatory character (m,w)").build());
		}
		jsonObject.put("message", "hello world!!");
		jsonObject.put("user", userName);

		return Response.status(200).entity(jsonObject).build();
	}
}