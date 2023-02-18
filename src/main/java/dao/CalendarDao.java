package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.CalendarDto;
import dto.bbsDTO;

//싱글톤 만들어주기
//잊지말자!
public class CalendarDao {
	//클래스 생성안하고 사용하기 위해 static으로 사용
	private static CalendarDao dao = null;
	
	
	private CalendarDao() {
		DBConnection.initConnection();
	}
	
	
	public static CalendarDao getInstance() {
		if(dao == null) {
			dao = new CalendarDao();
		}
		return dao;
	}
	
	
	
	
	public List<CalendarDto> getCalendarList(String id, String yyyyMM) {
		String sql = " select seq, id, title, content, rdate, wdate "
				+ " from "
				+ "	(select row_number()over(partition by substr(rdate, 1, 8) order by rdate asc) as rnum, "
				+ "		seq, id, title, content, rdate, wdate "
				+ "	from calendar "
				+ "	where id=? and substr(rdate, 1, 6)=?) a "
				+ " where rnum between 1 and 5 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//리턴할 변수
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getCalendarList success");
			
			//sql로 쿼리문 받고 쿼리문에 ?값 넣기
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, yyyyMM);
			System.out.println("2/3 getCalendarList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 getCalendarList success");
			
			//데이터 다 가져오기
			while(rs.next()) {
				int i = 1;
				CalendarDto dto = new CalendarDto(rs.getInt(i++),
												  rs.getString(i++),
												  rs.getString(i++),
												  rs.getString(i++),
												  rs.getString(i++),
												  rs.getString(i++));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getCalendarList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}	
	
	
	
	//일정추가
	public boolean addCalendar(CalendarDto dto) {
		String sql = " insert into calendar(id, title, content, rdate, wdate) "
				+ " values(?, ?, ?, ?, now()) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addCalendar success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getRdate());
			System.out.println("2/3 addCalendar success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 addCalendar success");
		} catch (SQLException e) {
			System.out.println("addCalendar false");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	
	
	
	
	//정보 받아오기
	//추후 설명에서 강사님은 getDay로 설명하심
	public CalendarDto getCalendar(int seq) {
		String sql = " select seq, id, title, content, rdate, wdate "
				+ " from calendar "
				+ " where seq=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		CalendarDto dto = null; //리턴해줄 변수 선언
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getCalendar success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 getCalendar success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 getCalendar success");
			
			if(rs.next()) {
			/*	dto = new CalendarDto(rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6)
								); */
				dto = new CalendarDto();
				
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRdate(rs.getString(5));
				dto.setWdate(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println("getCalendar fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
	}
	
	
	
	
	
	
	
		//수정
		public boolean calUpdate(CalendarDto dto) {
			String sql = "update calendar "
					+ " set title=?, content=?, rdate=?, wdate=now() "
					+ " where seq=? ";
					
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;			
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 calUpdate success");

				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getContent());
				psmt.setString(3, dto.getRdate());
				psmt.setInt(4, dto.getSeq());
				System.out.println("2/3 calUpdate success");
				
				count = psmt.executeUpdate();
				System.out.println("3/3 calUpdate success");
				
			} catch (SQLException e) {
				System.out.println("calUpdate fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);
			}
			return count>0?true:false;
		}
		
		
		
		
		
		//삭제
		public boolean calDelete(int seq) {
			String sql = " delete from calendar "
					+ " where seq=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 calDelete success");
				
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				System.out.println("2/3 calDelete success");
				
				count = psmt.executeUpdate();
				System.out.println("3/3 calDelete success");
				
			} catch (SQLException e) {
				System.out.println("calDelete fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);
			}
			
			return count>0?true:false;
		}
		
		
	
		
	//callist
	public List<CalendarDto> getDayList(String id, String yyyymmdd) {
		String sql = " select seq, id, title, content, rdate, wdate "
				+ " from calendar "
				+ " where id=? and substr(rdate, 1, 8)=? "
				+ " order by rdate asc ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null; //쿼리문 받기
		
		//값 넘겨주기
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getDayList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, yyyymmdd);
			System.out.println("2/3 getDayList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 getDayList success");
			
			while(rs.next()) {
				int i =1;
				CalendarDto dto = new CalendarDto( rs.getInt(i++),
													rs.getString(i++),
													rs.getString(i++),
													rs.getString(i++),
													rs.getString(i++),
													rs.getString(i++));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println(" getDayList success");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
		
		
		
		
		
		
		//일정검색
		public List<CalendarDto> getCalSearchList(String choice, String search) {
				
				String sql = " select seq, id, title, content, rdate, wdate "
						+ "    from calendar ";
				
				String searchSql = "";
				if(choice.equals("title")) {
					searchSql = " where title like '%" + search + "%'";
				}
				else if(choice.equals("content")) {
					searchSql = " where content like '%" + search + "%'";
				}  
				sql += searchSql;
				
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				List<CalendarDto> list = new ArrayList<CalendarDto>();
						
				try {
					conn = DBConnection.getConnection();
					System.out.println("1/4 getCalSearchList success");
					
					psmt = conn.prepareStatement(sql);
					System.out.println("2/4 getCalSearchList success");
					
					rs = psmt.executeQuery();
					System.out.println("3/4 getCalSearchList success");
					
					while(rs.next()) {
						
						CalendarDto dto = new CalendarDto(rs.getInt(1),
															rs.getString(2),
															rs.getString(3),
															rs.getString(4),
															rs.getString(5),
															rs.getString(6));
						
						list.add(dto);
					}
					System.out.println("4/4 getCalSearchList success");
					
				} catch (SQLException e) {	
					System.out.println("getCalSearchList fail");
					e.printStackTrace();
				} finally {
					DBClose.close(conn, psmt, rs);
				}
				
				return list;		
		}

		
		
}
