package m.ermolaev.autotradeapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import m.ermolaev.autotradeapp.loginData.LoginDataSource
import m.ermolaev.autotradeapp.loginData.LoginRepository

class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}