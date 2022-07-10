package com.example.notes.presentation.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.HourItemBinding
import com.example.notes.presentation.models.DayModel

class HourAdapter(private val listener: Listener ) :RecyclerView.Adapter<HourAdapter.HourHolder>() {

    private var dayList = emptyList<String>()
    private var date= ""

    class HourHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = HourItemBinding.bind(item)
        fun bind(day: List<String>, listener: Listener, date: String) = with(binding){
            if(day[position].toInt() <10)
                tvHour.text = "0${day[position]}:00"
            else
                tvHour.text = "${day[position]}:00"

            val dayModel = DayModel(
            hour = day[position],
            date = date
            )
            itemView.setOnClickListener{
                listener.onClick(dayModel = dayModel)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hour_item, parent, false)
        return HourHolder(view)
    }

    override fun onBindViewHolder(holder: HourHolder, position: Int) {
        holder.bind(dayList, listener, date)
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    fun addDay(day: List<String>, date: String){
        dayList = day
        this.date = date
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(dayModel: DayModel)
    }

}