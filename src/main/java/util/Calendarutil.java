package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import dto.CalendarDto;

public class Calendarutil {

	// 문자열 검사: 빈문자열이면 true가 나오게
	//if null("컬럼명", value) -> 컬럼이 null이면 value값을 집어넣어라
	public static boolean nvl(String msg) {
		return msg==null||msg.trim().equals("")?true:false;
	}
	
	
	
	
	
	
	//한자리 숫자 -> 두 자리 숫자 로 만들어주는 함수
	// => 1~9의 숫자를 01~09의 숫자로 생성
	public static String two(String msg) {
		return msg.trim().length()<2?"0"+msg.trim():msg.trim();
	}
	
	
	
	
	
	
	//날짜별 일정을 모두 볼 수 있는 callist.jsp로 이동
	public static String callist(int year, int month, int day) {
		String str = "";
		
		//값을 넣어줄 수 있는 함수
		str += String.format("&nbsp;<a href='callist.jsp?year=%d&month=%d&day=%d'>", year, month, day);
		str += String.format("%2d", day);
		str += "</a>";
		
		return str;
	}
	
	
	
	
	
	
	
	//일정추가
	public static String calwrite(int year, int month, int day) {
		String str = ""; //return값
		
		String image = "<img src='images/pen2.png' width='18px' height='18px' title='일정추가'>";		
		str = String.format("<a href='calwrite.jsp?year=%d&month=%d&day=%d'>%s</a>", 
													    year, month, day, image);		
		return str;
	}
	
	
	
	
	
	
	
	//문장이 길어질때, 단락으로 처리
	//폭설로 인한 교통체증 증가 -> 폭설로....
	public static String dot3(String msg) {
		String str = "";
		if(msg.length() >= 10) {
			str = msg.substring(0, 10);
			str += "...";
		}else {
			str = msg.trim();
		}
		return str;
	}
	
	
	
	
	
	
	
	
	//날짜별로 테이블을 생성하기위한 함수
	//해당 날짜별로 넣어야 하므로 리스트로 생성해주기
	public static String makeTable(int year, int month, int day, List<CalendarDto> list) {
		String str = "";
		
		// 2023 02 16 -> 20230216
		String dates = (year + "") + two(month + "") + two(day + "");
		
		str += "<table>";
		
		//dto에 리스트값 넣기
		// rdate = 2023 02 16 11 04 35 -> 20230216
		for(CalendarDto dto : list) {
			//0부터 8전까지의 날짜 받아오기
			if(dto.getRdate().substring(0, 8).equals(dates)) {
				str += "<tr>";				
				str += "  <td style='padding:0px;height:20'>";				
				str += "    <a href='caldetail.jsp?seq=" + dto.getSeq() + "'>";				
				str += "      <font style='font-size:10px'>";				
				str += 			dot3(dto.getTitle());					
				str += "      </font>";				
				str += "    </a>"; 				
				str += "  </td>";				
				str += "</tr>";					
			}
		}
		str += "</table>";
		return str;
	}
	
	
	
	
	
	
	
	//날짜 보기 편하게 출력하는 함수
	public static String toDates(String mdate) {
		// 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
			
		// 202302170942 -> yyyy-MM-dd hh:mm
		String s = mdate.substring(0, 4) + "-"		// yyyy
					+ mdate.substring(4, 6) + "-"	// MM
					+ mdate.substring(6, 8) + " "	// dd
					+ mdate.substring(8, 10) + ":"	// hh
					+ mdate.substring(10) + ":00";	// mm		
		Timestamp d = Timestamp.valueOf(s);
				
		return sdf.format(d);
	}
	
	
	
	
	
	
	
	//01~09식으로 이루어진 문자열을 1~9로 변환해주는 함수
	public static String toOne(String msg) {
		return msg.charAt(0) == '0'? msg.charAt(1) + "":msg;
	}
	
	
}
