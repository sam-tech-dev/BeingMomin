package com.beingmomin.mominapp.utils

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.util.Patterns
import android.widget.EditText
import com.beingmomin.mominapp.R
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*



/**
 * Created by amitshekhar on 07/07/17.
 */

object CommonUtils {

    val timestamp: String
        get() = SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())


    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }


    fun edittextToObservable(editText: EditText):Observable<String>{
        return editText.textChanges().skip(1).map {
            it.toString()
        }
    }


    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun isMobileNumberValid(mobileNumber: String): Boolean {
        return mobileNumber.length == 10
    }


    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

}
