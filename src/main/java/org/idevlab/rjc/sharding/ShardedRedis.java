/*
 * Copyright 2010-2011. Evgeny Dolgov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.idevlab.rjc.sharding;

import org.idevlab.rjc.RedisOperations;
import org.idevlab.rjc.SortingParams;
import org.idevlab.rjc.Client;

import java.util.*;

public class ShardedRedis implements RedisOperations {

    private NodeLocator<? extends RedisOperations> locator;

    public ShardedRedis() {
    }

    public ShardedRedis(NodeLocator<? extends RedisOperations> locator) {
        this.locator = locator;
    }

    public void setLocator(NodeLocator<? extends RedisOperations> locator) {
        this.locator = locator;
    }

    public String set(final String key, final String value) {
        return locator.getNode(key).set(key, value);
    }

    public String get(final String key) {
        return locator.getNode(key).get(key);
    }

    public boolean exists(final String key) {
        return locator.getNode(key).exists(key);
    }

    public Long del(String... keys) {
        long result = 0;
        for (RedisOperations node : locator.getNodes()) {
            result += node.del(keys);
        }
        return result;
    }

    public String type(final String key) {
        return locator.getNode(key).type(key);
    }

    public boolean expire(final String key, final int seconds) {
        return locator.getNode(key).expire(key, seconds);
    }

    public boolean expireAt(final String key, final long unixTime) {
        return locator.getNode(key).expireAt(key, unixTime);
    }

    public Long ttl(final String key) {
        return locator.getNode(key).ttl(key);
    }

    public String getSet(final String key, final String value) {
        return locator.getNode(key).getSet(key, value);
    }

    public Long setnx(final String key, final String value) {
        return locator.getNode(key).setnx(key, value);
    }

    public String setex(final String key, int seconds, String value) {
        return locator.getNode(key).setex(key, seconds, value);
    }

    public Long decrBy(final String key, int integer) {
        return locator.getNode(key).decrBy(key, integer);
    }

    public Long decr(final String key) {
        return locator.getNode(key).decr(key);
    }

    public Long incrBy(final String key, int integer) {
        return locator.getNode(key).incrBy(key, integer);
    }

    public Long incr(final String key) {
        return locator.getNode(key).incr(key);
    }

    public Long append(final String key, String value) {
        return locator.getNode(key).append(key, value);
    }

    public String substr(final String key, int start, int end) {
        return locator.getNode(key).substr(key, start, end);
    }

    public Set<String> keys(String pattern) {
        Set<String> result = new HashSet<String>();
        for (RedisOperations node : locator.getNodes()) {
            result.addAll(node.keys(pattern));
        }
        return result;
    }

    public boolean persist(String key) {
        return locator.getNode(key).persist(key);
    }

    public Long hset(final String key, String field, String value) {
        return locator.getNode(key).hset(key, field, value);
    }

    public String hget(final String key, String field) {
        return locator.getNode(key).hget(key, field);
    }

    public Long hsetnx(final String key, String field, String value) {
        return locator.getNode(key).hsetnx(key, field, value);
    }

    public String hmset(final String key, Map<String, String> hash) {
        return locator.getNode(key).hmset(key, hash);
    }

    public List<String> hmget(final String key, String... fields) {
        return locator.getNode(key).hmget(key, fields);
    }

    public Long hincrBy(final String key, String field, int value) {
        return locator.getNode(key).hincrBy(key, field, value);
    }

    public Boolean hexists(final String key, String field) {
        return locator.getNode(key).hexists(key, field);
    }

    public Long hdel(final String key, String field) {
        return locator.getNode(key).hdel(key, field);
    }

    public Long hlen(final String key) {
        return locator.getNode(key).hlen(key);
    }

    public Set<String> hkeys(final String key) {

        return locator.getNode(key).hkeys(key);
    }

    public Collection<String> hvals(final String key) {

        return locator.getNode(key).hvals(key);
    }

    public Map<String, String> hgetAll(final String key) {

        return locator.getNode(key).hgetAll(key);
    }

    public Long rpush(final String key, String string) {

        return locator.getNode(key).rpush(key, string);
    }

    public Long rpushx(String key, String string) {
        return locator.getNode(key).rpushx(key, string);
    }

    public Long lpush(final String key, String string) {

        return locator.getNode(key).lpush(key, string);
    }

    public Long lpushx(String key, String string) {
        return locator.getNode(key).lpushx(key, string);
    }

    public Long llen(final String key) {

        return locator.getNode(key).llen(key);
    }

    public List<String> lrange(final String key, int start, int end) {

        return locator.getNode(key).lrange(key, start, end);
    }

    public String ltrim(final String key, int start, int end) {

        return locator.getNode(key).ltrim(key, start, end);
    }

    public String lindex(final String key, int index) {

        return locator.getNode(key).lindex(key, index);
    }

    public String lset(final String key, int index, String value) {

        return locator.getNode(key).lset(key, index, value);
    }

    public Long lrem(final String key, int count, String value) {

        return locator.getNode(key).lrem(key, count, value);
    }

    public String lpop(final String key) {

        return locator.getNode(key).lpop(key);
    }

    public String rpop(final String key) {

        return locator.getNode(key).rpop(key);
    }

    public Long sadd(final String key, String member) {

        return locator.getNode(key).sadd(key, member);
    }

    public Set<String> smembers(final String key) {

        return locator.getNode(key).smembers(key);
    }

    public Long srem(final String key, String member) {

        return locator.getNode(key).srem(key, member);
    }

    public String spop(final String key) {

        return locator.getNode(key).spop(key);
    }

    public Long scard(final String key) {

        return locator.getNode(key).scard(key);
    }

    public Boolean sismember(final String key, String member) {

        return locator.getNode(key).sismember(key, member);
    }

    public String srandmember(final String key) {

        return locator.getNode(key).srandmember(key);
    }

    public Long zadd(final String key, Number score, String member) {

        return locator.getNode(key).zadd(key, score, member);
    }

    public Set<String> zrange(final String key, int start, int end) {

        return locator.getNode(key).zrange(key, start, end);
    }

    public Long zrem(final String key, String member) {

        return locator.getNode(key).zrem(key, member);
    }

    public String zincrby(final String key, Number score, String member) {

        return locator.getNode(key).zincrby(key, score, member);
    }

    public Long zrank(final String key, String member) {

        return locator.getNode(key).zrank(key, member);
    }

    public Long zrevrank(final String key, String member) {

        return locator.getNode(key).zrevrank(key, member);
    }

    public Set<String> zrevrange(final String key, int start, int end) {

        return locator.getNode(key).zrevrange(key, start, end);
    }

    public Map<String, String> zrangeWithScores(final String key, int start, int end) {

        return locator.getNode(key).zrangeWithScores(key, start, end);
    }

    public Map<String, String> zrevrangeWithScores(final String key, int start, int end) {

        return locator.getNode(key).zrevrangeWithScores(key, start, end);
    }

    public Long zcard(final String key) {

        return locator.getNode(key).zcard(key);
    }

    public String zscore(final String key, String member) {

        return locator.getNode(key).zscore(key, member);
    }

    public List<String> sort(final String key) {

        return locator.getNode(key).sort(key);
    }

    public List<String> sort(final String key, SortingParams sortingParameters) {

        return locator.getNode(key).sort(key, sortingParameters);
    }

    public Long zcount(final String key, Number min, Number max) {

        return locator.getNode(key).zcount(key, min, max);
    }

    public Set<String> zrangeByScore(final String key, String min, String max) {

        return locator.getNode(key).zrangeByScore(key, min, max);
    }

    public Set<String> zrangeByScore(final String key, String min, String max, int offset, int count) {
        return locator.getNode(key).zrangeByScore(key, min, max, offset, count);
    }

    public Map<String, String> zrangeByScoreWithScores(final String key, String min, String max) {
        return locator.getNode(key).zrangeByScoreWithScores(key, min, max);
    }

    public Map<String, String> zrangeByScoreWithScores(final String key, String min,
                                                       String max, int offset, int count) {

        return locator.getNode(key).zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public Long zremrangeByRank(final String key, int start, int end) {
        return locator.getNode(key).zremrangeByRank(key, start, end);
    }

    public Long zremrangeByScore(final String key, Number start, Number end) {
        return locator.getNode(key).zremrangeByScore(key, start, end);
    }

    public Long linsert(final String key, Client.LIST_POSITION where, String pivot,
                        String value) {
        return locator.getNode(key).linsert(key, where, pivot, value);
    }

    public Long publish(String channel, String message) {
        return locator.getNode(channel).publish(channel, message);
    }

    public Long getBit(String key, int offset) {
        return locator.getNode(key).getBit(key, offset);
    }

    public Long setBit(String key, int offset, String value) {
        return locator.getNode(key).setBit(key, offset, value);
    }
}