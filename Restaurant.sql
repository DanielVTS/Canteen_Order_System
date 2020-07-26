
drop database if exists RestaurantSystem;

create database RestaurantSystem;

use RestaurantSystem;


-- 餐厅管理人员表 

create table Staff
(

Staff_Id int(3),

Staff_Name varchar(10),

primary key(Staff_Id),

Job varchar(15) not null,

Gender varchar(2) check(Gender in("男","女")),

StaffPassword varchar(20) not null
);

 INSERT INTO Staff values(100,'李大','经理','男',123456);
 INSERT INTO Staff values(101,'王二','副经理','男',12456);
 INSERT INTO Staff values(102,'张三','采购员','男',13456);
  INSERT INTO Staff values(103,'李四','采购员','男',14456);
   INSERT INTO Staff values(104,'小五','厨师','女',123456);
    INSERT INTO Staff values(105,'小六','厨师','女',16456);
     INSERT INTO Staff values(106,'花花','服务员','女',17456);
      INSERT INTO Staff values(107,'瓜瓜','服务员','男',18456);
 
 
 
 
 
 


-- 菜单表

create table Menu
(
Menu_Id int(3),

Menu_Name varchar(10),

primary key(Menu_ID),

Menu_Kind varchar(10) not null, -- (属于肉类/蔬菜/饮料)  

Menu_Status int(1) not null default 1,-- (餐品状态默认为1,1为有该产品,0即为无)

Price int(3) not null default 0
);

INSERT INTO Menu values(001,'红烧肉','肉类',1,60);
INSERT INTO Menu values(002,'东坡肉','肉类',1,78);
INSERT INTO Menu values(003,'脆皮鸡','肉类',1,80);
INSERT INTO Menu values(004,'烤乳鸽','肉类',1,99);
INSERT INTO Menu values(005,'红烧茄子','蔬菜',1,20);
INSERT INTO Menu values(006,'番茄炒蛋','蔬菜',1,22);
INSERT INTO Menu values(007,'凉拌青瓜','蔬菜',1,8);
INSERT INTO Menu values(008,'可乐','饮料',1,3);
INSERT INTO Menu values(009,'雪碧','饮料',1,3);
INSERT INTO Menu values(010,'蜂蜜柠檬茶','饮料',1,6);
INSERT INTO Menu values(011,'蜂蜜柚子茶','饮料',1,6);
-- 台位表

create table Table_Information
(
Table_Id int(3) primary key,

Table_Seat int(2) not null,-- (桌椅有多少个座位)

Table_Status int(1) not null default 1,-- (该台位的状态，默认为1，即为空,2即为有人占用)

Phone int(11) default 0,

OrderTime timestamp default CURRENT_TIMESTAMP
);
-- (预定了座位客人的预定时间一般情况下为空)

INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(101,4,1,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(102,4,2,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(103,4,1,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(104,6,1,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(105,6,1,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(106,6,2,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(107,8,1,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(108,8,1,0);
INSERT INTO Table_Information(Table_Id,Table_Seat,Table_Status,Phone) values(109,12,1,0);


-- 订单表

create table ClientOrder
(

Table_Id int(3),

Menu_Id int(3),

Menu_Count int(3) default 1 not null,-- (餐品的数量)

Price int(3) not null default 0,

OrderStatus int(1) default 1 not null,-- (订单的状态 默认为1，未付款，2已付款，3预定)

constraint Order_Table foreign key (Table_id) references Table_Information(Table_id),

constraint Order_Menu foreign key (Menu_id) references Menu(Menu_id)
);

INSERT INTO ClientOrder values(101,001,4,60,1);
INSERT INTO ClientOrder values(101,002,4,78,1);
INSERT INTO ClientOrder values(101,006,4,22,1);
INSERT INTO ClientOrder values(102,002,4,78,2);
INSERT INTO ClientOrder values(102,004,4,99,2);
INSERT INTO ClientOrder values(102,005,4,20,2);
INSERT INTO ClientOrder values(103,003,5,80,1);
INSERT INTO ClientOrder values(103,001,5,60,1);
INSERT INTO ClientOrder values(103,006,5,22,1);
INSERT INTO ClientOrder values(104,004,6,99,2);
INSERT INTO ClientOrder values(105,005,5,20,1);
INSERT INTO ClientOrder values(106,006,6,22,1);
INSERT INTO ClientOrder values(107,007,4,8,1);
INSERT INTO ClientOrder values(108,008,6,3,1);




-- 厨师分工表

create table ChefWork
(

Staff_Id int(3),

Staff_Name varchar(10),-- (这里要用一个（插入触发器）当插入员工表数据时，当他的职位是厨师时插入Id,名字到这里)

Chef_Job varchar(10),-- (厨师负责餐品的种类,数据要与Menu_Kind的数据一致)

constraint Chef_Staff1 foreign key (Staff_Id) references Staff(Staff_Id)
);



insert into ChefWork values(104,'小五',"肉类");
insert into ChefWork values(104,'小五',"饮料");
insert into ChefWork values(105,'小六',"蔬菜");

	

-- 厨师订单表

create  table ChefOrder
(
Staff_Id int(3),
Table_Id int(3),
Menu_Id int(3),
Menu_count int(3),

constraint Chef_3 foreign key (Staff_Id) references ChefWork(Staff_Id),

constraint Chef_Menu foreign key (Menu_Id) references Menu(Menu_Id)
);

insert into ChefOrder values(104,101,001,4);
insert into ChefOrder values(104,102,002,4);	
insert into ChefOrder values(104,103,003,5);
insert into ChefOrder values(104,104,004,6);
insert into ChefOrder values(105,105,005,5);
	

	create view v_clientorder1
	as
	select c.Table_Id,m.Menu_Name,c.Menu_Count,m.Price,c.Menu_Count*m.Price as RowPrice,c.OrderStatus from clientorder c join menu m on c.Menu_Id =m.Menu_Id
	where c.OrderStatus=1
    order by c.Table_Id;

	create view v_clientorder2
	as
	select c.Table_Id,m.Menu_Name,c.Menu_Count,m.Price,c.Menu_Count*m.Price as RowPrice,c.OrderStatus from clientorder c join menu m on c.Menu_Id =m.Menu_Id
	where c.OrderStatus=2
    order by c.Table_Id;
    
	create view v_clientorder3
	as
	select c.Table_Id,m.Menu_Name,c.Menu_Count,m.Price,c.Menu_Count*m.Price as RowPrice,c.OrderStatus from clientorder c join menu m on c.Menu_Id =m.Menu_Id
    order by c.Table_Id;


create view V_chef
as
select w.Staff_Name,o.Table_Id,m.Menu_Name,o.Menu_Count from 
chefwork w join menu m on w.Chef_Job=m.Menu_Kind join clientorder o on o.Menu_Id=m.Menu_Id
where o.OrderStatus=1
order by m.Menu_Id;

-- //查找订单的总价
delimiter $$
create procedure P_OrderPrice(in tableid int(3),in mstatus int(1),out sumPrice int(5))
begin
select sum(c.Menu_Count*m.Price) into sumprice from clientorder c join menu m on c.Menu_Id=m.Menu_Id
where c.table_id=tableid and c.OrderStatus=mstatus;
end$$
delimiter ;


--新加的触发器 当修改订单动态修改台位信息
delimiter $$
create trigger tri_updatetable after update on clientorder for each row
begin
if(new.OrderStatus=3) then
update table_information set Table_Status=0,OrderTime=CURRENT_TIMESTAMP where Table_Id=old.Table_Id;
else if(new.OrderStatus=1) then
update table_information set Table_Status=new.OrderStatus,OrderTime=CURRENT_TIMESTAMP where Table_Id=old.Table_Id;
else if(new.OrderStatus=2) then
update table_information set Table_Status=new.OrderStatus,OrderTime=CURRENT_TIMESTAMP where Table_Id=old.Table_Id;
end if;
end if;
end if;
end$$
delimiter ;

-- 删除桌子
delimiter $$
create trigger tri_del before delete on table_information
for each row
begin
delete from cheforder where Table_Id=old.table_Id;
delete from clientorder where Table_Id=old.Table_Id;
end$$
delimiter ;

-- 新加的触发器
delimiter $$
create trigger tri_delmenu before delete on menu
for each row
begin
delete from cheforder where Menu_Id=old.Menu_Id;
delete from clientorder where Menu_Id=old.Menu_Id;
end$$
delimiter ;



desc menu;

alter table menu modify Menu_Id int(3) auto_increment;

show TABLES;

select * from menu;

DELETE from menu where menu_id=13;

INSERT into menu(menu_name,menu_kind,menu_status,price) VALUES("111","11",1,10);





select * from cheforder;