package com.dsckiet.pocketsaving.Repo

import androidx.lifecycle.LiveData
import com.dsckiet.pocketsaving.dao.PocketSavingDoa
import com.dsckiet.pocketsaving.dao.TripDao
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import com.dsckiet.pocketsaving.entity.TripEntity


class TripRepo(private  val tripDao: TripDao) {

    val alltrips: LiveData<List<TripEntity>> = tripDao.getTripDao()


    fun insert(tripEntity: TripEntity){
        tripDao.insert(tripEntity)
    }
    fun delete(tripEntity: TripEntity){
        tripDao.delete(tripEntity)
    }
    //    fun  update(Subjectname: String, status: String, TotalClasses: String, AttendedClasses: String,currdatetime:String){
//        pocketSavingDoa.updateSubject(Subjectname,status,TotalClasses,AttendedClasses,currdatetime)
//    }
    fun getSubject(){
        tripDao.getTripDao()
    }
    fun deleteall(){
        tripDao.deletall()
    }
}