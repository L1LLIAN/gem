package dev.lillian.gem.setting.impl.primitive.number.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.lillian.gem.setting.impl.primitive.number.AbstractNumberSetting;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class IntegerNumberSetting extends AbstractNumberSetting<Integer> {
    public IntegerNumberSetting(@NotNull Object parent, @NotNull Field field) {
        super(parent, field);
    }

    @Override
    public void writeJson(@NotNull ObjectNode node) {
        node.put(name, getValue());
    }

    @Override
    public void readJson(@NotNull JsonNode node) {
        setValue(node.get(name).asInt());
    }

    @Override
    public void setValue(@NotNull Integer value) {
        if (value < min) {
            value = (int) min;
        } else if (value > max) {
            value = (int) max;
        }

        super.setValue(value);
    }
}
