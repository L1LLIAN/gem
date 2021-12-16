package dev.lillian.gem.setting.exception;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class SettingException extends RuntimeException {
    public static final long serialVersionUID = 437697306486687626L;

    public SettingException(@NotNull Field field, @NotNull String reason) {
        super("Field " + field.getName() + " is an invalid setting! Reason = " + reason);
    }
}
