package com.beingmomin.mominapp.ui.ambassadorModule.signIn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivitySignInBinding
import com.beingmomin.mominapp.ui.ambassadorModule.ambassadorDescription.AmbassadorDescriptionActivity
import com.beingmomin.mominapp.ui.base.BaseActivity
import com.beingmomin.mominapp.ui.ambassadorModule.main.MainActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>(), SignInNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var signInViewModel: SignInViewModel

    lateinit var mActivitySignInBinding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        mActivitySignInBinding = getViewDataBinding()
    }

    override fun onResume() {
        super.onResume()


        et_password.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                signInToServer()
                return@OnEditorActionListener true
            }
            false
        })

    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_sign_in

    override val viewModel: SignInViewModel
        get() {
            if (!::signInViewModel.isInitialized) {
                signInViewModel = ViewModelProviders.of(this, factory).get(SignInViewModel::class.java)
            }
            return signInViewModel
        }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SignInActivity::class.java)
        }
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(this, "error" + throwable.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
    }


    override fun openAmbassadorDescriptionActivity() {
        startActivity(Intent(this, AmbassadorDescriptionActivity::class.java))
    }


    override fun signInToServer() {
        val username = et_username.getText().toString()
        val password = et_password.getText().toString()
        if (viewModel.isEmailAndPasswordValid(username, password)) {
            hideKeyboard()
            viewModel.login(username, password)
        } else {
            Toast.makeText(this, "Either username or password is not valid", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(msg: String) {
        tv_error_msg.setText(msg)
    }
}
