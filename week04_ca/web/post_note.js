$(function () {

    var writeToChatboard = function (text) {
        $("#postarea").val(text + "\n" + $("#postarea").val());
    };

    var url = "ws://" + document.location.host + document.location.pathname +"displayupdate";
    var socket = new WebSocket(url);

    socket.onmessage = function (evt) {
        var msg = JSON.parse(evt.data);
        writeToChatboard("title:" + msg.title + ",date:" + msg.timestamp + ",content:" + msg.message + "category:" + msg.category);
    };
    
    socket.onopen = function (msg) {
        alert(msg.data);
        writeToChatboard("Connected to post server");
    };
    
    socket.onclose = function (msg) {
        alert(msg.data);
        writeToChatboard("Disconnected from chat server");
    };
    
    socket.onerror = function(evt){
        writeToChatboard("error:"+evt.data);
    };

});