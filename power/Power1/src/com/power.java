package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.xdevapi.Table;

public class Power {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrodb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readPower()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table class='table' border='1'><thead class='table-dark'>"
					+ "<th>Customer Name</th>"
					+ "<th>Power Account No</th>"
					+ "<th>pS Unit</th>"
					+ "<th>Power Date</th>"
					+ "<th>Power Amout</th>"	
					+ "<th>Update</th>"
					+ "<th>Remove</th></thead>";
	 
			String query = "select * from power"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String cusName = Integer.toString(rs.getInt("cusName")); 
				String pAccNo = rs.getString("pAccNo");
				String psUnit = rs.getString("psUnit");
				String pDate = rs.getString("pDate");
				String pAmout = rs.getString("pAmout");
				

				// Add into the HTML table 
				output += "<tr><td><input id='hidPowerIDUpdate' "
						+ "name='hidPowerIDUpdate' "
						+ "type='hidden' value='" + powerId + "'>" 
						+ cusId + "</td>"; 
				output += "<td>" + cusName + "</td>";
				output += "<td>" + pAccNo + "</td>";
				output += "<td>" + psUnit + "</td>";
				output += "<td>" + pDate + "</td>";
				output += "<td>" + pAmout + "</td>";

				// buttons     
//				output += "<td><input name='btnUpdate' type='button'"
//						+ "value='Update' class='btnUpdate btn btn-secondary'></td>"
//						+ "<td><form method='post' action='Power.jsp'>"
//						+ "<input name='btnRemove' type='submit'"
//						+ "value='Remove' class='btnRemove btn btn-danger'>"
//						+ "<input name='hidPowerIDDelete' type='hidden'"
//						+ "value='" + powerId + "'>" + "</form></td></tr>"; 
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-powerid='" + powerId + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-powerid='" + powerId + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Power.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//Add details about the power
	public String insertPower (String cusName, String pAccNo, String psUnit, String pDate, String pAmout )  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting.";
			} 
	 
			// create a prepared statement 
			String query = " insert into power (cusName , pAccNo , psUnit , pDate , pAmout )"+ " values (?, ?, ?, ?, ?, ?, ?)"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, cusName);
			 preparedStmt.setString(3, pAccNo);
			 preparedStmt.setString(4, psUnit);
			 preparedStmt.setString(5, pDate);
			 preparedStmt.setString(6, pAmout);
			
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newPower = readPower(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newPower + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Power.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updatePower(String payId, String cusName, String pAccNo, String psUnit, String pDate, String pAmout)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for updating.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE power SET cusName=?,pAccNo=?,psUnit=?,pDate=?,pAmout=? WHERE powerId=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, cusName);
			preparedStmt.setString(2, pAccNo);
			preparedStmt.setString(3, psUnit);
			preparedStmt.setString(4, pDate);
			preparedStmt.setString(5, pAmout);
			preparedStmt.setInt(6, Integer.parseInt(powerId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPower = readPower();    
			output = "{\"status\":\"success\", \"data\": \"" + newPower + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Power.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deletePower(String payId)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "delete from Power where powerId=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(PowerId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPower = readPower();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newPower + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the power.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
