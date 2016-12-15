var zmq = require('zmq')
var subscriber = zmq.socket('sub')

if(process.argv.length!=4){
	console.log("Usage: node subscriber2 port tipo");
	process.exit(0);
}

var url = process.argv[2];
var tipo = process.argv[3];

subscriber.on("message", function(reply) {
  console.log('Received message: ', reply.toString());
})

subscriber.connect(url);
subscriber.subscribe(tipo);

process.on('SIGINT', function() {
  subscriber.close()
  console.log('\nClosed')
})
