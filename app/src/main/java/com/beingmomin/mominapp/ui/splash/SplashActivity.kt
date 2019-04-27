package com.beingmomin.mominapp.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivitySplashBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import com.beingmomin.mominapp.ui.main.MainActivity
import com.beingmomin.mominapp.ui.signIn.SignInActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),SplashNavigator{

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override val layoutId: Int
        get() = R.layout.activity_splash

    override val viewModel: SplashViewModel
        get() {
            if(!::splashViewModel.isInitialized){
                splashViewModel= ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
            }
            return splashViewModel
        }

    override val bindingVariable: Int
        get() = BR.viewModel


    override fun openSignInActivity() {
        startActivity(SignInActivity.newIntent(this))
    }

    override fun openMainActivity() {
        startActivity(MainActivity.newIntent(this))
    }
}
