package com.sitiaisyah.idn.finaltest.story

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sitiaisyah.idn.finaltest.R
import kotlinx.android.synthetic.main.item_story.view.*

class StoryAdapter (private val listener: (StoryModel) -> Unit):
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    private val listSg = ArrayList<StoryModel>()

    fun setData(items: ArrayList<StoryModel>) {
        listSg.clear()
        listSg.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_story, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listSg.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSg[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(sg: StoryModel, listener: (StoryModel) -> Unit) {
            with(itemView) {
                Glide.with(itemView.context).load(sg.img).apply(RequestOptions().override(500))
                    .into(iv_sg)

                tv_username.setText(sg.username)

                itemView.setOnClickListener { listener(sg) }
            }
        }

    }
}