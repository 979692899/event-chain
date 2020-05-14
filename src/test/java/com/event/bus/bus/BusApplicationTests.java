package com.event.bus.bus;

import com.event.bus.bus.component.EventBusCenter;
import com.event.bus.bus.component.bean.ABean;
import com.event.bus.bus.component.bean.BBean;
import com.event.bus.bus.component.bean.CompanyBean;
import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.EventType;
import com.event.bus.bus.component.bean.ProjectBean;
import com.event.bus.bus.component.bean.ResourceBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BusApplicationTests {

    @Autowired
    private EventBusCenter<ResourceBase> eventBusCenter;

    /**
     * 直接调用
     */
    @Test
    void contextLoads() {
        Event<ResourceBase> event = new Event<>();
        event.setUuid("B_COMPONENT");
        event.setEventType(EventType.ADD);
        event.setContext(new BBean("name"));
        event.setContexts(new ArrayList<>());
        event.setExtraContext("");

        eventBusCenter.postAsync(event);
    }

    /**
     * 树状调用
     *
     *          COMPANY_COMPONENT
     *                 ｜
     *                 ｜
     *          PROJECT_COMPONENT
     *                /   \
     *              /      \
     *   B_COMPONENT        A_COMPONENT
     */
    @Test
    void tree() {
        Event<ResourceBase> event = new Event<>();
        event.setUuid("COMPANY_COMPONENT");
        event.setEventType(EventType.ADD);
        event.setContext(new CompanyBean("company"));
        event.setContexts(new ArrayList<>());
        event.setExtraContext("");

        Event<ResourceBase> projectEvent = new Event<>();
        projectEvent.setUuid("PROJECT_COMPONENT");
        projectEvent.setEventType(EventType.ADD);
        projectEvent.setContext(new ProjectBean("project"));
        projectEvent.setContexts(new ArrayList<>());
        projectEvent.setExtraContext("");

        Event<ResourceBase> codeEvent = new Event<>();
        codeEvent.setUuid("B_COMPONENT");
        codeEvent.setEventType(EventType.ADD);
        codeEvent.setContext(new BBean("B"));
        codeEvent.setContexts(new ArrayList<>());
        codeEvent.setExtraContext("");

        Event<ResourceBase> cafeEvent = new Event<>();
        cafeEvent.setUuid("A_COMPONENT");
        cafeEvent.setEventType(EventType.DELETE);
        cafeEvent.setContext(new ABean("A"));
        cafeEvent.setContexts(new ArrayList<>());
        cafeEvent.setExtraContext("");

        event.addChildrenEvent(projectEvent);
        projectEvent.addChildrenEvent(cafeEvent);
        projectEvent.addChildrenEvent(codeEvent);

        eventBusCenter.postAsync(event);
    }

}
