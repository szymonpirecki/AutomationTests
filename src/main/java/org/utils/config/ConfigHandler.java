package org.utils.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ConfigHandler {

    public static void setConfigProperties() {
        Map<String, Object> configData = YamlReader.getInstance().getYamlModel().getConfigData();

        for (Map.Entry<String, Object> entry : configData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) {
                log.error("Value for {} is missing - can't be null", key);
            } else {
                System.setProperty(key, value.toString());
                log.info("{} set to: {}", key, value);
            }
        }
    }
}