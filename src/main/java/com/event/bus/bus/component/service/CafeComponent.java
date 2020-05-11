package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.EventBusCenter;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.CafeBean;
import com.event.bus.bus.component.bean.CodeBean;
import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.EventType;
import com.event.bus.bus.component.bean.ResourceBase;
import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.Tree;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("CAFE_COMPONENT")
public class CafeComponent extends AbstractComponent<CafeBean> implements IComponent<CafeBean> {

    @Override
    protected boolean filter(Event<CafeBean> event) {
        if (StringUtils.isEmpty(event.getUuid())) {
            return false;
        }
        return !event.getUuid().contains("CAFE_COMPONENT");
    }

    @Override
    protected void create(Event<CafeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Cafe create");
    }

    @Override
    protected void delete(Event<CafeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println(event.getContext().getName());
        System.out.println("Cafe delete");
        dispatchEvent(event);
    }

    @Override
    protected void add(Event<CafeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println(event.getContext().getName());
        System.out.println("Cafe add");
        dispatchEvent(event);
    }

    @Override
    protected void update(Event<CafeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Cafe update");
    }

    @Override
    protected void clean(Event<CafeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Cafe clean");
    }
}
