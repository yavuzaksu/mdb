package com.monitise.mdb.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;



@Path("/project")
public class Project {
 
	@GET
	@Path("/worse")
	public Response getBottomTen() {
	   return Response.status(200).build();
 	}
 
}