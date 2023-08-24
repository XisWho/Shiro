package com.xw.customize.cache;

//public class RedisCache<K, V> implements Cache<K, V> {
//
//    private RedisClient redisClient;
//
//    private static final String PREFIX = "ruyuan_cache_";
//
//    private static final long TIMEOUT_SECONDS = 30 * 60L;
//
//    public RedisCache(RedisClient redisClient) {
//        this.redisClient = redisClient;
//    }
//
//    private byte[] getKey(K k) {
//        if (k instanceof String) {
//            return (PREFIX + k).getBytes();
//        }
//        return SerializationUtils.serialize(k);
//    }
//
//    @Override
//    public V get(K k) throws CacheException {
//        if (k == null) {
//            return null;
//        }
//        byte[] value = redisClient.get(getKey(k));
//        if (value != null) {
//            return (V) SerializationUtils.deserialize(value);
//        }
//        return null;
//    }
//
//    @Override
//    public V put(K k, V v) throws CacheException {
//        if (k == null) {
//            return null;
//        }
//        byte[] key = getKey(k);
//        byte[] value = SerializationUtils.serialize(v);
//        redisClient.put(key, value, TIMEOUT_SECONDS);
//        return v;
//    }
//
//    @Override
//    public V remove(K k) throws CacheException {
//        if (k == null) {
//            return null;
//        }
//        V v = get(k);
//        redisClient.del(getKey(k));
//        return v;
//    }
//
//    @Override
//    public void clear() throws CacheException {
//        Set<byte[]> result = redisClient.scan((PREFIX + "*").getBytes());
//        if (!CollectionUtils.isEmpty(result)) {
//            for (byte[] key : result) {
//                redisClient.del(key);
//            }
//        }
//    }
//
//    @Override
//    public int size() {
//        Set<byte[]> result = redisClient.scan((PREFIX + "*").getBytes());
//        if (!CollectionUtils.isEmpty(result)) {
//            return result.size();
//        }
//        return 0;
//    }
//
//    @Override
//    public Set<K> keys() {
//        Set<byte[]> result = redisClient.scan((PREFIX + "*").getBytes());
//        Set<K> set = new HashSet<>();
//        if (!CollectionUtils.isEmpty(result)) {
//            for (byte[] key : result) {
//                set.add((K) SerializationUtils.deserialize(key));
//            }
//        }
//        return set;
//    }
//
//    @Override
//    public Collection<V> values() {
//        Set<byte[]> result = redisClient.scan((PREFIX + "*").getBytes());
//        List<V> list = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(result)) {
//            for (byte[] key : result) {
//                list.add((V) SerializationUtils.deserialize(redisClient.get(key)));
//            }
//        }
//        return list;
//    }
//
//}
