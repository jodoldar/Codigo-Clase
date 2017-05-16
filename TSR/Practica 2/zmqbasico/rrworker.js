// Hello World server in Node.js
// Connects REP socket to tcp://*:5560
// Expects "Hello" from client, replies with "World"

var zmq = require('zmq');
var responder = zmq.socket('rep');

responder.connect('tcp://localhost:8005');
responder.on('message', function(msg) {
  console.log('received request:', msg.toString());
  setTimeout(function() {
    responder.send("World");
  }, 1000);
});