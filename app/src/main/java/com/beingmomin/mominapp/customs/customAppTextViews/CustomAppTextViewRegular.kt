package com.beingmomin.mominapp.customs.customAppTextViews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet


class CustomAppTextViewRegular : androidx.appcompat.widget.AppCompatTextView {


    constructor(context: Context) : super(context) {
        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Regular.ttf")
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Regular.ttf")
    }
}
