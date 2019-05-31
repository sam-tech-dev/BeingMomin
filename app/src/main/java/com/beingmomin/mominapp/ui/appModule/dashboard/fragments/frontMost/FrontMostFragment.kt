package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.frontMost

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.FragmentHomeBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import javax.inject.Inject


class FrontMostFragment : BaseFragment<FragmentHomeBinding, FrontMostViewModel>(), FrontMostNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mFrnotMostViewModel:FrontMostViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_frontmost

    override val viewModel: FrontMostViewModel
        get() {
            if(!::mFrnotMostViewModel.isInitialized){
                mFrnotMostViewModel = ViewModelProviders.of(this, factory).get(FrontMostViewModel::class.java)
            }
            return mFrnotMostViewModel
        }

    override fun goBack() {
        baseActivity?.onFragmentDetached(TAG)
    }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFrnotMostViewModel.setNavigator(this)
    }

    companion object {
        val TAG = FrontMostFragment::class.java.simpleName

        fun newInstance(): FrontMostFragment {
            val args = Bundle()
            val fragment = FrontMostFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun handleError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
