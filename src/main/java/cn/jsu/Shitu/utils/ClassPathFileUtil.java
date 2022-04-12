package cn.jsu.projectmanage.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * @Author houLi
 * description
 */
public class ClassPathFileUtil {
    private static PathMatchingResourcePatternResolver patternResolver;

    static {
        patternResolver = new PathMatchingResourcePatternResolver();
    }

    public static String packageName2Path(String pkg) {
        return pkg.replace(".", "/");
    }

    public static Resource[] getResources(String localPattern) throws IOException {
        return patternResolver.getResources("classpath*:" + localPattern);
    }

    public static Resource[] getResourcesByClassPath(String classpath) throws IOException {
        return getResources(classpath + "/**");
    }

    public static Resource[] getClassFileResourcesByPackageName(String pkg) throws IOException {
        return getResources(packageName2Path(pkg) + "/**/*.class");
    }

    public static boolean isDirectory(Resource resource) throws IOException {
        return resource.getURL().getFile().endsWith("/");
    }
}
