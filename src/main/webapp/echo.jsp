<!DOCTYPE HTML>
<html> 
<head> 
<title>Web Socket Echo Test</title> 
<script> 
        var ws = null;  
        var count = 0;  
        function setConnected(connected) {  
            document.getElementById('connect').disabled = connected;  
            document.getElementById('disconnect').disabled = !connected;  
            document.getElementById('echo').disabled  = !connected;  
        }  
   
        function connect() {  
            var target = document.getElementById('target').value;  
            if (target == '') {  
                alert('Please select server side connection implementation.');  
                return;  
            }  
   
            if ('WebSocket' in window) {  
                ws = new WebSocket(target);  
            } else if ('MozWebSocket' in window) {  
                ws = new MozWebSocket(target);  
            } else {  
                alert('WebSocket is not supported by this browser.');  
                return;  
            }  
               
            ws.onopen = function () {  
                setConnected(true);  
                log('Info: WebSocket connection opened.');  
            };  
            ws.onmessage = function (event) {  
                log('Received: ' + event.data);  
                if(event.data instanceof ArrayBuffer)  
                {  
                    var bytes = new Uint8Array(event.data);  
                    alert(bytes.length + " : " + bytes[0]);  
                }  
            };  
            ws.onclose = function (event) {  
                setConnected(false);  
                log('Info: WebSocket connection closed, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));  
            };  
               
        }  
   
        function disconnect() {  
            if (ws != null) {  
                ws.doClose();  
                ws = null;  
            }  
            setConnected(false);  
        }  
   
        function echo() {  
            if (ws != null) {  
                var message = document.getElementById('message').value;  
                log('Sent: ' + message);  
                ws.send(JSON.stringify({'textMessage': message}));   
                count++  
            } else {  
                alert('WebSocket connection not established, please connect.');  
            }  
        }  
   
        function log(message) {  
            var echomsg = document.getElementById('echomsg');  
            var p = document.createElement('p');  
            p.style.wordWrap = 'break-word';  
            p.appendChild(document.createTextNode(message));  
            echomsg.appendChild(p);  
            while (echomsg.childNodes.length > 25) {  
                echomsg.removeChild(console.firstChild);  
            }  
            echomsg.scrollTop = console.scrollHeight;  
        }  
   
        document.addEventListener("DOMContentLoaded", function() {  
            // Remove elements with "noscript" class - <noscript>is not allowed in XHTML  
            var noscripts = document.getElementsByClassName("noscript");  
            for (var i = 0; i < noscripts.length; i++) {  
                noscripts[i].parentNode.removeChild(noscripts[i]);  
            }  
        }, false);  
</script> 
</head> 
<body> 
    <div> 
        <h4>URL - ws://localhost:8080/attendance-webapp/echo</h4> 
        <input id="target" type="text" size="40" style="width: 350px" /> 
    </div> 
    <div> 
        <button id="connect" onclick="connect();">Connect</button> 
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button> 
    </div> 
    <div> 
        <textarea id="message" style="width: 350px">Here is a message!</textarea> 
    </div> 
    <div> 
        <button id="echo" onclick="echo();" disabled="disabled">Echo message</button> 
    </div> 
    <div id="echomsg"> 
    </div> 
</body> 
</html>