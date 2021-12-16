package dev.lillian.gem.setting;

import dev.lillian.gem.setting.impl.primitive.BooleanSetting;
import dev.lillian.gem.setting.impl.primitive.number.impl.DoubleNumberSetting;
import dev.lillian.gem.setting.impl.primitive.number.impl.IntegerNumberSetting;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public final class SettingFactory {
    @Nullable
    public static AbstractSetting<?> createAll(@NotNull Object parent, @NotNull Field field) {
        if (field.getType() == boolean.class) {
            return new BooleanSetting(parent, field);
        }

        //<editor-fold desc="Numbers">
        if (field.getType() == int.class) {
            return new IntegerNumberSetting(parent, field);
        }

        if (field.getType() == double.class) {
            return new DoubleNumberSetting(parent, field);
        }
        //</editor-fold>

        return null;
    }

    @NotNull
    @SuppressWarnings("java:S108")
    public static Set<AbstractSetting<?>> createAll(@NotNull Object parent) {
        Set<AbstractSetting<?>> settings = new HashSet<>();

        for (Field field : parent.getClass().getDeclaredFields()) {
            try {
                AbstractSetting<?> setting = createAll(parent, field);
                settings.add(setting);
            } catch (Exception ignored) {
            }
        }

        return settings;
    }
}
