package dev.lillian.gem.setting;

import dev.lillian.gem.module.AbstractModule;
import dev.lillian.gem.module.AbstractModuleMode;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SettingManager {
    private final Map<String, Set<AbstractSetting<?>>> settings = new HashMap<>();

    public void register(@NotNull AbstractModule module) {
        settings.put(module.getName(), SettingFactory.createAll(module));
    }

    public void register(@NotNull AbstractModuleMode<?> mode) {
        settings.put(mode.getParent().getName() + ":" + mode.getName(), SettingFactory.createAll(mode));
    }

    @NotNull
    public Set<AbstractSetting<?>> getSettings(@NotNull AbstractModule module) {
        return settings.getOrDefault(module.getName(), Collections.emptySet());
    }

    @NotNull
    public Set<AbstractSetting<?>> getSettings(@NotNull AbstractModuleMode<?> mode) {
        return settings.getOrDefault(mode.getParent().getName() + ":" + mode.getName(), Collections.emptySet());
    }
}
