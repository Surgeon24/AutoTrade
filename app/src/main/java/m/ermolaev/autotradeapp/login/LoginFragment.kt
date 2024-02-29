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
import m.ermolaev.autotradeapp.login.pincode.CreatePinFragment

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Find the TextView
        val passwordRecoveryTextView: TextView = view.findViewById(R.id.clickable_passwordRecovery)
        val registrationTextView: TextView = view.findViewById(R.id.clickable_signUp)
        val loginButton: Button = view.findViewById(R.id.login)
        val pincodeTextView: TextView = view.findViewById(R.id.clickable_pincode)

        // Set click listener
        passwordRecoveryTextView.setOnClickListener { onForgotPasswordClicked() }
        registrationTextView.setOnClickListener { onRegistrationClicked() }
        loginButton.setOnClickListener { onLoginClicked() }
        pincodeTextView.setOnClickListener { onLoginByPincodeClicked() }

        return view
    }
    private fun onForgotPasswordClicked() {
        val passwordRecoveryFragment = PasswordRecoveryFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, passwordRecoveryFragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun onLoginByPincodeClicked() {
        val pincodeFragment = CreatePinFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, pincodeFragment)
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