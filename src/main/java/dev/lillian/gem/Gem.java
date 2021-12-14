package dev.lillian.gem;

import dev.lillian.gem.event.EventBus;
import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.events.render.RenderOverlayEvent;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public final class Gem {
    private static final Gem INSTANCE = new Gem();

    private final EventBus eventBus = new EventBus();

    private Gem() {
    }

    @NotNull
    public static Gem get() {
        return INSTANCE;
    }

    public void onStartGame() {
        eventBus.subscribe(this);
    }

    @Subscribe
    public void onHudRender(Class<RenderOverlayEvent> ignored) {
        Minecraft.getMinecraft().fontRendererObj.drawString("Gem", 12, 12, Color.RED.getRGB());
    }

    @NotNull
    public EventBus getEventBus() {
        return eventBus;
    }
}