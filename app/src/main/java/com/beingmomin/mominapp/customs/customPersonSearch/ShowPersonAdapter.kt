package com.beingmomin.mominapp.customs.customPersonSearch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.Person
import com.beingmomin.mominapp.databinding.LayoutPersonShowBinding


class ShowPersonAdapter constructor() : RecyclerView.Adapter<ShowPersonAdapter.viewHolder>() {

    lateinit var context: Context
    lateinit  var listOfPersons:List<Person>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context=parent.context
        val binding :LayoutPersonShowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_person_show, parent, false)
        return ShowPersonAdapter.viewHolder(binding)

    }

    override fun getItemCount(): Int {
        return if(::listOfPersons.isInitialized) listOfPersons.size else 0
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(listOfPersons.get(position))
    }

    fun updatePersonList(personList:MutableList<Person>){
        this.listOfPersons = personList
        notifyDataSetChanged()
    }


    class viewHolder( var binding: LayoutPersonShowBinding) : RecyclerView.ViewHolder(binding.root){

        private var viewModel = ShowPersonItemViewModel()
        fun bind(person: Person){
            viewModel.bind(person)
            binding.viewModel = this.viewModel
        }

    }


}