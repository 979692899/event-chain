package com.event.bus.bus.component.service;

import com.event.bus.bus.component.AbstractComponent;
import com.event.bus.bus.component.IComponent;
import com.event.bus.bus.component.bean.CompanyBean;
import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.comtext.CompanyContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("COMPANY_COMPONENT")
public class CompanyComponent extends AbstractComponent<CompanyBean> implements IComponent<CompanyBean> {

    @Override
    protected boolean filter(Event<CompanyBean> event) {
        if (StringUtils.isEmpty(event.getUuid())) {
            return false;
        }
        return !event.getUuid().contains("COMPANY_COMPONENT");
    }

    @Override
    protected void create(Event<CompanyBean> event) {
        System.out.println("Company create");
    }

    @Override
    protected void delete(Event<CompanyBean> event) {
        System.out.println("Company delete");
    }

    @Override
    protected void add(Event<CompanyBean> event) {
        CompanyBean companyBean = new CompanyBean("company extra");
        System.out.println(event.getContext().getName());
        System.out.println("Company add");
        event.setExtraContext(new CompanyContext(companyBean));
    }

    @Override
    protected void update(Event<CompanyBean> event) {
        System.out.println("Company update");
    }

    @Override
    protected void clean(Event<CompanyBean> event) {
        System.out.println("Company clean");
    }

}
