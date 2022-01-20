package com.dsckiet.pocketsaving.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loan_table")
class LoanEntity(
    @ColumnInfo(name = "loanamount") val loanamount: String,
    @ColumnInfo(name = "frindname") val frindname: String,
    @ColumnInfo(name = "givenortaken") val givenortaken: String,
    @ColumnInfo(name = "loandate") val loandate: String,
    @ColumnInfo(name = "loanreason") val loanreason: String,
    ){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="index") var i=0
}