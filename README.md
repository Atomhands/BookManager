# BookManeger

#### 介绍

图书管理系统servlet工程

#### 软件架构
软件架构说明  


#### 使用说明

1. 此工程使用的是作者的本地数据库(oracle)，作者qq  2262152973@qq.com

使用需要更改配置数据库连接池 

3. 管理员表
CREATE table TB_ADMIN(
admin_Id  varchar2(50) primary key ,
account varchar2(50),
password varchar2(50),
name varchar2(10),
phone varchar2(16),
create_Time timestamp,
role_Name varchar2(10)
);

4. 图书表
   CREATE table TB_BOOK_INFO(
   book_Id  varchar2(50) primary key ,
   book_Name varchar2(50),
   publisher varchar2(50),
   author varchar2(10),
   book_Type varchar2(16),
   remain varchar2(10)
   );
   INSERT INTO TB_BOOK_INFO values (1,'斗罗大陆','江苏','唐家三少','小说',50);

