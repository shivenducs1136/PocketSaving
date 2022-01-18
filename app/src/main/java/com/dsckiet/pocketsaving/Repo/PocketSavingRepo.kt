package com.dsckiet.pocketsaving.Repo

import androidx.lifecycle.LiveData
import com.dsckiet.pocketsaving.dao.PocketSavingDoa
import com.dsckiet.pocketsaving.entity.PocketSavingEntity

class PocketSavingRepo(private  val pocketSavingDoa: PocketSavingDoa) {

    val allNotes: LiveData<List<PocketSavingEntity>> = pocketSavingDoa.getSubjectDao()


    fun insert(pocketSavingEntity: PocketSavingEntity){
        pocketSavingDoa.insert(pocketSavingEntity)
    }
    fun delete(pocketSavingEntity: PocketSavingEntity){
        pocketSavingDoa.delete(pocketSavingEntity)
    }
//    fun  update(Subjectname: String, status: String, TotalClasses: String, AttendedClasses: String,currdatetime:String){
//        pocketSavingDoa.updateSubject(Subjectname,status,TotalClasses,AttendedClasses,currdatetime)
//    }
    fun getSubject(){
        pocketSavingDoa.getSubjectDao()
    }
    fun deleteall(){
        pocketSavingDoa.deletall()
    }
}