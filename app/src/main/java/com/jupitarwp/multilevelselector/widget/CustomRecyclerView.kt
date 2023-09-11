package com.jupitarwp.multilevelselector.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * 请求父容器不拦截
 */
class CustomRecyclerView : RecyclerView {


    constructor(context: Context) : super(context) {}

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, @Nullable attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {}


    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.onInterceptTouchEvent(e)
    }
}
