// Simple request-reply broker in Node.js

var zmq = require('zmq');
var frontend = zmq.socket('router');
var backend = zmq.socket('dealer');

if(process.argv.length!=4){
	console.log("Usage: node rrbroker2.js cport sport");
	process.exit(0);
}

var cport = process.argv[2];
var sport = process.argv[3];

frontend.bindSync('tcp://*:' + cport);
backend.bindSync('tcp://*:' + sport);

frontend.on('message', function() {
  // Note that separate message parts come as function arguments.
  var args = Array.apply(null, arguments);
  // Pass array of strings/buffers to send multipart messages.
  backend.send(args);
});

backend.on('message', function() {
  var args = Array.apply(null, arguments);
  frontend.send(args);
});
