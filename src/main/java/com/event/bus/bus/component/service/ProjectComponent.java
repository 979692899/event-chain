package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.comtext.CompanyContext;
import com.event.bus.bus.component.comtext.ExtraContext;
import com.event.bus.bus.component.bean.ProjectBean;
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
        System.out.println("Project create");
    }

    @Override
    protected void delete(Event<ProjectBean> event) {
        System.out.println("Project delete");
    }

    @Override
    protected void add(Event<ProjectBean> event) {
        System.out.println(event.getContext().getName());
        CompanyContext context = (CompanyContext) event.getExtraContext();
        System.out.println(context.getCompanyBean());
        System.out.println("Project add");
    }

    @Override
    protected void update(Event<ProjectBean> event) {
        System.out.println("Project update");
    }

    @Override
    protected void clean(Event<ProjectBean> event) {
        System.out.println("Project clean");
    }

}
