package m.ermolaev.autotradeapp.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import m.ermolaev.autotradeapp.R



class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadLoginFragment()
    }

    private fun loadLoginFragment() {
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, loginFragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun loadRegistrationFragment() {
        val registrationFragment = RegistrationFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, registrationFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun loadPasswordRecoveryFragment() {
        val passwordRecoveryFragment = PasswordRecoveryFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, passwordRecoveryFragment)
            .addToBackStack(null)
            .commit()
    }

}