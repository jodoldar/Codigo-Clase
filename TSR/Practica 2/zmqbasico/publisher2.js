var zmq = require('zmq')
var publisher = zmq.socket('pub')

if(process.argv.length!=6){
  console.log("Usage: node publisher2 port msgs tipo1 tipo2");
  process.exit(0);
}

var port = process.argv[2];
var msgs = process.argv[3];
var tipo1 = process.argv[4];
var tipo2 = process.argv[5];

publisher.bind('tcp://*:'+port, function(err) {
  if(err)
    console.log(err)
  else
    console.log("Listening on " + port);
});

for (var i=1 ; i<=msgs ; i++)
    setTimeout(function() {
        console.log('sent');
        publisher.send(tipo1+"Hello there! Tipo1");
        publisher.send(tipo2+"Hello there! Tipo2");
    }, 1000 * i)

process.on('SIGINT', function() {
  publisher.close()
  console.log('\nClosed')
})
