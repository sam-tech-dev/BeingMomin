package com.beingmomin.mominapp.ui.splash

import android.os.Handler
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.ui.base.BaseViewModel


class SplashViewModel constructor(dataManager: DataManager ,schedulerProvider: SchedulerProvider): BaseViewModel<SplashNavigator>(schedulerProvider) {


    init {

        Handler().postDelayed(
                Runnable {
                    if(dataManager.getIsLogin()){
                        getNavigator()?.openMainActivity()
                    }else{
                        getNavigator()!!.openSignInActivity()
                    }
                }, 2000)


    }

}
