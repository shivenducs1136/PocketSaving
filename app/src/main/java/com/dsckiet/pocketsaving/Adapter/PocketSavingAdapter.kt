package com.dsckiet.pocketsaving.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import com.dsckiet.pocketsaving.ui.HomeFragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.math.RoundingMode
import java.text.DateFormat
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList


class PocketSavingAdapter(val context: Context, val listener: HomeFragment): RecyclerView.Adapter<PocketSavingAdapter.NoteViewHolder>() {

    val allSubject = ArrayList<PocketSavingEntity>()
    var stack = ArrayDeque<Boolean>()
    var i=0
    var currdatetime =""
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       val itemTitle= itemView.findViewById<TextView>(R.id.title)
       val rupees= itemView.findViewById<TextView>(R.id.myrupya)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.expenses,parent,false))

        viewHolder.itemView.setOnLongClickListener(View.OnLongClickListener {
//            listener.dialogBox(allSubject[viewHolder.adapterPosition])
            true // <- set to true
        })
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
      val collect = allSubject[position]

        holder.itemTitle.text = collect.title
        if(collect.amountspend.isNullOrEmpty()){
            holder.rupees.text = collect.amountadded
        }
        else{
            holder.rupees.text = collect.amountspend
        }

    }

    override fun getItemCount(): Int {
        return allSubject.size
    }
    fun updateSubjectList(newlist:List<PocketSavingEntity>){
        allSubject.clear()
        allSubject.addAll(newlist)
        notifyDataSetChanged()
    }

}
