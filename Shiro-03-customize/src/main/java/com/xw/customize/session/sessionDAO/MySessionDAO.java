package com.xw.customize.session.sessionDAO;

//public class MySessionDAO extends AbstractSessionDAO {
//
//    private RedisClient redisClient;
//
//    public MySessionDAO(RedisClient redisClient) {
//        this.redisClient = redisClient;
//    }
//
//    private static final String PREFIX = "ruyuan_session:";
//
//    private byte[] getKey(String str) {
//        return (PREFIX + str).getBytes();
//    }
//
//    private void saveSession(Session session) {
//        byte[] key = getKey(session.getId().toString());
//        byte[] value = SerializationUtils.serialize(session);
//        redisClient.put(key, value, session.getTimeout());
//    }
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = generateSessionId(session);
//        assignSessionId(session, sessionId);
//        saveSession(session);
//        return sessionId;
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        if (sessionId == null){
//            return null;
//        }
//        byte[] key = getKey(sessionId.toString());
//        byte[] bytes = redisClient.get(key);
//        return (Session) SerializationUtils.deserialize(bytes);
//    }
//
//    @Override
//    public void update(Session session) throws UnknownSessionException {
//        saveSession(session);
//    }
//
//    @Override
//    public void delete(Session session) {
//        redisClient.del(getKey(session.getId().toString()));
//    }
//
//    @Override
//    public Collection<Session> getActiveSessions() {
//        Set<byte[]> keys = redisClient.scan((PREFIX + "*").getBytes());
//        List<Session> list = new ArrayList<>(keys.size());
//        for (byte[] key : keys) {
//            list.add((Session) SerializationUtils.deserialize(redisClient.get(key)));
//        }
//        return list;
//    }
//
//}
