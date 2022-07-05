package com.anshad.whetherapp.utils

import android.view.View

interface ItemViewPosClickCallback <T> {
    fun onViewClick(view: View?, item: T, position:Int, status: Boolean)
}