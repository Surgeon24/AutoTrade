package m.ermolaev.autotradeapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import m.ermolaev.autotradeapp.R


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        // Find the TextView
        val loginTextView: TextView = view.findViewById(R.id.clickable_login)
        // Set click listener
        loginTextView.setOnClickListener { onLoginClicked() }

        return view
    }

    private fun onLoginClicked() {
        val loginFragment = LoginFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, loginFragment)
            commit()
        }
    }
}