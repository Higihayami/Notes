package com.example.notes.presentation.newnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentNewNotesBinding
import com.example.notes.presentation.models.DayModel

class NewNotesFragment : Fragment() {

    private lateinit var vm: NewNotesViewModel
    lateinit var binding :FragmentNewNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this)[NewNotesViewModel::class.java]

        val dayModel = arguments?.getSerializable("Args") as DayModel
        val hourEnd = dayModel.hour
        binding.tvDate.text = dayModel.date
        val hourStart = (hourEnd.toInt()-1).toString()
        if(hourStart.toInt()<10)
            binding.tvFirstTime.text = "0$hourStart:00"
        else
            binding.tvFirstTime.text = "$hourStart:00"
        if(hourEnd.toInt()<10)
            binding.tvSecondTime.text = "0$hourEnd:00"
        else
            binding.tvSecondTime.text = "$hourEnd:00"
    }

    companion object {

        @JvmStatic
        fun newInstance() = NewNotesFragment()
    }
}