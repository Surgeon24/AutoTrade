package m.ermolaev.autotradeapp.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.SocketManager

class ApplicationActivity : AppCompatActivity() {
    private val socketManager = SocketManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        val actionBar = supportActionBar
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar)

        socketManager.connect()

        loadBalanceFragment()
    }

    private fun loadBalanceFragment() {
        val balanceFragment = BalanceFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, balanceFragment)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketManager.disconnect()
    }
}