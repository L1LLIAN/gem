package dev.lillian.gem;

import dev.lillian.gem.event.EventBus;
import dev.lillian.gem.module.ModuleManager;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public final class Gem {
    private static final Gem INSTANCE = new Gem();

    private final EventBus eventBus = new EventBus();

    private ModuleManager moduleManager;

    private Gem() {
    }

    @NotNull
    public static Gem get() {
        return INSTANCE;
    }

    public void onStartGame() {
        moduleManager = new ModuleManager(eventBus);
    }
}
