// Simple request-reply broker in Node.js

var zmq = require('zmq');
var frontend = zmq.socket('router');
var backend = zmq.socket('dealer');

frontend.bindSync('tcp://*:8000');
backend.bindSync('tcp://*:8005');

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
