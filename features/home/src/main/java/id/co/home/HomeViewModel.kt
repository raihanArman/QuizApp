package id.co.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase

class HomeViewModel(val useCase: UseCase): ViewModel() {
    fun getUsersById(id: String): LiveData<ResponseState<Users>> {
        return useCase.getUsersById(id).asLiveData()
    }

    fun getMateri(): LiveData<ResponseState<List<Materi>>>{
        return useCase.getMateri().asLiveData()
    }

    fun getCategory(): LiveData<ResponseState<List<Category>>>{
        return useCase.getCategory().asLiveData()
    }

}