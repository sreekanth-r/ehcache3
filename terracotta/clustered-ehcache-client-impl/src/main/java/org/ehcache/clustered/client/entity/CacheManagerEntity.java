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
package org.ehcache.clustered.client.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.ehcache.clustered.cache.CacheIdentifier;
import org.ehcache.clustered.cache.CacheStoreViewImpl;
import org.ehcache.clustered.cache.operations.CacheOperation;
import org.ehcache.clustered.cache.operations.LifeCycleOperation;
import org.ehcache.clustered.cache.operations.LifeCycleOperation.Type;
import org.ehcache.clustered.config.ClusteredCacheConfiguration;
import org.ehcache.clustered.entity.api.CacheStoreView;
import org.ehcache.clustered.entity.api.EhcacheManagerEntity;
import org.terracotta.entity.EntityClientEndpoint;

/**
 * 
 * @author Abhilash
 *
 */

public class CacheManagerEntity implements EhcacheManagerEntity {

  private final EntityClientEndpoint endpoint;
  private final Map<String, CacheStoreView<?, ?>> clusteredCaches = new ConcurrentHashMap<String, CacheStoreView<?, ?>>();

  public CacheManagerEntity(EntityClientEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @Override
  public void close() {
    endpoint.close();
  }

  @Override
  public <K, V> CacheStoreView<K, V> createClusteredCacheStoreView(
      ClusteredCacheConfiguration<K, V> clusteredCacheConfiguration, boolean strict) {
    // use status transtioner to provide exclusivity and lifecycle statuses
    byte[] result = invokeWithReturn(new LifeCycleOperation() {

      @Override
      public org.ehcache.clustered.cache.operations.ClusterOperation.Type getType() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public CacheIdentifier getCacheIdentifier() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public Type getLifeCycleOperationType() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public <K, V> ClusteredCacheConfiguration<K, V> getClusteredCacheConfiguration() {
        // TODO Auto-generated method stub
        return null;
      }
    });
    // based on the result we got above create a new cache else return null
    CacheStoreView<K, V> cache = new CacheStoreViewImpl<K, V>(this,
        clusteredCacheConfiguration.getIdentifier());
    clusteredCaches.put(clusteredCacheConfiguration.getIdentifier().getIdentifier(), cache);
    return cache;
  }

  @Override
  public void destroyClusteredCacheStoreView(CacheIdentifier cacheIdentifier) {
    // TODO Auto-generated method stub

  }

  @Override
  public CacheStoreView<?, ?> getClusteredCacheStoreView(CacheIdentifier cacheIdentifier) {
    return clusteredCaches.get(cacheIdentifier.getIdentifier());
  }

  @Override
  public void handleMessage(byte[] payload) {
    // TODO Based on the higher 32 bits find cache
    String cacheId = new String(payload);
    clusteredCaches.get(cacheId).backHandle(1L);
    ;

  }

  @Override
  public Future<byte[]> invoke(CacheOperation cacheOperation) {
    return endpoint.beginInvoke().payload(null).invoke();
  }

  @Override
  public byte[] invokeWithReturn(LifeCycleOperation cacheOperation) {
    try {
      return endpoint.beginInvoke().payload(null)
          .ackCompleted()
          .ackReplicated()
          .invoke().get();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public <K, V> CacheStoreView<K, V> useExistingCacheStoreView(CacheIdentifier cacheIdentifier) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <K, V> CacheStoreView<K, V> overrideIfExistingCacheStoreView(
      ClusteredCacheConfiguration<K, V> clusteredCacheConfiguration) {
    // TODO Auto-generated method stub
    return null;
  }
}
