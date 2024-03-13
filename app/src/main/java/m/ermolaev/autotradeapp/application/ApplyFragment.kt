package m.ermolaev.autotradeapp.application

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import m.ermolaev.autotradeapp.R

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.channels.ticker
import m.ermolaev.autotradeapp.socket.SendMessage
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

            val argumentsArray = JSONArray()
            argumentsArray.put(symbol)
            argumentsArray.put(strategyId)
            val currentThread = (requireActivity() as ApplicationActivity).getCurrentThread()
            argumentsArray.put(currentThread)
            val pairJson = JSONObject()
            pairJson.put("method", "startStrategy")
            pairJson.put("arguments",argumentsArray)

            SendMessage().execute(pairJson.toString())
            (requireActivity() as ApplicationActivity).setCurrentThread(currentThread+1)
            ApplicationActivity.activeStrategyList.add(Bot(currentThread, symbol, strategyId))
            editText.text.clear()
            editNumber.text.clear()

            val appData = (requireActivity() as ApplicationActivity).getAppData()
            (requireActivity() as ApplicationActivity).setAppDataStatus("Online")
            (requireActivity() as ApplicationActivity).setAppDataActive(appData.numberActiveStrategies + 1)
            (requireActivity() as ApplicationActivity).setAppDataAccepted(appData.numberAcceptedStrategies + 1)
            Toast.makeText(requireContext(), "Strategy was applied", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }

        return rootView
    }
}

