package com.example.notes.presentation.list

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.databinding.FragmentListBinding
import com.example.notes.presentation.models.DayModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ListFragment : Fragment(), HourAdapter.Listener {

    private lateinit var vm: ListViewModel
    lateinit var binding: FragmentListBinding
    var cal = Calendar.getInstance()
    var btnChoiseDate: Button? = null
    var btnToday: Button? = null
    var date : String? = null
    var day = mutableListOf<String>()

    private val adapter = HourAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnChoiseDate = binding.btnChoiseDate
        btnToday = binding.btnToday

        vm = ViewModelProvider(this)[ListViewModel::class.java]

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted = current.format(formatter)
        btnChoiseDate!!.text = formatted
        date = formatted

        btnToday!!.setOnClickListener {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatted = current.format(formatter)
            btnChoiseDate!!.text = formatted
            date = formatted
        }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
                init()
            }
        btnChoiseDate!!.setOnClickListener {
            DatePickerDialog(
                requireActivity(),
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }



        for(i in 1..24){
            day.add(i.toString())
        }
        init()

    }
    private fun init() {
        binding.apply {
            rcViewHours.layoutManager =LinearLayoutManager(context)
            rcViewHours.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
            rcViewHours.adapter =  adapter
            adapter.addDay(day, date.toString())
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        btnChoiseDate!!.text = sdf.format(cal.time)
        date = sdf.format(cal.time)
    }

    override fun onClick(dayModel: DayModel) {
        val bundle = Bundle()
        bundle.putSerializable("Args", dayModel)
        findNavController().navigate(R.id.action_listFragment_to_newNotesFragment, bundle)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }


}