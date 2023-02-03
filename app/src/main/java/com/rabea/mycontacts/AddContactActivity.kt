package com.rabea.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rabea.mycontacts.databinding.ActivityAddContactBinding
import com.rabea.mycontacts.db.Contact
import com.rabea.mycontacts.db.MasterDatabase

class AddContactActivity : AppCompatActivity() {

    private lateinit var masterDatabase: MasterDatabase
    private lateinit var binding: ActivityAddContactBinding
//    private var contact: Contact? = null
    private var UpdateTableContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact)

        if(intent.hasExtra("contact")) {
            UpdateTableContact = intent.extras!!.getSerializable("contact") as Contact
        }

        masterDatabase = MasterDatabase.getInstance(this)

//        if (contact!= null) {
//            binding.etContactName.setText(contact.name)
//        }
        UpdateTableContact?.let {
            binding.etContactName.setText(it.name)
            binding.etContactNumber.setText(it.contact)
            binding.title.text = "Update Contact"
            binding.btnSave.text = "Update"
        }

        binding.btnSave.setOnClickListener {

            val contactName = binding.etContactName.text.toString()
            val contactNumber = binding.etContactNumber.text.toString()

            val contact = Contact(name = contactName, contact = contactNumber)

            if (binding.title.text.equals("Update Contact")) {

//                masterDatabase.getContactDao().updateContact(contact.id!!, contactName, contactNumber)
//                masterDatabase.getContactDao().updateContact(UpdateTableContact!!.id!!, contactName, contactNumber)
                contact.id = UpdateTableContact!!.id!!
                masterDatabase.getContactDao().updateContact(contact)
                Toast.makeText(this,"contact successfully updated",Toast.LENGTH_SHORT).show()
                finish()

            } else{

                masterDatabase.getContactDao().insertContact(contact)
                Toast.makeText(this,"contact successfully saved",Toast.LENGTH_SHORT).show()

            }

//            masterDatabase.getContactDao().insertContact(contact)
//            Toast.makeText(this,"contact successfully saved",Toast.LENGTH_SHORT).show()

            binding.etContactName.setText("")
            binding.etContactNumber.setText("")

        }

    }
}