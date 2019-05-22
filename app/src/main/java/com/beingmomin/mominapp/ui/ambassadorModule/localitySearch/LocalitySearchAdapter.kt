package com.beingmomin.mominapp.ui.ambassadorModule.localitySearch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.Ambassador
import com.beingmomin.mominapp.databinding.ViewLocalityChildBinding

class LocalitySearchAdapter : RecyclerView.Adapter<LocalitySearchAdapter.viewHolder>() {

    lateinit var context:Context
    lateinit  var listOfLocalities:List<Ambassador>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context=parent.context
        val binding : ViewLocalityChildBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_locality_child, parent, false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::listOfLocalities.isInitialized) listOfLocalities.size else 0
    }

    fun updateLocalityList(localityList:MutableList<Ambassador>){
        this.listOfLocalities = localityList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(listOfLocalities.get(position))
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class viewHolder (var binding :ViewLocalityChildBinding): RecyclerView.ViewHolder(binding.root){

        private var viewModel = LocalitySearchItemViewModel()

        fun bind(ambassador: Ambassador){
            viewModel.bind(ambassador)
            binding.viewModel = this.viewModel
        }

        /*fun unbind(){
           binding.unbind()
        }*/

    }
}