package com.jupitarwp.multilevelselector.widget

import android.content.Context
import com.jupitarwp.multilevelselector.R
import com.jupitarwp.multilevelselector.bean.BaseBean
import org.jetbrains.annotations.NotNull

class BottomLevelSelector {

    private var context: Context? = null

    //默认文字，请选择
    private var defaultNameHint: String? = null

    //默认的name文字颜色
    private var defaultNameColor: Int? = null

    //默认的name文字字体大小
    private var defaultNameTextSize: Int? = null

    //列表默认文字颜色
    private var defaultDataColor: Int? = null
    private var defaultIcon: Int? = null
    private var defaultDataTextSize: Int? = null
    private var originalDatas = ArrayList<BaseBean>()

    constructor(
        context: Context?,
        defaultNameHint: String?,
        defaultNameColor: Int?,
        defaultNameTextSize: Int?,
        defaultDataColor: Int?,
        defaultDataTextSize: Int?,
        defaultIcon: Int?,
        originalDatas: ArrayList<BaseBean>
    ) {
        this.context = context
        this.defaultNameHint = defaultNameHint?:"请选择"
        //this.defaultNameColor = defaultNameColor?:context!!.resources.getColor(android@)
        this.defaultNameTextSize = defaultNameTextSize
        this.defaultDataColor = defaultDataColor
        this.defaultDataTextSize = defaultDataTextSize
        this.defaultIcon = defaultIcon
        this.originalDatas = originalDatas
    }

    public fun show(){

    }


    class Builder {
        //上下文呢对象
        private var context: Context? = null

        //默认文字，请选择
        private var defaultNameHint: String? = null

        //默认的name文字颜色
        private var defaultNameColor: Int? = null

        //默认的name文字字体大小
        private var defaultNameTextSize: Int? = null

        //列表默认文字颜色
        private var defaultDataColor: Int? = null

        //列表默认字体字体大小
        private var defaultDataTextSize: Int? = null

        //列表默认选中图表icon
        private var defaultChooseIcon: Int? = null

        private var originalDatas = ArrayList<BaseBean>()

        fun setContext(): Builder {
            this.context = context
            return this
        }


        fun setDefaultNameHint(defaultNameHint: String): Builder {
            this.defaultNameHint = defaultNameHint
            return this
        }

        fun setDefaultNameColor(defaultNameColor: Int): Builder {
            this.defaultNameColor = defaultNameColor
            return this
        }

        fun setDefaultNameTextSize(defaultNameTextSize: Int): Builder {
            this.defaultNameTextSize = defaultNameTextSize
            return this
        }

        fun setDefaultDataColor(defaultDataColor: Int): Builder {
            this.defaultDataColor = defaultDataColor
            return this
        }

        fun setDefaultDataTextSize(defaultDataTextSize: Int): Builder {
            this.defaultDataTextSize = defaultDataTextSize
            return this
        }

        fun setDefaultIcon(defaultIcon: Int): Builder {
            this.defaultChooseIcon = defaultIcon
            return this
        }

        fun setData(originalDatas: ArrayList<BaseBean>): Builder {
            this.originalDatas = originalDatas
            return this
        }


        fun build(): BottomLevelSelector {
            requireNotNull(context) { "context must not be null" }
            return BottomLevelSelector(
                context,
                defaultNameHint,
                defaultNameColor,
                defaultNameTextSize,
                defaultDataColor,
                defaultDataTextSize,
                defaultChooseIcon,
                originalDatas
            )
        }


    }

}

