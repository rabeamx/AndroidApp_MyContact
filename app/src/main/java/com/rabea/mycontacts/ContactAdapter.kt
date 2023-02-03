package com.rabea.mycontacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rabea.mycontacts.db.Contact

//class ContactAdapter(val context: Context, val contacts: ArrayList<Contact>, val onItemClick: (((Contact) -> Unit)) ) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
//class ContactAdapter(val context: Context, val contacts: ArrayList<Contact>, val onItemInteraction: ContactAdapter.OnItemInteraction ) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
class ContactAdapter(val context: Context, val contacts: ArrayList<Contact>, val onItemInteraction: OnItemInteraction ) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    class ContactViewHolder(view:View) : ViewHolder(view){
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvContact = itemView.findViewById<TextView>(R.id.tvContact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]

        holder.tvName.text = contact.name
        holder.tvContact.text = contact.contact


//        holder.ivImage.setImageResource(photoUrl)

//        holder.ivImage.setImageResource(R.drawable.ic_image_placeholder)
//        Glide.with(context)
//            .load(imageModel.urls.thumb)
////            .load(imageModel.urls.small)
//            .into(holder.ivImage)

        holder.itemView.setOnClickListener {
//            onItemClick?.invoke(contacts[position])
//            onItemInteraction.onItemClick(contacts[position],)
            onItemInteraction.onItemClick(contacts[position], position)
        }

        holder.itemView.setOnLongClickListener {
            onItemInteraction.onItemLongClick(contacts[position], position)
            true
        }

    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    interface OnItemInteraction {
//        fun onItemClick (contact: Contact)
        fun onItemClick (contact: Contact, position: Int)

        fun onItemLongClick (contact: Contact, position: Int)
    }


}