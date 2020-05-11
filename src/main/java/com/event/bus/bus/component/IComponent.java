package com.event.bus.bus.component;

import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.ResourceBase;

public interface IComponent<Listener extends ResourceBase> {

    void onEvent(Event<Listener> event);

}
