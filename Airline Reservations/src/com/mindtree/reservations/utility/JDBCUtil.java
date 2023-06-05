package com.mindtree.reservations.utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.reservations.exception.daoexception.DatabaseConnectionException;

public class JDBCUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/reservation?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "ROOT";
	
	//Getting connections with Database
	public static Connection getConnection() throws DatabaseConnectionException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			throw new DatabaseConnectionException("Error While Connecting");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			throw new DatabaseConnectionException("Error While Connecting");
		}
		return con;
	}
	
	//Closing Connections with Database
	public static  void closeConnection(ResultSet rs) throws DatabaseConnectionException {
		try {
			if(rs!=null)
				rs.close();
		}catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
			throw new DatabaseConnectionException("Error While closing Connection");
		}
	}
	
	//Closing Connections with Database
	public static  void closeConnection(Statement s) throws DatabaseConnectionException {
		try {
			if(s!=null)
				s.close();
		}catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
			throw new DatabaseConnectionException("Error While closing Connection");
		}
	}
	
	//Closing Connections with Database
	public static  void closeConnection(Connection con) throws DatabaseConnectionException {
		try {
			if(con!=null)
				con.close();
		}catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
			throw new DatabaseConnectionException("Error While closing Connection");
		}
	}
	
	//Closing Connections with Database
	public void closeConnection(CallableStatement statmnt) throws DatabaseConnectionException {
		try {
			if (statmnt != null)
				statmnt.close();
		} catch (Exception e) {
			throw new DatabaseConnectionException("Error in closing connection");
		}
	}

}
