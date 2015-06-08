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

import org.ehcache.clustered.config.ClusteredCacheManagerConfiguration;
import org.ehcache.clustered.entity.api.EhcacheManagerEntity;
import org.terracotta.entity.EntityClientEndpoint;
import org.terracotta.entity.EntityClientService;

/**
 * 
 * @author Abhilash
 *
 */

public class CacheManagerEntityService implements
    EntityClientService<EhcacheManagerEntity, ClusteredCacheManagerConfiguration> {

  @Override
  public boolean handlesEntityType(Class<EhcacheManagerEntity> cls) {
    return true; // for now
  }

  @Override
  public EhcacheManagerEntity create(EntityClientEndpoint endpoint) {
    return new CacheManagerEntity(endpoint);
  }

  @Override
  public byte[] serializeConfiguration(ClusteredCacheManagerConfiguration configuration) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ClusteredCacheManagerConfiguration deserializeConfiguration(byte[] configuration) {
    // TODO Auto-generated method stub
    return null;
  }

}
