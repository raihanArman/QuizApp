package id.co.materi.ui

import android.util.Log
import androidx.lifecycle.*
import id.co.core.data.model.Chapter
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase
import id.co.materi.utils.Constant

class MateriViewModel(val usecase: UseCase): ViewModel() {
    val getMateri = usecase.getMateri().asLiveData()

    var chapterId = MutableLiveData<String>()
    var materiId = MutableLiveData<String>()
    var position = MutableLiveData<Int>()
    var listChapter = MutableLiveData<List<Chapter>>()

    fun setSelectedChapter(chapterId: String, position: Int) {
        this.chapterId.value = chapterId
        this.position.value = position
    }

    fun setListChapter(chapterList: List<Chapter>){
        this.listChapter.value = chapterList
    }

    fun setSelectedMateri(materiId: String) {
        this.chapterId.value = materiId
    }

    var selectedChapter: LiveData<ResponseState<Chapter>> = Transformations.switchMap(chapterId){selectedPosition->
        Log.d("Mantap", "selectedChapter tertrigger: dipanggil")
        usecase.getContentById(selectedPosition).asLiveData()
    }

    fun getContentById(chapterId: String): LiveData<ResponseState<Chapter>> {
        return usecase.getContentById(chapterId).asLiveData()
    }


//    var selectedMateri: LiveData<ResponseState<List<Chapter>>> = Transformations.switchMap(materiId){selectedPosition->
//        usecase.getBabByMateri(selectedPosition).asLiveData()
//    }

    fun getBabByMateri(id: String): LiveData<ResponseState<List<Chapter>>> {
        return usecase.getBabByMateri(id).asLiveData()
    }

    fun setNextPage() {
        Log.d("Mantap", "setNextPage: di tekan")
        if (selectedChapter.value != null) {
            val dataChapter = Constant.listChapter
            if(dataChapter != null && dataChapter.isNotEmpty()){
                val positionChapter = position.value
                if(dataChapter?.size-1 > positionChapter!!) {
                    val chapterNextId = dataChapter[positionChapter!! + 1].id
                    position.value = positionChapter + 1
                    chapterId.value = chapterNextId
                }
            }else{
                Log.d("Mantap", "setNextPage: ")
            }
        }else{
            Log.d("Mantap", "setNextPage: selected null")
        }
    }
    fun setPrevPage() {
        Log.d("Mantap", "setNextPage: di tekan")
        if (selectedChapter.value != null) {
            val dataChapter = Constant.listChapter
            if(dataChapter != null && dataChapter.isNotEmpty()){
                val positionChapter = position.value
                if(positionChapter!! > 0) {
                    val chapterNextId = dataChapter[positionChapter!! - 1].id
                    position.value = positionChapter - 1
                    chapterId.value = chapterNextId
                }
            }else{
                Log.d("Mantap", "setNextPage: ")
            }
        }else{
            Log.d("Mantap", "setNextPage: selected null")
        }
    }

//    fun getContentById(id: String): LiveData<ResponseState<Chapter>>{
//        return usecase.getContentById(id).asLiveData()
//    }

}