package com.dsckiet.pocketsaving.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_table")
class TripEntity(
    @ColumnInfo(name = "number_of_people") val numberOfPeople: String,
    @ColumnInfo(name = "amount_spent") val amount_spent: String,
    @ColumnInfo(name = "per_head_cost") val perheadCost: String,
    @ColumnInfo(name = "item_name") val item_name: String,
    @ColumnInfo(name = "DateandTime") val Dateandtime: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "note") val note: String,

    ){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="index") var i=0
}