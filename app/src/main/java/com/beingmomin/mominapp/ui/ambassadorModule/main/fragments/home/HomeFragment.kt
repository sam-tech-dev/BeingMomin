package com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.FragmentHomeBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mHomeViewModel: HomeViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_home

    override val viewModel: HomeViewModel
        get() {
            if(!::mHomeViewModel.isInitialized){
                mHomeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
            }
            return mHomeViewModel
        }

    override fun goBack() {
        baseActivity?.onFragmentDetached(TAG)
    }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeViewModel.setNavigator(this)
    }

    companion object {
        val TAG = HomeFragment::class.java.simpleName

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun handleError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
