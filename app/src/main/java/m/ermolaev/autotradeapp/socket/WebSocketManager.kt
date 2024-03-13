package m.ermolaev.autotradeapp.socket

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class WebSocketManager(serverUri: URI) : WebSocketClient(serverUri) {

    override fun onOpen(handshakedata: ServerHandshake?) {
        println("WebSocket connection opened")
        // Можно сделать что-то при успешном открытии соединения
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        println("WebSocket connection closed")
        // Можно сделать что-то при закрытии соединения
    }

    override fun onMessage(message: String?) {
        println("Received message: $message")
        // Обработка полученного сообщения
    }

    override fun onError(ex: Exception?) {
        println("Error: ${ex?.message}")
        // Обработка ошибки
    }
}
