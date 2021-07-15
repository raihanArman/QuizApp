package id.co.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import id.co.core.data.network.ResponseState
import id.co.login.databinding.FragmentSignUpBinding
import id.co.login.module.LoginModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class SignUpFragment : Fragment() {

    private lateinit var dataBinding: FragmentSignUpBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(LoginModule.loginModule)

        dataBinding.btnSignUp.setOnClickListener {
            registerProses()
        }

    }

    private fun registerProses() {
        val email = dataBinding.etEmail.text.toString()
        val name = dataBinding.etNama.text.toString()
        val password = dataBinding.etPassword.text.toString()
        viewModel.registerUser(email, name, password)
            .observe(viewLifecycleOwner, Observer { response ->
                when(response){
                    is ResponseState.Loading ->{

                    }
                    is ResponseState.Success ->{
                        goToMainActivity()
                    }
                    is ResponseState.Error ->{
                        Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun goToMainActivity() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("quiz://main"))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}