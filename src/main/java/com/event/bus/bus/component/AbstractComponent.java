package com.event.bus.bus.component;

import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.ResourceBase;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractComponent<Listener extends ResourceBase> implements IComponent<Listener> {

    @Autowired
    private EventBusCenter<Listener> eventBusCenter;

    @Override
    @Subscribe
    public void onEvent(Event<Listener> event) {
        switch (event.getEventType()) {
            case CREATE:
                create(event);
                break;
            case DELETE:
                delete(event);
                break;
            case ADD:
                add(event);
                break;
            case UPDATE:
                update(event);
                break;
            case CLEAN:
                clean(event);
                break;
            default:
                break;
        }
    }

    protected abstract boolean filter(Event<Listener> event);

    protected abstract void create(Event<Listener> event);

    protected abstract void delete(Event<Listener> event);

    protected abstract void add(Event<Listener> event);

    protected abstract void update(Event<Listener> event);

    protected abstract void clean(Event<Listener> event);

    /**
     * 传播事件
     * @param event 事件变种
     */
    protected void dispatchEvent(Event<Listener> event) {
        if (CollectionUtils.isNotEmpty(event.getChildren())) {
            for (Event<Listener> child : event.getChildren()) {
                eventBusCenter.postAsync(child);
            }
        }
    }
}
