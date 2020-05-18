package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.ABean;
import com.event.bus.bus.component.bean.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("A_COMPONENT")
public class AComponent extends AbstractComponent<ABean, ABean> implements IComponent<ABean> {

    @Override
    protected boolean filter(Event<ABean> event) {
        if (StringUtils.isEmpty(event.getUuid())) {
            return false;
        }
        return !event.getUuid().contains("A_COMPONENT");
    }

    @Override
    protected void create(Event<ABean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("A create");
    }

    @Override
    protected void delete(Event<ABean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println(event.getContext().getName());
        System.out.println("A delete");
        dispatchEvent(event);
    }

    @Override
    protected void add(Event<ABean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println(event.getContext().getName());
        System.out.println("A add");
        dispatchEvent(event);
    }

    @Override
    protected void update(Event<ABean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("A update");
    }

    @Override
    protected void clean(Event<ABean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("A clean");
    }
}
