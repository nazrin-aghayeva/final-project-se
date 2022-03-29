#Build and run backend app
docker build -t backend .
docker run --name backend -p 80:80 backend

#Build and run frontend app
docker build -t frontend .
docker run --name frontend -p 8080:8080 frontend

#Build and run database
docker build -t mysql_db .
docker run --name mysql_db -dp 3306:3306 mysql_db
