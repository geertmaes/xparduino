package com.cegeka.xparduino.componentaction.handler;

import com.cegeka.xparduino.componentaction.handler.impl.BaseLedComponentHandler;
import com.cegeka.xparduino.componentaction.handler.impl.NoopComponentHandler;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;

public class ComponentHandlerFactory {

    private static final Map<ComponentType, ComponentHandler> componentActionHandlerMap
            = new ImmutableMap.Builder<ComponentType, ComponentHandler>()
                .put(BASE_LED, new BaseLedComponentHandler())
                .build();

    public ComponentHandler create(Component component) {
        return componentActionHandlerMap.getOrDefault(component.getType(), new NoopComponentHandler());
    }

}
