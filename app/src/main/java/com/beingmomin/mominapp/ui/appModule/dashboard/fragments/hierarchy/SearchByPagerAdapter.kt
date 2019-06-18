package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SearchByPagerAdapter constructor(context: Context?, fm: FragmentManager?) :FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        when(position){

            0-> return SearchByPersonFragment()

            1-> return SearchByLocalityFragment()

            else-> return SearchByPersonFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0-> return "Search by Person"

            1-> return "Search by Locality"

            else-> return ""
        }
    }
}