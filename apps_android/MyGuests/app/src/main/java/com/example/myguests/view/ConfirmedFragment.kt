package com.example.myguests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myguests.databinding.FragmentConfirmedBinding
import com.example.myguests.viewmodel.ConfirmedViewModel

class ConfirmedFragment : Fragment() {

    private lateinit var confirmedViewModel: ConfirmedViewModel
    private var _binding: FragmentConfirmedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        confirmedViewModel =
            ViewModelProvider(this).get(ConfirmedViewModel::class.java)

        _binding = FragmentConfirmedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textConfirmed
        confirmedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}