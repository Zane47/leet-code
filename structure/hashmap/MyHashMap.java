package leetcode.structure.hashmap;

// https://juejin.cn/post/6844903920947429390
public class MyHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    // private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final float DEFAULT_LOAD_FACTOR = 0.25f;
    // 存放元素的数组
    private MyEntry[] table;

    private int mapSize;

    public MyHashMap() {
        this.table = new MyEntry[DEFAULT_INITIAL_CAPACITY];
        mapSize = 0;
    }

    public class MyEntry<K, V> {
        K key;
        V value;
        int hashCode;

        // 冲突时，链表存储
        MyEntry<K, V> next;

        public MyEntry(K key, V value, int hashCode, MyEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
            this.next = next;
        }
    }


    public V put(K key, V value) {
        // key为null的时候放在第一个
        if (key == null) {
            throw new RuntimeException("key is null");
        }

        // Object自带的hashCode方法
        int hash = key.hashCode();
        int index = indexFor(hash);

        MyEntry<K, V> e = table[index];
        while (null != e) {
            //先比较hash是否相等，再比较对象是否相等，或者比较equals方法
            //如果相等了，说明有一样的key,这时要更新旧值为新的value,同时返回旧的值
            if (e.hashCode == hash && (key == e.key || key.equals(e.key))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
            e = e.next;
        }

        // 没有元素相等，那么保存下来
        MyEntry<K, V> next = table[index];

        // next有可能为null，也有可能不为null，
        // 不管是否为null
        // next都要作为新元素的下一个节点
        // (next传给了QEntry的构造函数)
        // 然后新的元素保存在了index这个位置
        // 头插法
        table[index] = new MyEntry<>(key, value, hash, next);

        if (mapSize++ >= (table.length * DEFAULT_LOAD_FACTOR) ) {
            resize();
        }

        return null;
    }

    private int indexFor(int hash) {
        hash = hash > 0 ? hash : -hash;
        return hash % table.length;
    }

    // 数组扩容成原来的两倍
    private void resize() {
        int newCapacity = 2 * table.length;

        MyEntry[] newTable = new MyEntry[newCapacity];
        MyEntry[] src = table;

        for (int i = 0 ; i < src.length; i++) {
            MyEntry<K, V> e = src[i];

            // e是链表，可能有多个节点
            while (null != e) {
                MyEntry<K, V> next = e.next;

                // 新的index
                int newIndex = e.hashCode % newCapacity;

                // newTable[i] 位置上有可能是null，也有可能不为null
                // 不管是否为null，都作为e这个节点的下一个节点
                e.next = newTable[newIndex];
                newTable[newIndex] = e;

                e = next;
            }
        }

        this.table = newTable;
    }

    private V get(K key) {
        if (null == key) {
            throw new RuntimeException("Key is null");
        }
        int hash = key.hashCode();
        int index = indexFor(hash);

        MyEntry<K, V> e = table[index];
        while (null != e) {
            if (hash == e.hashCode && (key == e.key || key.equals(e.key))) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(1, "hello_1");
        map.put(2, "Hello_2");
        map.put(17, "aha_17");
        map.put(3, "hello_3");
        map.put(4, "hello_4");
        // 扩容
        map.put(8, "hello_8");

        map.get(1);
        /*
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("name", "tom");
        map.put("age", "23");
        map.put("address", "beijing");
        String oldValue = map.put("address", "shanghai"); //key一样，返回旧值，保存新值

        System.out.println(map.get("name"));
        System.out.println(map.get("age"));

        System.out.println("旧值=" + oldValue);
        System.out.println("新值=" + map.get("address"));
        */
    }











}
