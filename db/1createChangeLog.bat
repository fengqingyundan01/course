#!/bin/sh

source ./setEnv.sh
call mvn package -Ddb.url=jdbc:mysql://127.0.1:3306/${DB_NAME}?autoReconnect=true