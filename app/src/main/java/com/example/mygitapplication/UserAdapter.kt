package com.example.mygitapplication

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<User>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(pos: Int) = data[pos]

    fun setData(users: List<User>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

}