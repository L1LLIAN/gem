package dev.lillian.gem.event.events.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Called when a Mouse button is pressed
 * ex: LMB, MB4, MB5, etc...
 */
@Getter
@AllArgsConstructor
public final class MouseInputEvent {
    private final int button;
}
