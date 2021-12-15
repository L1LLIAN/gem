package dev.lillian.gem.module.impl.visual;

import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.events.render.RenderOverlayEvent;
import dev.lillian.gem.font.AWTFontRenderer;
import dev.lillian.gem.module.AbstractModule;

import java.awt.*;

public final class HUDModule extends AbstractModule {
    private static final AWTFontRenderer VERDANA_21 = new AWTFontRenderer(new Font("Verdana", Font.PLAIN, 21), true);

    public HUDModule() {
        super("hud", "HUD");
        setBind(3, true); // mouse 4
    }

    @Subscribe
    void onRenderOverlay(Class<RenderOverlayEvent> ignored) {
        VERDANA_21.drawString("Gem", 12, 12, Color.RED.getRGB(), false);
    }
}
