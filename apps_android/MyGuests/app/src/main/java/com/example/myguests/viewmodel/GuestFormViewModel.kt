package com.example.myguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myguests.model.GuestModel
import com.example.myguests.service.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val _context = application.applicationContext
    private val _guestRepository: GuestRepository = GuestRepository.getInstance(_context)

    private var _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    fun save(name: String, presence: Boolean) {
        val guest = GuestModel(name = name, presence = presence)
        _saveGuest.value = _guestRepository.saveGuest(guest)
    }
}