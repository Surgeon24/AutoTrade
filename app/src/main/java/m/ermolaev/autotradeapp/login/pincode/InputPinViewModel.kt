package m.ermolaev.autotradeapp.login.pincode

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redmadrobot.pinkman.Pinkman

class InputPinViewModel(private val pinkman: Pinkman) : Fragment() {

    val pinIsValid = MutableLiveData<Boolean>()

    fun validatePin(pin: String) {
        pinIsValid.value = pinkman.isValidPin(pin)
    }
}