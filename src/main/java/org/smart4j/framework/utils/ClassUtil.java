package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by snow on 2016/4/17.
 * 工具类，提供类操作相关的方法：获取类加载器，加载类，获取指定包名下的所有类等
 * @author openforever
 * @since 1.0.0
 */
public final class ClassUtil {
    /**
     * ClassUtil.class对象对应的SLF4J日志对象LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器：只需要获取当前线程的ClassLoader即可
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 根据类名加载类：提供类名和是否初始化的标志
     * @param className
     * @param isInitialized 是否执行类的静态代码块 false不执行，可以提高加载类的性能
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized){
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    /**
     * 获取指定包名下的所有类：根据包名并将其转换为文件路径，读取class文件或jar包。获取指定的类名去加载类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {/*返回类型为Enumeration不能用List*/
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if (url != null){/*url的协议名*/
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")){
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classSet, packagePath, packageName);
                    }else if (protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null){
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        final File[] files = new File(packagePath).listFiles(new FileFilter() {
            /*如果是.class文件或者是目录就返回*/
            public boolean accept(File pathname) {
                return (pathname.isFile() && pathname.getName().endsWith(".class") ||
                pathname.isDirectory());
            }
        });
        for (File file : files){
            String fileName = file.getName();
            if (file.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)){
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            }else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);/*递归*/
            }
        }

    }

    /**
     * 加载类，并放入指定classSet中
     * @param classSet
     * @param className
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> clazz = loadClass(className, false);
        classSet.add(clazz);
    }
}
