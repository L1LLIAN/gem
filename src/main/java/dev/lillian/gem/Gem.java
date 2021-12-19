package dev.lillian.gem;

import dev.lillian.gem.command.TestCommand;
import dev.lillian.gem.event.EventBus;
import dev.lillian.gem.module.ModuleManager;
import dev.lillian.gem.setting.SettingManager;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.core.BaseCommandHandler;

@Getter
@Log4j2
public final class Gem {
    private static final Gem INSTANCE = new Gem();

    private final EventBus eventBus = new EventBus();
    private final SettingManager settingManager = new SettingManager();
    private final ModuleManager moduleManager = new ModuleManager(eventBus, settingManager);
    private final BaseCommandHandler commandHandler = new BaseCommandHandler();

    private Gem() {
    }

    @NotNull
    public static Gem get() {
        return INSTANCE;
    }

    public void onStartGame() {
        commandHandler.register(new TestCommand());

        log.info("Gem loaded");
    }
}
