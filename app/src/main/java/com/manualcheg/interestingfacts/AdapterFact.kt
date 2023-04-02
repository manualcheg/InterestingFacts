package com.manualcheg.interestingfacts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterFact(private val facts: List<Fact>) : RecyclerView.Adapter<ViewHolder>()   {
//class AdapterFact() : RecyclerView.Adapter<ViewHolder>()   {
//    var factArrayList = ArrayList<Fact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int =  facts.size
//    override fun getItemCount(): Int =  factArrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(factArrayList[position])
        holder.bind(facts[position])
    }

}