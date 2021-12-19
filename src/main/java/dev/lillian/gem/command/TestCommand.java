package dev.lillian.gem.command;

import dev.lillian.gem.lamp.EntityPlayerSPActor;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;

@Command("test")
public class TestCommand {
    @Default
    public void execute(EntityPlayerSPActor actor) {
        actor.reply("Hello!");
    }
}
