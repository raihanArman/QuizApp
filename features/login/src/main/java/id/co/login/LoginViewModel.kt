package id.co.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase

class LoginViewModel(val useCase: UseCase): ViewModel() {
    fun loginUser(email: String, password: String): LiveData<ResponseState<Users>> {
        return useCase.loginUser(email, password).asLiveData()
    }

    fun registerUser(
        email: String,
        name: String,
        password: String
    ): LiveData<ResponseState<Users>> {
        return useCase.registerUser(email, name, password).asLiveData()
    }

}