package org.utils.config;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class YamlModel {

    public Map<String, Object> configData = new HashMap<>();

}