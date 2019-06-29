package com.beingmomin.mominapp.ui.appModule.detailedPerson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.Family
import kotlinx.android.synthetic.main.layout_family_group_child.view.*

class FamilyGroupAdapter : RecyclerView.Adapter<FamilyGroupAdapter.viewHolder>() {

    lateinit var context: Context
    lateinit var listOfFamilyGroups: List<Family>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.layout_family_group_child, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (::listOfFamilyGroups.isInitialized) listOfFamilyGroups.size else 0
    }

    fun updateFamilyGroupsList(list: List<Family>) {
        this.listOfFamilyGroups = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val familyGroup = listOfFamilyGroups.get(position)
        holder.familyGroupName.text = familyGroup.group
        holder.groupMemberView.layoutManager = LinearLayoutManager(context)
        val adapter = FamilyMemberAdapter()
        adapter.updateMembersList(familyGroup.members)
        holder.groupMemberView.adapter = adapter
    }

    class viewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        var familyGroupName = view.tv_family_group_name
        var groupMemberView = view.rv_group_members
    }

}