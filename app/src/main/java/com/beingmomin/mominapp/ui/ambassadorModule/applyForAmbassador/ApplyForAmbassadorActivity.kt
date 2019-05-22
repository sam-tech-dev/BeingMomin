package com.beingmomin.mominapp.ui.ambassadorModule.applyForAmbassador

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivityApplyForAmbassadorBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import com.beingmomin.mominapp.utils.CommonUtils
import io.reactivex.Observable
import io.reactivex.functions.Function6
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_apply_for_ambassador.*
import javax.inject.Inject


class ApplyForAmbassadorActivity : BaseActivity<ActivityApplyForAmbassadorBinding, ApplyForAmbassadorViewModel>(), ApplyForAmbassadorNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var applyForAmbassadorViewModel: ApplyForAmbassadorViewModel

    private lateinit var mActivityApplyForAmbassadorBinding: ActivityApplyForAmbassadorBinding


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_apply_for_ambassador

    override val viewModel: ApplyForAmbassadorViewModel
        get() {
            if (!::applyForAmbassadorViewModel.isInitialized) {
                applyForAmbassadorViewModel = ViewModelProviders.of(this, factory).get(ApplyForAmbassadorViewModel::class.java)
            }
            return applyForAmbassadorViewModel
        }


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setNavigator(this)
        mActivityApplyForAmbassadorBinding = getViewDataBinding()

        var serial = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            android.os.Build.getSerial()
        } else {
            android.os.Build.SERIAL
        }

        Log.d("az", "Serial No.: " + serial)


        Observable.combineLatest(CommonUtils.edittextToObservable(et_first_name), CommonUtils.edittextToObservable(et_last_name),
                CommonUtils.edittextToObservable(et_mobile_number), CommonUtils.edittextToObservable(et_email_address),
                CommonUtils.edittextToObservable(et_qualification), CommonUtils.edittextToObservable(et_home_address),
                object : Function6<String, String, String, String, String, String, Boolean> {

                    override fun apply(t1: String, t2: String, t3: String, t4: String, t5: String, t6: String): Boolean {
                        return (t1.isNotEmpty() && t2.isNotEmpty() && t3.isNotEmpty() && t4.isNotEmpty()
                                && t5.isNotEmpty() && t6.isNotEmpty() && CommonUtils.isMobileNumberValid(t3) && CommonUtils.isEmailValid(t4))
                    }
                }).subscribe(object : DisposableObserver<Boolean>() {

            override fun onNext(aBoolean: Boolean) {
                /*if (aBoolean) bt_submit.isEnabled = true
                else bt_submit.isEnabled = false*/
            }

            override fun onError(e: Throwable) {
                //
            }

            override fun onComplete() {
                //
            }
        })
    }


    override fun handleError(throwable: Throwable) {
        showToast(throwable.localizedMessage)
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun pressBack() {
        finish()
    }
}
