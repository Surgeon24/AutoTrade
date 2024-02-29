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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ApplyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
            SendMessage().execute(Pair(editText.text.toString(), editNumber.text.toString().toInt()))
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

