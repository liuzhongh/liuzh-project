package org.code.generator.db;

public class ObjectFactory {
    private static ClassLoader externalClassLoader;

    /**
     * Utility class. No instances allowed
     */
    private ObjectFactory() {
        super();
    }

    private static ClassLoader getClassLoader() {
        if (externalClassLoader != null) {
            return externalClassLoader;
        } else {
            return Thread.currentThread().getContextClassLoader();
        }
    }

    public static synchronized void setExternalClassLoader(
            ClassLoader classLoader) {
        ObjectFactory.externalClassLoader = classLoader;
    }

    /**
     * This method returns a class loaded from the context classloader, or the
     * classloader supplied by a client. This is appropriate for JDBC drivers,
     * model root classes, etc. It is not appropriate for any class that extends
     * one of the supplied classes or interfaces.
     * 
     * @param type
     * @return the Class loaded from the external classloader
     * @throws ClassNotFoundException
     */
    public static Class<?> externalClassForName(String type)
            throws ClassNotFoundException {

        Class<?> clazz;

        try {
            clazz = getClassLoader().loadClass(type);
        } catch (Throwable e) {
            // ignore - fail safe below
            clazz = null;
        }

        if (clazz == null) {
            clazz = Class.forName(type);
        }

        return clazz;
    }

    public static Object createExternalObject(String type) {
        Object answer;

        try {
            Class<?> clazz = externalClassForName(type);

            answer = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(type); //$NON-NLS-1$
        }

        return answer;
    }

    public static Class<?> internalClassForName(String type)
            throws ClassNotFoundException {
        Class<?> clazz = null;

        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            clazz = cl.loadClass(type);
        } catch (Exception e) {
            // ignore - failsafe below
        }

        if (clazz == null) {
            clazz = Class.forName(type);
        }

        return clazz;
    }

    public static Object createInternalObject(String type) {
        Object answer;

        try {
            Class<?> clazz = internalClassForName(type);

            answer = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(type); //$NON-NLS-1$

        }

        return answer;
    }

}
