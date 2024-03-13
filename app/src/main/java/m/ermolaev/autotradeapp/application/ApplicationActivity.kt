package m.ermolaev.autotradeapp.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.SocketManager

data class Bot(
    val id: Int,
    val symbol: String,
    val strategy: String
)
data class AppData(
    var status: String = "Offline",
    var numberActiveStrategies: Int = 0,
    var numberAcceptedStrategies: Int = 0,
    var numberOwnStrategies: Int = 0,
    var currentThread: Int = 0
)

class ApplicationActivity : AppCompatActivity() {
    private val socketManager = SocketManager()
    private var appData = AppData()

    companion object {
        val activeStrategyList = mutableListOf<Bot>()
    }

    fun findStrategy(id: Int): Bot? {
        for (s in activeStrategyList)
            if (s.id == id)
                return s
        return null
    }
    // Method to retrieve the status
    fun getAppData(): AppData {
        return appData
    }

    fun getCurrentThread(): Int {
        return appData.currentThread
    }
    fun setAppDataStatus(s: String){
        appData.status = s
    }
    fun setAppDataActive(n: Int){
        appData.numberActiveStrategies = n
    }
    fun setAppDataAccepted(n: Int){
        appData.numberAcceptedStrategies = n
    }
    fun setAppDataOwn(n: Int){
        appData.numberOwnStrategies = n
    }

    fun setCurrentThread(n: Int){
        appData.currentThread = n
    }

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