var net = require('net');

var PROG_PORT = 8000;
var LOCAL_IP = '127.0.0.1';
var LOCAL_PORTS = [8001,8002,8003,8004,8005,8006,8007,8008];
var REMOTE_IP = ['158.42.184.5','158.42.4.23','216.58.210.163',
	'158.42.156.2','147.156.222.65','127.0.0.1',
	'127.0.0.1','127.0.0.1'];
var REMOTE_PORTS = [80,80,443,80,80,80,80,80];
var servers = [];

for(var i=0; i<LOCAL_PORTS.length; i++){
	servers[i] = net.createServer(function(x){ 
			return function(socket){
				socket.on('data', function(msg){
					var serviceSocket = new net.Socket();

					serviceSocket.connect(REMOTE_PORTS[i],REMOTE_IP[i],function(){
						serviceSocket.write(msg);
						console.log(msg);
					});

					serviceSocket.on('data', function(data){
						socket.write(data);
						console.log(msg);
					});
				});
			}	
	}(i)).listen(LOCAL_PORTS[i],LOCAL_IP);
	console.log('Server iniciado en %d', LOCAL_PORTS[i]);
}

var serverProg = net.createServer(function(socket){
	socket.on('data', function(data){
		var jData = JSON.parse(data);
		if(jData['op']=='set'){
			for(var i=0; i<LOCAL_PORTS.length; i++){
				if(LOCAL_PORTS[i] == jData['inPort']){
					 REMOTE_PORTS[i] = jData['remote']['port'];
					 REMOTE_IP[i] = jData['remote']['ip'];
				}
			}
		}
	})
}).listen(8000,LOCAL_IP);