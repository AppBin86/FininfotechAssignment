package com.appbin.fininfocomassignment.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appbin.fininfocomassignment.R
import com.appbin.fininfocomassignment.database.UserInfo
import java.io.File

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var data = ArrayList<UserInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.name_text)
        val number : TextView = itemView.findViewById(R.id.number_text)
        val address : TextView = itemView.findViewById(R.id.address_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_user, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = data.get(position).name
        holder.number.text = data.get(position).age
        holder.address.text = data.get(position).city

    }

}