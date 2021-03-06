package com.example.notes.presentation.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private lateinit var vm: DescriptionViewModel
    lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this)[DescriptionViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = DescriptionFragment()
    }
}