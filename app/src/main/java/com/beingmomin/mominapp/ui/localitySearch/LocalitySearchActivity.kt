package com.beingmomin.mominapp.ui.localitySearch

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.databinding.ActivitySearchLocalityBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search_locality.*
import javax.inject.Inject
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.text.Editable
import com.beingmomin.mominapp.ui.applyForAmbassador.ApplyForAmbassadorActivity
import com.beingmomin.mominapp.ui.showLocality.ShowLocalityActivity


class LocalitySearchActivity : BaseActivity<ActivitySearchLocalityBinding,LocalitySearchViewModel>() , LocalitySearchNavigator{


    @Inject
    lateinit var localitySearchViewModel: LocalitySearchViewModel

    private lateinit var mActivityLocalitySearchBinding: ActivitySearchLocalityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        mActivityLocalitySearchBinding = getViewDataBinding()

        rcv_localities.layoutManager= LinearLayoutManager(this)

        et_search_locality.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
              //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filterList(s.toString())
            }
        })


        fab_request_for_ambassador.setOnClickListener {
            openApplyForAmbassadorActivity()
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel


    override val layoutId: Int
        get() = R.layout.activity_search_locality


    override val viewModel: LocalitySearchViewModel
        get() = localitySearchViewModel


    override fun handleError(throwable: Throwable) {
        Log.d("az","error "+throwable.toString())
        Toast.makeText(this, "error"+throwable.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun openApplyForAmbassadorActivity() {
        startActivity(Intent(this, ApplyForAmbassadorActivity::class.java))
    }

}
