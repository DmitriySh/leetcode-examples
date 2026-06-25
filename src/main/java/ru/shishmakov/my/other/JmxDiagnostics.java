package ru.shishmakov.my.other;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Map;
import java.util.Optional;

public class JmxDiagnostics {

    public static Map<String, Object> getJvmSnapshot() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();

        return Map.of(
                "timestamp", System.currentTimeMillis(),
                "memory", Map.of(
                        "heap_used_mb", memory.getHeapMemoryUsage().getUsed() / 1024 / 1024,
                        "heap_max_mb", memory.getHeapMemoryUsage().getMax() / 1024 / 1024,
                        "non_heap_used_mb", memory.getNonHeapMemoryUsage().getUsed() / 1024 / 1024
                ),
                "threads", Map.of(
                        "live", threads.getThreadCount(),
                        "peak", threads.getPeakThreadCount(),
                        "daemon", threads.getDaemonThreadCount(),
                        "deadlocked_count", Optional.ofNullable(threads.findDeadlockedThreads()).map(t -> t.length).orElse(0)
                ),
                "system", Map.of(
                        "cpu_load_pct", ((int) os.getSystemLoadAverage() * 100),
                        "available_processors", os.getAvailableProcessors()
                )
        );
    }

    public static void main(String[] args) {
        Map<String, Object> jvmSnapshot = JmxDiagnostics.getJvmSnapshot();
        jvmSnapshot.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
