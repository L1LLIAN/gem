package dev.lillian.gem.event;

import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.subscriber.IEventSubscriber;
import dev.lillian.gem.event.subscriber.impl.MethodEventSubscriber;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.*;

public final class EventBus {
    private final Map<Class<?>, Set<IEventSubscriber>> subscriptions = new HashMap<>();

    public void subscribe(@NotNull Object parent) {
        for (Method method : parent.getClass().getDeclaredMethods()) {
            if (method.getParameterCount() != 1 || !method.isAnnotationPresent(Subscribe.class)) {
                continue;
            }

            Class<?> eventClass = method.getParameterTypes()[0];
            MethodEventSubscriber methodEventSubscriber = new MethodEventSubscriber(parent, method);

            subscriptions.compute(eventClass, (eventClass1, subscribers) -> {
                if (subscribers == null) {
                    subscribers = new HashSet<>();
                }

                subscribers.add(methodEventSubscriber);

                return subscribers;
            });
        }
    }

    public void unsubscribe(@NotNull Object parent) {
        for (Method method : parent.getClass().getDeclaredMethods()) {
            if (method.getParameterCount() != 1 || !method.isAnnotationPresent(Subscribe.class)) {
                continue;
            }

            Class<?> eventClass = method.getParameterTypes()[0];
            subscriptions.computeIfPresent(eventClass, (eventClass1, subscribers) -> {
                Iterator<IEventSubscriber> subscriberIterator = subscribers.iterator();
                while (subscriberIterator.hasNext()) {
                    IEventSubscriber subscriber = subscriberIterator.next();
                    if (subscriber instanceof MethodEventSubscriber) {
                        MethodEventSubscriber methodEventSubscriber = (MethodEventSubscriber) subscriber;
                        if (method.equals(methodEventSubscriber.getMethod())) {
                            subscriberIterator.remove();
                            break;
                        }
                    }
                }

                return subscribers;
            });
        }
    }

    public void post(Object event) {
        boolean isClassEvent = event instanceof Class<?>;
        Class<?> type = isClassEvent ? (Class<?>) event : event.getClass();
        if (!subscriptions.containsKey(type)) {
            return;
        }

        // copy bc sometimes we modify the set while iterating causing cme
        Set<IEventSubscriber> subscribers = new HashSet<>(subscriptions.get(type));
        for (IEventSubscriber subscriber : subscribers) {
            subscriber.invoke(isClassEvent ? null : event);
        }
    }
}
