package id.co.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase

class ProfileViewModel(val useCase: UseCase): ViewModel() {
    fun getUserById(): LiveData<ResponseState<Users>> {
        return useCase.getUsersById().asLiveData()
    }
}