package com.example.myguests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myguests.databinding.FragmentDeniedBinding
import com.example.myguests.viewmodel.DeniedViewModel

class DeniedFragment : Fragment() {

    private lateinit var deniedViewModel: DeniedViewModel
    private var _binding: FragmentDeniedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deniedViewModel =
            ViewModelProvider(this).get(DeniedViewModel::class.java)

        _binding = FragmentDeniedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDenied
        deniedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}