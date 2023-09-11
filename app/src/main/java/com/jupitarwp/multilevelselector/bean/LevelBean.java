package com.jupitarwp.multilevelselector.bean;

import java.util.ArrayList;


/**
 * 实际数据，继承BaseBean，重写方法即可
 */
public class LevelBean extends BaseBean {

    private String label;

    private ArrayList<LevelBean> childrens;

    public LevelBean(String label, ArrayList<LevelBean> childrens) {
        this.label = label;
        this.childrens = childrens;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<LevelBean> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<LevelBean> childrens) {
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

    public static ArrayList<? extends BaseBean> getDatas() {
        ArrayList<LevelBean> levelBeans = new ArrayList<>();
        //添加第一级别
        for (int i = 1; i < 24; i++) {
            levelBeans.add(new LevelBean("第" + i + "层", getFirstChild(i + "", 1)));

        }
        return levelBeans;
    }

    private static ArrayList<LevelBean> getFirstChild(String name, int level) {
        ArrayList<LevelBean> levelBeans = new ArrayList<>();
        if (level < 4) {
            level = level + 1;
            for (int i = 1; i < 5; i++) {
                levelBeans.add(new LevelBean("第" + name + "-" + i + "层",
                        getFirstChild(name + "-" + i, level)));
            }
        }

        return levelBeans;
    }


}
