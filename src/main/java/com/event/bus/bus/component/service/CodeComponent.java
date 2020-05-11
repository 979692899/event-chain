package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.CafeBean;
import com.event.bus.bus.component.bean.CodeBean;
import com.event.bus.bus.component.bean.Event;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("CODE_COMPONENT")
public class CodeComponent extends AbstractComponent<CodeBean> implements IComponent<CodeBean> {

    @Override
    protected boolean filter(Event<CodeBean> event) {
        if (StringUtils.isEmpty(event.getUuid())) {
            return false;
        }
        return !event.getUuid().contains("CODE_COMPONENT");
    }

    @Override
    protected void create(Event<CodeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Code create");
    }

    @Override
    protected void delete(Event<CodeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Code delete");
    }

    @Override
    protected void add(Event<CodeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println(event.getContext().getName());
        System.out.println("Code add");
        dispatchEvent(event);
    }

    @Override
    protected void update(Event<CodeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Code update");
    }

    @Override
    protected void clean(Event<CodeBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Code clean");
    }

}
