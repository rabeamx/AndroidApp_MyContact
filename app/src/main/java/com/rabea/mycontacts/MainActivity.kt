package com.rabea.mycontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabea.mycontacts.databinding.ActivityMainBinding
import com.rabea.mycontacts.db.Contact
import com.rabea.mycontacts.db.MasterDatabase

class MainActivity : AppCompatActivity() {

    private var contacts = ArrayList<Contact>()
    private lateinit var masterDatabase: MasterDatabase

//    private var counter: Int = 0
      private lateinit var binding: ActivityMainBinding
//    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        masterDatabase = MasterDatabase.getInstance(this)

//        sharedPreferences = getSharedPreferences(Constants.sharedPreferenceName, MODE_PRIVATE)
//        // akhane hobe or null point ar exception khabe
//
//        counter = sharedPreferences.getInt("counter", 0)
//        // get value kora hoice
//        binding.tvCounter.text = counter.toString()
//        // text view te save hobe
//
//        // local storage
//        // shared preference - key value
//        // sqlite / room database
//
////        sharedPreferences = getSharedPreferences("My Prefs", MODE_PRIVATE)
//        // set data and get data
////        sharedPreferences.edit().putInt("counter", counter).apply() // set value kora hoice
////        sharedPreferences.getInt("counter", 0) // get value kora hoice
//
//        binding.btnCount.setOnClickListener {
//            counter++
//
//            binding.tvCounter.text = counter.toString()
//
//            sharedPreferences.edit().putInt("counter", counter).apply()
//        // set value kora hoice
//        }



//        contacts.add(Contact(name = "Hasan", contact = "02154689779"))


        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.setHasFixedSize(true)

//        binding.rvContacts.adapter = ContactAdapter(this, contacts){
//        }
        binding.rvContacts.adapter = ContactAdapter(this, contacts,
            object : ContactAdapter.OnItemInteraction {
            override fun onItemClick(contact: Contact, pos: Int) {
//                val bundle = Bundle()
//                bundle.putSerializable("contact", contacts)

                val intent = Intent(this@MainActivity, AddContactActivity::class.java)
//                intent.putExtra(bundle)
                intent.putExtra("contact", contacts)
                startActivity(intent)
            }

            override fun onItemLongClick(contact: Contact, pos: Int) {
                masterDatabase.getContactDao().deleteContact(contact)
                contacts.remove(contact)
//                binding.rvContacts.adapter!!.notifyDataSetChanged()
                binding.rvContacts.adapter!!.notifyItemRemoved(pos)
            }

        })


        contacts.addAll(masterDatabase.getContactDao().getAllContact())
        binding.rvContacts.adapter!!.notifyDataSetChanged()


        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                contacts.clear()
                contacts.addAll(masterDatabase.getContactDao().searchContact("%"+s.toString()+"%"))
                binding.rvContacts.adapter!!.notifyDataSetChanged()

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        // room database
        // database file
        // entity
        // dao file

    }

    override fun onResume() {
        super.onResume()

        if (binding != null && binding.rvContacts.adapter!= null) {
            contacts.clear()
            contacts.addAll(masterDatabase.getContactDao().getAllContact())
            binding.rvContacts.adapter!!.notifyDataSetChanged()
        }

    }

}
















