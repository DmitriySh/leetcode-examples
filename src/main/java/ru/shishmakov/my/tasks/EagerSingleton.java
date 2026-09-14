package ru.shishmakov.my.tasks;

/**
 * Eager Singleton
 */
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        // dummy
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        var singleton = EagerSingleton.getInstance();
        var otherSingleton = EagerSingleton.getInstance();

        System.out.println("singleton == otherSingleton: " + (singleton == otherSingleton));
    }
}
