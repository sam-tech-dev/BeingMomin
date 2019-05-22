package com.beingmomin.mominapp.customs.customDateSelection

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.DatePicker
import java.util.*


class CustomDateSelectionEditText : androidx.appcompat.widget.AppCompatEditText, DatePickerDialog.OnDateSetListener  {


    var myCalendar = Calendar.getInstance();

    constructor(context: Context) : super(context) {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Regular.ttf")

        this.setOnClickListener {

            DatePickerDialog(getContext(), this, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Regular.ttf")
        this.setOnClickListener {
            DatePickerDialog(getContext(), this, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

       /* myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
*/
        val actualMonth= month+1
        this.setText("$dayOfMonth/$actualMonth/$year")
        this.tag="$year-$actualMonth-$dayOfMonth"

    }








}