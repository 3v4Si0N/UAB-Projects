var chatEndpoint = "ws://localhost:8080/P2PChat/chat/";
var ws;
var channel;


function initiateChatSession() {
	console.log("In initiateChatSession");
	if ("WebSocket" in window) {
		// Let us open a web socket
		channel = $("#channelID").val();
		chatEndpoint += channel;
		
		ws = new WebSocket(chatEndpoint);
		ws.onopen = function(evt) {onOpen(evt);};
		ws.onmessage = function(evt) {onMessage(evt);}; 
		ws.close = function(evt) { onClose(evt);};
		ws.onerror = function(evt) { onError(evt);};
	} else {
		// The browser doesn't support WebSocket
		alert("WebSocket NOT supported!");
	}
}
 
function onOpen(evt){
	console.log("On Open");
}

function onClose(evt){	
	console.log("On Close");
}

function onMessage(evt){
	console.log("In onMessage");
	var msg = evt.data;
	
	switch (msg) {
		case ":>refresh":
			console.log("MESSAGE refresh RECEIVED");
			$("#refresh-convs").click();
			break;
		case ":>notification":
			console.log("MESSAGE notification RECEIVED");
			$("#add-notification").click();
			break;
	
		default:
			console.log("CLIENT MESSAGE RECEIVED");
			console.log("message received --> " + msg);
			addMessageToHistory(msg + ":>oth");
			break;
	}
}

function addMessageToHistory(msg) {
	console.log("In addMessageToHistory");
	
	var clientType = (msg.split(":>")[0] == "true" ||
			msg.split(":>")[0] == "operator") ? "operator" : "user";
	var getFrom = msg.split(":>")[1];
	var message = msg.split(":>")[2];
	var owner = (msg.split(":>")[3] == "own") ? "justify-content-end" : "";
	var inverse = (owner == "justify-content-end") ? "order-inverse" : "";
	
	if (message.length > 0) {
		var history = $("#history").html();
		var contentMessage = '<div class="row ' + owner + ' chat ' + clientType +'">' + 
							 	'<img src="resources/img/' + clientType +'.png" class="client-photo">' +
							 	'<span class="chat-message ' + inverse + '">'+ message +'</span>' +
							 '</div>';
		
		$("#history").html(history + contentMessage);
		$("#history").scrollTop(9999999999999999999);
	}
}

/**
 * This function is called on a connection error.
 * @param evt the event that contains the error.
 */
function onError(evt){
	ws.close();
}

function sendChatMessage() {
	console.log("In sendChatMessage");
	
	if(ws != null) {
		var messageToSend = $("#messageToSend").val();
		var username = $("#username").val();
		var clientType = $("#clientType").val();
		
		ws.send(messageToSend);
		document.getElementById("messageToSend").value = "";
		addMessageToHistory(clientType + ":>" + username + ":>" + messageToSend + ":>own");
	} else {
		console.log("ERROR!! There is no established connection");
		alert('ERROR!! Please initiate session');
	}
}

window.onload = function(){
	console.log("onload");
	initiateChatSession();};
	
