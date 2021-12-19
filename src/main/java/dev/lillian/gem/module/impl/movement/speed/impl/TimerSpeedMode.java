package dev.lillian.gem.module.impl.movement.speed.impl;

import dev.lillian.gem.event.annotation.Subscribe;
import dev.lillian.gem.event.events.misc.TickEvent;
import dev.lillian.gem.module.AbstractModuleMode;
import dev.lillian.gem.module.impl.movement.speed.SpeedModule;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public final class TimerSpeedMode extends AbstractModuleMode<SpeedModule> {
    public TimerSpeedMode(@NotNull SpeedModule parent) {
        super(parent, "timer", "Timer");
    }

    @Override
    public void onDisable() {
        timer().timerSpeed = 1;
    }

    @Subscribe
    void onTick(TickEvent event) {
        timer().timerSpeed = 5 * ThreadLocalRandom.current().nextFloat();
    }
}

