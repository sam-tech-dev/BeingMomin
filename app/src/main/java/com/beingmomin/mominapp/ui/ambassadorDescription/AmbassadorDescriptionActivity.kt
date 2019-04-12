package com.beingmomin.mominapp.ui.ambassadorDescription

import android.content.Intent
import android.os.Bundle
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.databinding.ActivityAmbassadorDescriptionBinding
import com.beingmomin.mominapp.ui.localitySearch.LocalitySearchActivity
import com.beingmomin.mominapp.ui.base.BaseActivity
import javax.inject.Inject

class AmbassadorDescriptionActivity : BaseActivity<ActivityAmbassadorDescriptionBinding, AmbassadorDescriptionViewModel>(),AmbassadorDescriptionNavigator{


    @Inject
    lateinit var ambassadorDescriptionViewModel: AmbassadorDescriptionViewModel

    private lateinit var mActivityAmbassadorDescriptionBinding: ActivityAmbassadorDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setNavigator(this)
        mActivityAmbassadorDescriptionBinding = getViewDataBinding()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_ambassador_description

    override val viewModel: AmbassadorDescriptionViewModel
        get() = ambassadorDescriptionViewModel


    override fun openSearchLocalityActivity() {
        startActivity(Intent(this,  LocalitySearchActivity::class.java))
    }

}
