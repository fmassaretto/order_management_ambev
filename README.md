# Order Management AMBEV
Project for ambev coding interview


## Swagger
To access the API documentation, hit the url: http://localhost:9503/swagger-ui/index.html

## Redis
This project uses redis for cache, in order to make work redis on the application, 
fist enable redis server, following the steps below:

Windows: <br>
1. - Open terminal in a wsl2 debian/ubunt console
2. - Run: sudo apt-get install redis
3. - Start the server with: redis-server

## To test load balance
First instance:<br>
1. - Run command on terminal: .\gradlew bootRun
2. - This will run on defaults and the port will be 9501

Second intance:<br>
1. - Run with Intellij<br>
2. - Select OrdermanagementApplication on 'Run / Debug Configurations'
3. - Click on 'More action' > Edit..
4. - Click on 'Modify Options' and add 'Program Arguments'
5. - Add --server.instance.id=2 --server.port=9502

## Run load test
1. - Run this project on port 9503
2. - Clone https://github.com/fmassaretto/Order_Management_Load_Test'
3. - Run the command in the root folder on terminal: mvn gatling:test