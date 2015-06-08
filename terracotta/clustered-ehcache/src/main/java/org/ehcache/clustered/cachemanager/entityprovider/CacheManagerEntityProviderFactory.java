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
package org.ehcache.clustered.cachemanager.entityprovider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.ehcache.clustered.config.ClusteredCacheManagerConfiguration;
import org.ehcache.clustered.config.Unit;
import org.ehcache.clustered.connection.EntityConnectionFactory;
import org.ehcache.clustered.entity.api.EhcacheManagerEntity;
import org.ehcache.spi.ServiceLocator;
import org.ehcache.spi.ServiceProvider;
import org.ehcache.spi.service.ServiceConfiguration;
import org.ehcache.spi.service.ServiceFactory;
import org.terracotta.connection.Connection;
import org.terracotta.connection.ConnectionException;
import org.terracotta.connection.entity.ConfigurationMismatchException;
import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityMaintenanceRef;
import org.terracotta.connection.entity.EntityRef;

import sun.management.counter.Units;

/**
 * 
 * @author Abhilash
 *
 */
public class CacheManagerEntityProviderFactory implements
    ServiceFactory<CacheManagerEntityProvider> {

  @Override
  public CacheManagerEntityProvider create(
      ServiceConfiguration<CacheManagerEntityProvider> serviceConfiguration,
      ServiceLocator serviceLocator) {
    return new CacheManagerEntityProvider() {

      @Override
      public void stop() {
      }

      @Override
      public void start(ServiceConfiguration<?> config, ServiceProvider serviceProvider) {
      }

      @Override
      public void releaseCacheManagerEntity(String entityId) {
      }

      @Override
      public EhcacheManagerEntity createCacheManagerEntity(String entityId) {
        Connection connection = null;
        try {
          connection = EntityConnectionFactory.connect(new URI("terracotta://localhost:9510"));
        } catch (ConnectionException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (URISyntaxException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        EntityMaintenanceRef<EhcacheManagerEntity, ClusteredCacheManagerConfiguration> mRef = connection
            .acquireMaintenanceModeRef(EhcacheManagerEntity.class, entityId);
        ClusteredCacheManagerConfiguration configuration = new ClusteredCacheManagerConfiguration() {

          @Override
          public Long getSize() {
            // TODO Auto-generated method stub
            return 50L;
          }

          @Override
          public String getEntityID() {
            // TODO Auto-generated method stub
            return null;
          }

          @Override
          public Unit getUnit() {
            // TODO Auto-generated method stub
            return null;
          }

        };
        // TODO handle multiple client trying to create entity.
        // for that u just need to check the acuire entity before and after entring mmode like
        // double check locking
        mRef.create(configuration);
        mRef.close();
        EntityRef<EhcacheManagerEntity, ClusteredCacheManagerConfiguration> entityRef = connection
            .getEntityRef(EhcacheManagerEntity.class, entityId);
        EhcacheManagerEntity cacheManagerEntity = null;
        try {
          cacheManagerEntity = entityRef.fetchEntity();
        } catch (Exception cme) {
          cme.printStackTrace();
        }
        return cacheManagerEntity;
      }
    };
  }

  @Override
  public Class<CacheManagerEntityProvider> getServiceType() {
    return CacheManagerEntityProvider.class;
  }

}
