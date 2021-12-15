package dev.lillian.gem.mixin.client;

import dev.lillian.gem.Gem;
import dev.lillian.gem.event.events.input.KeyboardInputEvent;
import dev.lillian.gem.event.events.input.MouseInputEvent;
import dev.lillian.gem.event.events.misc.TickEvent;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
class MinecraftMixin {
    @Inject(method = "startGame", at = @At("RETURN"))
    void startGame(CallbackInfo ci) {
        Gem.get().onStartGame();
    }

    @Inject(method = "runTick", at = @At(value = "HEAD"))
    void runTick(CallbackInfo ci) {
        Gem.get().getEventBus().post(TickEvent.class);
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V", ordinal = 0))
    void runTickMouse(CallbackInfo ci) {
        int button = Mouse.getEventButton(); // -1 = nothing
        boolean state = Mouse.getEventButtonState(); // false = released
        if (button == -1 || state) {
            return;
        }

        Gem.get().getEventBus().post(new MouseInputEvent(button));
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V", ordinal = 1))
    void runTickKeyboard(CallbackInfo ci) {
        int key = Keyboard.getEventKey(); // -1 = nothing
        boolean state = Keyboard.getEventKeyState(); // false = released
        if (key == -1 || state) {
            return;
        }

        Gem.get().getEventBus().post(new KeyboardInputEvent(key));
    }
}