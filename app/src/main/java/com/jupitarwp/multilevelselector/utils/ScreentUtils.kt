package com.jupitarwp.multilevelselector.utils

import android.content.Context
import android.view.WindowManager

// 获取屏幕高度的函数
fun getScreenHeight(context: Context): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val screenHeight = display.height
    return screenHeight
}