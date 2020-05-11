package com.event.bus.bus.component;

import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.ResourceBase;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Component
public class EventBusCenter<Listener extends ResourceBase> implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private List<String> uuids = new ArrayList<>();

    private AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EventBusCenter.applicationContext = applicationContext;
    }

    public void postAsync(Event<Listener> event) {
        asyncEventBus.post(event);
    }

    @PostConstruct
    public void init() {
        // 写死数据，可改为从数据库获取
        uuids.add("CAFE_COMPONENT");
        uuids.add("CODE_COMPONENT");
        uuids.add("COMPANY_COMPONENT");
        uuids.add("PROJECT_COMPONENT");

        for (String uuid : uuids) {
            asyncEventBus.register(applicationContext.getBean(uuid));
        }
    }
}
