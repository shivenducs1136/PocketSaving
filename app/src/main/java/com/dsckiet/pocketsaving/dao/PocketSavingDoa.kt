package com.dsckiet.pocketsaving.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dsckiet.pocketsaving.entity.PocketSavingEntity

@Dao
interface PocketSavingDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pocketSavingEntity: PocketSavingEntity)

    @Delete
    fun delete(pocketSavingEntity: PocketSavingEntity)

    @Query("DELETE FROM pocketmoney_table")
    fun deletall()

    @Query("SELECT * FROM pocketmoney_table")
    fun getSubjectDao(): LiveData<List<PocketSavingEntity>>

//    @Query("UPDATE subject_table SET TotalClasses = :TotalClasses ,AttendedClasses= :AttendedClasses,Status= :status , lastupdate=:currdatetime WHERE SubjectName LIKE :Subjectname ")
//    fun updateSubject(Subjectname: String, status: String, TotalClasses: String, AttendedClasses: String,currdatetime:String)
}