package com.java.example.jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EmployeeDao {
	
	Scanner in = new Scanner(System.in);
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/jdbcex";
	
	static final String USER = "root";
	static final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public EmployeeDao() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(Exception e) {
			
		}
    }
	

	public void addEmp() throws SQLException {
System.out.println("Enter details");
		
		
		//System.out.println("enter id :");
		//int id = in.nextInt();
		//System.out.println("id is:"+id);
		
		System.out.println("enter name :");
		String name = in.next();
		//System.out.println("name: "+name);
		
		System.out.println("Enter age :");
		int age = in.nextInt();
		//System.out.println("age is: "+age);
		
		System.out.println("enter salary :");
		int salary = in.nextInt();
		//System.out.println("salary is :" +salary);
		
		System.out.println("enter department :");
		String dept = in.next();
		//System.out.println("designation :"+des);
		
		String sql = "INSERT INTO employees (name, age, salary, department) values (?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setInt(2, age);
		pstmt.setInt(3,salary);
		pstmt.setString(4, dept);
		
		int insertCount = pstmt.executeUpdate();
		System.out.println(insertCount + " Employee(s) inserted");
	
	}
	
	public void printDetails() throws SQLException,NullPointerException {
		
		String selectQuery = "SELECT * FROM employees";
		pstmt = conn.prepareStatement(selectQuery);
		rs =pstmt.executeQuery();
		
		//System.out.format("\t%s \t%s \t%s \t%s \t%s \n", "Id", "Name", "Age", "Salary", "Department");

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			int salary = rs.getInt("salary");
			String department = rs.getString("department");
		
			System.out.format("\t%d \t%s \t%d \t%d \t%s \n", id, name, age, salary, department);

		}

	} 
	
	public void updateDetails(int id1) throws SQLException {
		//System.out.println("enter name :");
//		String name = in.next();
//				
//		System.out.println("Enter age :");
//		int age = in.nextInt();
//					
//		System.out.println("enter salary :");
//		int salary = in.nextInt();
//		
		System.out.println("enter department :");
		String dept = in.next();   
	     
		String updateQuery = "UPDATE employees SET department = ? WHERE id = ?";
		pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, dept);
		pstmt.setInt(2, id1);	
		int updateCount = pstmt.executeUpdate();
		System.out.println(updateCount + " Employee(s) updated");

		
	}
	
	public void deleteDetails(int id) throws SQLException {
		String deleteQuery = "DELETE FROM employees WHERE id = ?";
		pstmt = conn.prepareStatement(deleteQuery);
		pstmt.setInt(1, id);			

		int deleteCount = pstmt.executeUpdate();
		System.out.println(deleteCount + " Employee(s) deleted");			

		//String selectQuery = "SELECT * FROM employee";
		//rs = stmt.executeQuery(selectQuery);
	}
	
	public void showEmployee(int id1) throws SQLException {
		String sql = "Select * from employees where id= "+id1+"";
		//pstmt = conn.prepareStatement(sql);
		//pstmt.setInt(1, id1);
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		System.out.format("\t%s \t%s \t%s \t%s \t%s \n", "Id", "Name", "Age", "Salary", "Department");


		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			int salary = rs.getInt("salary");
			String department = rs.getString("department");
		
			System.out.format("\t%d \t%s \t%d \t%d \t%s \n", id, name, age, salary, department);

		}
	}
	
	public void statistics() throws SQLException, ClassNotFoundException, NullPointerException {
		String selectQuery = "SELECT * FROM employees";
		pstmt = conn.prepareStatement(selectQuery);
		rs =pstmt.executeQuery();
		
		List<Employee> emplist = new ArrayList();
		//System.out.format("\t%s \t%s \t%s \t%s \t%s \n", "Id", "Name", "Age", "Salary", "Department");

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			int salary = rs.getInt("salary");
			String department = rs.getString("department");
			Employee e1 = new Employee();
			e1.setId(id);
			e1.setName(name);
			e1.setAge(age);
			e1.setSalary(salary);
			e1.setDeparment(department);
			
			emplist.add(e1);
			
			//System.out.format("\t%d \t%s \t%d \t%d \t%s \n", id, name, age, salary, department);
		}
		List<Integer> count = emplist.stream().filter(e->e.getAge()>23).map(e->e.getId()).collect(Collectors.toList());
	    System.out.println("\n List of employees whose age greater than 23 are :"+count);
	    
	    List<String> counts = emplist.stream().collect(Collectors.groupingBy(e->e.getDeparment(), Collectors.counting()))
	    	    .entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList());
	    	    System.out.println("\n designations with more No. of employees: "+counts);
	    	    
	    List<String> counts1 = emplist.stream().filter(e->e.getName().startsWith("n")).map(e->e.getName()).collect(Collectors.toList());
	    	    System.out.println("\n employees starts with letter 'n' :"+counts1);
	    
	    	    long count1;
	    	    count1 = emplist.stream().filter(e->e.getAge()>23).count();
	    	    System.out.println("\n No. of employees whose age greater than 23 are :"+count1);
	    	    
	    	    Map count2 = new TreeMap();
	    	    
	    	    //designation wise count of employees
	    	    count2 = emplist.stream().collect(Collectors.groupingBy(e->e.getDeparment(), Collectors.counting()));
	    	    System.out.println("\n designation wise count of employees :"+count2);
	    	    
	    	    count2 = emplist.stream()
	    	    		.collect(Collectors.groupingBy(e->e.getDeparment(), Collectors.averagingInt(e->e.getSalary())));
	    	    System.out.println("\n avg salary by designation is:"+count2);
	    	    
	    	    count2 = emplist.stream().sorted(Comparator.comparing(e->e.getDeparment()))
	    	    		.collect(Collectors.groupingBy(e->e.getDeparment(), TreeMap::new, Collectors.counting()));
	    	    System.out.println("\n count of employees with sorted designation :"+count2);
	    	    
	    	    int totalsal = emplist.stream().map(Employee::getSalary).reduce(0, (a,b) -> a.intValue() + b.intValue()).intValue();
	    	    System.out.println("\n Total salary of all employees :"+totalsal);
	    	    
	    	    int avgSalary = emplist.stream().collect(Collectors.averagingInt(Employee::getSalary)).intValue();
	    	    System.out.println("\n Average salary of all employees :"+avgSalary);
	    			  	    
	}

}