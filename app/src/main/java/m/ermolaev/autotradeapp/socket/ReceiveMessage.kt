package m.ermolaev.autotradeapp.socket

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket
class ReceiveMessage : AsyncTask<Void, Void, String>() {
    private var exception: Exception? = null

    override fun doInBackground(vararg params: Void): String? {
        var receivedData: String? = null
        try {
            val socket = Socket("192.168.56.1", 8888)
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            receivedData = reader.readLine() // Read the JSON string from the server
            socket.close() // Close the socket after receiving data
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return receivedData
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null) {
            try {
                // Parse the received JSON string
                val receivedJson = JSONObject(result)
                val balance = receivedJson.getDouble("balance")
                val activeStrategies = receivedJson.getJSONArray("activeStrategies")
                val acceptedStrategies = receivedJson.getJSONArray("acceptedStrategies")
                val ownStrategies = receivedJson.getJSONArray("ownStrategies")

                // Process the received data as needed
                // For example, update UI with the received data
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            // Handle case where no data is received
        }
    }
}
