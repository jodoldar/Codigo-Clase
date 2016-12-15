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
  		workers.push([args[0],'READY',args[3]]);
  		if(queue.length!=0){
  			send('');
  		}
  	}else{
  		workers.push([args[0],'READY',args[2]]);
  		args.shift(); args.shift(); args.shift();
  		frontend.send(args);
  		if(queue.length!=0){
  			send('');
  		}
  	}
  
});

var backendAux = zmq.socket('router');
backendAux.bindSync('tcp://*:'+backPort+1);

backendAux.on('message',function(){
	//Buscar en la lista quien manda el mensaje y asignarle la carga recibida
});

setInterval(function () {
	for (var i in workers){
		backendAux.send([workers[i],'','STATE']);
	}
},2000);

send = function(args){
	if(workers.length>0){
  		if(queue.length==0){
  			args.unshift('');
  			var temp=1000; var sender = 0;
  			for (var i in workers){
  				if(workers[i][1]=='READY'){
  					if(workers[i][2]<temp){
  						temp = workers[i][2];
  						sender = i;
  					}
  				}
  			}
  			workers[sender][1] = 'BUSY';
  			args.unshift(workers[sender][0]);
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
