package ru.shishmakov.my.tasks;

/**
 * Lazy Singleton with DCL
 */
public class DoubleCheckedLockSingleton {
    private static final Object LOCK = new Object();
    private static volatile DoubleCheckedLockSingleton INSTANCE = null;

    private DoubleCheckedLockSingleton() {
        // dummy
    }

    public static DoubleCheckedLockSingleton getInstance() {
        var result = INSTANCE;

        if (result == null) {
            synchronized (LOCK) {

                result = INSTANCE;
                if (result == null) {
                    result = new DoubleCheckedLockSingleton();
                    INSTANCE = result;
                }

            }
        }

        return result;
    }

    public static void main(String[] args) {
        var singleton = DoubleCheckedLockSingleton.getInstance();
        var otherSingleton = DoubleCheckedLockSingleton.getInstance();

        System.out.println("singleton == otherSingleton: " + (singleton == otherSingleton));
    }
}
