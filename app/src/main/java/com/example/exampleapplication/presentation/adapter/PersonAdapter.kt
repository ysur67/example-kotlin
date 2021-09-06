package com.example.exampleapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapplication.R
import com.example.exampleapplication.data.model.person.Person

class PersonAdapter(
    private var dataSet: ArrayList<Person>
    ) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personNameView: TextView = view.findViewById(R.id.personName)
        val personIdView: TextView = view.findViewById(R.id.personId)
        val personEmailView: TextView = view.findViewById(R.id.personEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = dataSet[position]
        holder.personNameView.text = current.name
        holder.personIdView.text = current.id.toString()
        holder.personEmailView.text = current.email
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun add(new: List<Person>) {
        dataSet = new as ArrayList<Person>
        notifyDataSetChanged()
    }
}
