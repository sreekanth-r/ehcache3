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
package org.ehcache.clustered.cache.operations.impl;

import org.ehcache.clustered.cache.CacheIdentifier;
import org.ehcache.clustered.cache.operations.CacheOperation;
import org.ehcache.clustered.cache.operations.ClusterOperation;
import org.ehcache.clustered.cache.operations.KeyWrapper;

/**
 * 
 * @author Abhilash
 *
 */

public abstract class AbstractCacheOperation<K> implements CacheOperation<K> {

  private final KeyWrapper<K> key;
  private final CacheIdentifier cacheIdentifier;

  public AbstractCacheOperation(KeyWrapper<K> key, CacheIdentifier cacheIdentifier) {
    this.key = key;
    this.cacheIdentifier = cacheIdentifier;
  }

  @Override
  public org.ehcache.clustered.cache.operations.ClusterOperation.Type getType() {
    return ClusterOperation.Type.CACHE;
  }

  @Override
  public KeyWrapper getKey() {
    return this.key;
  }

  @Override
  public CacheIdentifier getCacheIdentifier() {
    return this.cacheIdentifier;
  }

}
