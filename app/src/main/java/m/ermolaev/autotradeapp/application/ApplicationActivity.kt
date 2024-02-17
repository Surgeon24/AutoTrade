package m.ermolaev.autotradeapp.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.login.LoginFragment

class ApplicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        val actionBar = supportActionBar
        actionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        actionBar?.setCustomView(R.layout.action_bar)

        loadBalanceFragment()
    }

    //change!
    private fun loadBalanceFragment() {
        val balanceFragment = BalanceFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, balanceFragment)
            commit()
        }
    }

}