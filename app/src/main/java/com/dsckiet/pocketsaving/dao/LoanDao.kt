package com.dsckiet.pocketsaving.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dsckiet.pocketsaving.entity.LoanEntity
import com.dsckiet.pocketsaving.entity.TripEntity

@Dao
interface LoanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loanEntity: LoanEntity)

    @Delete
    fun delete(loanEntity: LoanEntity)

    @Query("DELETE FROM loan_table")
    fun deletall()

    @Query("SELECT * FROM loan_table")
    fun getLoanDao(): LiveData<List<LoanEntity>>

//    @Query("UPDATE subject_table SET TotalClasses = :TotalClasses ,AttendedClasses= :AttendedClasses,Status= :status , lastupdate=:currdatetime WHERE SubjectName LIKE :Subjectname ")
//    fun updateSubject(Subjectname: String, status: String, TotalClasses: String, AttendedClasses: String,currdatetime:String)
}