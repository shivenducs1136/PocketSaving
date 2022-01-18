package com.dsckiet.pocketsaving.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pocketmoney_table")
class PocketSavingEntity(
    @ColumnInfo(name = "amountspend") val amountspend: String,
    @ColumnInfo(name = "amountleft") val amountleft: String,
    @ColumnInfo(name = "totalamount") val totalamount: String,
    @ColumnInfo(name = "amountadded") val amountadded: String,
    @ColumnInfo(name = "DateandTime") val Dateandtime: String,
    @ColumnInfo(name = "itemtitle") val title: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "note") val note: String,

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="index") var i=0
}