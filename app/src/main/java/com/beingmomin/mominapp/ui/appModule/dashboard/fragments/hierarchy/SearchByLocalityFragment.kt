package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.data.network.models.GetFamiliesBody
import com.beingmomin.mominapp.data.network.models.Locality
import com.beingmomin.mominapp.databinding.LayoutSearchByLocalityBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_search_by_locality.view.*
import javax.inject.Inject


class SearchByLocalityFragment : Fragment() {


    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var viewModel: HierarchyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: LayoutSearchByLocalityBinding = DataBindingUtil.inflate(inflater, R.layout.layout_search_by_locality, container, false)
        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(activity!!, factory).get(HierarchyViewModel::class.java)
        }
        dataBinding.viewModel= viewModel
        return dataBinding.root

    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.et_locality.setOnItemClickListener { parent, view, position, id ->
            viewModel.getFamilies(GetFamiliesBody((parent.getItemAtPosition(position) as Locality).localityId))
        }

        view.tv_request_to_register.setText(Html.fromHtml("Search your locality below \n if your locality not found the <a>click to register </a>"))

        view.rcv_families.layoutManager = LinearLayoutManager(context)
    }
}
