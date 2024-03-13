package m.ermolaev.autotradeapp.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import m.ermolaev.autotradeapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StockFragment : Fragment(), OnTradeClickListener {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stock, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        val dataList = ArrayList<StockData>()
        dataList.add(StockData("Apple Inc", "AAPL","designs, manufactures and markets smartphones", "180"))
        dataList.add(StockData("NVIDIA Corp.", "NVDA", "Key innovator of computer graphics and AI technology", "776"))
        dataList.add(StockData("Stock 3","TKT", "Description of the Stock 3", "90"))

        val adapter = StockListAdapter(dataList, this)
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
            override fun onTabUnselected(tab: TabLayout.Tab?) { // Not used
            }
            override fun onTabReselected(tab: TabLayout.Tab?) { // Not used
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireView().findViewById<TabLayout>(R.id.menu)).getTabAt(2)?.select()
    }

    override fun onTradeClick(ticker: String) {
        val applyFragment = ApplyFragment()
        val bundle = Bundle()
        bundle.putString("ticker", ticker)
        applyFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, applyFragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StockFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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
    }
    private fun onBotButtonClicked() {
        val botFragment = BotFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, botFragment)
//            addToBackStack(null)
            commit()
        }
    }
}