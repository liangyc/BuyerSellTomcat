package com.mkyong.rest;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.*;

import dataService.ImageService;
import dataService.PriceData;



@Path("/web")
public class BigServer {
		
    private PriceData pd = new PriceData();
	private ImageService imageS = new ImageService();
	private RecommendationService recS = new RecommendationService();
	
	@GET
	@Path("")
	public Response getMsg(@QueryParam("item") String item) throws JSONException, SQLException {
		
		
		JSONObject ret = new JSONObject();
		JSONObject purchased = new JSONObject();
		purchased.put("image", imageS.getItemImage(item));
		
		JSONObject trend = this.pd.getPriceTrend(item, 0);
		purchased.put("trend", trend.get("trend"));
		JSONObject sellprice = this.pd.getSuggestSellingPrice(item);
		purchased.put("sellprice",  sellprice.get("suggestSellPrice"));
		
		ret.put("purchased", purchased);
		ret.put("recommendation", recS.getRecommand(item) );
		
		return Response.status(200).entity(ret.toString()).build();
 
	}
}
