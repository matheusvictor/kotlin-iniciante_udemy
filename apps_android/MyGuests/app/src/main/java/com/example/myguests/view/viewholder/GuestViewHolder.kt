package com.example.myguests.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myguests.R
import com.example.myguests.model.GuestModel

class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindList(guest: GuestModel) {
        val rowTextGuestName = itemView.findViewById<TextView>(R.id.row_text_guest_name)
        rowTextGuestName.text = guest.name
    }

}
