package com.cegeka.xpdays.arduino.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.reflections.ReflectionUtils.getMethods;

public class ReflectionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);
    private static final String METHOD_INVOCATION_EXCEPTION = "Failed to invoke method ({}) of class ({}) for parameter ({})";

    @SuppressWarnings("unchecked")
    public static Set<Method> getMethodsWithParameterType(Object obj, Class parameterType) {
        return getMethods(obj.getClass(), method -> methodHasParameter(method, parameterType) && Modifier.isPublic(method.getModifiers()));
    }

    private static boolean methodHasParameter(Method method, Class<?> parameterType) {
        Class<?>[] parameters = method.getParameterTypes();
        return parameters.length > 0 && parameters[0].isAssignableFrom(parameterType);
    }

    public static void invokeMethod(Object obj, Method method, Object parameters) {
        try {
            method.invoke(obj, parameters);
        } catch (Exception e) {
            LOGGER.warn(METHOD_INVOCATION_EXCEPTION, method, obj.getClass().getSimpleName(), parameters, e);
        }
    }
}
