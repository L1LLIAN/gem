package dev.lillian.gem.file;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.lillian.gem.Gem;
import dev.lillian.gem.file.impl.BindsFile;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public final class FileManager {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Set<IFile> files = new HashSet<>();

    public FileManager() {
        files.add(new BindsFile());
    }

    public void saveAll() {
        for (IFile file : files) {
            save(file);
        }
    }

    public void loadAll() {
        for (IFile file : files) {
            load(file);
        }
    }

    private void save(@NotNull IFile file) {
        try {
            ObjectNode node = file.getData();
            File javaFile = getFile(file);
            Path path = javaFile.toPath();

            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path);
            }

            Files.deleteIfExists(path);
            Files.createFile(path);

            FileOutputStream fos = new FileOutputStream(javaFile);
            IOUtils.write(node.toString(), fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(@NotNull IFile file) {
        File javaFile = getFile(file);
        if (!javaFile.exists()) {
            return;
        }

        try {
            JsonNode node = OBJECT_MAPPER.readTree(javaFile);
            file.load(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private File getFile(@NotNull IFile file) {
        return new File(Gem.DATA_DIR, file.getName() + ".json");
    }
}
