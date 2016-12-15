var net = require('net');

console.log("Port: "+ process.argv[3]);
console.log("Host: "+ process.argv[2]);

var client = net.connect({port: process.argv[3], host: process.argv[2]}, function() {
    console.log("Connected to server!");
    client.write("\r\n");
});

client.on("data", function(data) {
    var result = JSON.parse(data);
    console.log("Server load: " + result.currentLoad);
});

client.on("end", function() {
    console.log("Client disconnected");
});
