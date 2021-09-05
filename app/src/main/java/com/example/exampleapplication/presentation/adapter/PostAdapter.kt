package com.example.exampleapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapplication.R
import com.example.exampleapplication.data.model.Post

class PostAdapter(
    private var dataSet: ArrayList<Post>
    ) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postTitleView: TextView = view.findViewById(R.id.postTitle)
        val postIdView: TextView = view.findViewById(R.id.postId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = dataSet[position]
        holder.postTitleView.text = current.title
        holder.postIdView.text = current.id.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun add(new: Post) {
        dataSet.add(new)
        notifyDataSetChanged()
    }

    fun add(new: List<Post>) {
        dataSet = new as ArrayList<Post>
        notifyDataSetChanged()
    }

    fun clearDataSet() {
        this.dataSet = ArrayList<Post>()
        notifyDataSetChanged()
    }
}