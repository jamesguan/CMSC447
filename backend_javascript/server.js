var WebSocketServer = require('websocket').server;
var http = require('http');

var server = http.createServer(function (request, response) {});
console.log("Server running");
server.listen(13379, function () {});

var wsServer = new WebSocketServer({
 httpServer: server
});

var connection;
 wsServer.on('request', function (request) {
 console.log("Connection made");
 connection = request.accept(null, request.origin);
 connection.on('close', function (connect) {
  console.log("Connection Closed");
 });

 connection.on('message', function(message) {
  try {
   console.log(message);
   var json = JSON.parse(message.data || message.utf8Data);
   console.log('JSON:' );
   console.log(json);
   /*
   console.log('Message: ');
   console.log(message.utf8Data);
   console.log(message.data);
   */
   if (json.hasOwnProperty('username'))
   {
     checkCredentials(json.username, json.password);
   } else if (json.hasOwnProperty('filename')){
     checkUpload(json);
   }

  } catch (e ) {
   console.log(e.message);
   return;
  }
 });
});

function checkCredentials (user, pass){
  console.log("sending credentials");
  if(user == 'nicholas' && pass == 'nicholas123'){
    connection.send(JSON.stringify({id:1221313, valid:true}));
  } else {
    connection.send(JSON.stringify({id:0, valid:false}));
  }
}
function checkUpload(json){

}
