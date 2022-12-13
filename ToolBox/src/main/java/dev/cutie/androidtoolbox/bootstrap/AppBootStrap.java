package dev.cutie.androidtoolbox.bootstrap;

import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppBootStrap {
    private List<Map.Entry<Class,Object>> services;
    private static AppBootStrap instance;

    public AppBootStrap()
    {
        this.services = new ArrayList<>();
    }


    public void addService(Class implementationType, Class implementationService)
    {
        try {
            Map.Entry<Class, Object> newEntry = new AbstractMap.SimpleEntry<>(implementationType, implementationService.newInstance());
            services.add(newEntry);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void addService(Class implementationType, Object implementationService)
    {
            Map.Entry<Class, Object> newEntry = new AbstractMap.SimpleEntry<>(implementationType, implementationService);
            services.add(newEntry);
    }

    public <T> T getService(Class implementationType)
    {
        for (Map.Entry<Class, Object> entry: services)
        {
            if(entry.getKey() == implementationType)
            {
                T service = (T)entry.getValue();
                if(service != null)
                    return service;
            }
        }
        // Maybe throw exception instead of null
        return null;
    }
    public static AppBootStrap getInstance()
    {
        if(instance == null) {
            instance = new AppBootStrap();
        }

        return instance;
    }
}
