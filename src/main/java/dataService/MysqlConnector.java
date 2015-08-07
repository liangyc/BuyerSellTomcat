package dataService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.*;



public class MysqlConnector {
	protected Connection conn;
	protected String dbName;
	private String user;
	private String pwd;
	private String host;
	private String port = "3306";

	public MysqlConnector(String host, String dbName, String user, String pwd){
		 
		try {
			    Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		
		 
		this.host = host;
		this.dbName = dbName;
		this.user = user;
		this.pwd = pwd;
		dbConnStart();
	}
	
	public void dbConnStart(){        
		 Properties connectionProps = new Properties();
	     connectionProps.put("user", user);
	     connectionProps.put("password", pwd);
	     try {
	    	 this.conn = DriverManager.getConnection("jdbc:" + "mysql" + "://" + host +
	                                              ":" + port + "/" + dbName,
	                                              connectionProps);
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }    
	}
	
	public JSONObject getPriceTrend(String item, int startDate) throws SQLException, JSONException{
		 Statement stmt = conn.createStatement();
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
		 
		 System.out.println(sql);
		 System.out.println(ret.toString());
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
