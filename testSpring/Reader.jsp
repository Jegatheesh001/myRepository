<script type="text/javascript">
	var wsocket;
	function connect() {
		wsocket = new WebSocket(
				"ws://localhost:8080/testSpring/ws/civilIdReader");
		// wsocket = new WebSocket("ws://192.168.0.147:9090");
		// wsocket = new WebSocket("wss://echo.websocket.org");
		wsocket.onopen = onOpen;
		wsocket.onmessage = function(evt) {
			onMessage(evt)
		};
		wsocket.onclose = function(evt) {
			onClose(evt)
		};
		wsocket.onerror = function(evt) {
			onError(evt)
		};
	}

	function onOpen(event) {
		// Display user friendly messages for the successful establishment of connection
		writeToScreen("Connection established");
		text = {
			"user" : "Jegatheesh",
			"connect" : "yes"
		}
		wsocket.send(JSON.stringify(text));
	}

	function onMessage(evt) {
		var msgObj = JSON.parse(evt.data);
		writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data
				+ '</span>');
	}

	function onClose(evt) {
		writeToScreen("DISCONNECTED");
	}

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	function writeToScreen(message) {
		console.log(message);
		var pre = document.createElement("p");
		pre.innerHTML = message;
		document.getElementById("contents").appendChild(pre);
	}
	window.addEventListener("load", connect, false);

	function eventLoad() {
		document.getElementById("read").style.display = "none";
		setTimeout(function() {
			connect();
		}, 1000);
	}
</script>
<a id="read" href="JnlpFile.jsp?username=1234" onclick="eventLoad()">Read</a>
<label id="contents"></label>