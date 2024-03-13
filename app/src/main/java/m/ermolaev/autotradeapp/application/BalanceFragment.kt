package m.ermolaev.autotradeapp.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.socket.SendMessage
import org.json.JSONArray
import org.json.JSONObject

class BalanceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_balance, container, false)

        setStrategyData(view)

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

        print("SEND THE QUESTION")

        val argumentsArray = JSONArray()
        val pairJson = JSONObject()
        pairJson.put("method", "askAccountData")
        pairJson.put("arguments",argumentsArray)

        SendMessage().execute(pairJson.toString())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireView().findViewById<TabLayout>(R.id.menu)).getTabAt(0)?.select()
    }

    private fun setStrategyData(view: View){
        val active: TextView = view.findViewById(R.id.active)
        val accepted: TextView = view.findViewById(R.id.accepted)
        val own: TextView = view.findViewById(R.id.own)
        val textStatus: TextView = view.findViewById(R.id.text_status)
        val imageStatus: ImageView = view.findViewById(R.id.image_status)

        val appStatus = (requireActivity() as ApplicationActivity).getAppData()
        active.text = appStatus.numberActiveStrategies.toString()
        accepted.text = appStatus.numberAcceptedStrategies.toString()
        own.text = appStatus.numberOwnStrategies.toString()
        textStatus.text = appStatus.status
        if (textStatus.text.equals("Online")) {
            imageStatus.setImageResource(R.drawable.online)
        } else {
            imageStatus.setImageResource(R.drawable.offline)
        }
    }
    private fun onBalanceButtonClicked() {
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
        println("onStockButtonClicked")
        val stockFragment = StockFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, stockFragment)
//            addToBackStack(null)
            commit()
        }
    }

    private fun onBotButtonClicked() {
        println("onBotButtonClicked")
        val botFragment = BotFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, botFragment)
//            addToBackStack(null)
            commit()
        }
    }
}