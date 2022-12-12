package com.example.workfour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.workfour.databinding.NoteListItemBinding
import com.example.workfour.room.NoteData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext
import java.lang.Exception

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    var items: List<Note> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var itemClick: (NoteData) -> Unit = {}
    fun itemClick(listener: (NoteData) -> Unit) {
        itemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.note = items[position]
        holder.itemView.setOnClickListener {
            itemClick = items[position]
        }
        val url = items[position].img
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_baseline_hide_image_24)
            .fit()
            .into(holder.binding.imageView3, object : Callback{
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {

                }

            })
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = NoteListItemBinding.bind(view)

    }
}