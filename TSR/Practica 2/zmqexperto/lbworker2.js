// Router-Router Server
// Connects REQ Socket to a tcp direction introduced by user
// Expects a message from Broker, replies with a response to Broker
// At first, the server send its identity to the Broker
// Usage: node lbworker.js [url] [identity] [available] [response] [verbose]

var zmq = require('zmq');

if(process.argv.length!=7){
	console.log("Usage: node lbworker.js [url] [identity] [available] [response] [verbose]");
	process.exit(1);
}
var workerURL = process.argv[2];
var workerId = process.argv[3];
var workerAvail = process.argv[4];
var workerText = process.argv[5];
var verbose = process.argv[6]=='true';

var responder = zmq.socket('req');
responder.identity = workerId;
if(verbose){console.log("Socket identity: ",workerId);}
responder.connect(workerURL);
if(verbose){console.log("Connected to: ",workerURL);}
responder.send([workerAvail,'',workerAvail]);
if(verbose){console.log("Sent: [workerAvail,'',workerAvail]");}

responder.on('message', function() {
	var args = Array.apply(null,arguments);
  	console.log('Received work %s from %s',args[2],args[0]);
  	args[2] = workerText;
  	responder.send(args);
});
