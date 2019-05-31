package com.beingmomin.mominapp.customs.customPersonSearch

import android.app.Dialog
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.beingmomin.mominapp.BeingMomin
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.Locality
import com.beingmomin.mominapp.data.network.models.SearchPersonApiBody
import com.beingmomin.mominapp.utils.AppConstants
import com.beingmomin.mominapp.utils.rx.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_person_search_dialog.*


class CustomPersonSearchEditText : androidx.appcompat.widget.AppCompatEditText {

    var adapter = ShowPersonAdapter(this)
    val schedulerProvider = AppSchedulerProvider()
    var locality = ""
     var gender = ""
    lateinit var title: String
    var localityList = mutableListOf<Locality>()

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

        dialog.pb_search_progress.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN )



       /* localityList.clear()
        localityList.add(locality)*/

        val localityAdapter = ArrayAdapter<Locality>(context!!, android.R.layout.simple_spinner_item, localityList)
        localityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialog.sp_locality.setAdapter(localityAdapter)

        dialog.sp_locality.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0) {
                    locality= (parent?.getItemAtPosition(position) as Locality).localityName
                }
            }
        })

        dialog.sp_locality.setSelection(1)


        val genderList = arrayOf("Male", "Female")
        val genderAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, genderList)
        genderAdapter .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialog.sp_gender.setAdapter(genderAdapter)
        dialog.sp_gender.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0) {
                     gender = parent?.getItemAtPosition(position) as String

                }
            }
        })

        if(gender.equals("Male",true)){
            dialog.sp_gender.setSelection(1)
        }else{
            dialog.sp_gender.setSelection(2)
        }

        dialog.et_search_text.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                dialog.pb_search_progress.visibility= View.VISIBLE
                searchPersonApiBody.searchName = query
                searchPersonApiBody.locality=locality
                searchPersonApiBody.gender=gender
                searchPerson(searchPersonApiBody,dialog.pb_search_progress)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        dialog.rcv_searched_persons.layoutManager = LinearLayoutManager(context)
        adapter = ShowPersonAdapter(this)
        dialog.rcv_searched_persons.adapter = adapter

        dialog.show()
    }


    fun searchPerson(request: SearchPersonApiBody, progressBar: ProgressBar) {

        CompositeDisposable().add(BeingMomin.getInstance().dataManager.doSearchPersonApiCall(request)
                .doOnSuccess({ response ->
                    Log.d(AppConstants.APP_TAG, response.toString())
                })
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    progressBar.visibility= View.GONE
                    adapter.updatePersonList(response.persons)

                }, { throwable ->
                    progressBar.visibility= View.GONE
                    Log.d(AppConstants.APP_TAG, throwable.toString())
                }))
    }


    fun getLocalities(localityAdapter: ArrayAdapter<String>) {

        CompositeDisposable().add(BeingMomin.getInstance().dataManager.doGetLocalitiesApiCall()
                .doOnSuccess({ response ->
                    Log.d(AppConstants.APP_TAG, response.toString())
                })
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    localityList= response.localities.sortedWith(compareBy({ it.localityName})).toMutableList()
                    localityAdapter.notifyDataSetChanged()

                }, { throwable ->
                    Log.d(AppConstants.APP_TAG, throwable.toString())
                }))
    }


}