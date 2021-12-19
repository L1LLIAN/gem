package dev.lillian.gem.file;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

public interface IFile {
    @NotNull
    String getName();

    void load(@NotNull JsonNode node);

    @NotNull
    ObjectNode getData();
}
