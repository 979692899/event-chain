package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.BBean;
import com.event.bus.bus.component.bean.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("B_COMPONENT")
public class BComponent extends AbstractComponent<BBean> implements IComponent<BBean> {

    @Override
    protected boolean filter(Event<BBean> event) {
        if (StringUtils.isEmpty(event.getUuid())) {
            return false;
        }
        return !event.getUuid().contains("B_COMPONENT");
    }

    @Override
    protected void create(Event<BBean> event) {
        System.out.println("B create");
    }

    @Override
    protected void delete(Event<BBean> event) {
        System.out.println("B delete");
    }

    @Override
    protected void add(Event<BBean> event) {
        System.out.println(event.getContext().getName());
        System.out.println("B add");
    }

    @Override
    protected void update(Event<BBean> event) {
        System.out.println("B update");
    }

    @Override
    protected void clean(Event<BBean> event) {
        System.out.println("B clean");
    }

}
