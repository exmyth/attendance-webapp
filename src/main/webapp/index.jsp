<script type="text/javascript"
	src="http://localhost:8080/Origami/websocket/sockjs-0.3.min.js"></script>
<script>
            var websocket;
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://localhost:8080/Origami/webSocketServer");
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://localhost:8080/Origami/webSocketServer");
            } else {
                websocket = new SockJS("http://localhost:8080/Origami/sockjs/webSocketServer");
            }
            websocket.onopen = function (evnt) {
            };
            websocket.onmessage = function (evnt) {
                $("#msgcount").html("(<font color='red'>"+evnt.data+"</font>)")
            };
            websocket.onerror = function (evnt) {
            };
            websocket.onclose = function (evnt) {
            }
 
        </script>
