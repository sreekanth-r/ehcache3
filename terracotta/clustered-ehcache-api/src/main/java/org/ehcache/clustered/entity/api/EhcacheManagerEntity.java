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

import java.util.concurrent.Future;

import org.ehcache.clustered.cache.operations.CacheOperation;
import org.ehcache.clustered.cache.operations.LifeCycleOperation;
import org.ehcache.clustered.entity.exceptions.EntityAccessException;
import org.terracotta.connection.entity.Entity;
import org.terracotta.entity.EndpointListener;

/**
 * 
 * @author Abhilash
 *
 */

public interface EhcacheManagerEntity extends Entity, CacheStoreViewManager,
    EndpointListener {

  // Only for CacheOpeartions and will return the future
  // for nonstop operations we can have timed get on the future
  // at the client side
  Future<byte[]> invoke(CacheOperation cacheOperation);

  // All the lifecycle operations will be blocking operation and
  // block until the future completes
  byte[] invokeWithReturn(LifeCycleOperation cacheOperation);

}
