package m.ermolaev.autotradeapp.application

import android.app.AlertDialog
import android.widget.Toast
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.socket.WebSocketManagerSingleton
import org.json.JSONArray
import org.json.JSONObject

class BotFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bot, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val stopAllButton = view.findViewById<Button>(R.id.stop_all_button)

        stopAllButton.setOnClickListener {
            onStopButtonClicked()
        }
        val strategiesList = ArrayList<Bot>()
        for (s in ApplicationActivity.activeStrategyList)
            strategiesList.add(s)

        val adapter = BotListAdapter(strategiesList, ApplicationActivity())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val tabLayout: TabLayout = view.findViewById(R.id.menu)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: return
                when (position) {
                    0 -> onBalanceButtonClicked()
                    1 -> onStrategyButtonClicked()
                    2 -> onStockButtonClicked()
                    3 -> onBotButtonClicked()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) { // Не используется
            }
            override fun onTabReselected(tab: TabLayout.Tab?) { // Не используется
            }
        })

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireView().findViewById<TabLayout>(R.id.menu)).getTabAt(3)?.select()
    }


    private fun onBalanceButtonClicked() {
        val balanceFragment = BalanceFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, balanceFragment)
//            addToBackStack(null)
            commit()
        }
    }
    private fun onStrategyButtonClicked() {
        val strategyFragment = StrategyFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, strategyFragment)
//            addToBackStack(null)
            commit()
        }
    }
    private fun onStockButtonClicked() {
        val stockFragment = StockFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, stockFragment)
//            addToBackStack(null)
            commit()
        }
    }
    private fun onBotButtonClicked() {

    }



    private fun onStopButtonClicked() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to stop all bots?")

        builder.setPositiveButton("Yes") { dialog, _ ->
            stopAllBots()
            updatePage()
            dialog.dismiss()
            Toast.makeText(requireContext(), "All bots stopped!", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun stopAllBots() {
        ApplicationActivity.activeStrategyList.clear() // Очищаем список

        // Отправляем сигнал на сервер
        val pairJson = JSONObject()
        pairJson.put("method", "stopAllStrategies")
        val argumentsArray = JSONArray()
        pairJson.put("arguments", argumentsArray)
        WebSocketManagerSingleton.webSocketManager.sendMessage(pairJson.toString())
    }

    private fun updatePage() {
        val botFragment = BotFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, botFragment)
//            addToBackStack(null)
            commit()
        }
    }
}