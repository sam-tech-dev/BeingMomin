package com.beingmomin.mominapp.ui.appModule.detailedPerson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.ExtraDetail
import kotlinx.android.synthetic.main.layout_extra_detail_view.view.*

class ExtraDetailAdapter : RecyclerView.Adapter<ExtraDetailAdapter.viewHolder>() {

    lateinit var context: Context
    lateinit var listOfExtraDetails : List<ExtraDetail>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context = parent.context
        val view= LayoutInflater.from(context).inflate(R.layout.layout_extra_detail_view,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(::listOfExtraDetails.isInitialized) listOfExtraDetails.size else 0
     }

    fun updateExtraDetailList(list : List<ExtraDetail>){
        this.listOfExtraDetails= list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val extraDetail = listOfExtraDetails.get(position)
        holder.extraDetailText.text= extraDetail.info
     }

    class viewHolder constructor(view :View): RecyclerView.ViewHolder(view){
        var extraDetailText = view.tv_extra_detail
    }

}