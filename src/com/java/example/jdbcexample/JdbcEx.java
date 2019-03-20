package com.java.example.jdbcexample;

import java.sql.SQLException;
import java.util.Scanner;

public class JdbcEx {
	 static Scanner sc = new Scanner(System.in);
	public static void main(String[] str) throws ClassNotFoundException, SQLException {
		Employee emp = new Employee();
		EmployeeServices emps = new EmployeeServices();
		
		while(true) {
			System.out.println("\n");
			System.out.println("*********Employee Management Application*********");
			System.out.println();
			System.out.println("1. Add new employee");
			System.out.println("2. View all employees details");
			System.out.println("3. delete employee");
			System.out.println("4. update employee details");
			System.out.println("5. view employee detail");
			System.out.println("6. view employee by age");
			System.out.println("7. print statistics");
			System.out.println("8. Export data");
			System.out.println("9. Import data");
			System.out.println("Enter choice : ");
			int choice = sc.nextInt();
			if(choice == 0) {
				break;
			}
			switch(choice) {
			case 1:
				emps.add();
				break;
				
			case 2:
				System.out.format("id\t name\t age\t salary\t  department\t  \n");
				System.out.println("-------------------------------------------------------");
				emps.show();
			    break;
    
			case 3:
				System.out.println("enter employee to be deleted");
				int id = sc.nextInt();
				emps.delete(id);
			    break;
		 
			case 4:
				System.out.println("enter employee to update");
				int id1 = sc.nextInt();
				emps.update(id1);
			    break;
			    
			case 5:
				System.out.println("enter employee to view details");
				int id2 = sc.nextInt();
				emps.showEmp(id2);
				break;
	/* 		
			case 6:
				System.out.println("enter employee to view details");
				int id3 = sc.nextInt();
				emp.showEmpByAge(id3);
				break;
		*/		
			case 7:
				emps.printStatistics();
				break;
			/*	
			case 8:
				emp.exportData();
				break;
			
			case 9: 
				emp.importData();
				break;
	*/
			default:
				System.out.println("enter valid choice");
			}
		}

	}
}
