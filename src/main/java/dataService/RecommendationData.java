/**
 * 
 */
package dataService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author yanliang
 *
 */
public class RecommendationData extends MysqlConnector{

	public RecommendationData() {
		super("10.9.210.116", "recommendation", "price", "password");
	}
	
	public JSONArray getRec(String item) throws  JSONException, SQLException{
		Statement stmt = this.conn.createStatement();
		String sql = "Select * from " + item + " ORDER BY RAND() LIMIT 3;";
		JSONArray ret = new JSONArray();
		
		ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return ret;
		}
		while(rs.next()) {
			JSONObject jo = new JSONObject();
			jo.put("epid",rs.getInt("epid"));
			jo.put("image",rs.getString("image"));
			jo.put("price",rs.getInt("price"));
			jo.put("desc",rs.getString("desc"));
			ret.put(jo);
		}
		
		return ret;
	}

}
