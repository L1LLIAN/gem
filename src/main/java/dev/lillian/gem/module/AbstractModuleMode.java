package dev.lillian.gem.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public abstract class AbstractModuleMode<M extends AbstractModule> {
    @NotNull
    private final M parent;
    @NotNull
    private final String name;
    @NotNull
    private final String displayName;
}
