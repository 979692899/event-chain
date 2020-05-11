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

    // 写死数据，可改为从数据库获取
    private List<String> uuids = new ArrayList<String>() {
        {
            add("CAFE_COMPONENT");
            add("CODE_COMPONENT");
            add("COMPANY_COMPONENT");
            add("PROJECT_COMPONENT");
        }
    };

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
        for (String uuid : uuids) {
            asyncEventBus.register(applicationContext.getBean(uuid));
        }
    }
}
