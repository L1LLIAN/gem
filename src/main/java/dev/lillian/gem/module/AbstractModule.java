package dev.lillian.gem.module;

import dev.lillian.gem.Gem;
import dev.lillian.gem.event.EventBus;
import dev.lillian.gem.model.IToggleable;
import dev.lillian.gem.model.MinecraftInstance;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractModule extends MinecraftInstance implements IToggleable {
    @NotNull
    protected final List<AbstractModuleMode<?>> modes = new LinkedList<>();

    @NotNull
    private final String name;
    @NotNull
    private final String displayName;
    private boolean toggled;

    @Nullable
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
        AbstractModuleMode<?> mode = getCurrentMode();

        if (toggled) {
            // unsub the current mode and module from the event bus
            bus.unsubscribe(this);
            onDisable();

            if (mode != null) {
                bus.unsubscribe(mode);
                mode.onDisable();
            }
        } else {
            bus.subscribe(this);
            onEnable();

            if (mode != null) {
                bus.subscribe(mode);
                mode.onEnable();
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
