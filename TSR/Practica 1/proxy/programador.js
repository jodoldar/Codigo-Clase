var net = require('net');

console.log("Port: "+ process.argv[3]);
console.log("Host: "+ process.argv[2]);

var client = net.connect({port: 8001, host: "127.0.0.1"}, function() {
    console.log("Connected to server!");
    var data = JSON.stringify({
		"REMOTE_PORT": process.argv[3],
		"REMOTE_IP": process.argv[2]
	});
    client.write(data+"\r\n");
    client.end();
});

client.on("end", function() {
    console.log("Client disconnected");
});
