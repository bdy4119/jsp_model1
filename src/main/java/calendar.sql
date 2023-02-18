drop table calendar;


create table calendar(
	seq int auto_increment primary key,
	id varchar(50) not null, 
	title varchar(200) not null,
	content varchar(4000),
	rdate varchar(12) not null,
	wdate timestamp not null
);


-- 추가
alter table calendar
add
constraint fi_cal_id foreign key(id)
references member(id);



select*from calendar;

-- 월별 일정에서 5개만 가져오기
--sub Query
select seq, id, title, content, rdate, wdate
from
	(select row_number()over(partition by substr(rdate, 1, 8) order by rdate asc) as rnum,
		seq, id, title, content, rdate, wdate
	from calendar
	where id='abc' and substr(rdate, 1, 6) = '20230216123525') a
where rnum between 1 and 5;





--callist
select seq, id, title, content, rdate, wdate
from calendar
where id='0' and substr(rdate, 1, 8)='20230228'
order by rdate asc;