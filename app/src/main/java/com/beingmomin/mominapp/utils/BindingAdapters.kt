package com.beingmomin.mominapp.utils

import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.data.network.models.Locality
import com.beingmomin.mominapp.utils.extension.getParentActivity
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


@BindingAdapter("adapter")
fun setAdapter(view: androidx.recyclerview.widget.RecyclerView, adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = value ?: View.VISIBLE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}


@BindingAdapter("bindImageUrl")
fun loadImage(view: CircleImageView, imageUrl: String) {
    if (view.context != null) {
        Glide.with(view.context)
                .load(imageUrl)
                .into(view)
    }

}

@BindingAdapter("bindSuggestionList")
fun setSuggestionList(view: AutoCompleteTextView, listOfSuggestion: MutableLiveData<MutableList<Locality>>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && listOfSuggestion != null) {
        view.setThreshold(1)
        listOfSuggestion.observe(parentActivity, Observer {
            val adapter = ArrayAdapter<Locality>(parentActivity, android.R.layout.simple_list_item_1, it)
            view.setAdapter(adapter)

            view.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    // show all suggestions
                    if (!view.getText().toString().equals(""))
                        adapter.getFilter().filter(view.getText().toString())
                    view.showDropDown()
                    return false
                }
            })
    })


}

}

@BindingAdapter("bindListToSpinner")
fun setListToSpinner(view: AppCompatSpinner, listOfSuggestion: MutableLiveData<MutableList<String>>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && listOfSuggestion != null) {
        listOfSuggestion.observe(parentActivity, Observer {
            val adapter = ArrayAdapter<String>(parentActivity, R.layout.simple_list_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.setAdapter(adapter)
        })
    }
}

@BindingAdapter("bindLocalitiesToSpinner")
fun setLocalitiesToSpinner(view: AppCompatSpinner, listOfSuggestion: MutableLiveData<MutableList<Locality>>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && listOfSuggestion != null) {
        listOfSuggestion.observe(parentActivity, Observer {
            val adapter = ArrayAdapter<Locality>(parentActivity, R.layout.simple_list_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.setAdapter(adapter)
        })
    }
}

