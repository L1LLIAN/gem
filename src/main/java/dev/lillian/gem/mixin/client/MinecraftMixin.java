package dev.lillian.gem.mixin.client;

import dev.lillian.gem.Gem;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
class MinecraftMixin {
    @Inject(method = "startGame", at = @At("RETURN"))
    void startGame(CallbackInfo info) {
        Gem.get().onStartGame();
    }
}