package dev.lillian.gem.event.subscriber;

import org.jetbrains.annotations.Nullable;

public interface IEventSubscriber {
    void invoke(@Nullable Object event);
}
