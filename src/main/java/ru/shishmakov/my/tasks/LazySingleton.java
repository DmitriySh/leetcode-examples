package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Lazy Singleton with static initialization
 */
public class LazySingleton {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private LazySingleton() {
        // dummy
    }

    private static class SingletonHolder {
        private static final LazySingleton HOLDER_INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public static void main(String[] args) {
        LazySingleton singleton = LazySingleton.getInstance();
        LazySingleton otherSingleton = LazySingleton.getInstance();

        System.out.println("singleton == otherSingleton: " + (singleton == otherSingleton));
    }
}
