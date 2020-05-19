package com.event.bus.bus.component.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Event<T extends ResourceBase> {

    private String uuid;

    private EventType eventType;

    private T context;

    private List<T> contexts;

    private List<Event<T>> children = new ArrayList<>();

    /**
     * 附加参数，事件链中上下游传递参数的一种折中参数，主要用与context和contexts不能满足的情况
     */
    private ExtraContext extraContext;

    public void addChildrenEvent(Event<T> event) {
        this.children.add(event);
    }

    public Event<T> generateChildrenEvent(String uuid, EventType eventType,
                                 T context, ArrayList<T> contexts, ExtraContext extraContext) {
        Event<T> event = new Event<>();
        event.setUuid(uuid);
        event.setEventType(eventType);
        event.setContext(context);
        event.setContexts(contexts);
        event.setExtraContext(extraContext);
        return event;
    }

    public ExtraContext getExtraContext() {
        if (this.extraContext == null) {
            return new ExtraContext();
        }
        return this.extraContext;
    }
}
