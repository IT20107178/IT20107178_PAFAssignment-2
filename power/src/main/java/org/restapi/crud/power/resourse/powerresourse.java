package org.restapi.crud.power.resourse;

import java.sql.SQLException;
import java.util.ArrayList;

import org.restapi.crud.power.model.powermodel;
import org.restapi.crud.power.service.powerservice;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

@Path("/power")
public class powerresourse {
	
	powerservice service = new powerservice();
	
	//create powerUnit details
		@Path("/")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public powermodel addPower (powermodel power) {
			return service.insertPower(power);
		}
		
	// reading user details
	@Path("/")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<powermodel> getPower() throws SQLException {
		
		return service.getPower();
	}
	
	// searching  power details
	@Path("/{powerId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<powermodel> getPowermodels (@PathParam("powerId") int powerId) throws SQLException {
		
		return service.getPowerById(powerId);
	}
	
	//update power details 
	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public powermodel updatePower(powermodel power) {
		return service.updatePower(power);
	}
	
	//delete user details
	@Path("/{powerId}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public int deletePower (@PathParam("powerId") int powerId) {
		return service.deletePower(powerId);
	}
	
}
