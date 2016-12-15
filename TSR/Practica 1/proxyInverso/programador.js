var net = require('net');

console.log("inPort: " + process.argv[2]);
console.log("Port: "+ process.argv[4]);
console.log("Host: "+ process.argv[3]);

var client = net.connect({port: 8000, host: "127.0.0.1"}, function() {
    console.log("Connected to server!");
    var data = JSON.stringify({
    	"op":"set",
    	"inPort":process.argv[2],
    	"remote":{
    		"port":process.argv[3],
    		"ip":process.argv[4]
    	}
	});
    client.write(data+"\r\n");
    client.end();
});

client.on("end", function() {
    console.log("Client disconnected");
});
