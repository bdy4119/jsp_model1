package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	

	//클래스가 있는지 없는지 확인
	public static void initConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loding Success");
			} catch (ClassNotFoundException e) {
			System.out.println("DB driver를 연결하지 못했습니다.");
			e.printStackTrace();
		}
	}
	
	
	//connection정보 확인
	public static Connection getConnection() {
		Connection conn = null;
		
		//DB, Java연결방법
		//conn = DriverManager.getConnection(현재 db서버에 대한 데이터, 계정명, pw)
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
		//	conn = DriverManager.getConnection("jdbc:mysql://192.168.219.124:3306/mydb", "root", "1234");
			System.out.println("Connection Success");
		} catch (SQLException e) {
			System.out.println("db를 연결하지 못했습니다.");
			e.printStackTrace();
		}
		return conn;
	}
	
}
