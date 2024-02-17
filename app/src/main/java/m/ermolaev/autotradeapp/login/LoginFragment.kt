package m.ermolaev.autotradeapp.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.application.ApplicationActivity
import m.ermolaev.autotradeapp.application.BalanceFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Find the TextView
        val passwordRecoveryTextView: TextView = view.findViewById(R.id.clickable_passwordRecovery)
        val registrationTextView: TextView = view.findViewById(R.id.clickable_signUp)
        val loginButton: Button = view.findViewById(R.id.login)

        // Set click listener
        passwordRecoveryTextView.setOnClickListener { onForgotPasswordClicked() }
        registrationTextView.setOnClickListener { onRegistrationClicked() }
        loginButton.setOnClickListener { onLoginClicked() }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun onForgotPasswordClicked() {
        val passwordRecoveryFragment = PasswordRecoveryFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, passwordRecoveryFragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun onRegistrationClicked() {
        val registrationFragment = RegistrationFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, registrationFragment)
            addToBackStack(null)
            commit()
        }
    }


    private fun onLoginClicked() {
        val intent = Intent(requireContext(), ApplicationActivity::class.java)
        this.startActivity(intent)
    }
}