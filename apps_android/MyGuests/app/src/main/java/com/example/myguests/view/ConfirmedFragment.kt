package com.example.myguests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myguests.R
import com.example.myguests.constants.GuestConstants
import com.example.myguests.databinding.FragmentConfirmedBinding
import com.example.myguests.view.adapter.GuestAdapter
import com.example.myguests.view.listener.GuestListener
import com.example.myguests.viewmodel.AllGuestsViewModel

class ConfirmedFragment : Fragment() {

    private lateinit var confirmedViewModel: AllGuestsViewModel
    private var _binding: FragmentConfirmedBinding? = null

    private val _adapter: GuestAdapter = GuestAdapter()
    private lateinit var _listener: GuestListener

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        confirmedViewModel =
            ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentConfirmedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // get a RecycleViewr
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_presents)
        // set layout
        recycler.layoutManager = LinearLayoutManager(context)
        // adapter
        recycler.adapter = _adapter

        _listener = object : GuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                confirmedViewModel.delete(id)
                confirmedViewModel.loadAllGuestsList(GuestConstants.FILTER.CONFIRMED)
            }
        }

        _adapter.attachListener(_listener)
        observer()

        return root
    }

    private fun observer() {
        confirmedViewModel.allGuestsList.observe(viewLifecycleOwner, Observer {
            _adapter.updateAllGuestList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        confirmedViewModel.loadAllGuestsList(GuestConstants.FILTER.CONFIRMED)
    }

}
