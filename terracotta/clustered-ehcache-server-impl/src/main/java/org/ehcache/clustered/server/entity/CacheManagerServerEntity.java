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
package org.ehcache.clustered.server.entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.ehcache.clustered.cache.operations.CacheOperation;
import org.ehcache.clustered.cache.operations.ClusterOperation;
import org.ehcache.clustered.cache.operations.LifeCycleOperation;
import org.ehcache.clustered.entity.api.CacheStoreView;
import org.terracotta.entity.AbstractDecodingServerEntity;
import org.terracotta.entity.ClientDescriptor;
import org.terracotta.entity.ConcurrencyStrategy;

/**
 * 
 * @author Abhilash
 *
 */

public class CacheManagerServerEntity extends
    AbstractDecodingServerEntity<ClusterOperation, Object> {

  private Map<String, CacheStoreView<?, ?>> clusteredCaches = new ConcurrentHashMap<String, CacheStoreView<?, ?>>();
  private Set<ClientDescriptor> connectedClients = new HashSet<ClientDescriptor>();

  private ReentrantLock clientsStateLock = new ReentrantLock();

  public CacheManagerServerEntity() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public byte[] getConfig() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
  }

  @Override
  protected ClusterOperation decodeInput(byte[] bytes) {
    // TODO serialize - serialization would change according to type of operation
    // need to write two kinds of serialization/desriaolization strategy
    return null;
  }

  @Override
  protected byte[] encodeOutput(Object o) {
    // TODO Deserialize - need something as OperationResult kind of thing
    return null;
  }

  @Override
  public void connected(ClientDescriptor clientDescriptor) {
    clientsStateLock.lock();
    try {
      connectedClients.add(clientDescriptor);
    } finally {
      clientsStateLock.unlock();
    }
  }

  @Override
  public void disconnected(ClientDescriptor clientDescriptor) {
    clientsStateLock.lock();
    try {
      connectedClients.remove(clientDescriptor);
    } finally {
      clientsStateLock.unlock();
    }
  }

  @Override
  protected Object invoke(ClientDescriptor clientDescriptor, ClusterOperation input) {
    // TODO Auto-generated method stub - shouldnt we have invokes for multiple type of operations
    // bring all the operations under one hood
    return input instanceof CacheOperation ? invokeCacheOperations((CacheOperation) input)
        : invokeLifeCycleOperation((LifeCycleOperation) input);

  }

  private synchronized Object invokeLifeCycleOperation(LifeCycleOperation operation) {
    return null;
  }

  private Object invokeCacheOperations(CacheOperation operation) {
    return null;
  }

  @Override
  public ConcurrencyStrategy getConcurrencyStrategy() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void createNew() {
    // TODO Auto-generated method stub

  }

  @Override
  public void loadExisting() {
    // TODO Auto-generated method stub

  }
}
