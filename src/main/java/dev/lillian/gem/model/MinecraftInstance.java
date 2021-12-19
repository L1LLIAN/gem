package dev.lillian.gem.model;

import dev.lillian.gem.mixin.client.IMinecraftMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Timer;
import org.jetbrains.annotations.NotNull;

public abstract class MinecraftInstance {
    @NotNull
    protected final Minecraft mc = Minecraft.getMinecraft();

    @NotNull
    protected final IMinecraftMixin mixinMC = (IMinecraftMixin) mc;

    protected final EntityPlayerSP player() {
        return mc.thePlayer;
    }

    protected final WorldClient world() {
        return mc.theWorld;
    }

    protected final Timer timer() {
        return mixinMC.getTimer();
    }
}
