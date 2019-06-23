package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.customs.customPersonSearch.ShowPersonItemViewModel
import com.beingmomin.mominapp.data.network.models.Person
import com.beingmomin.mominapp.databinding.LayoutPersonShowBinding
import com.beingmomin.mominapp.ui.appModule.detailedPerson.DetailedPersonActivity


class PersonAdapter  : RecyclerView.Adapter<PersonAdapter.viewHolder>() {

    lateinit var context: Context
    lateinit  var listOfPersons:List<Person>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context=parent.context
        val binding :LayoutPersonShowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_person_show, parent, false)
        return PersonAdapter.viewHolder(binding)

    }

    override fun getItemCount(): Int {
        return if(::listOfPersons.isInitialized) listOfPersons.size else 0
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val person=listOfPersons.get(position)
        holder.bind(person)
        holder.binding.cvPerson.setOnClickListener {
         val intent = DetailedPersonActivity.newIntent(context, person.id)
            context.startActivity(intent)
        }
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