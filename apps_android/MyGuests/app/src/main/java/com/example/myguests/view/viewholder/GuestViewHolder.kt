package com.example.myguests.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myguests.R
import com.example.myguests.model.GuestModel
import com.example.myguests.view.listener.GuestListener

class GuestViewHolder(
    itemView: View,
    private val listener: GuestListener
) : RecyclerView.ViewHolder(itemView) {

    fun bindList(guest: GuestModel) {
        val rowTextGuestName = itemView.findViewById<TextView>(R.id.row_text_guest_name)
        rowTextGuestName.text = guest.name

        rowTextGuestName.setOnClickListener {
            listener.onClick(guest.id)
        }

        rowTextGuestName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { dialog, wich ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()

            true
        }
    }

}
