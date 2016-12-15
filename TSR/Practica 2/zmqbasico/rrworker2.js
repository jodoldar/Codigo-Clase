// Hello World server in Node.js
// Connects REP socket to tcp://*:5560
// Expects "Hello" from client, replies with "World"

var zmq = require('zmq');
var responder = zmq.socket('rep');

if(process.argv.length!=5){
	console.log("Usage: node rrworker2.js url esp text");
	process.exit(0);
}

var url = process.argv[2];
var esp = process.argv[3];
var text = process.argv[4];

responder.connect(url);
responder.on('message', function(msg) {
  console.log('received request:', msg.toString());
  setTimeout(function() {
    responder.send(text);
  }, esp);
});
