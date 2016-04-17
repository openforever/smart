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
 * �����࣬�ṩ�������صķ�������ȡ��������������࣬��ȡָ�������µ��������
 * @author openforever
 * @since 1.0.0
 */
public final class ClassUtil {
    /**
     * ClassUtil.class�����Ӧ��SLF4J��־����LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * ��ȡ���������ֻ��Ҫ��ȡ��ǰ�̵߳�ClassLoader����
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * �������������ࣺ�ṩ�������Ƿ��ʼ���ı�־
     * @param className
     * @param isInitialized �Ƿ�ִ����ľ�̬����� false��ִ�У�������߼����������
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
     * ��ȡָ�������µ������ࣺ���ݰ���������ת��Ϊ�ļ�·������ȡclass�ļ���jar������ȡָ��������ȥ������
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {/*��������ΪEnumeration������List*/
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if (url != null){/*url��Э����*/
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
            /*�����.class�ļ�������Ŀ¼�ͷ���*/
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
                addClass(classSet, subPackagePath, subPackageName);/*�ݹ�*/
            }
        }

    }

    /**
     * �����࣬������ָ��classSet��
     * @param classSet
     * @param className
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> clazz = loadClass(className, false);
        classSet.add(clazz);
    }
}
