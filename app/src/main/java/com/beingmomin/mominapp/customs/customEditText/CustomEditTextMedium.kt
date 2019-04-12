package com.beingmomin.mominapp.customs.customEditText

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet


class CustomEditTextMedium : androidx.appcompat.widget.AppCompatEditText {

    constructor(context: Context) : super(context) {
        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Medium.ttf")
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        /*Defining Custom type face*/
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/GT-Walsheim-Medium.ttf")
    }
}
