package dev.lillian.gem;

import dev.lillian.gem.command.TestCommand;
import dev.lillian.gem.event.EventBus;
import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.events.game.GameQuitEvent;
import dev.lillian.gem.event.events.game.GameStartEvent;
import dev.lillian.gem.file.FileManager;
import dev.lillian.gem.module.ModuleManager;
import dev.lillian.gem.setting.SettingManager;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.core.BaseCommandHandler;

import java.io.File;

@Getter
@Log4j2
public final class Gem {
    public static final File DATA_DIR;

    private static final Gem INSTANCE = new Gem();

    static {
        StringBuilder mcDataDir = new StringBuilder(Minecraft.getMinecraft().mcDataDir.toString());

        if (mcDataDir.length() != 1) {
            mcDataDir.deleteCharAt(mcDataDir.length() - 1);
            mcDataDir.deleteCharAt(mcDataDir.length() - 1);
            DATA_DIR = new File(mcDataDir.toString(), "Sapphire");
        } else {
            DATA_DIR = new File("Sapphire");
        }
    }

    private final EventBus eventBus = new EventBus();
    private final SettingManager settingManager = new SettingManager();
    private final ModuleManager moduleManager = new ModuleManager(eventBus, settingManager);
    private final BaseCommandHandler commandHandler = new BaseCommandHandler();
    private final FileManager fileManager = new FileManager();

    private Gem() {
        eventBus.subscribe(this);
    }

    @NotNull
    public static Gem get() {
        return INSTANCE;
    }

    @Subscribe
    void onGameStart(GameStartEvent event) {
        fileManager.loadAll();

        commandHandler.register(new TestCommand());

        log.info("Gem loaded");
    }

    @Subscribe
    void onGameQuit(GameQuitEvent event) {
        fileManager.saveAll();

        log.info("Gem quit");
    }
}
