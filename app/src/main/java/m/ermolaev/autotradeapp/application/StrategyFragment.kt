package m.ermolaev.autotradeapp.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import m.ermolaev.autotradeapp.R

class StrategyFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val strategiesList = ArrayList<StrategyData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_strategy, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val adapter = StrategyListAdapter(strategiesList)
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
        (requireView().findViewById<TabLayout>(R.id.menu)).getTabAt(1)?.select()

        sharedViewModel.strategies.observe(viewLifecycleOwner) { strategies ->
            strategies.forEach { strategy ->
                strategiesList.add(StrategyData(strategy.name, strategy.description))
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
    private fun onBotButtonClicked() {
        val botFragment = BotFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, botFragment)
//            addToBackStack(null)
            commit()
        }
    }
}