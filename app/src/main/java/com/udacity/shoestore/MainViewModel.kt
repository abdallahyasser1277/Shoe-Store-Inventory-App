package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel: ViewModel() {

    var shoetemp=Shoe("Default Name",
        45.0,
        "Default Company Name",
        "Default Description")

    private var _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf(Shoe(shoetemp.name,
        shoetemp.size,
        shoetemp.company
        ,shoetemp.description)))

    val shoeList : LiveData<MutableList<Shoe>> get()=_shoeList

    private var _saveButton =MutableLiveData<Boolean>(false)
    val saveButton: LiveData<Boolean> get() = _saveButton

    private fun addShoe() {
        var list = _shoeList.value!!
        list.add(Shoe(shoetemp.name,shoetemp.size,shoetemp.company,shoetemp.description))
        _shoeList.value= list
    }


    fun eventSaveButtonClicked(){
         addShoe()
        _saveButton.value=true
    }
    fun eventSaveClickedCompleted(){
        _saveButton.value=false
    }

}