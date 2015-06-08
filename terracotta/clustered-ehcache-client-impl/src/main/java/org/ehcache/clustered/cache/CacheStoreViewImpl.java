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
package org.ehcache.clustered.cache;

import java.util.Map;
import java.util.Set;

import org.ehcache.clustered.cache.operations.KeyWrapper;
import org.ehcache.clustered.cache.operations.impl.PutOperation;
import org.ehcache.clustered.client.entity.CacheManagerEntity;
import org.ehcache.clustered.entity.api.CacheStoreView;

/**
 * 
 * @author Abhilash
 *
 */

public class CacheStoreViewImpl<K, V> implements CacheStoreView<K, V> {

  private final CacheManagerEntity entity;
  private final CacheIdentifier identifier;

  public CacheStoreViewImpl(CacheManagerEntity cacheManagerEntity, CacheIdentifier identifier) {
    this.entity = cacheManagerEntity;
    this.identifier = identifier;
  }

  @Override
  public V get(KeyWrapper<K> key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void put(KeyWrapper<K> key, byte[] value) {

    this.entity.invoke(new PutOperation<K>(key, identifier, value));
  }

  @Override
  public boolean containsKey(KeyWrapper<K> key) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void remove(KeyWrapper<K> key) {
    // TODO Auto-generated method stub

  }

  @Override
  public Map<K, V> getAll(Set<KeyWrapper<K>> keys) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void putAll(Map<KeyWrapper<K>, byte[]> entries) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeAll(Set<KeyWrapper<K>> keys) {
    // TODO Auto-generated method stub

  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

  @Override
  public V putIfAbsent(KeyWrapper<K> key, byte[] value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean remove(KeyWrapper<K> key, byte[] value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public V replace(KeyWrapper<K> key, byte[] value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean replace(KeyWrapper<K> key, byte[] oldValue, byte[] newValue) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void backHandle(long hash) {
    // TODO Auto-generated method stub

  }

}
