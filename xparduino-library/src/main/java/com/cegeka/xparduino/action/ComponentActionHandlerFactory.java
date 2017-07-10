package com.cegeka.xparduino.action;

import com.cegeka.xparduino.action.impl.BaseLedComponentActionHandler;
import com.cegeka.xparduino.action.impl.NoopComponentActionHandler;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;

public class ComponentActionHandlerFactory {

    private static final Map<ComponentType, ComponentActionHandler> componentActionHandlerMap
            = new ImmutableMap.Builder<ComponentType, ComponentActionHandler>()
                .put(BASE_LED, new BaseLedComponentActionHandler())
                .build();

    public ComponentActionHandler create(Component component) {
        return componentActionHandlerMap.getOrDefault(component.getType(), new NoopComponentActionHandler());
    }

}
