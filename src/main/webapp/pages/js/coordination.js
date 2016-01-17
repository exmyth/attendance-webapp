var name = 'admin';
var coordinationId = 1;
var fileId = 1;
var stompClient = null;
//连接服务器的函数
function connect() {
    var socket = new SockJS('/coordination');
    stompClient = Stomp.over(socket);
    stompClient.connect('', '', function (frame) {
        console.log('Connected: ' + frame);
        //用户聊天订阅
        stompClient.subscribe('/userChat/chat' + coordinationId, function (chat) {
            showChat(JSON.parse(chat.body));
        });
        //初始化文档
        stompClient.subscribe('/app/initDocument/' + coordinationId+'/' + fileId, function (initData) {
            console.log(initData);
            var body = JSON.parse(initData.body);
            content = body.document.content;
            var chat = body.chat;
            editor.setContent(content);
            chat.forEach(function(item) {
                showChat(item);
            });
        });
    }, function () {
        //connect();
    });
}

//发送聊天信息
function sendName() {
    var input = $('#content');
    var inputValue = input.val();
    input.val("");
    stompClient.send("/app/userChat", {}, JSON.stringify({
        'name': encodeURIComponent(name),
        'chatContent': encodeURIComponent(inputValue),
        'coordinationId': coordinationId
    }));
}

//显示聊天信息
function showChat(message) {
    var response = document.getElementById('message');
    response.value += decodeURIComponent(message.name) + ':' + decodeURIComponent(message.chatContent) + '\n';
}

$(function () {
    connect();
});