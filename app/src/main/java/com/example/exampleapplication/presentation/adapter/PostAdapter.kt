package com.example.exampleapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapplication.R
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.PostWithPerson

class PostAdapter(
    private var dataSet: ArrayList<PostWithPerson>
    ) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postTitleView: TextView = view.findViewById(R.id.postTitle)
        val postIdView: TextView = view.findViewById(R.id.postId)
        val personNameView: TextView = view.findViewById(R.id.postPersonName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = dataSet[position]
        holder.postTitleView.text = current.post.title
        holder.postIdView.text = current.post.id.toString()
        holder.personNameView.text = current.person.name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun add(new: PostWithPerson) {
        dataSet.add(new)
        notifyDataSetChanged()
    }

    fun add(new: List<PostWithPerson>) {
        dataSet = new as ArrayList<PostWithPerson>
        notifyDataSetChanged()
    }

    fun clearDataSet() {
        dataSet = ArrayList()
        notifyDataSetChanged()
    }
}