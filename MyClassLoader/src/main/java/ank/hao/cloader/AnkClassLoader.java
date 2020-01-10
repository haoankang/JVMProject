package ank.hao.cloader;

import java.net.URL;

public class AnkClassLoader extends ClassLoader {

    public AnkClassLoader(){

    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    @Override
    public URL getResource(String name) {
        return super.getResource(name);
    }
}
