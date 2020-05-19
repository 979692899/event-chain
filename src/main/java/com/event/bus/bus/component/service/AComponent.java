package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.ABean;
import com.event.bus.bus.component.bean.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("A_COMPONENT")
public class AComponent extends AbstractComponent<ABean> implements IComponent<ABean> {

    @Override
    protected boolean filter(Event<ABean> event) {
        if (StringUtils.isEmpty(event.getUuid())) {
            return false;
        }
        return !event.getUuid().contains("A_COMPONENT");
    }

    @Override
    protected void create(Event<ABean> event) {
        System.out.println("A create");
    }

    @Override
    protected void delete(Event<ABean> event) {
        System.out.println(event.getContext().getName());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A delete");
    }

    @Override
    protected void add(Event<ABean> event) {
        System.out.println(event.getContext().getName());
        System.out.println("A add");
    }

    @Override
    protected void update(Event<ABean> event) {
        System.out.println("A update");
    }

    @Override
    protected void clean(Event<ABean> event) {
        System.out.println("A clean");
    }
}
