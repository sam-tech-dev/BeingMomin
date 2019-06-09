package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.data.network.models.GetFamiliesBody
import com.beingmomin.mominapp.data.network.models.Locality
import com.beingmomin.mominapp.data.network.models.SearchPersonApiBody
import com.beingmomin.mominapp.databinding.FragmentHomeBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_hierachy.*
import kotlinx.android.synthetic.main.fragment_hierachy.view.*
import javax.inject.Inject


class HierarchyFragment : BaseFragment<FragmentHomeBinding, HierarchyViewModel>(), HierarchyNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var mHierarchyViewModel: HierarchyViewModel

    var locality = ""
    var gender = ""


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_hierachy

    override val viewModel: HierarchyViewModel
        get() {
            if (!::mHierarchyViewModel.isInitialized) {
                mHierarchyViewModel = ViewModelProviders.of(this, factory).get(HierarchyViewModel::class.java)
            }
            return mHierarchyViewModel
        }

    override fun goBack() {
        baseActivity?.onFragmentDetached(TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHierarchyViewModel.setNavigator(this)
    }

    companion object {
        val TAG = HierarchyFragment::class.java.simpleName

        fun newInstance(): HierarchyFragment {
            val args = Bundle()
            val fragment = HierarchyFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_request_to_register.setText(Html.fromHtml("Search your locality below \n if your locality not found the <a>click to register </a>"))
        rcv_persons.layoutManager = LinearLayoutManager(context)
        rcv_families.layoutManager= LinearLayoutManager(context)
        view.sp_locality.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position >= 0)
                    locality = (parent?.getItemAtPosition(position) as Locality).localityName
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

        sv_person.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchPerson(SearchPersonApiBody(query, locality, gender))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        et_locality.setOnItemClickListener { parent, view, position, id ->
            viewModel.getFamilies(GetFamiliesBody((parent.getItemAtPosition(position) as Locality).localityId))
        }
    }

    override fun handleError(throwable: Throwable) {
        //
    }
}
