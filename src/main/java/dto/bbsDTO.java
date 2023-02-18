package dto;

import java.io.Serializable;

public class bbsDTO implements Serializable {

	private int seq; 		//sequence 글번호(고유번호) -> 글순서 번호가 아님
	private String id; 		//작성자 아이디
	
	//답글용 변수들
	private int ref;		//sequence 그룹번호(글번호)
	private int step;		//		   행번호
	private int depth;		//		   깊이번호
	
	private String title;	//글제목
	private String content; //글내용
	private String wdate;	//작성날짜
	
	private int del;		//글이 삭제됐는지 안됐는지 확인
	private int readcount;	//조회수
	
	
	
	//기본생성자
	public bbsDTO() {
		
	}

	
	//매개변수 생성자
	//외부에서 들어오는 정보들
	public bbsDTO(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	
	//매개변수 생성자2
	//내부에서 작성해줘야하는 정보들
	public bbsDTO(int seq, String id, int ref, int step, int depth, String title, String content, String wdate, int del,
			int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
	}


	
	//setter getter
	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}


	public int getStep() {
		return step;
	}


	public void setStep(int step) {
		this.step = step;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	public int getDel() {
		return del;
	}


	public void setDel(int del) {
		this.del = del;
	}


	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	
	
	@Override
	public String toString() {
		return "bbsDTO [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", title="
				+ title + ", content=" + content + ", wdate=" + wdate + ", del=" + del + ", readcount=" + readcount
				+ "]";
	}
	
	
}
