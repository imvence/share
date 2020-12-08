#!/bin/bash

TargetURL="http://www.baidu.com"

STATUS_CODE=`curl -o /dev/null --connect-timeout 10 -s -w %{http_code} $TargetURL`
echo "$TargetURL Status Code:$STATUS_CODE"

if [ "$STATUS_CODE" = "502" ]; then
    /etc/init.d/php-fpm restart
fi