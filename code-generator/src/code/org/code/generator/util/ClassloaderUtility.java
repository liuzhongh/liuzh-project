package org.code.generator.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds methods useful for constructing custom classloaders.
 * 
 */
public class ClassloaderUtility {

    /**
     * Utility Class - No Instances
     */
    private ClassloaderUtility() {
    }

    public static ClassLoader getCustomClassloader(List<String> entries) {
        List<URL> urls = new ArrayList<URL>();
        File file;

        if (entries != null) {
            for (String classPathEntry : entries) {
                file = new File(classPathEntry);
                if (!file.exists()) {
                    throw new RuntimeException(classPathEntry); //$NON-NLS-1$
                }

                try {
                    urls.add(file.toURI().toURL());
                } catch (MalformedURLException e) {
                    // this shouldn't happen, but just in case...
                    throw new RuntimeException(classPathEntry); //$NON-NLS-1$
                }
            }
        }

        ClassLoader parent = Thread.currentThread().getContextClassLoader();

        URLClassLoader ucl = new URLClassLoader(urls.toArray(new URL[urls
                .size()]), parent);

        return ucl;
    }
}
