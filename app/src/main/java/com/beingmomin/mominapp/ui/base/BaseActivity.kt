package com.beingmomin.mominapp.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.beingmomin.mominapp.utils.CommonUtils
import com.beingmomin.mominapp.utils.NetworkUtils
import com.beingmomin.mominapp.ui.signIn.SignInActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection


/**
 * Created by amitshekhar on 07/07/17.
 */

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    // TODO
    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities
    //lateinit  var context : Context

    private var mProgressDialog: ProgressDialog? = null

    private lateinit  var viewDataBinding: T

    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V




    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    fun openActivityOnTokenExpire() {
        startActivity(Intent(this, SignInActivity::class.java));
        finish()
    }

   private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }


    fun showToast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }


    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }
}

