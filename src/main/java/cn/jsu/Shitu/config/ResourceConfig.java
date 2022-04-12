package cn.jsu.projectmanage.config;

import cn.jsu.projectmanage.utils.ClassPathFileUtil;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * @Author houLi
 * description
 */



public class ResourceConfig {
    private static Properties properties;

    static {
        init();
    }

    /**
     * 初始化配置类
     */
    private static void init(){
        properties = new Properties();
        try {
            Resource[] resources = ClassPathFileUtil.getResourcesByClassPath("config");
            for (Resource resource : resources) {
                if (!ClassPathFileUtil.isDirectory(resource)) {
                    properties.load(resource.getInputStream());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String
     */
    public static String getConfigString(String configName) {
        return getConfigString(configName, null);
    }

    public static String getConfigString(String configName, String defaultValue) {
        String property = properties.getProperty(configName);
        return property != null ? property : defaultValue;
    }

    /**
     * Long
     */
    public static Long getConfigLong(String configName) {
        return getConfigLong(configName, null);
    }

    public static Long getConfigLong(String configName, Long defaultValue) {
        String property = properties.getProperty(configName);
        return property != null ? Long.parseLong(property) : defaultValue;
    }

    /**
     * Integer
     */
    public static Integer getConfigInteger(String configName) {
        return getConfigInteger(configName, null);
    }

    public static Integer getConfigInteger(String configName, Integer defaultValue) {
        String property = properties.getProperty(configName);
        return property != null ? Integer.parseInt(property) : defaultValue;
    }

    /**
     * Short
     */
    public static Short getConfigShort(String configName) {
        return getConfigShort(configName, null);
    }

    public static Short getConfigShort(String configName, Short defaultValue) {
        String property = properties.getProperty(configName);
        return property != null ? Short.parseShort(property) : defaultValue;
    }

    /**
     * Boolean
     */
    public static Boolean getConfigBoolean(String configName) {
        return getConfigBoolean(configName, null);
    }

    public static Boolean getConfigBoolean(String configName, Boolean defaultValue) {
        String property = properties.getProperty(configName);
        return property != null ? Boolean.parseBoolean(property) : defaultValue;
    }
}
