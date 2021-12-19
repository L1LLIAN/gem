package dev.lillian.gem.module.impl.movement.speed.impl;

import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.events.misc.TickEvent;
import dev.lillian.gem.mixin.client.IMinecraftMixin;
import dev.lillian.gem.module.AbstractModuleMode;
import dev.lillian.gem.module.impl.movement.speed.SpeedModule;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public final class TimerSpeedMode extends AbstractModuleMode<SpeedModule> {
    public TimerSpeedMode(@NotNull SpeedModule parent) {
        super(parent, "timer", "Timer");
    }

    @Subscribe
    void onTick(TickEvent event) {
        ((IMinecraftMixin) Minecraft.getMinecraft()).getTimer().timerSpeed = 10 * ThreadLocalRandom.current().nextFloat();
    }
}

