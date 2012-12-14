import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * Class loader used so we can dynamically load classes. Normal class loader
 * doesn't close the .jar so you can't reload. This fixes that.
 *
 * @author James
 */
public class MyClassLoader extends URLClassLoader {

    /**
     * Creates loader
     *
     * @param urls
     * @param loader
     */
    public MyClassLoader(URL[] urls, ClassLoader loader) {
        super(urls, loader);
    }

    /**
     * Fix here.
     */
    public void close() {
        try {
            Class<?> clazz = java.net.URLClassLoader.class;
            java.lang.reflect.Field ucp = clazz.getDeclaredField("ucp");

            ucp.setAccessible(true);
            Object sun_misc_URLClassPath = ucp.get(this);
            java.lang.reflect.Field loaders = sun_misc_URLClassPath.getClass().getDeclaredField("loaders");

            loaders.setAccessible(true);
            Object java_util_Collection = loaders.get(sun_misc_URLClassPath);

            for (Object sun_misc_URLClassPath_JarLoader : ((java.util.Collection) java_util_Collection).toArray()) {
                try {
                    java.lang.reflect.Field loader = sun_misc_URLClassPath_JarLoader.getClass().getDeclaredField("jar");

                    loader.setAccessible(true);
                    Object java_util_jar_JarFile = loader.get(sun_misc_URLClassPath_JarLoader);

                    ((java.util.jar.JarFile) java_util_jar_JarFile).close();
                } catch (Throwable t) {
                    // if we got this far, this is probably not a JAR loader so
                    // skip it
                }
            }
        } catch (Throwable t) {
            // probably not a SUN VM
        }
        return;
    }

    /**
     * Overrides loadClass to allow plugins to access eachothers class paths.
     * @param name binary name of the class
     * @return the class
     * @throws ClassNotFoundException 
     * @see URLClassLoader.loadClass(String name)
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> toRet = null;
        try{
            toRet = super.loadClass(name);
        } catch(ClassNotFoundException ex){
            for (MyClassLoader cl : PluginLoader.getMyClassLoaders()) {
                if (toRet != null) {
                    return toRet;
                }
                if (cl == this) {
                    continue;
                }
                try{
                    toRet = cl.loadClassCanaryStyle(name);
                }
                catch(ClassNotFoundException clnfe){
                    continue;
                }
                catch(NoClassDefFoundError ncdfe){
                    continue;
                }
            }
        }
        return toRet;
    }

    /**
     * skips our special method or loading classes and loads it how a URLClassLoader would
     * @param name binary name of the class
     * @return the class
     * @throws ClassNotFoundException 
     * @see URLClassLoader.loadClass(String name)
     */
    public Class<?> loadClassCanaryStyle(String name) throws ClassNotFoundException{
        return super.loadClass(name);
    }
    
    @Override
    public void addURL(URL url){
        super.addURL(url);
    }
}
