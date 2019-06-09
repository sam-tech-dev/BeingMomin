package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.Ancestor
import com.beingmomin.mominapp.databinding.LayoutFamilyShowBinding
import com.beingmomin.mominapp.utils.ShowFamilyModel


class FamilyAdapter  : RecyclerView.Adapter<FamilyAdapter.viewHolder>() {

    lateinit var context: Context
    lateinit  var listOfAncestors:List<Ancestor>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context=parent.context
        val binding :LayoutFamilyShowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_family_show, parent, false)
        return FamilyAdapter.viewHolder(binding)

    }

    override fun getItemCount(): Int {
        return if(::listOfAncestors.isInitialized) listOfAncestors.size else 0
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val ancestor=listOfAncestors.get(position)
        ancestor.familyCount = position+1
        holder.bind(ancestor)
        holder.binding.cvFamily.setOnClickListener {

        }
    }


    fun updateAncestorList(ancestorList:List<Ancestor>){
        this.listOfAncestors = ancestorList
        notifyDataSetChanged()
    }


    class viewHolder( var binding: LayoutFamilyShowBinding ) : RecyclerView.ViewHolder(binding.root){

        private var viewModel = ShowFamilyModel()
        fun bind(ancestor: Ancestor){
            viewModel.bind(ancestor)
            binding.viewModel = this.viewModel
        }

    }


}