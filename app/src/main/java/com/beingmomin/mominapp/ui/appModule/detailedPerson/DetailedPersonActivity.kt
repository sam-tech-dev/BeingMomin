package com.beingmomin.mominapp.ui.appModule.detailedPerson

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivityDetailedPersonBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import javax.inject.Inject


class DetailedPersonActivity : BaseActivity<ActivityDetailedPersonBinding, DetailedPersonViewModel>(), DetailedPersonNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var activityDetailedPersonBinding: ActivityDetailedPersonBinding
    lateinit var detailedPersonViewModel: DetailedPersonViewModel


    override val layoutId: Int
        get() = R.layout.activity_detailed_person

    override val viewModel: DetailedPersonViewModel
        get() {
            if (!::detailedPersonViewModel.isInitialized) {
                detailedPersonViewModel = ViewModelProviders.of(this, factory).get(DetailedPersonViewModel::class.java)
            }
            return detailedPersonViewModel
        }

    override val bindingVariable: Int
        get() = BR.viewModel

    companion object {
        fun newIntent(context: Context, personId: Int): Intent {
            val intent= Intent(context, DetailedPersonActivity::class.java)
            intent.putExtra("personId",personId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        activityDetailedPersonBinding = getViewDataBinding()

        viewModel.loadDetailedPerson(intent.extras)
    }


    override fun onHandleError(throwable: Throwable) {
        showToast(throwable.message!!)
    }


}
