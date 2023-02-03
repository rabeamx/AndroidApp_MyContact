package com.rabea.mycontacts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 2)
abstract class MasterDatabase: RoomDatabase() {

    companion object { // companion object sobar jonno same, constant type ar => class level variable . ar object level variable change hoi

        private var instance: MasterDatabase? = null

        fun getInstance(context: Context): MasterDatabase {

            return instance ?: synchronized(this){ // multi threading ar concept ata
                return instance ?: databaseBuilder(
                    context,
                    MasterDatabase::class.java,
                    "MasterDatabase"
                )
                        // main thread a long time running operation cholte dei na android system // 2)) anr - application not responding
                    .allowMainThreadQueries() // 1)) android(ui) app run hole ui run hoi main thread ba ui thread
                    .fallbackToDestructiveMigration() // database a kono change hole ager data destroi korbe // real life aidui dangerous jinis
                    .build().also {
                        instance = it
                    }
            }

        }

    }

    abstract fun getContactDao(): ContactDao
    // singleton - pattern - only allow one instance(memory conjuction barbe pain)


}






























