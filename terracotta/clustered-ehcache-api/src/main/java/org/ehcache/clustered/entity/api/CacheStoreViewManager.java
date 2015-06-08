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

import org.ehcache.clustered.cache.CacheIdentifier;
import org.ehcache.clustered.config.ClusteredCacheConfiguration;

// This interface is just for managing the lifecycle of the Caches.
// The lifecycle api for entity is provided by platform, through which we can control the lifecycle of the entity.

/**
 * 
 * @author Abhilash
 *
 */

public interface CacheStoreViewManager {

  // Creates a clustered with a provided cache config.
  // if the strict is true then it throws back to the user.
  <K, V> CacheStoreView<K, V> createClusteredCacheStoreView(
      ClusteredCacheConfiguration<K, V> clusteredCacheConfiguration, boolean strict);

  // destroy a cache store at server side
  void destroyClusteredCacheStoreView(CacheIdentifier cacheIdentifier);

  // Use the existing server side store for the given cache identifier
  <K, V> CacheStoreView<K, V> useExistingCacheStoreView(CacheIdentifier cacheIdentifier);

  // Use the existing server side store for the given clustered cache configuration
  // destroy and create id the config does not match
  <K, V> CacheStoreView<K, V> overrideIfExistingCacheStoreView(
      ClusteredCacheConfiguration<K, V> clusteredCacheConfiguration);

  // this just gets the local reference
  <K, V> CacheStoreView<K, V> getClusteredCacheStoreView(CacheIdentifier cacheIdentifier);

}
