package com.example.myguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myguests.constants.GuestConstants
import com.example.myguests.model.GuestModel
import com.example.myguests.service.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    // get instance of repository
    private val _guestRepository = GuestRepository.getInstance(application.applicationContext)

    private val _allGuestsList = MutableLiveData<List<GuestModel>>()
    val allGuestsList: LiveData<List<GuestModel>> = _allGuestsList

    fun loadAllGuestsList(filter: Int) {
        when (filter) {
            GuestConstants.FILTER.EMPTY -> {
                _allGuestsList.value = _guestRepository.getAllGuests()
            }
            GuestConstants.FILTER.DENIED -> {
                _allGuestsList.value = _guestRepository.getAbsentGuests()
            }
            else -> {
                _allGuestsList.value = _guestRepository.getPresentGuests()
            }
        }
    }

    fun delete(id: Int) {
        _guestRepository.delete(id)
    }

}
