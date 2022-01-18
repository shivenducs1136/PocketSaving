package com.dsckiet.pocketsaving.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dsckiet.pocketsaving.dao.PocketSavingDoa
import com.dsckiet.pocketsaving.entity.PocketSavingEntity

@Database(entities = [PocketSavingEntity::class],version = 1)
abstract class PocketSavingDatabase: RoomDatabase() {

    abstract fun PocketSavingDAO(): PocketSavingDoa
    companion object{

        @Volatile
        private var INSTANCE: PocketSavingDatabase? = null
        fun getDatabase(context: Context): PocketSavingDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PocketSavingDatabase::class.java,
                    "pocketdatabase_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }
}