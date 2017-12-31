package com.cegeka.xparduino.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.reflections.ReflectionUtils.getAllMethods;

public class DynamicMethodInvoker {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DynamicMethodInvoker.class);
    private static final String METHOD_INVOCATION_EXCEPTION
            = "Failed to invoke method ({}) of class ({}) for parameter ({})";

    private final String methodName;

    public DynamicMethodInvoker(String methodName) {
        this.methodName = methodName;
    }

    @SuppressWarnings("unchecked")
    public void invoke(Object destination, Object obj) {
        getAllMethods(destination.getClass()).stream()
                .filter(methodHasMatchingName())
                .filter(methodIsPublic())
                .filter(methodHasParameter(obj))
                .forEach(invokeMethod(destination, obj));
    }

    private Predicate<Method> methodHasMatchingName() {
        return method -> method.getName().equalsIgnoreCase(methodName);
    }

    private Predicate<Method> methodIsPublic() {
        return method -> Modifier.isPublic(method.getModifiers());
    }

    private Predicate<Method> methodHasParameter(Object toDispatch) {
        return method -> {
            Class<?>[] parameters = method.getParameterTypes();
            return parameters.length > 0
                    && parameters[0].isAssignableFrom(toDispatch.getClass());
        };
    }

    private Consumer<Method> invokeMethod(Object destination, Object parameter) {
        return method -> {
            try {
                method.invoke(destination, parameter);
            } catch (Exception e) {
                LOGGER.warn(METHOD_INVOCATION_EXCEPTION, method, destination.getClass().getSimpleName(), parameter, e);
            }
        };
    }
}
