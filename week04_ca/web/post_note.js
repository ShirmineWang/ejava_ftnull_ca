$(function () {

    var writeToChatboard = function (text) {
        $("#postarea").val(text + "\n" + $("#postarea").val());
    };

    var url = "ws://" + document.location.host + document.location.pathname + "displayupdate";
    var socket = new WebSocket(url);

    socket.onmessage = function (msg) {
        writeToChatboard("title:" + msg.title + ",date:" + msg.timestamp + ",content:" + msg.message + "category:" + msg.category);
    };

    socket.onopen = function () {
        writeToChatboard("Connected to post server");
    };

    socket.onclose = function () {
        writeToChatboard("Disconnected from post server");
    };

    socket.onerror = function (evt) {
        writeToChatboard("error:" + evt.data);
    };

});