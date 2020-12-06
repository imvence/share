#!/bin/bash

target_path="/home/wwwroot/default/test";	
backup_path="/home/wwwroot/default/backup";
backup_file=`date +%Y%m%d`;
expired_days=3

mysql_host='localhost';
mysql_user='';
mysql_pass='';
mysql_char='utf8';
mysql_db=('test1' 'test2' 'test3')


#检查要备份的目录是否存在
if [ ! -d $target_path ]; then
 
    exit;
         
fi

#检查备份存放目录是否存在
if [ ! -d $backup_path ]; then
 
    mkdir $backup_path;
         
fi

#删除指定时间外的备份档案
find $backup_path/* -type f -mtime +$expired_days -exec rm {} \;

#把目录压缩放到指定目录下
tar czPf $backup_path/$backup_file.tar.gz $target_path;

#对数据库进行备份
for db in ${mysql_db[@]};
do
	mysqldump -u$mysql_user -p$mysql_pass -h$mysql_host $db > $backup_path/$backup_file'_'$db.sql;
done


