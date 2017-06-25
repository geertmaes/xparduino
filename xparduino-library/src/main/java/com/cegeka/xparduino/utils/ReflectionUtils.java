package com.cegeka.xparduino.utils;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.reflections.ReflectionUtils.getMethods;

public class ReflectionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);
    private static final String METHOD_INVOCATION_EXCEPTION = "Failed to invoke method ({}) of class ({}) for parameter ({})";

    @SuppressWarnings("unchecked")
    public static void invokeEventAssignableMethods(Object obj, Event event) {
        getMethods(obj.getClass(), method -> isEventMethod(event, method))
                .forEach(method -> invokeMethod(obj, method, event));
    }

    private static boolean isEventMethod(Event event, Method method) {
        return methodHasAnnotation(method, Handle.class)
                && methodHasParameter(method, event.getClass());
    }

    private static boolean methodHasAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    private static boolean methodHasParameter(Method method, Class<?> parameterType) {
        Class<?>[] parameters = method.getParameterTypes();
        return parameters.length > 0
                && parameters[0].isAssignableFrom(parameterType);
    }

    private static void invokeMethod(Object obj, Method method, Object parameters) {
        try {
            method.invoke(obj, parameters);
        } catch (Exception e) {
            LOGGER.warn(METHOD_INVOCATION_EXCEPTION, method, obj.getClass().getSimpleName(), parameters, e);
        }
    }
}
