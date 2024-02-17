package m.ermolaev.autotradeapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import m.ermolaev.autotradeapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PasswordRecoveryFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_password_recovery, container, false)
        // Find the TextView
        val loginTextView: TextView = view.findViewById(R.id.clickable_login)
        // Set click listener
        loginTextView.setOnClickListener { onLoginClicked() }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PasswordRecoveryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun onLoginClicked() {
        val loginFragment = LoginFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, loginFragment)
            commit()
        }
    }
}