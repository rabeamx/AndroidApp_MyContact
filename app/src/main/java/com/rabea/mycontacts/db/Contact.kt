package com.rabea.mycontacts.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactTable")
data class Contact (

    @PrimaryKey(autoGenerate = true)
     var id: Long? = null,

//   private var name:String,

    @ColumnInfo(name = "contactName")
     var name:String,

     var contact:String

        ) : java.io.Serializable