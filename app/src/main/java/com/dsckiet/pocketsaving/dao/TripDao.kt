package com.dsckiet.pocketsaving.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import com.dsckiet.pocketsaving.entity.TripEntity

@Dao
interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tripEntity: TripEntity)

    @Delete
    fun delete(tripEntity: TripEntity)

    @Query("DELETE FROM trip_table")
    fun deletall()

    @Query("SELECT * FROM trip_table")
    fun getTripDao(): LiveData<List<TripEntity>>

//    @Query("UPDATE subject_table SET TotalClasses = :TotalClasses ,AttendedClasses= :AttendedClasses,Status= :status , lastupdate=:currdatetime WHERE SubjectName LIKE :Subjectname ")
//    fun updateSubject(Subjectname: String, status: String, TotalClasses: String, AttendedClasses: String,currdatetime:String)
}