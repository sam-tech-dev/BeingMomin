package com.beingmomin.mominapp.ui.showLocality

import android.os.Bundle
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.databinding.ActivityShowLocalityBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import javax.inject.Inject

class ShowLocalityActivity : BaseActivity<ActivityShowLocalityBinding, ShowLocalityViewModel>(), ShowLocalityNavigator {

    @Inject
    lateinit var showLocalityViewModel: ShowLocalityViewModel

    private lateinit var mActivityShowLocalityBinding: ActivityShowLocalityBinding

    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setNavigator(this)
        mActivityShowLocalityBinding = getViewDataBinding()

        bundle=intent.extras

        showLocalityViewModel.ShowData(bundle)
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_show_locality

    override val viewModel: ShowLocalityViewModel
        get() = showLocalityViewModel

    override fun onResume() {
        super.onResume()
    }
}
