package com.example.myguests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myguests.R
import com.example.myguests.model.GuestModel
import com.example.myguests.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var _allGuestList: List<GuestModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item)
    }

    override fun getItemCount(): Int {
        return _allGuestList.count()
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bindList(_allGuestList[position])
    }

    fun updateAllGuestList(list: List<GuestModel>) {
        _allGuestList = list
        notifyDataSetChanged()
    }

}
