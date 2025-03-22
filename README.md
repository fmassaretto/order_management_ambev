# Order Management AMBEV
Project for ambev coding interview


## Redis
This project uses redis for cache, in order to make work redis on the application, 
fist enable redis server, following the steps below:

Windows: <br>
1. - Open terminal in a wsl2 debian/ubunt console<br>
2. - Run: sudo apt-get install redis<br>
3. - Start the server with: redis-server<br>

## To test load balance
First instance:<br>
1. - Run command on terminal: .\gradlew bootRun<br>
2. - This will run on defaults and the port will be 9501<br>

Second intance:<br>
1. - Run with Intellij<br>
2. - Select OrdermanagementApplication on 'Run / Debug Configurations'<br>
3. - Click on 'More action' > Edit..<br>
4. - Click on 'Modify Options' and add 'Program Arguments'<br>
5. - Add --server.instance.id=2 --server.port=9502<br>

