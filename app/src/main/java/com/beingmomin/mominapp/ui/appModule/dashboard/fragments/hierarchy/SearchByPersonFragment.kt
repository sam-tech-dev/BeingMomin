package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.data.network.models.Locality
import com.beingmomin.mominapp.data.network.models.SearchPersonApiBody
import com.beingmomin.mominapp.databinding.LayoutSearchByPersonBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_search_by_person.view.*
import javax.inject.Inject


class SearchByPersonFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var viewModel: HierarchyViewModel
    var localityId = -1
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: LayoutSearchByPersonBinding = DataBindingUtil.inflate(inflater, R.layout.layout_search_by_person, container, false)
        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(activity!!, factory).get(HierarchyViewModel::class.java)
        }
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.sp_locality.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position > 0)
                    localityId = (parent?.getItemAtPosition(position) as Locality).localityId
            }
        }

        view.sp_gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0)
                    gender = parent?.getItemAtPosition(position) as String
            }
        }


        view.sv_person.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchPerson(SearchPersonApiBody(query, localityId, gender))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        view.rcv_persons.layoutManager = LinearLayoutManager(context)


    }
}
