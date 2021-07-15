package id.co.login.module

import id.co.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LoginModule {
    val loginModule = module{
        viewModel {
            LoginViewModel(get())
        }
    }
}