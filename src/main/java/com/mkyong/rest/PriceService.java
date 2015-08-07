package com.mkyong.rest;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.*;

import dataService.PriceData;


@Path("/price")
public class PriceService {
	

    private PriceData mysqlc = new PriceData();
	
	@GET
	@Path("/trend")
	public Response getTrend(
			@QueryParam("item") String item,
			@QueryParam("from") String startDate) {
 
		int startDateInt;
		startDateInt = Integer.parseInt(startDate);
		
		String output = "";
		try {
			output = this.mysqlc.getPriceTrend(item, startDateInt).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(404).entity("ERROR 404/n " + e.toString()).build();
		}
		
		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("/suggestsell")
	public Response getSuggestSellingPrice(
			@QueryParam("item") String item){
		String output = "";
		try {
			output = this.mysqlc.getSuggestSellingPrice(item).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(404).entity("ERROR 404/n " + e.toString()).build();
		}
		
		return Response.status(200).entity(output).build();
	}
 
}