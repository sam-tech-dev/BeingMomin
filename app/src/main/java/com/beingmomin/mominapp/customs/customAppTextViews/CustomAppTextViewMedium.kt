package com.beingmomin.mominapp.customs.customAppTextViews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet


class CustomAppTextViewMedium : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context) {
        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Medium.ttf")
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Medium.ttf")
    }
}
