package com.example.myguests.view

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
import com.example.myguests.databinding.FragmentAllBinding
import com.example.myguests.view.adapter.GuestAdapter
import com.example.myguests.viewmodel.AllGuestsViewModel
import kotlinx.android.synthetic.main.fragment_all.*

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private var _binding: FragmentAllBinding? = null

    private val _adapter: GuestAdapter = GuestAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // get a RecycleViewr
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        // define layout
        recycler.layoutManager = LinearLayoutManager(context)

        // adapter
        recycler.adapter = _adapter

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