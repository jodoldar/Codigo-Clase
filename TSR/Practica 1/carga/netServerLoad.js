var net = require("net");
var fs = require('fs');

function getLoad(){
    data=fs.readFileSync("/proc/loadavg"); //requiere fs
    var tokens = data.toString().split(' ');
    var min1 = parseFloat(tokens[0])+0.01;
    var min5 = parseFloat(tokens[1])+0.01;
    var min15 = parseFloat(tokens[2])+0.01;
    return min1*10+min5*2+min15;
};

var server = net.createServer(function(c) {
    console.log("Client connected!");
    c.on("data", function(data) {
        var currentLoad = getLoad();
        var sending = JSON.stringify({
            "currentLoad": currentLoad
        });
        c.write(sending);
        c.end();
    });
    c.on("end", function() {
        console.log("Client disconnected");
    })
});

server.listen(3000, function() {
    console.log("Server listening on port 3000");
});
