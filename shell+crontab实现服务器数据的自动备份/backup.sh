#!/bin/bash

target_path="/home/wwwroot/default/test";	
backup_path="/home/wwwroot/default/backup";
backup_file=`date +%Y%m%d`;
expired_days=3

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

