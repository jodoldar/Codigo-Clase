var net = require('net');

var LOCAL_PORT = 8000;
var PROG_PORT = 8001;
var LOCAL_IP = '127.0.0.1';
var REMOTE_PORT = process.argv[3];
var REMOTE_IP = process.argv[2];

var server = net.createServer(function(socket) {

	socket.on('data', function(msg) {
		var serviceSocket = new net.Socket();

		serviceSocket.connect(parseInt(REMOTE_PORT), REMOTE_IP, function() {
			serviceSocket.write(msg);
		});

		serviceSocket.on("data", function(data) {
			socket.write(data);
		});
	});
}).listen(LOCAL_PORT, LOCAL_IP);

var serverProg = net.createServer(function(socket) {

	socket.on("data", function(data) {
		console.log("Developer connected!");
		var data = JSON.parse(data);
		REMOTE_PORT = data.REMOTE_PORT;
		REMOTE_IP = data.REMOTE_IP;
		console.log("-> " + REMOTE_IP + ":" + REMOTE_PORT);
	});

}).listen(PROG_PORT, LOCAL_IP);

console.log("TCP server accepting connection on port:" + LOCAL_PORT);
console.log("TCP server accepting connection on port:" + PROG_PORT);
