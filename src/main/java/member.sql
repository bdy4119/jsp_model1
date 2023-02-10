
drop table memeber;

create table member(
	id varchar(50) primary key, -- 중복방지/ null과 같은값 허용X
	pwd varchar(50) not null,
	name varchar(50) not null,
	email varchar(50) unique, -- null은 허용O, 같은값은 허용X
	auth int -- user인지 client인지 확인
);

select id
from member
where id='abc';


select count(*)
from member
where id = 'abc';

select * from member;