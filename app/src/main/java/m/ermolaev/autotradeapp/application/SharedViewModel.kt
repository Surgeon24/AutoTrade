package m.ermolaev.autotradeapp.application

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val strategies = MutableLiveData<List<Strategy>>()
    val stocks = MutableLiveData<List<Stock>>()

    init {
        strategies.value = listOf(
            Strategy("Simple moving average strategy", "This strategy uses only one financial instrument - a moving average and determines only the general trend of a stock, based on which it gives signals to buy or sell.", 1),
            Strategy("Investment strategy", "This strategy uses several financial instruments at once - SMA RSI MACD. Each of these instruments uses an increased number of previous prices to more accurately calculate values and make decisions about buying or selling.", 2),
            Strategy("Trading strategy", "This strategy, in addition to the instruments from the second strategy, uses ADX instrument, stop-losses and take-profits. The RSI tool has been rebalanced to allow for more entries.", 3)
        )

        stocks.value = listOf(
            Stock("Apple Inc", "AAPL", "Electronic technology Telecommunication equipment", 1),
            Stock("Tesla Inc.", "TSLA", "Automobile industry", 2),
            Stock("Netflix Inc.", "NFLX","Internet services and software", 3),
            Stock("Shell PLC", "SHEL", "Energy and mineral resources, Oil industry", 4),
            Stock("Alphabet Inc (Google)", "GOOGL", "Computer technologies, Internet services", 5)
        )
    }
}

data class Stock(
    val name: String,
    val ticker: String,
    val description: String,
    val id: Int
)

data class Strategy(
    val name: String,
    val description: String,
    val id: Int
)