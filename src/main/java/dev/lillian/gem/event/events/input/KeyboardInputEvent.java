package dev.lillian.gem.event.events.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Called when a key is released
 */
@Getter
@AllArgsConstructor
public final class KeyboardInputEvent {
    private final int key;
}
