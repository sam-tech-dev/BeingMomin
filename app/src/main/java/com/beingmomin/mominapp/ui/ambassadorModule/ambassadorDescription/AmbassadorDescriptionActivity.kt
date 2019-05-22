package com.beingmomin.mominapp.ui.ambassadorModule.ambassadorDescription

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivityAmbassadorDescriptionBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import com.beingmomin.mominapp.ui.ambassadorModule.localitySearch.LocalitySearchActivity
import javax.inject.Inject

class AmbassadorDescriptionActivity : BaseActivity<ActivityAmbassadorDescriptionBinding, AmbassadorDescriptionViewModel>(),AmbassadorDescriptionNavigator{


    @Inject
    lateinit var factory: ViewModelProviderFactory

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
        get() {
            if (!::ambassadorDescriptionViewModel.isInitialized) {
                ambassadorDescriptionViewModel= ViewModelProviders.of(this, factory).get(AmbassadorDescriptionViewModel::class.java)
            }
            return ambassadorDescriptionViewModel
        }


    override fun openSearchLocalityActivity() {
        startActivity(Intent(this,  LocalitySearchActivity::class.java))
    }

}
