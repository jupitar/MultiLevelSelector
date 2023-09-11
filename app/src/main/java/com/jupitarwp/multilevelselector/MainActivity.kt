package com.jupitarwp.multilevelselector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jupitarwp.multilevelselector.bean.BaseBean
import com.jupitarwp.multilevelselector.bean.LevelBean

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LevelSelector(this).setDatas(LevelBean.getDatas(), 1, true)

    }
}
