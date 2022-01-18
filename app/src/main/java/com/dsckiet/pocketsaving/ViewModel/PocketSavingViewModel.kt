package com.dsckiet.pocketsaving.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dsckiet.pocketsaving.Database.PocketSavingDatabase
import com.dsckiet.pocketsaving.Repo.PocketSavingRepo
import com.dsckiet.pocketsaving.dao.PocketSavingDoa
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PocketSavingViewModel(application: Application): AndroidViewModel(application) {

    val allSubjects: LiveData<List<PocketSavingEntity>>
    private val repository: PocketSavingRepo
    init{
        val dao = PocketSavingDatabase.getDatabase(application).PocketSavingDAO()
        repository = PocketSavingRepo(dao)
        allSubjects = repository.allNotes
    }

    fun deleteFriend(pocketSavingEntity: PocketSavingEntity) = viewModelScope.launch(    Dispatchers.IO){

        repository.delete(pocketSavingEntity)
    }

    fun insertFriend(pocketSavingEntity: PocketSavingEntity)= viewModelScope.launch (Dispatchers.IO){
        repository.insert(pocketSavingEntity)
    }
//    fun updateSubject(Subjectname: String, status: String, TotalClasses: String, AttendedClasses: String,currdatetime:String)= viewModelScope.launch (
//        Dispatchers.IO){
//        repository.update(Subjectname,status,TotalClasses,AttendedClasses,currdatetime)
//    }
    fun getSub()=viewModelScope.launch (Dispatchers.IO){
        repository.getSubject()
    }
    fun deleteall() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteall()
    }
}