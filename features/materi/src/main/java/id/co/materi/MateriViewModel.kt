package id.co.materi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.domain.usecase.UseCase

class MateriViewModel(val usecase: UseCase): ViewModel() {
    val getMateri = usecase.getMateri().asLiveData()
}