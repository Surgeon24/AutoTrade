package m.ermolaev.autotradeapp.socket

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketManager : WebSocketListener() {
    private var webSocket: WebSocket? = null
    private var exception: Exception? = null

    fun connect() {
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://192.168.31.250:8888").build()
        webSocket = client.newWebSocket(request, this)
        Log.d("CONNECT", "connected successfully");
    }

    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        // WebSocket соединение установлено
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        exception = Exception("WebSocket connection failed", t)
        Log.e("WebSocket", "WebSocket failure: ${t.message}", t)
        webSocket.cancel()
        this.webSocket = null
    }


    override fun onMessage(webSocket: WebSocket, text: String) {
        // Получено сообщение от сервера
    }
    fun sendMessage(message: String) {
        if (webSocket == null) {
            Log.e("WebSocket", "WebSocket is null, message not sent: $message")
        } else {
            Log.d("WebSocket", "Attempting to send message: $message")
            webSocket?.send(message)
        }
    }



    fun disconnect() {
        webSocket?.cancel()
    }
}
