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
import org.ehcache.clustered.cache.operations.KeyWrapper;

/**
 * 
 * @author Abhilash
 *
 */

public class PutOperation<K> extends AbstractCacheOperation<K> {

  private final byte[] value;

  public PutOperation(KeyWrapper<K> key, CacheIdentifier identifier, byte[] value) {
    super(key, identifier);
    this.value = value;
  }

  @Override
  public org.ehcache.clustered.cache.operations.CacheOperation.Type getCacheOperationType() {
    return CacheOperation.Type.PUT;
  }

  @Override
  public byte[] getValue() {
    return this.value;
  }

}
