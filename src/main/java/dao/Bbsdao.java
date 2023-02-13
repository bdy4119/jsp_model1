package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.bbsDTO;

public class Bbsdao {
	
	private static Bbsdao dao = null;
	
	public Bbsdao() {
	}
	
	public static Bbsdao getInstance() {
		if(dao == null) {
			dao = new Bbsdao();
		}
		return dao;
	}
	
	public List<bbsDTO> getBbsList() {
		
		String sql = " select seq, id, ref, step, depth, "
				+ " title, content, wdate, del, readcount "
				+ " from bbs "
				+ " order by ref desc, step asc ";	//정렬
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<bbsDTO> list = new ArrayList<bbsDTO>();
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getbbslist success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getbbslist success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getbbslist success");
			
			while(rs.next()) {
				
				bbsDTO dto = new bbsDTO(rs.getInt(1),
										rs.getString(2),
										rs.getInt(3),
										rs.getInt(4),
										rs.getInt(5),
										rs.getString(6),
										rs.getString(7),
										rs.getString(8),
										rs.getInt(9),
										rs.getInt(10));
				list.add(dto);
				
			}
			System.out.println("4/4 getbbslist success");
		} catch (SQLException e) {
			System.out.println("getbbslist fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
		
	}
	
	
	
	
	//글의 총 갯수
	public int getAllBbs(String choice, String search) {
		
		String sql = " select count(*) from bbs ";
		
		String searchSql = "";
		if(choice.equals("title")) {
			searchSql = " where title like '%" + search + "%'";
		}
		else if(choice.equals("content")) {
			searchSql = " where content like '%" + search + "%'";
		} 
		else if(choice.equals("writer")) {
			searchSql = " where id='" + search + "'"; 
		} 
		sql += searchSql;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
				
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return count;
	}
	
	
	
	//글쓰기
	public boolean writeBbs(bbsDTO dto) {
		String sql = " insert into bbs(id, ref, step, depth, title, content, wdate, del, readcount)"
				+ "    values(?, "
				+ "       (select ifnull(max(ref), 0)+1 from bbs b), 0, 0, "
				+ "       ?, ?, now(), 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 writeBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println("2/3 writeBbs success");
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 writeBbs success");
			
		} catch (SQLException e) {
			System.out.println("writeBbs fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
	}

			
	//게시판 글검색
	public List<bbsDTO> getBbsSearchList(String choice, String search) {
			
			String sql = " select seq, id, ref, step, depth,"
					+ "			  title, content, wdate, del, readcount "
					+ "    from bbs ";
			
			String searchSql = "";
			if(choice.equals("title")) {
				searchSql = " where title like '%" + search + "%'";
			}
			else if(choice.equals("content")) {
				searchSql = " where content like '%" + search + "%'";
			} 
			else if(choice.equals("writer")) {
				searchSql = " where id='" + search + "'"; 
			} 
			sql += searchSql;
			
			sql += "    order by ref desc, step asc ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			List<bbsDTO> list = new ArrayList<bbsDTO>();
					
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/4 getBbsList success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("2/4 getBbsList success");
				
				rs = psmt.executeQuery();
				System.out.println("3/4 getBbsList success");
				
				while(rs.next()) {
					
					bbsDTO dto = new bbsDTO(rs.getInt(1), 
											rs.getString(2), 
											rs.getInt(3), 
											rs.getInt(4), 
											rs.getInt(5), 
											rs.getString(6), 
											rs.getString(7), 
											rs.getString(8), 
											rs.getInt(9), 
											rs.getInt(10));
					
					list.add(dto);
				}
				System.out.println("4/4 getBbsList success");
				
			} catch (SQLException e) {	
				System.out.println("getBbsList fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return list;		
	}

	
	public List<bbsDTO> getBbsPageList(String choice, String search, int pageNumber) {
		
		String sql = " select seq, id, ref, step, depth, title, content, wdate, del, readcount "
				+ " from "
				+ " (select row_number()over(order by ref desc, step asc) as rnum,"
				+ "	seq, id, ref, step, depth, title, content, wdate, del, readcount "
				+ " from bbs ";

		String searchSql = "";
		if(choice.equals("title")) {
			searchSql = " where title like '%" + search + "%' ";
		}
		else if(choice.equals("content")) {
			searchSql = " where content like '%" + search + "%' ";
		} 
		else if(choice.equals("writer")) {
			searchSql = " where id='" + search + "' "; 
		} 
		sql += searchSql;
		
		sql += 	  " order by ref desc, step asc) a "
				+ " where rnum between ? and ? ";
				
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		// pageNumber(0, 1, 2...)
		int start, end;
		start = 1 + 10 * pageNumber;	//  1 11 21 31 41
		end = 10 + 10 * pageNumber;		// 10 20 30 40 50
		
		List<bbsDTO> list = new ArrayList<bbsDTO>();
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbsPageList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getBbsPageList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbsPageList success");
			
			while(rs.next()) {
				
				bbsDTO dto = new bbsDTO(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10));
				
				list.add(dto);
			}
			System.out.println("4/4 getBbsPageList success");
			
		} catch (SQLException e) {	
			System.out.println("getBbsList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;		
	}
}