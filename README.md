# voya
	Install steps for db and environment variables
1. First step is from sql folder instal voya.mwb
2. Second step is to set up environment variables in IDE.
	For eclipse go to Run-> Run Configurations...->
	Environment tab, and there insert your variables as it follow:
	
		CLEARDB_DATABASE_DRIVER = com.mysql.cj.jdbc.Driver;
		CLEARDB_DATABASE_URL = jdbc:mysql://localhost:3306/voya?useSSL=false;
		
		(change localhost and port from your db setup)
		
		CLEARDB_DATABASE_USERNAME = your db username
		CLEARDB_DATABASE_PASSWORD = your db password
		

For logs i used log4j. The logs are shown in console.
 If needed the log4j.properties file can be modify to 
save logs in file locally.
		