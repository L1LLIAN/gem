package dev.lillian.gem.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractModuleMode<M extends AbstractModule> {
    private final M parent;
    private final String name;
    private final String displayName;
}
