package com.event.bus.bus.component.bean;

import com.event.bus.bus.component.comtext.ExtraContext;
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
     * 附加参数，事件链中上下游传递参数
     */
    private ExtraContext extraContext;

    public void addChildrenEvent(Event<T> event) {
        this.children.add(event);
    }

    public ExtraContext getExtraContext() {
        if (this.extraContext == null) {
            return new ExtraContext();
        }
        return this.extraContext;
    }
}
