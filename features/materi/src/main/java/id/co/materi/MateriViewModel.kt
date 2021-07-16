package id.co.materi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.Chapter
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase

class MateriViewModel(val usecase: UseCase): ViewModel() {
    val getMateri = usecase.getMateri().asLiveData()

    fun getBabByMateri(id: String): LiveData<ResponseState<List<Chapter>>> {
        return usecase.getBabByMateri(id).asLiveData()
    }

}