package m.ermolaev.autotradeapp.socket

import android.os.AsyncTask
import org.json.JSONObject
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

class SendMessage : AsyncTask<Pair<String, Int>, Void, Void>() {
    private var exception: Exception? = null

    override fun doInBackground(vararg params: Pair<String, Int>): Void? {
        try {
            try {
                val socket = Socket("192.168.56.1", 8888)
                val outToServer = PrintWriter(
                    OutputStreamWriter(
                        socket.getOutputStream()
                    )
                )
                // Serialize the pair to JSON before sending
                val pairJson = JSONObject()
                pairJson.put(params[0].first, "string")
                pairJson.put(params[0].second.toString(), "integer")
                outToServer.println(pairJson.toString())
                outToServer.flush()
                socket.close() // Close the socket after sending
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            exception = e
            return null
        }
        return null
    }
}