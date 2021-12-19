package dev.lillian.gem.mixin.entity;

import dev.lillian.gem.Gem;
import dev.lillian.gem.lamp.EntityPlayerSPActor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixin {
    private static final char[] COMMAND_PREFIXES = {'-', '.'};

    @Shadow
    protected Minecraft mc;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String message, CallbackInfo ci) {
        boolean isCommand = false;
        for (char commandPrefix : COMMAND_PREFIXES) {
            if (message.charAt(0) == commandPrefix) {
                isCommand = true;
                break;
            }
        }

        if (isCommand) {
            ci.cancel();

            EntityPlayerSPActor actor = new EntityPlayerSPActor(mc.thePlayer);

            String input = message.substring(1);
            Gem.get().getCommandHandler().dispatch(actor, input);
        }
    }
}
