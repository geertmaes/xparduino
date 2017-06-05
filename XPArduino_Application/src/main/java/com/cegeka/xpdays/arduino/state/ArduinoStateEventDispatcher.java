package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import static org.reflections.ReflectionUtils.getMethods;

public class ArduinoStateEventDispatcher implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoStateEventDispatcher.class);
    private static final String METHOD_INVOCATION_EXCEPTION = "Failed to invoke method ({}) of class ({}) for parameter ({})";

    private final ArduinoState arduinoState;

    public ArduinoStateEventDispatcher(ArduinoState arduinoState) {
        this.arduinoState = arduinoState;
    }

    public void on(Event event) {
        Collection<ComponentState> componentStates = arduinoState.getComponentStates();

        componentStates.stream()
                .filter(state -> state.getPin() == event.getPin())
                .forEach(state -> dispatch(state, event));
    }

    private void dispatch(ComponentState state, Event event) {
        getMethodsWithParameterType(state, event.getClass())
                .forEach(method -> invokeMethod(state, method, event));
    }

    @SuppressWarnings("unchecked")
    private Set<Method> getMethodsWithParameterType(Object obj, Class parameterType) {
        return getMethods(obj.getClass(), method -> methodHasParameter(method, parameterType));
    }

    private boolean methodHasParameter(Method method, Class<?> parameterType) {
        Class<?>[] parameters = method.getParameterTypes();
        return parameters.length > 0 && parameters[0].isAssignableFrom(parameterType);
    }

    private void invokeMethod(Object obj, Method method, Object parameters) {
        try {
            method.invoke(obj, parameters);
        } catch (Exception e) {
            LOGGER.warn(METHOD_INVOCATION_EXCEPTION, method, obj.getClass().getSimpleName(), parameters, e);
        }
    }
}
