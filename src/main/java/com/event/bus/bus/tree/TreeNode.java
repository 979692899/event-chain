package com.event.bus.bus.tree;

import com.event.bus.bus.component.bean.CodeBean;
import com.event.bus.bus.component.bean.Event;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode<T> implements Serializable {

    protected String val;

    protected TreeNode<T> parent;

    protected List<TreeNode<T>> children = new ArrayList<>();


    public void setChildrenNode(TreeNode<T> codeNode) {
        this.children.add(codeNode);
    }
}
