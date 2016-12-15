// Router-Router Broker
// Expects messages from Client and Worker
// Usage: node lbbroker.js [frontend] [backend] [verbose]

var zmq = require('zmq');

if(process.argv.length!=5){
	console.log("Usage: node lbbroker.js [frontend] [backend] [verbose]");
	process.exit(1);
}
var frontPort = process.argv[2];
var backPort = process.argv[3];
var verbose = process.argv[4]=='true';
var workers = [];
var queue = [];

var frontend = zmq.socket('router');
frontend.bindSync('tcp://*:'+frontPort);

frontend.on('message', function() {
	var args = Array.apply(null,arguments);
	if(verbose){console.log("Mensaje: ",args.toString());}
  	send(args);
});

var backend = zmq.socket('router');
backend.bindSync('tcp://*:'+backPort);

backend.on('message', function() {
  	var args = Array.apply(null, arguments);
  	if(verbose){console.log("Mensaje: ", args.toString());}
  	if(args[2]=='READY'){
  		workers.push(args[0]);
  		if(queue.length!=0){
  			send('');
  		}
  	}else{
  		workers.push(args[0]);
  		args.shift(); args.shift();
  		frontend.send(args);
  		if(queue.length!=0){
  			send('');
  		}
  	}
  
});

send = function(args){
	if(workers.length>0){
  		if(queue.length==0){
  			args.unshift('');
  			args.unshift(workers.shift());
  			backend.send(args);
  		}else{
  			queue.push(args);
  			args2 = queue.shift();
  			args2.unshift();
  			args2.unshift(workers.shift());
  			backend.send(args2);
  		}
  	}else{
  		queue.push(args);
  	}
}
