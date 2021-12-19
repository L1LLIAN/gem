package dev.lillian.gem.file.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.lillian.gem.Gem;
import dev.lillian.gem.file.IFile;
import dev.lillian.gem.module.AbstractModule;
import org.jetbrains.annotations.NotNull;

public final class BindsFile implements IFile {
    @NotNull
    @Override
    public String getName() {
        return "binds";
    }

    @Override
    public void load(@NotNull JsonNode node) {
        for (AbstractModule module : Gem.get().getModuleManager().getModuleMap().values()) {
            if (!node.has(module.getName())) {
                continue;
            }

            int bind = node.get(module.getName()).asInt();
            module.setBind(bind);
        }
    }

    @NotNull
    @Override
    public ObjectNode getData() {
        ObjectNode node = new ObjectNode(JsonNodeFactory.instance);

        for (AbstractModule module : Gem.get().getModuleManager().getModuleMap().values()) {
            node.put(module.getName(), module.getBind());
        }

        return node;
    }
}
