package com.NepalCode.BeanLife;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LBEFCollegeDao {

	/*
	 * private String driver = "com.mysql.jdbc.Driver"; private String url =
	 * "jdbc:mysql://localhost:3306/studentdatabase"; private String username =
	 * "root"; private String password = "root";
	 */

	private String driver;
	private String url;
	private String username;
	private String password;
	private Connection con;

	// to inject this value we have to use setter or construction injection

	public void setDriver(String driver) {
		System.out.println("setting driver");
		this.driver = driver;
	}

	public void setUrl(String url) {
		System.out.println("setting url");
		this.url = url;
	}

	public void setUsername(String username) {
		System.out.println("setting username");
		this.username = username;
	}

	public void setPassword(String password) {
		System.out.println("setting password");
		this.password = password;
	}

	// use @PostConstruct: to avoid recalling createlbefDBconnection in multi
	// utility method
	@PostConstruct
	public void init() throws ClassNotFoundException, SQLException {
		System.out.println("Inside the custom init method");
		createLbefDBConnection();
	}

	// use @PostConstruct: to avoid recalling createlbefDBconnection in multi
	// utility method

	public void createLbefDBConnection() throws ClassNotFoundException, SQLException {

		// laod driver
		Class.forName(driver);

		// get a connection
		con = DriverManager.getConnection(url, username, password);
	}

	public void selectAllRows() throws ClassNotFoundException, SQLException {

		System.out.println("Reteriving student data");
		// createLbefDBConnection();

		/*
		 * // load the driver Class.forName(driver);
		 * 
		 * // get a connection Connection con = DriverManager.getConnection(url,
		 * username, password);
		 * 
		 */

		// execute a query
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM studentdatabase.studentinfo");

		while (rs.next()) {
			int studentid = rs.getInt(1);
			String studentName = rs.getString(2);
			String phoneNum = rs.getString(3);
			double fees = rs.getDouble(4);

			System.out.println("Student id is: " + studentid + "student name is: " + studentName
					+ " student phone num is: " + phoneNum + " fees is: " + fees);
		}

		// closing the connection
		// con.close();

		closeConnection();
	}

	public void deleteRecord(int studentId) throws ClassNotFoundException, SQLException {

		// createLbefDBConnection();
		/*
		 * // laod the driver Class.forName(driver);
		 * 
		 * // get a connection Connection con = DriverManager.getConnection(url,
		 * username, password);
		 * 
		 */

		// execute query
		Statement stmt = con.createStatement();
		stmt.executeUpdate("DELETE  FROM studentdatabase.studentinfo where student_id =" + studentId);

		System.out.println("Record delete with id " + studentId);

		// closing the connection
		// con.close();

		closeConnection();
	}

	// @PreDestroy
	public void closeConnection() throws SQLException {

		// closing connection
		// con.close();
	}

	@PreDestroy
	public void initClose() throws SQLException {
		// clean up job
		System.out.println("closing init method called");
		closeConnection();

	}

}
