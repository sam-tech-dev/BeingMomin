package com.beingmomin.mominapp.customs.customPersonSearch

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.beingmomin.mominapp.R
import kotlinx.android.synthetic.main.layout_person_search_dialog.*


class CustomPersonSearchEditText : androidx.appcompat.widget.AppCompatEditText {

    var adapter = ShowPersonAdapter()

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

        val dialog = Dialog(context)
        dialog.setContentView(R.layout.layout_person_search_dialog)
        dialog.setTitle("Search Father's Name")
        dialog.et_search_text.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("'az", "query :" + query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        dialog.rcv_searched_persons.layoutManager=LinearLayoutManager(context)
        dialog.rcv_searched_persons.adapter=adapter
        dialog.show()
    }


   /* fun searchPerson() {
        getCompositeDisposable().add(dataManager.doLocalityAmbassadorApiCall()
                .doOnSuccess({ response ->
                    log(response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                    // openMainActivity()
                    listOfLocalities=response.ambassadors
                    localitySearchAdapter.updateLocalityList(response.ambassadors)

                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.handleError(throwable)
                }))
    }*/






}