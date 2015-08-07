/**
 * 
 */
package com.mkyong.rest;

import java.sql.SQLException;

import org.json.*;

import dataService.MysqlConnector;
import dataService.RecommendationData;

/**
 * @author yanliang
 *
 */
public class RecommendationService {
    
	RecommendationData rd = new RecommendationData();
    
    public JSONArray getRecommand(String item) throws SQLException, JSONException{
    	JSONArray ret = rd.getRec(item);
    	return ret;
    }
	
}
