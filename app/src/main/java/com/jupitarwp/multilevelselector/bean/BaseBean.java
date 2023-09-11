package com.jupitarwp.multilevelselector.bean;

import java.util.ArrayList;

/**
 * 抽象实体类
 */
public abstract class BaseBean {



    //获取标题的文字，必须重写
    public abstract String getName();

    //获取标题下面的list数据，必须重写
    public abstract ArrayList<? extends BaseBean> getChildren();

}
