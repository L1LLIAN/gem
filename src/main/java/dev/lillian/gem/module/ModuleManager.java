package dev.lillian.gem.module;

import dev.lillian.gem.event.EventBus;
import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.events.input.KeyboardInputEvent;
import dev.lillian.gem.event.events.input.MouseInputEvent;
import dev.lillian.gem.module.impl.movement.speed.SpeedModule;
import dev.lillian.gem.module.impl.visual.HUDModule;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class ModuleManager {
    private final Map<String, AbstractModule> moduleMap = new HashMap<>();

    public ModuleManager(@NotNull EventBus eventBus) {
        eventBus.subscribe(this);

        register(new HUDModule());
        register(new SpeedModule());

        // temp
        moduleMap.values().forEach(AbstractModule::postLoad);
    }

    private void register(@NotNull AbstractModule module) {
        moduleMap.put(module.getName(), module);
    }

    @Subscribe
    void onMouseInput(MouseInputEvent event) {
        for (AbstractModule module : moduleMap.values()) {
            if (module.isMouseBind() && module.getBind() + 100 == event.getButton()) {
                module.toggle();
            }
        }
    }

    @Subscribe
    void onKeyboardInput(KeyboardInputEvent event) {
        for (AbstractModule module : moduleMap.values()) {
            if (module.isKeyboardBind() && module.getBind() == event.getKey()) {
                module.toggle();
            }
        }
    }
}
