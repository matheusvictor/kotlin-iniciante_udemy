package com.example.myguests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myguests.R
import com.example.myguests.constants.GuestConstants
import com.example.myguests.databinding.FragmentAllBinding
import com.example.myguests.view.adapter.GuestAdapter
import com.example.myguests.view.listener.GuestListener
import com.example.myguests.viewmodel.AllGuestsViewModel
import kotlinx.android.synthetic.main.fragment_all.*

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private var _binding: FragmentAllBinding? = null

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
        allGuestsViewModel =
            ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // get a RecycleViewr
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
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
                allGuestsViewModel.delete(id)
                allGuestsViewModel.loadAllGuestsList()
            }
        }

        _adapter.attachListener(_listener)
        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.loadAllGuestsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        allGuestsViewModel.allGuestsList.observe(viewLifecycleOwner, Observer {
            _adapter.updateAllGuestList(it)
        })
    }
}