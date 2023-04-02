package com.manualcheg.interestingfacts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
        .inflate(R.layout.item_fact_view,parent,false)
    ) {
    private val text = itemView.findViewById<TextView>(R.id.text_fact)
    private val discipline = itemView.findViewById<TextView>(R.id.text_discipline)

    fun bind(model: Fact){
        text.text = model.text
        discipline.text = model.discipline
    }
}