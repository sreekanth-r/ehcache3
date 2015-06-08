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
package org.ehcache.clustered.server.caches;

import java.util.Map;
import java.util.Set;

import org.ehcache.clustered.config.ClusteredCacheConfiguration;
import org.terracotta.corestorage.ImmutableKeyValueStorageConfig;
import org.terracotta.corestorage.KeyValueStorage;
import org.terracotta.corestorage.StorageManager;
import org.terracotta.entity.ClientDescriptor;

/**
 * 
 * @author Abhilash
 *
 */

public class ServerCacheStorage<K, V> {

  // work on the best way to segment and align the segments acorss cache config and keyvaluestorage
  // Implement locks per buket also as this ll screw up things
  // private final List<KeyValueStorage<K, V>> buckets ;
  private final KeyValueStorage<K, V> store;
  private final KeyReferenceBookKeeper keyToClientReferences;

  public ServerCacheStorage(final ClusteredCacheConfiguration<K, V> configuration,
      StorageManager manager, KeyReferenceBookKeeper bookKeeper) {
    this.store = manager.createKeyValueStorage(
        configuration.getIdentifier().getIdentifier(),
        ImmutableKeyValueStorageConfig
            .builder(configuration.getKeyType(), configuration.getValueType())
            .concurrency(configuration.getSegments()).build());
    this.keyToClientReferences = bookKeeper;
  }

  public V get(K key, ClientDescriptor clientDescriptor) {
    return store.get(key);
  }

  public void put(K key, V value, ClientDescriptor clientDescriptor) {
    store.put(key, value);
  }

  public boolean containsKey(K key, ClientDescriptor clientDescriptor) {
    return store.containsKey(key);
  }

  public void remove(K key, ClientDescriptor clientDescriptor) {
    store.remove(key);
  }

  public Map<K, V> getAll(Set<? extends K> keys, ClientDescriptor clientDescriptor) {
    // add this method in KeyValueStorage -why its not there
    return null;
  }

  public void putAll(Map<? extends K, ? extends V> entries, ClientDescriptor clientDescriptor) {
    // add this method in KeyValueStorage -why its not there
  }

  public void removeAll(Set<? extends K> keys, ClientDescriptor clientDescriptor) {
    store.removeAll((Set<K>) keys);
  }

  public void clear() {
    store.clear();
  }

  public V putIfAbsent(K key, V value, ClientDescriptor clientDescriptor) {
    return null;
  }

  public boolean remove(K key, V value, ClientDescriptor clientDescriptor) {
    return store.remove(key);
  }

  public V replace(K key, V value, ClientDescriptor clientDescriptor) {
    return null;
  }

  public boolean replace(K key, V oldValue, V newValue, ClientDescriptor clientDescriptor) {
    // TODO Is replace is something which screws up Offheap
    return false;
  }

  public void addClient(ClientDescriptor descriptor) {
    this.keyToClientReferences.addClient(descriptor);
  }

  public void removeClient(ClientDescriptor descriptor) {
    this.keyToClientReferences.removeClient(descriptor);
  }

}
