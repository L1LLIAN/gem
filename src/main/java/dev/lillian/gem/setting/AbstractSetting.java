package dev.lillian.gem.setting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.lillian.gem.setting.annotation.Internal;
import dev.lillian.gem.setting.annotation.Setting;
import dev.lillian.gem.setting.exception.SettingException;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

@Getter
@Setter
@SuppressWarnings({"java:S3011", "java:S108"})
public abstract class AbstractSetting<T> {
    protected final Field field;
    protected final String name;

    private final Object parent;
    private final boolean internal;

    protected AbstractSetting(@NotNull Object parent, @NotNull Field field) {
        this.name = fetchName();
        this.parent = parent;
        this.field = field;
        this.internal = field.isAnnotationPresent(Internal.class);

        field.setAccessible(true);
    }

    public abstract void writeJson(@NotNull ObjectNode node);

    public abstract void readJson(@NotNull JsonNode node);

    @NotNull
    private String fetchName() {
        if (!field.isAnnotationPresent(Setting.class)) {
            throw new SettingException(field, "No setting annotation");
        }

        Setting annotation = field.getAnnotation(Setting.class);
        String settingName = annotation.value();
        if (settingName.isEmpty()) {
            throw new SettingException(field, "Invalid @Setting value " + settingName);
        }

        return settingName;
    }

    @NotNull
    public T getValue() {
        try {
            //noinspection unchecked
            return (T) field.get(parent);
        } catch (IllegalAccessException e) {
            throw new SettingException(field, e.getMessage());
        }
    }

    public void setValue(@NotNull T value) {
        try {
            field.set(parent, value);
        } catch (IllegalAccessException ignored) {
        }
    }
}
