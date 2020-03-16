package com.alonginfo.psmpcore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis使用工具类
 **/
@Component
public class RedisUtil {

	@Value("${spring.redis.namespace:along}" + "::")
	private String namespace;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 添加 set 元素
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long add(String key, String... values) {
		return redisTemplate.opsForSet().add(namespace + key, values);
	}

//key-value操作

	/**
	 * 添加 ZSet 元素
	 *
	 * @param key
	 * @param value
	 * @param score
	 */
	public boolean add(String key, Object value, double score) {
		return redisTemplate.opsForZSet().add(namespace + key, value, score);
	}

	/**
	 * 返回指定分数区间 [min,max] 的元素个数
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public long countZSet(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(namespace + key, min, max);
	}

	/**
	 * 删除指定 hash 的 HashKey
	 *
	 * @param key
	 * @param hashKeys
	 * @return 删除成功的 数量
	 */
	public Long delete(String key, String... hashKeys) {
		return redisTemplate.opsForHash().delete(namespace + key, hashKeys);
	}

	/**
	 * 删除指定 hash 的 HashKey
	 *
	 * @param key
	 * @param hashKeys
	 * @return 删除成功的 数量
	 */
	public Long deleteHash(String key, String... hashKeys) {
		return redisTemplate.opsForHash().delete(namespace + key, hashKeys);
	}

	/**
	 * 删除指定的key
	 *
	 * @param key
	 * @return
	 */
	public boolean deleteKey(String key) {
		return redisTemplate.delete(namespace + key);
	}

	/**
	 * 获取两个集合的差集
	 *
	 * @param key
	 * @param otherkey
	 * @return
	 */
	public Set<Object> difference(String key, String otherkey) {
		return redisTemplate.opsForSet().difference(namespace + key, namespace + otherkey);
	}

	/**
	 * 将  key 与 otherkey 的差集 ,添加到新的 newKey 集合中
	 *
	 * @param key
	 * @param otherkey
	 * @param newKey
	 * @return 返回差集的数量
	 */
	public Long differenceAndStore(String key, String otherkey, String newKey) {
		return redisTemplate.opsForSet().differenceAndStore(namespace + key, namespace + otherkey, namespace + newKey);
	}

	/**
	 * 将 key 和 集合  collections 中的 key 集合的差集 添加到  newkey 集合中
	 *
	 * @param key
	 * @param otherKeys
	 * @param newKey
	 * @return 返回差集的数量
	 */
	public Long differenceAndStore(String key, Collection<Object> otherKeys, String newKey) {
		return redisTemplate.opsForSet().differenceAndStore(namespace + newKey, namespace + otherKeys, namespace + newKey);
	}

	/**
	 * 获取 String 类型 key-value
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return get(key, String.class);
	}

//hash操作

	/**
	 * 获取指定类型 key-value
	 *
	 * @param key
	 * @param tClass
	 * @param <T>    指定的类型
	 * @return
	 */
	public <T> T get(String key, Class<T> tClass) {
		return (T) redisTemplate.opsForValue().get(namespace + key);
	}

	/**
	 * 获取 key 下的 所有  hashkey 和 value
	 *
	 * @param key
	 * @return
	 */
	public Map<Object, Object> getHash(String key) {
		return redisTemplate.opsForHash().entries(namespace + key);
	}

	/**
	 * 获取 key 下的 所有  hashkey 和 value
	 *
	 * @param key
	 * @return
	 */
	public Map<Object, Object> getHashEntries(String key) {
		return redisTemplate.opsForHash().entries(namespace + key);
	}

	/**
	 * 获取指定 key 下的 hashkey
	 *
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public String getHashKey(String key, String hashKey) {
		return getHashKey(key, hashKey, String.class);
	}

	/**
	 * 获取指定类型 key 下的 hashkey
	 *
	 * @param key
	 * @param hashKey
	 * @param tClass
	 * @param <T>     指定的类型
	 * @return
	 */
	public <T> T getHashKey(String key, String hashKey, Class<T> tClass) {
		return (T) redisTemplate.opsForHash().get(namespace + key, hashKey);
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	/**
	 * 验证有没有指定的key
	 *
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(namespace + key);
	}

	/**
	 * 验证指定 key 下 有没有指定的 hashkey
	 *
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public boolean hashKey(String key, String hashKey) {
		return redisTemplate.opsForHash().hasKey(namespace + key, hashKey);
	}

	/**
	 * 获取 key 下的 所有 hashkey 字段名
	 *
	 * @param key
	 * @return
	 */
	public Set<Object> hashKeys(String key) {
		return redisTemplate.opsForHash().keys(namespace + key);
	}

	/**
	 * 获取指定 hash 下面的 键值对 数量
	 *
	 * @param key
	 * @return
	 */
	public Long hashSize(String key) {
		return redisTemplate.opsForHash().size(namespace + key);
	}

	/**
	 * 给指定 hash 的 hashkey 做增减操作
	 *
	 * @param key
	 * @param hashKey
	 * @param number
	 * @return
	 */
	public Long increment(String key, String hashKey, long number) {
		return redisTemplate.opsForHash().increment(namespace + key, hashKey, number);
	}

	/**
	 * 给指定 hash 的 hashkey 做增减操作
	 *
	 * @param key
	 * @param hashKey
	 * @param number
	 * @return
	 */
	public Double increment(String key, String hashKey, Double number) {
		return redisTemplate.opsForHash().increment(namespace + key, hashKey, number);
	}

	/**
	 * 对指定的 zset 的 value 值 , socre 属性做增减操作
	 *
	 * @param key
	 * @param value
	 * @param score
	 * @return
	 */
	public Double incrementScore(String key, Object value, double score) {
		return redisTemplate.opsForZSet().incrementScore(namespace + key, value, score);
	}

	/**
	 * key 和 otherKey 两个集合的交集,保存在 destKey 集合中
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long intersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(namespace + key, namespace + otherKey, namespace + destKey);
	}

//List 操作

	/**
	 * key 和 otherKeys 多个集合的交集,保存在 destKey 集合中
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(namespace + key, namespace + otherKeys, namespace + destKey);
	}

	/**
	 * 判断 set 集合中 是否有 value
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean isMember(String key, Object value) {
		return redisTemplate.opsForSet().isMember(namespace + key, value);
	}

	/**
	 * 指定 list 从左出栈
	 * 如果列表没有元素,会堵塞到列表一直有元素或者超时为止
	 *
	 * @param key
	 * @return 出栈的值
	 */
	public Object leftPop(String key) {
		return redisTemplate.opsForList().leftPop(namespace + key);
	}

	/**
	 * 指定 list 从左入栈
	 *
	 * @param key
	 * @return 当前队列的长度
	 */
	public Long leftPush(String key, Object value) {
		return redisTemplate.opsForList().leftPush(namespace + key, value);
	}

	/**
	 * 从左边依次入栈
	 * 导入顺序按照 Collection 顺序
	 * 如: a b c => c b a
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long leftPushAll(String key, Collection<Object> values) {
		return redisTemplate.opsForList().leftPushAll(namespace + key, values);
	}

	/**
	 * 获取列表 指定范围内的所有值
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> listRange(String key, long start, long end) {
		return redisTemplate.opsForList().range(namespace + key, start, end);
	}

	/**
	 * 删除 key 中 值为 value 的 count 个数.
	 *
	 * @param key
	 * @param count
	 * @param value
	 * @return 成功删除的个数
	 */
	public Long listRemove(String key, long count, Object value) {
		return redisTemplate.opsForList().remove(namespace + key, count, value);
	}

	/**
	 * 获取列表指定长度
	 *
	 * @param key
	 * @param index
	 * @return
	 */
	public Long listSize(String key, long index) {
		return redisTemplate.opsForList().size(namespace + key);
	}

	/**
	 * 删除 列表 [start,end] 以外的所有元素
	 *
	 * @param key
	 * @param start
	 * @param end
	 */
	public void listTrim(String key, long start, long end) {
		redisTemplate.opsForList().trim(namespace + key, start, end);

	}

	/**
	 * 返回集合中所有元素
	 *
	 * @param key
	 * @return
	 */
	public Set<Object> members(String key) {
		return redisTemplate.opsForSet().members(namespace + key);
	}

	/**
	 * 将 key 中的 value 转入到 destKey 中
	 *
	 * @param key
	 * @param value
	 * @param destKey
	 * @return 返回成功与否
	 */
	public boolean moveSet(String key, Object value, String destKey) {
		return redisTemplate.opsForSet().move(namespace + key, value, namespace + destKey);
	}

	/**
	 * 移除指定key 的过期时间
	 *
	 * @param key
	 * @return
	 */
	public boolean persist(String key) {
		return redisTemplate.boundValueOps(namespace + key).persist();
	}

//set 操作  无序不重复集合

	/**
	 * 根据下标获取值
	 *
	 * @param key
	 * @param index
	 * @return
	 */
	public Object popIndex(String key, long index) {
		return redisTemplate.opsForList().index(namespace + key, index);
	}

	/**
	 * 添加 Hash 键值对
	 *
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void put(String key, String hashKey, String value) {
		redisTemplate.opsForHash().put(namespace + key, hashKey, value);
	}

	/**
	 * 批量添加 hash 的 键值对
	 * 有则覆盖,没有则添加
	 *
	 * @param key
	 * @param map
	 */
	public void putAll(String key, Map<String, String> map) {
		redisTemplate.opsForHash().putAll(namespace + key, map);
	}

	/**
	 * 添加 hash 键值对. 不存在的时候才添加
	 *
	 * @param key
	 * @param hashKey
	 * @param value
	 * @return
	 */
	public boolean putIfAbsent(String key, String hashKey, String value) {
		return redisTemplate.opsForHash().putIfAbsent(namespace + key, hashKey, value);
	}

	/**
	 * 随机获取一个元素
	 *
	 * @param key
	 * @return
	 */
	public Object randomSet(String key) {
		return redisTemplate.opsForSet().randomMember(namespace + key);
	}

	/**
	 * 随机获取指定数量的元素,去重(同一个元素只能选择两一次)
	 *
	 * @param key
	 * @param count
	 * @return
	 */
	public Set<Object> randomSetDistinct(String key, long count) {
		return redisTemplate.opsForSet().distinctRandomMembers(namespace + key, count);
	}

	/**
	 * 随机移除一个元素,并返回出来
	 *
	 * @param key
	 * @return
	 */
	public Object randomSetPop(String key) {
		return redisTemplate.opsForSet().pop(namespace + key);
	}

	/**
	 * 获取索引区间内的排序结果集合(从0开始,从小到大,只有列名)
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<Object> range(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(namespace + key, start, end);
	}

	/**
	 * 获取分数范围内的 [min,max] 的排序结果集合 (从小到大,只有列名)
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<Object> rangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(namespace + key, min, max);
	}

	/**
	 * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从小到大,不带分数的集合)
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset 从指定下标开始
	 * @param count  输出指定元素数量
	 * @return
	 */
	public Set<Object> rangeByScore(String key, double min, double max, long offset, long count) {
		return redisTemplate.opsForZSet().rangeByScore(namespace + key, min, max, offset, count);
	}

	/**
	 * 获取 key 中指定 value 的排名(从0开始,从小到大排序)
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Long rank(String key, Object value) {
		return redisTemplate.opsForZSet().rank(namespace + key, value);
	}

	/**
	 * 删除一个或多个集合中的指定值
	 *
	 * @param key
	 * @param values
	 * @return 成功删除数量
	 */
	public Long remove(String key, Object... values) {
		return redisTemplate.opsForSet().remove(namespace + key, values);
	}

	/**
	 * 删除指定索引位置的成员,其中成员分数按( 从小到大 )
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long removeRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(namespace + key, start, end);
	}

	/**
	 * 删除指定 分数范围 内的成员 [main,max],其中成员分数按( 从小到大 )
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long removeRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(namespace + key, min, max);
	}

	/**
	 * Zset 删除一个或多个元素
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long removeZset(String key, String... values) {
		return redisTemplate.opsForZSet().remove(namespace + key, values);
	}

	/**
	 * 获取索引区间内的排序结果集合(从0开始,从大到小,只有列名)
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<Object> reverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(namespace + key, start, end);
	}

//Zset 根据 socre 排序   不重复 每个元素附加一个 socre  double类型的属性(double 可以重复)

	/**
	 * 获取分数范围内的 [min,max] 的排序结果集合 (从大到小,集合不带分数)
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<Object> reverseRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(namespace + key, min, max);
	}

	/**
	 * 返回 分数范围内 指定 count 数量的元素集合, 并且从 offset 下标开始(从大到小,不带分数的集合)
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset 从指定下标开始
	 * @param count  输出指定元素数量
	 * @return
	 */
	public Set<Object> reverseRangeByScore(String key, double min, double max, long offset, long count) {
		return redisTemplate.opsForZSet().reverseRangeByScore(namespace + key, min, max, offset, count);
	}

	/**
	 * 获取 key 中指定 value 的排名(从0开始,从大到小排序)
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Long reverseRank(String key, Object value) {
		return redisTemplate.opsForZSet().reverseRank(namespace + key, value);
	}

	/**
	 * 指定 list 从右出栈
	 * 如果列表没有元素,会堵塞到列表一直有元素或者超时为止
	 *
	 * @param key
	 * @return 出栈的值
	 */
	public Object rightPop(String key) {
		return redisTemplate.opsForList().rightPop(namespace + key);
	}

	/**
	 * 将 key 右出栈,并左入栈到 key2
	 *
	 * @param key  右出栈的列表
	 * @param key2 左入栈的列表
	 * @return 操作的值
	 */
	public Object rightPopAndLeftPush(String key, String key2) {
		return redisTemplate.opsForList().rightPopAndLeftPush(namespace + key, namespace + key2);

	}

	/**
	 * 指定 list 从右入栈
	 *
	 * @param key
	 * @return 当前队列的长度
	 */
	public Long rightPush(String key, Object value) {
		return redisTemplate.opsForList().rightPush(namespace + key, value);
	}

	/**
	 * 从右边依次入栈
	 * 导入顺序按照 Collection 顺序
	 * 如: a b c => a b c
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long rightPushAll(String key, Collection<Object> values) {
		return redisTemplate.opsForList().rightPushAll(namespace + key, values);
	}

	/**
	 * 获取指定成员的 score 值
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Double score(String key, Object value) {
		return redisTemplate.opsForZSet().score(namespace + key, value);
	}

	/**
	 * 存储 String 类型 key-value
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(namespace + key, value);
	}

	/**
	 * 存储key-value字符串
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(namespace + key, value);
	}

	/**
	 * 存储 String 类型 key-value 并添加失效时间
	 *
	 * @param key
	 * @param value
	 * @param expire 失效时间，单位：秒
	 */
	public void set(String key, Object value, long expire) {
		redisTemplate.opsForValue().set(namespace + key, value, expire, TimeUnit.SECONDS);
	}

	/**
	 * 设置失效时间
	 *
	 * @param key
	 * @param expire
	 */
	public void setKeyExpire(String key, long expire) {
		redisTemplate.expire(namespace + key, expire, TimeUnit.SECONDS);
	}

	/**
	 * 无序集合的大小
	 *
	 * @param key
	 * @return
	 */
	public Long setSize(String key) {
		return redisTemplate.opsForSet().size(namespace + key);
	}

	/**
	 * 返回 zset 集合数量
	 *
	 * @param key
	 * @return
	 */
	public long sizeZset(String key) {
		return redisTemplate.opsForZSet().size(namespace + key);
	}

	/**
	 * 将 key 与 otherKey 的并集,保存到 destKey 中
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return destKey 数量
	 */
	public Long unionAndStoreSet(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(namespace + key, namespace + otherKey, namespace + destKey);
	}

	/**
	 * 将 key 与 otherKey 的并集,保存到 destKey 中
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return destKey 数量
	 */
	public Long unionAndStoreSet(String key, Collection<Object> otherKeys, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(namespace + key, namespace + otherKeys, namespace + destKey);
	}

	/**
	 * key 和 other 两个集合的并集,保存在 destKey 集合中, 列名相同的 score 相加
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long unionAndStoreZset(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(namespace + key, namespace + otherKey, namespace + destKey);
	}

	/**
	 * key 和 otherKeys 多个集合的并集,保存在 destKey 集合中, 列名相同的 score 相加
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long unionAndStoreZset(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(namespace + key, namespace + otherKeys, namespace + destKey);
	}

	/**
	 * 返回 key 和 othere 的并集
	 *
	 * @param key
	 * @param otherKey
	 * @return
	 */
	public Set<Object> unionSet(String key, String otherKey) {
		return redisTemplate.opsForSet().union(namespace + key, namespace + otherKey);
	}

	/**
	 * 返回 key 和 otherKeys 的并集
	 *
	 * @param key
	 * @param otherKeys key 的集合
	 * @return
	 */
	public Set<Object> unionSet(String key, Collection<Object> otherKeys) {
		return redisTemplate.opsForSet().union(namespace + key, namespace + otherKeys);
	}

}
