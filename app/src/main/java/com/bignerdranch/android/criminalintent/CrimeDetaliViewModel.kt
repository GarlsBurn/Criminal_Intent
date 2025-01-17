package com.bignerdranch.android.criminalintent


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.criminalintent.database.CrimeRepository
import java.util.UUID
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CrimeDetailViewModel(private val crimeId: UUID) : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>()
    private val crimeMediatorLiveData = MediatorLiveData<Crime?>()

    init {
        crimeMediatorLiveData.addSource(crimeIdLiveData) { crimeId ->
            viewModelScope.launch {
                try {
                    val crime = crimeRepository.getCrime(crimeId)
                    crimeMediatorLiveData.value = crime
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
            }
        }


    var crimeLiveData: LiveData<Crime?> = crimeMediatorLiveData

    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    class CrimeDetailViewModelFactory(private val crimeId: UUID) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CrimeDetailViewModel::class.java)) {
                return CrimeDetailViewModel(crimeId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun saveCrime(crime: Crime){
        crimeRepository.updateCrime(crime)
    }
}