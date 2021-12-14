package dev.lillian.gem.mixin.gui;

import dev.lillian.gem.Gem;
import dev.lillian.gem.event.events.render.RenderOverlayEvent;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameForge.class)
class GuiIngameForgeMixin {
    @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", ordinal = 1))
    void renderGameOverlay(float partialTicks, CallbackInfo ci) {
        Gem.get().getEventBus().post(RenderOverlayEvent.class);
    }
}
