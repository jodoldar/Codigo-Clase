// Router-Router Client
// Connects REQ Socket to a tcp direction introduced by user
// Sends a message to server, expects response back
// Invocation: node lbclient.js [url] [identity] [text]
var zmq = require('zmq');

if(process.argv.length!=5){
	console.log("Correct usage: node lbclient.js [url] [identity] [text]");
	process.exit(1);
}
var clientURL = process.argv[2];
var clientId = process.argv[3];
var clientText = process.argv[4];

var requester = zmq.socket('req');
requester.identity = clientId;
requester.connect(clientURL);

requester.on('message', function(msg) {
  	console.log("Got reply: ", msg.toString());
  	process.exit(0);
});

requester.send(clientText);


