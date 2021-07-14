package id.co.search

import androidx.lifecycle.*
import id.co.core.data.model.Materi
import id.co.core.data.model.Quiz
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase
import kotlinx.coroutines.launch

class SearchViewModel(val useCase: UseCase): ViewModel() {
//    val mutableSearch = MutableLiveData<ResponseState<List<Quiz>>>()
//
//    fun getQuizSearch(search: String) = viewModelScope.launch{
//        mutableSearch.postValue(useCase.getQuizSearch(search))
//    }
    fun getQuizSearch(search: String): LiveData<ResponseState<List<Quiz>>> {
        return useCase.getQuizSearch(search).asLiveData()
    }

}