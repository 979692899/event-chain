package com.event.bus.bus.component;

import com.event.bus.bus.component.bean.Event;
import com.event.bus.bus.component.bean.ResourceBase;

/**
 * 组件接口，定义了onEvent方法，所有组件都需要实现该接口
 * @param <Listener>
 */
public interface IComponent<Listener extends ResourceBase> {

    /**
     * 监听事件消息
     *
     * @param event 事件
     */
    void onEvent(Event<Listener> event);

}
