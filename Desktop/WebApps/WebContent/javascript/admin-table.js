function submit(i) {
	db = mysql.createConnection('mysql://mysn:mypwd@server-name.cleardb.com/heroku_randomletters?reconnect=true');
alert("sada");
	Class.forName("com.mysql.jdbc.Driver");
	var mysql = require('mysql');
	
	alert("sds");var connection = mysql.createConnection({
		host: 'jdbc:mysql://localhost',
		user: 'keatmar',
		password: '1234',
		database: 'webapp',
		port: 3306 });
	connection.connect();
alert(connection);
	alert(i);
}

function reject(i) {
	alert(i);
}