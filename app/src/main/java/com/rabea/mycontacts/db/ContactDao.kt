package com.rabea.mycontacts.db

import androidx.room.*

@Dao
interface ContactDao {

    // insert
    @Insert
    fun insertContact(contact: Contact)

    // delete
    @Delete
    fun deleteContact(contact: Contact)

    // update
    @Update
    fun updateContact(contact: Contact)

    // get
    @Query("SELECT * FROM contactTable")
    fun getAllContact():List<Contact>

    // get
    @Query("select * from contactTable where (:keyword is null or (contactName like :keyword or contact like :keyword ))")
    fun searchContact(keyword: String?):List<Contact>

    // manual update
    @Query("update contactTable set contactName = :name, contact = :contact where id = :id ")
    fun updateContact(id: Long, name: String, contact: String)

}























