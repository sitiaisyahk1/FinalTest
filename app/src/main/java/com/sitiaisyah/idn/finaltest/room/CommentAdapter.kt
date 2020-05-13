package com.sitiaisyah.idn.finaltest.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sitiaisyah.idn.finaltest.R

class CommentAdapter internal constructor(context: Context):
RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var comment = emptyList<Comment>()

    internal fun setComment(comments: List<Comment>){
        this.comment = comments
        notifyDataSetChanged()
    }
    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val commentView : TextView = view.findViewById(R.id.tv_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = inflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount()= comment.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentPosition = comment[position]
        holder.commentView.text = currentPosition.comment
    }
}