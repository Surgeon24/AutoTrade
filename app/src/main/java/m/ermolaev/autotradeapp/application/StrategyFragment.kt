package m.ermolaev.autotradeapp.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import m.ermolaev.autotradeapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class StrategyFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_strategy, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val dataList = ArrayList<StrategyData>()
        dataList.add(StrategyData("Strategy 1", "Description of the strategy 1"))
        dataList.add(StrategyData("Strategy 2", "Description of the strategy 2"))
        dataList.add(StrategyData("Strategy 3", "Description of the strategy 3"))


        val adapter = StrategyListAdapter(dataList)
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
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) { // Не используется
            }
            override fun onTabReselected(tab: TabLayout.Tab?) { // Не используется
            }
        })

        return view
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StrategyFragment().apply {
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
    }
    private fun onStockButtonClicked() {
        val stockFragment = StockFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, stockFragment)
//            addToBackStack(null)
            commit()
        }
    }
}