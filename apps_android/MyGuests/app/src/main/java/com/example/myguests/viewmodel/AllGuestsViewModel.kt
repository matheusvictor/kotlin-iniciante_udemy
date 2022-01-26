package com.example.myguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myguests.model.GuestModel
import com.example.myguests.service.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    // get instance of repository
    private val _guestRepository = GuestRepository.getInstance(application.applicationContext)

    private val _allGuestsList = MutableLiveData<List<GuestModel>>()
    val allGuestsList: LiveData<List<GuestModel>> = _allGuestsList

    fun loadAllGuestsList() {
        _allGuestsList.value = _guestRepository.getAllGuests()
    }

}
