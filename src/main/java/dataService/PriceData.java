/**
 * 
 */
package dataService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author yanliang
 *
 */
public class PriceData extends MysqlConnector{

	public PriceData() {
		super("10.9.210.116", "priceTrend", "price", "password");
	}
	
	public JSONObject getPriceTrend(String item, int startDate) throws SQLException, JSONException{
		 Statement stmt = this.conn.createStatement();
		 String sql = "Select month,avg_price from " + item + " where month>=" + startDate + " order by month;";
		 
		 ResultSet rs = stmt.executeQuery(sql);
		 
		 JSONObject ret = new JSONObject();
		 ret.put("item", item);
		 ret.put("from", startDate);
		 
		 ArrayList<Integer> priceTrend = new ArrayList<Integer>();
		 while(rs.next()) {
			 priceTrend.add(rs.getInt("avg_price"));
		 }
		 ret.put("trend", priceTrend);
		 
		 return ret;
	}

	public JSONObject getSuggestSellingPrice(String item) throws SQLException, JSONException {
		Statement stmt = conn.createStatement();
		String sql = "Select sell_price from suggest_sell_price where item_name=\"" + item + "\";";
		ResultSet rs = stmt.executeQuery(sql);
		JSONObject ret = new JSONObject();
		ret.put("item", item);
		
		if (rs.next()) {
			ret.put("suggestSellPrice", rs.getInt("sell_price") );
		}else{
			throw new SQLException("no price");
		}
			
		return ret;
	}
	


}
