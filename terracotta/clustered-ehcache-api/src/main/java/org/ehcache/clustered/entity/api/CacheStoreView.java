/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehcache.clustered.entity.api;

import java.util.Map;
import java.util.Set;

import org.ehcache.clustered.cache.operations.KeyWrapper;

//Client side view of the server side cache storage.
//the concrete impl of this class will actually deleagate all the calls to the entity 

/**
 * 
 * @author Abhilash
 *
 */

public interface CacheStoreView<K, V> {

  V get(KeyWrapper<K> key);

  void put(KeyWrapper<K> key, byte[] value);

  boolean containsKey(KeyWrapper<K> key);

  void remove(KeyWrapper<K> key);

  Map<K, V> getAll(Set<KeyWrapper<K>> keys);

  // we can comeup with a better way of doing bulk operations
  // like values intelligently seralized in bulk and then comepressed.
  void putAll(Map<KeyWrapper<K>, byte[]> entries);

  void removeAll(Set<KeyWrapper<K>> keys);

  void clear();

  V putIfAbsent(KeyWrapper<K> key, byte[] value);

  boolean remove(KeyWrapper<K> key, byte[] value);

  V replace(KeyWrapper<K> key, byte[] value);

  boolean replace(KeyWrapper<K> key, byte[] oldValue, byte[] newValue);

  void backHandle(long hash);

}
