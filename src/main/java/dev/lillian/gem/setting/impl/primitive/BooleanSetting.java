package dev.lillian.gem.setting.impl.primitive;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.lillian.gem.setting.AbstractSetting;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class BooleanSetting extends AbstractSetting<Boolean> {
    public BooleanSetting(@NotNull Object parent, @NotNull Field field) {
        super(parent, field);
    }

    @Override
    public void writeJson(@NotNull ObjectNode node) {
        node.put(name, getValue());
    }

    @Override
    public void readJson(@NotNull JsonNode node) {
        setValue(node.get(name).asBoolean());
    }
}
