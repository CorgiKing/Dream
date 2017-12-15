package org.corgiking.release;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ConfigUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
    private static final String CONFIG_FILE = "config";
    private static Configuration configuration = loadConfiguration();

    private ConfigUtil() {
    }

    private static Configuration loadConfiguration() {
        try {
            CompositeConfiguration compositeConfiguration = new CompositeConfiguration();

            // priority 1: load file system config, this override following configurations
            String configFilePath = System.getProperty(CONFIG_FILE);
            if (!StringUtils.isBlank(configFilePath)) {
                LOG.info("Config file is specified, use it to overwrite default configurations");
                File file = new File(configFilePath);
                if (file.exists()) {
                    PropertiesConfiguration fileConfig = new PropertiesConfiguration(file.getAbsolutePath());
                    fileConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
                    compositeConfiguration.addConfiguration(fileConfig);
                }
            }

            // priority 2: load app_env.properties defined in environment module, contains varied config for different env
            compositeConfiguration.addConfiguration(new PropertiesConfiguration("env_config.properties"));

            // priority 3: the default base config
            compositeConfiguration.addConfiguration(new PropertiesConfiguration("base_config.properties"));

            return compositeConfiguration;
        } catch (ConfigurationException e) {
            LOG.error("Failed to load configuration", e);
            throw new RuntimeException(e);
        }
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
    
    public static float getFloat(String key){
    	return configuration.getFloat(key);
    }
}
