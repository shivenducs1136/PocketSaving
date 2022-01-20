package com.dsckiet.pocketsaving.Adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.entity.LoanEntity
import com.dsckiet.pocketsaving.entity.TripEntity
import com.dsckiet.pocketsaving.ui.LoanFragment
import com.dsckiet.pocketsaving.ui.TripPlanning


class LoanAdapter(val context: Context, val listener: LoanFragment): RecyclerView.Adapter<LoanAdapter.NoteViewHolder>() {

    val allSubject = ArrayList<LoanEntity>()
    var stack = ArrayDeque<Boolean>()
    var i=0
    var currdatetime =""
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val friendname = itemView.findViewById<TextView>(R.id.myfriend_name)
        val greenbar = itemView.findViewById<ImageView>(R.id.green_attendance_bar)
        val redbar = itemView.findViewById<ImageView>(R.id.red_attandance_bar)
        val loanamount= itemView.findViewById<TextView>(R.id.loan_amount)
        val loandate= itemView.findViewById<TextView>(R.id.loan_date)
        val loanreason= itemView.findViewById<TextView>(R.id.loan_reason)
        val hastakenorgiven= itemView.findViewById<TextView>(R.id.hastakenorgiven)
        val MarkAsPaid = itemView.findViewById<TextView>(R.id.markaspaidbtn)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.loaditem,parent,false))

        viewHolder.itemView.setOnLongClickListener(View.OnLongClickListener {
//            listener.dialogBox(allSubject[viewHolder.adapterPosition])
            true // <- set to true
        })
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val collect = allSubject[position]
        holder.friendname.text = collect.frindname
        holder.loanamount.text = collect.loanamount
        holder.hastakenorgiven.text = "has ${collect.givenortaken}"
        holder.loandate.text = "${ collect.loandate } : "
        holder.loanreason.text = collect.loanreason
        val givenortaken= collect.givenortaken
        Log.e("LAgivenortaken",collect.givenortaken)
        if(givenortaken == "taken"){
            holder.greenbar.visibility = View.VISIBLE
            holder.redbar.visibility = View.INVISIBLE
        }
        if(givenortaken == "given"){
            holder.greenbar.visibility = View.INVISIBLE
            holder.redbar.visibility = View.VISIBLE
        }
        holder.MarkAsPaid.setOnClickListener {

            val builder1 = AlertDialog.Builder(context)
            builder1.setMessage("Marking as paid and deleting due.")
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Ok"
            ) { dialog, id ->

                listener.delete(collect)
                Toast.makeText(context,"Due Cleared", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            builder1.setNegativeButton("Cancel"){
                dialog,id->
                Toast.makeText(context,"Canceled", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }

            val alert11 = builder1.create()
            alert11.show()

        }

    }

    override fun getItemCount(): Int {
        return allSubject.size
    }
    fun updateSubjectList(newlist:List<LoanEntity>){
        allSubject.clear()
        allSubject.addAll(newlist)
        notifyDataSetChanged()
    }

}
