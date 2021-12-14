package dev.lillian.gem.event.subscriber;

import org.jetbrains.annotations.NotNull;

public interface IEventSubscriber {
    void invoke(@NotNull Object event);
}
