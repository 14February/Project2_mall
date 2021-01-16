package com.cskaoyan.bean.backstage.goodsbean.catandbrand;

import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: CategoryListBean
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-09 21:19
 */
public class
CategoryListBean {
    /**
     * value : 1005000
     * label : 居家
     * children : [{"value":1008002,"label":"布艺软装"},{"value":1008008,"label":"被枕"},{"value":1008009,"label":"床品件套"},{"value":1008016,"label":"灯具"},{"value":1010003,"label":"地垫"},{"value":1011003,"label":"床垫"},{"value":1011004,"label":"家饰"},{"value":1015000,"label":"家具"},{"value":1017000,"label":"宠物"},{"value":1036000,"label":"夏凉"}]
     */

    private int value;
    private String label;
    private List<ChildrenBean> children;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }
}
