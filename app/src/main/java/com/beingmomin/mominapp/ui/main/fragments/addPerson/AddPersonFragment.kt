package com.beingmomin.mominapp.ui.main.fragments.addPerson

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.FragmentAddPersonBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import javax.inject.Inject


class AddPersonFragment : BaseFragment<FragmentAddPersonBinding, AddPersonViewModel>(), AddPersonNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mAddPersonViewModel: AddPersonViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_add_person

    override val viewModel: AddPersonViewModel
        get() {
            if(!::mAddPersonViewModel.isInitialized){
                mAddPersonViewModel = ViewModelProviders.of(this, factory).get(AddPersonViewModel::class.java)
            }
            return mAddPersonViewModel
        }

    override fun goBack() {
        baseActivity?.onFragmentDetached(TAG)
    }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAddPersonViewModel.setNavigator(this)
    }

    companion object {
        val TAG = AddPersonFragment::class.java.simpleName

        fun newInstance(): AddPersonFragment {
            val args = Bundle()
            val fragment = AddPersonFragment()
            fragment.setArguments(args)
            return fragment
        }
    }
}
