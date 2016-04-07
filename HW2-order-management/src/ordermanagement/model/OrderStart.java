package ordermanagement.model;
//package com.mkyong.common;

import java.sql.DriverManager;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;

import ordermanagement.gui.UI.Interface;

public class OrderStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ! CONNECTING TO DATABASE		
		Interface go = new Interface();
		go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        go.setSize(400,250);
        go.setBackground(Color.yellow);
        go.setVisible(true);
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pt_homework", "Andras_homework",
					"homework");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		/*
		 * try{ connection.close (); System.out.println("Connection closed...");
		 * }catch (Exception e) { System.out.println ("Connection close error");
		 * }
		 */
		//Customer David = new Customer(20, "David", "07111111", "address");
		// ordermanagement.DAL.CustomerDAL.insert(David);
		//List<Customer> output = null;
		
		/*Customer test = new Customer();
			for (int i = 1; i <= 5; i++) {
				new CustomerBLL();
				try{
					test = CustomerBLL.read(i);
					System.out.println("ID: "+test.getId()+" Address: "+test.getAddress() + " Name: "+test.getName() + " Phone: "+test.getNumber());
				}
				catch (Exception rse) {
		        	System.out.println("Read finished");      
		        }	
			}*/
			//output.add(test);	
	// new CustomerBLL().insert(David);
	// output = CustomerDAL.read();

}

}
