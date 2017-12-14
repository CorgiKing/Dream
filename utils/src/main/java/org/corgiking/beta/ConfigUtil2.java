package org.corgiking.beta;

import java.io.File;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil2 {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil2.class);
    private static final String CONFIG_FILE = "config";
    private static Configuration configuration = loadConfiguration();

    private ConfigUtil2() {
    }

    private static Configuration loadConfiguration() {
            CompositeConfiguration compositeConfiguration = new CompositeConfiguration();

            String configFilePath = System.getProperty(CONFIG_FILE);
            if (!StringUtils.isBlank(configFilePath)) {
                LOG.info("Config file is specified, use it to overwrite default configurations");
                File file = new File(configFilePath);
                if (file.exists()) {
                    PropertiesConfiguration fileConfig = new PropertiesConfiguration();
                    // TODO commons-configuration2 配置文件使用方法
                    compositeConfiguration.addConfiguration(fileConfig);
                }
            }
            return compositeConfiguration;
    }

    public static String getProperty(String key) {
        return configuration.getString(key);
    }

    public static boolean getBoolean(String key) {
        return configuration.getBoolean(key, false);
    }

    public static int getInt(String key) {
        return configuration.getInt(key, 0);
    }
}
