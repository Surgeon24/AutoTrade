package m.ermolaev.autotradeapp.application

import android.content.Context
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import m.ermolaev.autotradeapp.R

import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import m.ermolaev.autotradeapp.socket.WebSocketManagerSingleton
import org.json.JSONArray
import org.json.JSONObject

class ApplyFragment() : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_apply, container, false)
        var symbolSelector = rootView.findViewById<TextView>(R.id.symbolSelector)
        val strategySelector = rootView.findViewById<TextView>(R.id.strategySelector)
        val button = rootView.findViewById<Button>(R.id.button)

        val symbols = ArrayList<String>()
        sharedViewModel.stocks.observe(viewLifecycleOwner) { stocks ->
            stocks.forEach { stock ->
                symbols.add(stock.name)
            }
        }
        symbolSelector.setOnClickListener {
            showSingleChoiceDialog("Select a symbol", symbols) { selectedOption ->
                symbolSelector.text = selectedOption
            }
        }

        val strats= ArrayList<String>()
        sharedViewModel.strategies.observe(viewLifecycleOwner) { strategies ->
            strategies.forEach { strategy ->
                strats.add(strategy.name)
            }
        }

        strategySelector.setOnClickListener {
            showSingleChoiceDialog("Select a strategy", strats) { selectedOption ->
                strategySelector.text = selectedOption
            }
        }

        button.setOnClickListener {
            val symbol = symbolSelector.text.toString()
            val strategyId = strategySelector.text.toString()
//            val currentThread = (requireActivity() as ApplicationActivity).getCurrentThread()
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
            symbolSelector.clearComposingText()
            strategySelector.clearComposingText()

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

    private fun showSingleChoiceDialog(title: String, options: ArrayList<String>, onOptionSelected: (String) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setItems(options.toTypedArray()) { _, which ->
            onOptionSelected(options[which])
        }
        builder.show()
    }

    private fun getBotId(): Int {
        val prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return prefs.getInt("botId", 100) // start value - 100
    }

    private fun incrementBotId() {
        val prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        var botId = getBotId() + 1
        if (botId >= 99999) botId = 100 // drop, if the value reach 99999
        prefs.edit().putInt("botId", botId).apply()
    }
}

