docker rm -f codewar-mysql


docker run --name codewar-mysql -v C:\sources\codewar\server\database\data:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=codewar -e MYSQL_ROOT_HOST=%% -d codewar-mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --lower_case_table_names=1