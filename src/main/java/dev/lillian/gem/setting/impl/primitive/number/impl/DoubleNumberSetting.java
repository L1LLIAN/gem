package dev.lillian.gem.setting.impl.primitive.number.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.lillian.gem.setting.impl.primitive.number.AbstractNumberSetting;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class DoubleNumberSetting extends AbstractNumberSetting<Double> {
    public DoubleNumberSetting(@NotNull Object parent, @NotNull Field field) {
        super(parent, field);
    }

    @Override
    public void writeJson(@NotNull ObjectNode node) {
        node.put(name, getValue());
    }

    @Override
    public void readJson(@NotNull JsonNode node) {
        setValue(node.get(name).asDouble());
    }

    @Override
    public void setValue(@NotNull Double value) {
        if (value < min) {
            value = min;
        } else if (value > max) {
            value = max;
        }

        super.setValue(value);
    }
}
