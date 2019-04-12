package com.beingmomin.mominapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.databinding.ActivityMainBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var mActivityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        mActivityMainBinding = getViewDataBinding()
    }


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = mainViewModel


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

}