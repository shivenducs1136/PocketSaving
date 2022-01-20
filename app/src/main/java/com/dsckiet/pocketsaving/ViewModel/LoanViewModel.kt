package com.dsckiet.pocketsaving.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dsckiet.pocketsaving.Database.PocketSavingDatabase
import com.dsckiet.pocketsaving.Repo.LoanRepo
import com.dsckiet.pocketsaving.Repo.PocketSavingRepo
import com.dsckiet.pocketsaving.entity.LoanEntity
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanViewModel(application: Application): AndroidViewModel(application) {

    val allLoan: LiveData<List<LoanEntity>>
    private val repository: LoanRepo
    init{
        val dao = PocketSavingDatabase.getDatabase(application).loanDao()
        repository = LoanRepo(dao)
        allLoan = repository.allloans
    }

    fun deleteLoan(loanEntity: LoanEntity) = viewModelScope.launch(    Dispatchers.IO){

        repository.delete(loanEntity)
    }

    fun insertLoan(loanEntity: LoanEntity)= viewModelScope.launch (Dispatchers.IO){
        repository.insert(loanEntity)
    }
    fun getLoan()=viewModelScope.launch (Dispatchers.IO){
        repository.getLoan()
    }
    fun deleteall() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteall()
    }
}