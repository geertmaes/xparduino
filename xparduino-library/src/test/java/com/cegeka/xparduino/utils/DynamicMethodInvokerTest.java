package com.cegeka.xparduino.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicMethodInvokerTest {

    @Test
    public void dispatch() throws Exception {
        TestClass destination = new TestClass();
        DynamicMethodInvoker invoker = new DynamicMethodInvoker("on");

        invoker.invoke(destination, 5);

        assertThat(destination.invoked).isTrue();
    }

    @Test
    public void dispatch_WhenNoMethodWithMatchingParameterTypeFound_ThenDoesNothing() throws Exception {
        TestClassWithoutOnMethod destination = new TestClassWithoutOnMethod();
        DynamicMethodInvoker invoker = new DynamicMethodInvoker("on");

        invoker.invoke(destination, "5");

        assertThat(destination.invoked).isFalse();
    }

    @Test
    public void dispatch_WhenNoMethodWithMatchingNameFound_ThenDoesNothing() throws Exception {
        TestClassWithoutOnMethod destination = new TestClassWithoutOnMethod();
        DynamicMethodInvoker invoker = new DynamicMethodInvoker("on");

        invoker.invoke(destination, 5);

        assertThat(destination.invoked).isFalse();
    }

    private static class TestClass {

        private boolean invoked = false;

        public void on(Integer integer) {
            invoked = true;
        }
    }

    private static class TestClassWithoutOnMethod {

        private boolean invoked = false;

        public void method(Integer integer) {
            invoked = true;
        }
    }

}