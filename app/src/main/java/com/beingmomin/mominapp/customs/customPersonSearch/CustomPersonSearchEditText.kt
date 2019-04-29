package com.beingmomin.mominapp.customs.customPersonSearch

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.beingmomin.mominapp.BeingMomin
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.SearchPersonApiBody
import com.beingmomin.mominapp.utils.AppConstants
import com.beingmomin.mominapp.utils.rx.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_person_search_dialog.*


class CustomPersonSearchEditText : androidx.appcompat.widget.AppCompatEditText {

    var adapter = ShowPersonAdapter(this)
    val schedulerProvider = AppSchedulerProvider()
    lateinit var locality: String
    lateinit var gender: String
    lateinit var title: String

    constructor(context: Context) : super(context) {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Regular.ttf")

        this.setOnClickListener {
            createDialog(context)
        }
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Regular.ttf")
        this.setOnClickListener {
            createDialog(context)
        }

    }


    fun createDialog(context: Context) {

        val searchPersonApiBody = SearchPersonApiBody("", locality, gender)
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.layout_person_search_dialog)
        dialog.setTitle("Search " + title)
        val wmlp = dialog.window!!.attributes
        wmlp.width = android.view.ViewGroup.LayoutParams.MATCH_PARENT
        wmlp.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT

        dialog.et_search_text.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchPersonApiBody.searchName = query
                searchPerson(searchPersonApiBody)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        dialog.rcv_searched_persons.layoutManager = LinearLayoutManager(context)
        adapter.dialog = dialog
        dialog.rcv_searched_persons.adapter = adapter

        dialog.show()
    }


    fun searchPerson(request: SearchPersonApiBody) {

        CompositeDisposable().add(BeingMomin.getInstance().dataManager.doSearchPersonApiCall(request)
                .doOnSuccess({ response ->
                    Log.d(AppConstants.APP_TAG, response.toString())
                })
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    adapter.updatePersonList(response.persons)

                }, { throwable ->
                    Log.d(AppConstants.APP_TAG, throwable.toString())
                }))
    }


}