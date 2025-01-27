package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.criminalintent.database.CrimeRepository
import kotlinx.coroutines.launch

class CrimeListViewModel: ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    val crimeLiveData = crimeRepository.getCrimes()

    fun addCrime(crime: Crime){
        viewModelScope.launch {
            crimeRepository.addCrime(crime)
        }
    }


    }
