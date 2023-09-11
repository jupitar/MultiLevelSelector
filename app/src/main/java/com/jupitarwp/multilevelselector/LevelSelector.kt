package com.jupitarwp.multilevelselector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jupitarwp.multilevelselector.utils.getScreenHeight
import com.jupitarwp.multilevelselector.adapter.DataListAdapter
import com.jupitarwp.multilevelselector.adapter.NameListAdapter
import com.jupitarwp.multilevelselector.bean.BaseBean
import com.jupitarwp.multilevelselector.bean.SimpleBean
import com.jupitarwp.multilevelselector.widget.CustomRecyclerView


class LevelSelector(val context: Context) {

    init {
        initView()
    }

    private var bottomSheetDialog: BottomSheetDialog? = null
    private var rvName: RecyclerView? = null
    private var rvData: CustomRecyclerView? = null
    private var nameAdapter: NameListAdapter? = null
    private var datasListAdapter: DataListAdapter? = null

    //数据
    private var originalDatas = ArrayList<BaseBean>()

    //存储层级到数据的映射
    private var datasMap = HashMap<Int, ArrayList<BaseBean>>()
    private var currentLevel = 1
    private var dataSeletedBean: BaseBean? = null


    //初始化数据
    public fun setDatas(datas: ArrayList<BaseBean>, showDialog: Boolean) {
        setDatas(datas, 1, showDialog)
    }

    //初始化数据
    public fun setDatas(datas: ArrayList<out BaseBean>, level: Int, showDialog: Boolean) {
        this.originalDatas = datas as ArrayList<BaseBean>
        datasListAdapter?.list = datas
        if (dataSeletedBean != null) {
            nameAdapter?.list?.add(nameAdapter?.list?.size!! - 1, dataSeletedBean!!)
        } else {
            nameAdapter?.list?.add(SimpleBean("请选择", ArrayList<BaseBean>()))
        }
        datasListAdapter?.selectedPosition = -1
        datasListAdapter?.notifyDataSetChanged()
        currentLevel = level
        datasMap[currentLevel] = datas
        nameAdapter?.selectedPosition = currentLevel - 1
        nameAdapter?.notifyDataSetChanged()
        if (showDialog) {
            showDialog()
        }
    }

    private fun initView() {
        bottomSheetDialog = BottomSheetDialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_level_selector, null)
        val screenHeight =
            getScreenHeight(context) // 获取屏幕高度
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            screenHeight * 2 / 3
        ) // 创建布局参数
        view.layoutParams = layoutParams // 设置视图的布局参数
        rvName = view.findViewById(R.id.rv_name_list)
        rvData = view.findViewById(R.id.rv_list)
        initRvNameList()
        initRvDataList()
        bottomSheetDialog!!.setContentView(view)
    }

    public fun showDialog() {
        bottomSheetDialog?.show()
    }

    public fun dismissDialog() {
        bottomSheetDialog?.dismiss()
    }

    private fun initRvNameList() {
        nameAdapter = NameListAdapter()
        nameAdapter!!.iNameClicklistsner = object : NameListAdapter.INameClicklistener {

            override fun click(position: Int, levelBean: BaseBean) {
                if ("请选择" != levelBean.name) {
                    currentLevel = position + 1
                    datasListAdapter?.list = datasMap[currentLevel]!!
                    datasListAdapter?.selectedPosition =
                        datasListAdapter?.list?.indexOf(levelBean)!!
                    datasListAdapter?.notifyDataSetChanged()
                }

            }

        }
        rvName?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvName?.adapter = nameAdapter
    }

    private fun initRvDataList() {
        datasListAdapter = DataListAdapter()
        datasListAdapter!!.customRecyclerView = rvData
        datasListAdapter!!.iDataClicklistsner = object : DataListAdapter.IDataClicklistsner {
            override fun click(position: Int, bean: BaseBean) {
                dataSeletedBean = bean
                if (bean.children != null && bean.children.size > 0) {
                    if (dataSeletedBean != null) {
                        //同一层级点击，更新数据
                        if (isExist(dataSeletedBean) && isExist(nameAdapter?.getSelectedBean())) {
                            if (dataSeletedBean != null) {
                                nameAdapter?.updateBean(dataSeletedBean!!)
                                nameAdapter?.removeBeans()
                                nameAdapter?.list?.add(
                                    SimpleBean(
                                        "请选择",
                                        ArrayList<BaseBean>()
                                    )
                                )
                                nameAdapter?.selectedPosition = nameAdapter?.list?.size!! - 1
                                nameAdapter?.notifyDataSetChanged()
                                datasListAdapter?.selectedPosition = -1
                                currentLevel += 1
                                datasMap[currentLevel] = bean!!.children as ArrayList<BaseBean>
                                datasListAdapter!!.list = bean!!.children as ArrayList<BaseBean>
                                datasListAdapter?.notifyDataSetChanged()
                            }
                        } else {
                            setDatas(bean.children as ArrayList<BaseBean>, currentLevel + 1, false)
                        }
                    }

                } else {
                    if (dataSeletedBean != null) {
                        nameAdapter?.updateLastPosition(dataSeletedBean!!)
                    }
                    nameAdapter?.notifyDataSetChanged()
                    Toast.makeText(context, "最后一层:" + bean.name, Toast.LENGTH_LONG).show()
                }

            }
        }
        rvData?.layoutManager = LinearLayoutManager(context)
        rvData?.adapter = datasListAdapter
    }

    private fun isExist(bean: BaseBean?): Boolean {
        val list = datasMap[currentLevel]
        if (list != null && bean != null) {
            for (_bean in list) {
                if (_bean.name == bean!!.name) {
                    return true
                }
            }
        }
        return false
    }

}