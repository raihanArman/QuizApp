package id.co.search.module

import id.co.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SearchModule {
    val searchModule = module{
        viewModel{
            SearchViewModel(get())
        }
    }
}