package dev.lillian.gem.module;

import dev.lillian.gem.Gem;
import dev.lillian.gem.event.EventBus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractModule {
    protected final List<AbstractModuleMode<?>> modes = new LinkedList<>();

    private final String name;
    private final String displayName;
    private boolean toggled;

    private String currentMode;

    /**
     * Binds lt or eq to 100 are mouse binds
     * 0 is none
     */
    private int bind;

    private void verifyModeState() {
        if (currentMode == null) {
            // if the currentMode is null and the modes list is empty then the state is valid
            if (modes.isEmpty()) {
                return;
            }

            // this would happen if you didn't have modes for a module and added them after a config had saved
            currentMode = modes.get(0).getName();
        } else {
            // would only happen if you had a mode selected and deleted all modes from the module
            if (modes.isEmpty()) {
                currentMode = null;
                return;
            }

            boolean anyMatch = modes.stream().anyMatch(mode -> currentMode.equals(mode.getName()));

            // if the currentMode doesn't exist any more just pick the first mode
            if (!anyMatch) {
                currentMode = modes.get(0).getName();
            }
        }
    }

    public void toggle() {
        EventBus bus = Gem.get().getEventBus();
        if (toggled) {
            // unsub the current mode and module from the event bus
            bus.unsubscribe(this);
            if (getCurrentMode() != null) {
                bus.unsubscribe(getCurrentMode());
            }
        } else {
            bus.subscribe(this);
            if (getCurrentMode() != null) {
                bus.subscribe(getCurrentMode());
            }
        }

        toggled = !toggled;
    }

    /**
     * Called after this module is loaded from a config
     * Verifies the state of the module
     */
    public void postLoad() {
        verifyModeState();
    }

    @Nullable
    public AbstractModuleMode<?> getCurrentMode() {
        return currentMode == null ? null : modes.stream().filter(mode -> currentMode.equals(mode.getName())).findFirst().orElse(null);
    }

    public boolean isMouseBind() {
        return bind != 0 && bind <= 100;
    }

    public boolean isKeyboardBind() {
        return bind > 0;
    }

    public void setBind(int bind, boolean mouse) {
        this.bind = mouse ? bind - 100 : bind;
    }
}
