package dev.lillian.gem.event.subscriber.impl;

import dev.lillian.gem.event.subscriber.IEventSubscriber;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MethodEventSubscriber implements IEventSubscriber {
    private final Object parent;
    private final Method method;

    @SuppressWarnings("java:S3011")
    public MethodEventSubscriber(@NotNull Object parent, @NotNull Method method) {
        this.parent = parent;
        this.method = method;

        method.setAccessible(true);
    }

    @Override
    public String toString() {
        return "MethodEventSubscriber{" + "parent=" + parent + ", method=" + method + '}';
    }

    @Override
    public void invoke(@Nullable Object event) {
        try {
            method.invoke(parent, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public Method getMethod() {
        return method;
    }
}
