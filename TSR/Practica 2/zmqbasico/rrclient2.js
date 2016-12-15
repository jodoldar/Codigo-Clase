// Hello World client in Node.js
// Connects REQ socket to tcp://localhost:5559
// Sends "Hello" to server, expects "World" back

var zmq = require('zmq');
var requester = zmq.socket('req');

if(process.argv.length!=6){
	console.log("Usage: node rrclient2.js url id num text");
	process.exit(0);
}

var url = process.argv[2];
var id = process.argv[3];
var num = process.argv[4];
var text = process.argv[5];

requester.identity=id;
requester.connect(url);
var replyNbr = 0;
requester.on('message', function(msg) {
  console.log('got reply', replyNbr, msg.toString());
  replyNbr += 1;
});

for (var i = 0; i < num; ++i) {
  requester.send(text);
}

