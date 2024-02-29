package m.ermolaev.autotradeapp.login.pincode

import android.inputmethodservice.KeyboardView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import m.ermolaev.autotradeapp.R
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.redmadrobot.pinkman_ui.PinView
import m.ermolaev.autotradeapp.login.LoginActivity
import m.ermolaev.autotradeapp.login.LoginFragment

class CreatePinFragment : Fragment() {

//    private val viewModel: LoginActivity.CreatePinViewModel by viewModels()
    private val viewModel: LoginActivity.CreatePinViewModel by viewModels {
        //TODO: fix the error in this viewModel
        CreatePinViewModelFactory((requireActivity().application as LoginActivity).pinkman)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pincode, container, false)

        val loginTextView: TextView = view.findViewById(R.id.clickable_login)
        loginTextView.setOnClickListener { onLoginClicked() }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pinIsCreated.observe(viewLifecycleOwner, Observer { isCreated ->
            findNavController().popBackStack(R.id.login, false)
        })

        val pinView = view.findViewById<PinView>(R.id.pin_view)
        val keyboard = view.findViewById<KeyboardView>(R.id.keyboard)
        pinView.onFilledListener = { viewModel.createPin("1234") }
//        keyboard.keyboardClickListener = { pinView.add('1') }
    }

    private fun onLoginClicked() {
        val loginFragment = LoginFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, loginFragment)
            commit()
        }
    }
}