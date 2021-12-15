package dev.lillian.gem.module;

import dev.lillian.gem.model.IToggleable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public abstract class AbstractModuleMode<M extends AbstractModule> implements IToggleable {
    @NotNull
    private final M parent;
    @NotNull
    private final String name;
    @NotNull
    private final String displayName;
}
