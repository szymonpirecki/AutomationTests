package org.utils.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Getter
@Slf4j
public class YamlReader {
    private static final String CONFIG_FILE_YAML = "configFile.yaml";
    private static final YamlReader INSTANCE = new YamlReader();
    private YamlModel yamlModel;

    private YamlReader() {
        log.info("YamlReader started");
        loadData();
    }

    public static YamlReader getInstance(){
        return INSTANCE;
    }

    public void loadData() {
        log.info("Loading data from: {}", CONFIG_FILE_YAML);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(CONFIG_FILE_YAML)).getFile());

        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            this.yamlModel = mapper.readValue(file, YamlModel.class);
            log.info("Data loaded from: {}", CONFIG_FILE_YAML);
        } catch (IOException e) {
            String errorMsg = "Error during loading data from: " + CONFIG_FILE_YAML;
            log.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
    }
}