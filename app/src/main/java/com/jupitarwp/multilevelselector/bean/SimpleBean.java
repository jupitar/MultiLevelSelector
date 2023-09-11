package com.jupitarwp.multilevelselector.bean;


import java.util.ArrayList;

/**
 * 为了创建抽象实体类而创建，方便创建对象
 */
public class SimpleBean extends BaseBean {

    private String label;

    private ArrayList<? extends BaseBean> childrens;


    public SimpleBean(String label, ArrayList<? extends BaseBean> childrens) {
        this.label = label;
        this.childrens = childrens;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<? extends BaseBean> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<? extends BaseBean> childrens) {
        this.childrens = childrens;
    }

    @Override
    public String getName() {
        return label;
    }

    @Override
    public ArrayList<? extends BaseBean> getChildren() {
        return childrens;
    }
}
