package dev.lillian.gem.setting.impl.primitive.number;

import dev.lillian.gem.setting.AbstractSetting;
import dev.lillian.gem.setting.annotation.Clamp;
import dev.lillian.gem.setting.exception.SettingException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public abstract class AbstractNumberSetting<N extends Number> extends AbstractSetting<N> {
    protected final double min;
    protected final double max;

    protected AbstractNumberSetting(@NotNull Object parent, @NotNull Field field) {
        super(parent, field);

        if (!field.isAnnotationPresent(Clamp.class)) {
            throw new SettingException(field, "Missing @Clamp annotation");
        }

        Clamp clamp = field.getAnnotation(Clamp.class);
        this.min = clamp.min();
        this.max = clamp.max();
    }
}
