package com.PAF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {
	
	public Connection connect() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/store","root","");
			
			//For testing
			System.out.print("Successfully connected");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
				
		return con;
	}
	public String insertItem(String code, String name, String price, String desc) {
		
		String output = "";
		
		Connection con = connect();
		
		if (con == null)
		{
			
			return "Error while connecting to the database";
		}
		
				// create a prepared statement
				String query = " insert into items (itemID,itemCode,itemName,itemPrice,itemDesc)"
				 + " values (?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt;
				
				try {
					
					preparedStmt = con.prepareStatement(query);
				
					//binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, code);
					preparedStmt.setString(3, name);
					preparedStmt.setDouble(4, Double.parseDouble(price));
					preparedStmt.setString(5, desc);
					
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				
				} catch (SQLException e) {
					
					output = "Error while inserting";
					e.printStackTrace();
				}
				
				
		return output;
	}
	public String readItems()
	{
		String output = "";
		
		try {
			
			Connection con = connect();
			if(con == null)
			{
				
				return "Error while connecting to database for reading";
			}
			else {
				
				String query = "select * from items ";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				output += "<div class='container'><table class=\"table\">";
				output += "<tr><th>Item Code</th><th>Item Name</th><th>Item Price</th><th>Item Description</th><th>Update</th><th>Delete</th></tr>";
				
				while (rs.next())
				{
					String itemID = Integer.toString(rs.getInt("itemID"));
					String itemCode = rs.getString("itemCode");
					String itemPrice = Double.toString(rs.getDouble("itemPrice"));
					String itemDesc = rs.getString("itemDesc");
					String itemName = rs.getString("itemName");
		
					
					// Add a row into the html table
					
					output += "<tr><td>" + itemCode + "</td>";
					output += "<td>" + itemName + "</td>";
					output += "<td>" + itemPrice + "</td>";
					output += "<td>" + itemDesc + "</td>";
					
					// buttons
					output += "<td><input  name=\"btnSubmit\" type=\"submit\" value=\"Update\" class=\"btn btn-primary\"></td>"
					+ "<form method='post' action='items.jsp'>"
					+ "<td><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'></td>"
					+ "<td><input name='itemID' type='hidden' value='" + itemID + "'></td>"
					+ "</form></tr>";
					
									
					
					// Complete the html table
					
					
				}
				con.close();	
				output += "</table></div>";	
			}
			
		}catch(Exception e) {
			
			output = "Error while reading the items";
			e.printStackTrace();
			
		}
		
		return output;
	}
	
	public String deleteItem(String itemID) {
		
		String output="";
		
		try
		{
			
		Connection con = connect();
		
			if (con == null) {
				
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from items where itemID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Deleted successfully";
			
		}
		catch (Exception e)
		{
			
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		
		}
		
		
		return output;
	}

}
