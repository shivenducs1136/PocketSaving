package com.dsckiet.pocketsaving.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import com.dsckiet.pocketsaving.entity.TripEntity
import com.dsckiet.pocketsaving.ui.TripPlanning

class TripAdapter(val context: Context, val listener: TripPlanning): RecyclerView.Adapter<TripAdapter.NoteViewHolder>() {

    val allSubject = ArrayList<TripEntity>()
    var stack = ArrayDeque<Boolean>()
    var i=0
    var currdatetime =""
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitle= itemView.findViewById<TextView>(R.id.trip_item_name)
        val total_amount= itemView.findViewById<TextView>(R.id.trip_item_amount)
        val numberofpeople = itemView.findViewById<TextView>(R.id.trip_item_noofpeople)
        val perheadcost= itemView.findViewById<TextView>(R.id.trip_item_perhead_amount)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.tripplanning_item,parent,false))

        viewHolder.itemView.setOnLongClickListener(View.OnLongClickListener {
//            listener.dialogBox(allSubject[viewHolder.adapterPosition])
            true // <- set to true
        })
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val collect = allSubject[position]

        holder.itemTitle.text = collect.item_name
        holder.numberofpeople.text = collect.numberOfPeople
        holder.perheadcost.text = collect.perheadCost
        holder.total_amount.text = collect.amount_spent
    }

    override fun getItemCount(): Int {
        return allSubject.size
    }
    fun updateSubjectList(newlist:List<TripEntity>){
        allSubject.clear()
        allSubject.addAll(newlist)
        notifyDataSetChanged()
    }

}
