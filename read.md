首先创建表：
```
CREATE TABLE `sys_sequence` (
   `NAME` varchar(50) NOT NULL,
   `CURRENT_VALUE` int(11) NOT NULL DEFAULT '0',
   `INCREMENT` int(11) NOT NULL DEFAULT '1',
   PRIMARY KEY (`NAME`)
 )
```

插入记录
```
INSERT INTO SYS_SEQUENCE(NAME,CURRENT_VALUE,INCREMENT) VALUES('TBL_FS', 1,1)
```
```
DELIMITER $$
 
DROP FUNCTION IF EXISTS `currval`$$
 
CREATE DEFINER=`root`@`%` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS INT(11)
BEGIN
DECLARE VALUE INTEGER;
SET VALUE=0;
SELECT current_value INTO VALUE
FROM sys_sequence 
WHERE NAME=seq_name;
RETURN VALUE;
END$$
 
DELIMITER ;
```

查询当前记录：
```
select currval('TBL_FS');
```
如果出现Error Code: 1449 The user specified as a definer ('root'@'%') does not exist 错误，则执行如下sql：
```
#创建账户
create user 'root'@'%' identified by  'password'

#赋予权限，with grant option这个选项表示该用户可以将自己拥有的权限授权给别人
grant all privileges on *.* to 'root'@'%' with grant option

#改密码&授权超用户，flush privileges 命令本质上的作用是将当前user和privilige表中的用户信息/权限设置从mysql库(MySQL数据库的内置库)中提取到内存里
flush privileges;

```

继续执行当前记录sql则成功
创建nextval函数
```
DELIMITER $$
DROP FUNCTION IF EXISTS `nextval`$$
 
CREATE DEFINER=`root`@`%` FUNCTION `nextval`(seq_name varchar(50)) RETURNS int(11)
BEGIN
UPDATE sys_sequence
SET CURRENT_VALUE = CURRENT_VALUE + INCREMENT
where name=seq_name;
return currval(seq_name);
END$$
```

执行nextval函数
select nextval('tbl_fs')
