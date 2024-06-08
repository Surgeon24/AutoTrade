package m.ermolaev.autotradeapp.socket

object WebSocketManagerSingleton {
    val webSocketManager: WebSocketManager by lazy { WebSocketManager() }
}
