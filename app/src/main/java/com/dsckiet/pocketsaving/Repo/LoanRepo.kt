package com.dsckiet.pocketsaving.Repo

import androidx.lifecycle.LiveData
import com.dsckiet.pocketsaving.dao.LoanDao
import com.dsckiet.pocketsaving.dao.TripDao
import com.dsckiet.pocketsaving.entity.LoanEntity
import com.dsckiet.pocketsaving.entity.TripEntity


class LoanRepo(private  val loanDao: LoanDao) {

    val allloans: LiveData<List<LoanEntity>> = loanDao.getLoanDao()


    fun insert(loanEntity: LoanEntity){
        loanDao.insert(loanEntity)
    }
    fun delete(loanEntity: LoanEntity){
        loanDao.delete(loanEntity)
    }

    fun getLoan(){
        loanDao.getLoanDao()
    }
    fun deleteall(){
        loanDao.deletall()
    }
}