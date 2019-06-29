package com.beingmomin.mominapp.ui.appModule.detailedPerson

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivityDetailedPersonBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detailed_person.*
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
            val intent = Intent(context, DetailedPersonActivity::class.java)
            intent.putExtra("personId", personId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        activityDetailedPersonBinding = getViewDataBinding()

        rv_extra_details.layoutManager = LinearLayoutManager(this)
        rv_family_group.layoutManager = LinearLayoutManager(this)


        viewModel.loadDetailedPerson(intent.extras)

        tv_know_more.setOnClickListener{
            if(tv_know_more.text.equals("Show more")){
                tv_education_details.visibility = View.VISIBLE
                tv_know_more.setText("Show less")
            }else{
                tv_education_details.visibility = View.GONE
                tv_know_more.setText("Show more")
            }
        }

    }


    override fun onHandleError(throwable: Throwable) {
        showToast(throwable.message!!)
    }

    override fun setProfilePlaceholder(gender: String) {
        if(gender.equals("Male",true))
            iv_profile_pic.setImageDrawable(resources.getDrawable(R.drawable.ic_male_profile_80px))
        else
            iv_profile_pic.setImageDrawable(resources.getDrawable(R.drawable.ic_female_profile_80px))

    }
}
