package com.dsckiet.pocketsaving.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dsckiet.pocketsaving.Database.PocketSavingDatabase
import com.dsckiet.pocketsaving.Repo.TripRepo
import com.dsckiet.pocketsaving.entity.TripEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TripViewModel(application: Application): AndroidViewModel(application) {

    val allTrips: LiveData<List<TripEntity>>
    private val repository: TripRepo
    init{
        val dao = PocketSavingDatabase.getDatabase(application).tripDao()
        repository = TripRepo(dao)
        allTrips = repository.alltrips
    }

    fun deletetrip(tripEntity: TripEntity) = viewModelScope.launch(    Dispatchers.IO){

        repository.delete(tripEntity)
    }

    fun inserttrip(tripEntity: TripEntity)= viewModelScope.launch (Dispatchers.IO){
        repository.insert(tripEntity)
    }
    fun getSub()=viewModelScope.launch (Dispatchers.IO){
        repository.getSubject()
    }
    fun deleteall() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteall()
    }
}