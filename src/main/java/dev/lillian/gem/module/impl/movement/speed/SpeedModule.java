package dev.lillian.gem.module.impl.movement.speed;

import dev.lillian.gem.module.AbstractModule;
import dev.lillian.gem.module.impl.movement.speed.impl.TimerSpeedMode;
import org.lwjgl.input.Keyboard;

public final class SpeedModule extends AbstractModule {
    public SpeedModule() {
        super("speed", "Speed");
        setBind(Keyboard.KEY_F);
        modes.add(new TimerSpeedMode(this));
    }
}
