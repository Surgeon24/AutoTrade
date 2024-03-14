package m.ermolaev.autotradeapp.socket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketManager : WebSocketListener() {
    private var webSocket: WebSocket? = null
    private var exception: Exception? = null

    fun connect() {
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://192.168.56.1:8888").build()
        webSocket = client.newWebSocket(request, this)
    }

    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        // WebSocket соединение установлено
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        // Произошла ошибка при установке WebSocket соединения
        exception = Exception("WebSocket connection failed", t)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        // Получено сообщение от сервера
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun disconnect() {
        webSocket?.cancel()
    }
}
