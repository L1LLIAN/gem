package dev.lillian.gem.lamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.command.CommandActor;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class EntityPlayerSPActor implements CommandActor {
    private final EntityPlayerSP player;

    @Override
    public @NotNull
    String getName() {
        return player.getName();
    }

    @Override
    public @NotNull
    UUID getUniqueId() {
        return player.getUniqueID();
    }

    @Override
    public void reply(@NotNull String message) {
        player.addChatMessage(new ChatComponentText(message));
    }

    @Override
    public void error(@NotNull String message) {
        player.addChatMessage(new ChatComponentText(message));
    }
}
