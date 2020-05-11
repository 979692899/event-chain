package com.event.bus.bus;

import com.event.bus.bus.component.EventBusCenter;
import com.event.bus.bus.component.bean.CafeBean;
import com.event.bus.bus.component.bean.CodeBean;
import com.event.bus.bus.component.bean.CompanyBean;
import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.EventType;
import com.event.bus.bus.component.bean.ProjectBean;
import com.event.bus.bus.component.bean.ResourceBase;
import com.event.bus.bus.tree.TreeNode;
import com.sun.source.tree.BinaryTree;
import com.sun.source.util.Trees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        event.setUuid("CAFE_COMPONENT");
        event.setEventType(EventType.ADD);
        event.setContext(new CodeBean("name"));
        event.setContexts(new ArrayList<>());
        event.setExtraContext("");

        eventBusCenter.postAsync(event);
    }

    /**
     * 树状调用
     *
     *          COMPANY_COMPONENT
     *                 ｜
     *          PROJECT_COMPONENT
     *                /   \
     *              /      \
     *            /         \
     * CODE_COMPONENT      CAFE_COMPONENT
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
        codeEvent.setUuid("CODE_COMPONENT");
        codeEvent.setEventType(EventType.ADD);
        codeEvent.setContext(new CodeBean("code"));
        codeEvent.setContexts(new ArrayList<>());
        codeEvent.setExtraContext("");

        Event<ResourceBase> cafeEvent = new Event<>();
        cafeEvent.setUuid("CAFE_COMPONENT");
        cafeEvent.setEventType(EventType.DELETE);
        cafeEvent.setContext(new CafeBean("cafe"));
        cafeEvent.setContexts(new ArrayList<>());
        cafeEvent.setExtraContext("");

        event.addChildrenEvent(projectEvent);
        projectEvent.addChildrenEvent(cafeEvent);
        projectEvent.addChildrenEvent(codeEvent);

        eventBusCenter.postAsync(event);
    }

}
