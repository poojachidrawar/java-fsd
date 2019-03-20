package com.java.example.jdbcexample;

import java.sql.SQLException;

public class EmployeeServices {
	
	EmployeeDao  dao = new EmployeeDao();
	
	public void add() throws SQLException {
		dao.addEmp();
	}
	
	public void show() throws SQLException {
		dao.printDetails();
	}
	
	public void update(int id) throws SQLException {
		int eid = id;
		dao.updateDetails(eid);
	}
	
	public void delete(int id) throws SQLException {
		int eid = id;
		dao.deleteDetails(eid);
	}
	
	public void showEmp(int id) throws SQLException {
		int eid = id;
		dao.showEmployee(eid);
	}
	
	public void printStatistics() throws ClassNotFoundException, NullPointerException, SQLException {
		dao.statistics();
	}
}
