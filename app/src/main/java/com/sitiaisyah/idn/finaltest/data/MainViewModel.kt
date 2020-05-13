package com.sitiaisyah.idn.finaltest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sitiaisyah.idn.finaltest.comon.BaseViewModel

class MainViewModel : ViewModel() {
    private val _likeNumber = MutableLiveData(0)

    val likeNumbers: LiveData<Int> = _likeNumber

    val popularity: LiveData<MainObservableViewModel.LikeNumbers> =
        Transformations.map(_likeNumber){
            when{
                it > 5 -> MainObservableViewModel.LikeNumbers.POP
                it > 1 -> MainObservableViewModel.LikeNumbers.NORMAL
                else -> MainObservableViewModel.LikeNumbers.START
            }
        }

    fun onLike(){
        _likeNumber.value = (_likeNumber.value ?: 0) + 1
    }
}

class MainObservableViewModel : BaseViewModel(){
    enum class LikeNumbers{
        START, NORMAL, POP
    }
}
