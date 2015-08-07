/**
 * 
 */
package com.mkyong.rest;

import java.sql.SQLException;

import org.json.JSONException;

import dataService.MysqlConnector;

/**
 * @author yanliang
 *
 */
public class TestMain {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, JSONException {
		// TODO Auto-generated method stub
		String dbName = "priceTrend";
		String user = "price";
		String pwd = "password";
		String host = "10.9.210.116";
	    MysqlConnector mysql = new MysqlConnector(host, dbName, user, pwd);
	    System.out.println( mysql.getPriceTrend("iphone5s16g", 201304).toString() );

	}

}
