package id.co.profil.module

import id.co.profil.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ProfileModule {
    val profileModule = module{
        viewModel {
            ProfileViewModel(get())
        }
    }
}