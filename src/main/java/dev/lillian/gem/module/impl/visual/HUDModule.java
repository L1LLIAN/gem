package dev.lillian.gem.module.impl.visual;

import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.events.render.RenderOverlayEvent;
import dev.lillian.gem.module.AbstractModule;
import net.minecraft.client.Minecraft;

import java.awt.*;

public final class HUDModule extends AbstractModule {
    public HUDModule() {
        super("hud", "HUD");
        setBind(3, true); // mouse 4
    }

    @Subscribe
    void onRenderOverlay(Class<RenderOverlayEvent> ignored) {
        Minecraft.getMinecraft().fontRendererObj.drawString("Gem", 12, 12, Color.RED.getRGB());
    }
}
