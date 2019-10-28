package com.sohu.spaces.version.ui;

public class TestJedis {
   /* @Test
    public void test01() {
//        1.创建jedis对象，指定ip、por
        Jedis jedis = new Jedis("192.168.192.168", 6379);
//        2.使用jedis操作redis
        jedis.set("name", "小明");
        String name = jedis.get("name");
        System.out.println(name);
//        3.释放资源
        jedis.close();
    }
    @Test
    public void test02() {
//        1.创建jedispoolconfig对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(20);
//        2.创建jedisjedispool对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.192.168", 6379);
//        3.获取jedis对象
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
//        4.操作redis
            String name = jedis.get("name");
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//        5.释放资源
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }*/
}
