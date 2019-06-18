package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.FragmentHomeBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_hierachy.*
import javax.inject.Inject


class HierarchyFragment : BaseFragment<FragmentHomeBinding, HierarchyViewModel>(), HierarchyNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var mHierarchyViewModel: HierarchyViewModel


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_hierachy

    override val viewModel: HierarchyViewModel
        get() {
            if (!::mHierarchyViewModel.isInitialized) {
                mHierarchyViewModel = ViewModelProviders.of(activity!!, factory).get(HierarchyViewModel::class.java)
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

        vp_hierachy.adapter= SearchByPagerAdapter(context,  getFragmentManager())
        tabLayout_search_by.setupWithViewPager(vp_hierachy)

    }

    override fun handleError(throwable: Throwable) {
        //
    }
}
