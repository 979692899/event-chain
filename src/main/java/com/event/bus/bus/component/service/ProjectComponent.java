package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.CafeBean;
import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.ProjectBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("PROJECT_COMPONENT")
public class ProjectComponent extends AbstractComponent<ProjectBean> implements IComponent<ProjectBean> {

    @Override
    protected boolean filter(Event<ProjectBean> event) {
        if (StringUtils.isEmpty(event.getUuid())) {
            return false;
        }
        return !event.getUuid().contains("PROJECT_COMPONENT");
    }

    @Override
    protected void create(Event<ProjectBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Project create");

    }

    @Override
    protected void delete(Event<ProjectBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Project delete");
    }

    @Override
    protected void add(Event<ProjectBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println(event.getContext().getName());
        System.out.println("Project add");
        dispatchEvent(event);
    }

    @Override
    protected void update(Event<ProjectBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Project update");
    }

    @Override
    protected void clean(Event<ProjectBean> event) {
        if (filter(event)) {
            return;
        }
        System.out.println("Project clean");
    }

}
