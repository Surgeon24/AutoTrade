package m.ermolaev.autotradeapp.login.pincode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redmadrobot.pinkman.Pinkman
import m.ermolaev.autotradeapp.login.LoginActivity

class CreatePinViewModelFactory(private val pinkman: Pinkman) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginActivity.CreatePinViewModel::class.java)) {
            return LoginActivity.CreatePinViewModel(pinkman) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
