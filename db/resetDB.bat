call setEnv.bat
mysql -uroot -proot -e "drop database if exists %DB_NAME%"

mysql -uroot -proot -e "create database %DB_NAME% DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci"
call mvn package -Ddb.url=jdbc:mysql://127.0.1:3306/%DB_NAME%?autoReconnect=true
call mvn dbdeploy:update -Ddb.url=jdbc:mysql://127.0.1:3306/%DB_NAME%?autoReconnect=true

pause