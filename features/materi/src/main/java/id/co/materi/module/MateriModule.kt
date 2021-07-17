package id.co.materi.module

import id.co.materi.ui.MateriViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MateriModule {
    val materiModule = module{
        viewModel {
            MateriViewModel(get())
        }
    }
}