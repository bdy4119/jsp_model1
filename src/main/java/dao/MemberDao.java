package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

//dao는 무조건 싱글톤으로 제작 -> 어디서든 불러올 수 있게

public class MemberDao {
	
	private static MemberDao dao = null;
	
	private MemberDao() {
		DBConnection.initConnection();
	}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	
	//select
	//해당 아이디가 존재하는지
	public boolean getId(String id) {
		String sql = " select id "
				+ " from member "
				+ " where id=? ";
		
		Connection conn = null;
		PreparedStatement  psmt = null;
		ResultSet rs = null;
		
		boolean findid = false;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getId success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/3 getId success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 getId success");
			
			//데이터가 존재하면
			if(rs.next()) {
				findid = true;
			}
		} catch (SQLException e) {
			System.out.println("getId fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return findid;
		
	}
	
	
	
	
	//insert
	public boolean addMember(MemberDto dto) {
		
		String sql = " insert into member(id, pwd, name, email, auth) "
				+ " value(?, ?, ?, ?, 3) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getId success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			System.out.println("2/3 getId success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 getId success");
		} catch (SQLException e) {
			System.out.println("addMember fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
		
	}
	
	
	
	//로그인 정보
	public MemberDto login(String id, String pwd) {
		
		String sql = " select id, name, email, auth " //id는 1번지
				+ " from member "
				+ " where id=? and pw=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//리턴할 변수
		MemberDto mem = null;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 login success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			System.out.println("2/3 login success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 login success");
			if(rs.next()) {
				String _id = rs.getString(1);
				String _name = rs.getString(2);
				String _email = rs.getString(3);
				int _auth = rs.getInt(4);
				
				mem = new MemberDto(_id, null, _name, _email, _auth);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return mem;
		
	}
	
	
	
}
