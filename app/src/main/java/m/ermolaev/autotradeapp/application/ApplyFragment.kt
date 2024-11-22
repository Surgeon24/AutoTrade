package m.ermolaev.autotradeapp.application

import android.content.Context
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import m.ermolaev.autotradeapp.R

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import m.ermolaev.autotradeapp.socket.WebSocketManagerSingleton
import org.json.JSONArray
import org.json.JSONObject

class ApplyFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_apply, container, false)
        var editText = rootView.findViewById<EditText>(R.id.editTextText)
        val editNumber = rootView.findViewById<EditText>(R.id.editTextNumber)
        val button = rootView.findViewById<Button>(R.id.button)

        val ticker = arguments?.getString("ticker")
        rootView.findViewById<EditText>(R.id.editTextText)?.setText(ticker)

        button.setOnClickListener {
            val symbol = editText.text.toString()
            val strategyId = editNumber.text.toString()
            val currentThread = (requireActivity() as ApplicationActivity).getCurrentThread()
            val pairJson = JSONObject()
            var botId = getBotId()
            incrementBotId()

            pairJson.put("method", "startStrategy")
            val argumentsArray = JSONArray()
            argumentsArray.put(symbol)
            argumentsArray.put(strategyId)
            argumentsArray.put(botId)
            pairJson.put("arguments", argumentsArray)

            WebSocketManagerSingleton.webSocketManager.sendMessage(pairJson.toString())
//            (requireActivity() as ApplicationActivity).setCurrentThread(currentThread+1)
            ApplicationActivity.activeStrategyList.add(Bot(botId, symbol, strategyId))
            editText.text.clear()
            editNumber.text.clear()

            val appData = (requireActivity() as ApplicationActivity).getAppData()
            (requireActivity() as ApplicationActivity).setAppDataStatus("Online")
            (requireActivity() as ApplicationActivity).setAppDataActive(appData.numberActiveStrategies + 1)
            (requireActivity() as ApplicationActivity).setAppDataAccepted(appData.numberAcceptedStrategies + 1)
            Toast.makeText(requireContext(), "Strategy was applied", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()

            Log.d("JSON", pairJson.toString())
        }

        return rootView
    }

    private fun getBotId(): Int {
        val prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return prefs.getInt("botId", 100) // Начальное значение - 100
    }

    private fun incrementBotId() {
        val prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        var botId = getBotId() + 1
        if (botId >= 99999) botId = 100 // Сброс, если botId достигнет 99999
        prefs.edit().putInt("botId", botId).apply()
    }
}

