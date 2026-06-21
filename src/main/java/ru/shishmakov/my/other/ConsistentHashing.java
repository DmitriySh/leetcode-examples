package ru.shishmakov.my.other;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Реализация алгоритма Consistent Hashing с поддержкой "виртуальных узлов"
 * для равномерного распределения ключей по узлам в распределённых системах.
 */
public class ConsistentHashing {

    /**
     * Количество "виртуальных узлов", создаваемых для каждого физического узла
     */
    private final int numberVirtualNodes;
    /**
     * Хеш‑кольцо (hash ring), реализованное через SortedMap (TreeMap)
     *
     * <p>Key — хеш виртуального узла (Integer), Value — имена физических узлов (String).</p>
     *
     * <p> {@code TreeMap<Integer,String>} отсортирован по 'Key' в натуральном порядке (от меньше к большему).
     * Обеспечивает поиск ближайшего физического узла по часовой стрелке (вправо) от хеша искомого элемента (item).</p>
     */
    private final SortedMap<BigInteger, String> circle;

    public ConsistentHashing(int numberVirtualNodes, List<String> nodes) {
        this.numberVirtualNodes = numberVirtualNodes;
        this.circle = new TreeMap<>();
        for (String nodeName : nodes) {
            addNode(nodeName);
        }
    }

    /**
     * Добавляет физический узел в хеш‑кольцо с созданием заданного количества виртуальных узлов
     *
     * @param nodeName название физического узла
     */
    public void addNode(String nodeName) {
        for (int i = 0; i < numberVirtualNodes; i++) {
            String vNodeName = nodeName + i;
            var vNodeHash = hash(vNodeName);
            circle.put(vNodeHash, nodeName);
        }
    }

    /**
     * Удаляет физический узел из хеш‑кольца вместе со всеми его виртуальными узлами
     *
     * @param nodeName название физического узла
     */
    public void removeNode(String nodeName) {
        for (int i = 0; i < numberVirtualNodes; i++) {
            String vNodeName = nodeName + i;
            var vNodeHash = hash(vNodeName);
            circle.remove(vNodeHash);
        }
    }

    /**
     * @return список имён физических серверов участвующих в распределении на хеш‑кольце
     */
    public Set<String> getNodeNames() {
        return new HashSet<>(circle.values());
    }

    /**
     * Находим физический узел, ответственный за хранение заданного элемента (item).
     *
     * <p>Алгоритм поиска:</p>
     * <ol>
     *   <li>Вычисляет хеш элемента.</li>
     *   <li>Находит первый узел в кольце по часовой стрелке от хеша элемента (с помощью tailMap).</li>
     *   <li>Если такой узел не найден (элемент за концом кольца), берёт первый узел кольца.</li>
     *   <li>Возвращает имя физического узла, которому принадлежит найденный виртуальный узел.</li>
     * </ol>
     *
     * @param itemName имя элемента
     * @return имя физического узла или null, если хеш‑кольцо пустое
     */
    public String getNodeByItem(String itemName) {
        if (circle.isEmpty()) {
            return null;
        }

        var itemHash = hash(itemName);
        SortedMap<BigInteger, String> rightPart = circle.tailMap(itemHash);
        var vNodeHash = rightPart.isEmpty() ? circle.firstKey() : rightPart.firstKey();

        return circle.get(vNodeHash);
    }

    /**
     * Алгоритм работы:
     * <ol>
     *   <li>создаёт экземпляр {@link MessageDigest} для алгоритма SHA‑1, генерирующий 160‑битный хэш (20 байт);</li>
     *   <li>каждый вызов метода создаёт новый экземпляр {@link MessageDigest}, обеспечивая потокобезопасность.</li>
     *   <li>вычисляет хэш строки (кодировка UTF‑8);</li>
     *   <li>преобразует полученный массив байт (хэш) в {@link BigInteger}, гарантируя не отрицательность результата
     *   независимо от значения старшего бита, диапазон значений: [0, 2<sup>160</sup> − 1];</li>
     * </ol>
     *
     * @param key ключ, для которого необходимо вычислить хэш
     * @return неотрицательное {@link BigInteger} значение хэш‑функции SHA‑1 для входной строки.
     * Диапазон значений: [0, 2<sup>160</sup> − 1].
     */
    private BigInteger hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(key.getBytes(StandardCharsets.UTF_8));
            byte[] hash = md.digest();
            // Используем конструктор с параметром signum=1, чтобы гарантировать неотрицательное число
            return new BigInteger(1, hash);
        } catch (Exception e) {
            throw new RuntimeException("Exception calculate hash", e);
        }
    }

    public static void main(String[] args) {
        List<String> nodes = List.of("node-A", "node-B", "node-C");
        int vNodes = 10;
        String[] testItems = {
                "user-123", "order-456", "product-789", "special-key-3",
                "session-abc", "cache-key-xyz", "data-001"
        };

        ConsistentHashing ch = new ConsistentHashing(vNodes, nodes);

        System.out.println("Физические узлы: " + ch.getNodeNames());
        System.out.println("Количество виртуальных узлов на 1 физический: " + vNodes);
        System.out.println("Общее количество виртуальных узлов на кольце: " + ch.circle.size());
        System.out.println();

        System.out.println("=== РАСПРЕДЕЛЕНИЕ ВСЕХ УЗЛОВ НА КОЛЬЦЕ ===");
        ch.circle.entrySet().forEach(System.out::println);
        System.out.println();

        System.out.println("=== ПРИВЯЗКА КЛЮЧЕЙ К СЕРВЕРАМ НА КОЛЬЦЕ ===");
        printItemNodes(testItems, ch);

        String removeNode = "node-B";
        ch.removeNode(removeNode);
        System.out.println("Удаление физического узла: " + removeNode);
        System.out.println("Физические узлы: " + ch.getNodeNames());
        System.out.println("Общее количество виртуальных узлов на кольце: " + ch.circle.size());
        System.out.println();

        System.out.println("=== РАСПРЕДЕЛЕНИЕ ВСЕХ УЗЛОВ НА КОЛЬЦЕ ПОСЛЕ УДАЛЕНИЯ УЗЛА ===");
        ch.circle.entrySet().forEach(System.out::println);
        System.out.println();

        System.out.println("=== ПРИВЯЗКА КЛЮЧЕЙ К СЕРВЕРАМ НА КОЛЬЦЕ ПОСЛЕ УДАЛЕНИЯ УЗЛА ===");
        printItemNodes(testItems, ch);
    }

    private static void printItemNodes(String[] testItems, ConsistentHashing ch) {
        for (String item : testItems) {
            String node = ch.getNodeByItem(item);
            System.out.printf("Item: '%s' -> %s\n", item, node);
        }
        System.out.println();
    }
}
