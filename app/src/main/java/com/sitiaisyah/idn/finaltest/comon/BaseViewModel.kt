package com.sitiaisyah.idn.finaltest.comon

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import java.util.*

open class BaseViewModel : ViewModel(), Observable {

    private val callbacks : PropertyChangeRegistry = PropertyChangeRegistry()
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    fun notifyPropertyChanged(position: Int){
        callbacks.notifyCallbacks(this, position, null)
    }

}