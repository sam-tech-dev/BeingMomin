package com.beingmomin.mominapp.ui.appModule.detailedPerson

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.Member
import kotlinx.android.synthetic.main.layout_family_member_view.view.*



class FamilyMemberAdapter : RecyclerView.Adapter<FamilyMemberAdapter.viewHolder>() {

    lateinit var context: Context
    lateinit var listOfMembers : List<Member>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context = parent.context
        val view= LayoutInflater.from(context).inflate(R.layout.layout_family_member_view,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(::listOfMembers.isInitialized) listOfMembers.size else 0
     }

    fun updateMembersList(list : List<Member>){
        this.listOfMembers= list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val member = listOfMembers.get(position)
        holder.memberName.text= member.name
        holder.memberRealtion.text = member.relation
        holder.memberLayout.setOnClickListener {
            if(member.id!=0){
                val intent= Intent(context,DetailedPersonActivity::class.java)
                intent.putExtra("personId",member.id)
                (context as Activity).finish()
                context.startActivity(intent)
            }else{
               Toast.makeText(context,"Clicked person is not recorded yet..",Toast.LENGTH_LONG).show()
            }
        }
     }

    class viewHolder constructor(view :View): RecyclerView.ViewHolder(view){
        var memberName = view.tv_member_name
        var memberRealtion = view.tv_member_relation
        val memberLayout = view.cnsrt_member_layout
    }

}