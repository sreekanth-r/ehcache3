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
package org.ehcache.clustered.store;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.ehcache.CacheConfigurationChangeListener;
import org.ehcache.Status;
import org.ehcache.Cache.Entry;
import org.ehcache.clustered.cache.CacheIdentifier;
import org.ehcache.clustered.cache.operations.KeyWrapper;
import org.ehcache.clustered.cachemanager.entityprovider.CacheManagerEntityProvider;
import org.ehcache.clustered.config.ClusteredCacheConfiguration;
import org.ehcache.clustered.config.Consistency;
import org.ehcache.clustered.config.Unit;
import org.ehcache.clustered.entity.api.CacheStoreView;
import org.ehcache.clustered.entity.api.EhcacheManagerEntity;
import org.ehcache.config.ResourcePool;
import org.ehcache.config.ResourceType;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.events.StoreEventListener;
import org.ehcache.exceptions.CacheAccessException;
import org.ehcache.expiry.Expiry;
import org.ehcache.function.BiFunction;
import org.ehcache.function.Function;
import org.ehcache.function.NullaryFunction;
import org.ehcache.function.Predicate;
import org.ehcache.function.Predicates;
import org.ehcache.internal.TimeSource;
import org.ehcache.spi.ServiceProvider;
import org.ehcache.spi.cache.Store;
import org.ehcache.spi.cache.tiering.AuthoritativeTier;
import org.ehcache.spi.serialization.SerializationProvider;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.service.ServiceConfiguration;

/**
 * 
 * @author Abhilash
 *
 */

public class ClusteredStore<K, V> implements AuthoritativeTier<K, V> {

  private final Class<K> keyType;
  private final Class<V> valueType;
  private final TimeSource timeSource;

  private final Expiry<? super K, ? super V> expiry;
  private final AtomicReference<Status> status = new AtomicReference<Status>(Status.UNINITIALIZED);

  private final Predicate<Map.Entry<K, ClusterValueHolder<V>>> evictionVeto;
  private final Serializer<K> keySerializer;
  private final Serializer<V> valueSerializer;
  private final long sizeInBytes;

  private final CacheStoreView<K, V> clusteredCache;

  // private final CacheOperationCodec<K, V> codec;
  // Use bitset to store set of hashes client is having

  public ClusteredStore(final Configuration<K, V> config, Serializer<K> keySerializer,
      Serializer<V> valueSerializer, TimeSource timeSource, long sizeInBytes,
      CacheStoreView<K, V> clusteredCache) {
    this.keyType = config.getKeyType();
    this.valueType = config.getValueType();
    this.timeSource = timeSource;

    // Need to check how both if this ll work in case of expiry and evictions done at server side
    this.expiry = config.getExpiry();
    this.evictionVeto = Predicates.none();

    this.keySerializer = keySerializer;
    this.valueSerializer = valueSerializer;

    this.sizeInBytes = sizeInBytes;
    this.clusteredCache = clusteredCache;
    // this.codec = new CacheOperationCodec<K, V>(keySerializer, valueSerializer);

  }

  private KeyWrapper<K> getKeyWrapper(K k) {
    // try {
    // return new KeyWrapper<K>(k, codec.encodeKey(k).array());
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    return null;
  }

  @Override
  public ValueHolder<V> get(K key) throws CacheAccessException {
    return null;
  }

  @Override
  public boolean containsKey(K key) throws CacheAccessException {
    return false;
  }

  @Override
  public void put(K key, V value) throws CacheAccessException {
    // try {
    // clusteredCache.put(getKeyWrapper(key), codec.encodeValue(value).array());
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
  }

  @Override
  public ValueHolder<V> putIfAbsent(K key, V value) throws CacheAccessException {
    return null;
  }

  @Override
  public void remove(K key) throws CacheAccessException {
  }

  @Override
  public boolean remove(K key, V value) throws CacheAccessException {
    return false;
  }

  @Override
  public ValueHolder<V> replace(K key, V value) throws CacheAccessException {
    return null;
  }

  @Override
  public boolean replace(K key, V oldValue, V newValue) throws CacheAccessException {
    return false;
  }

  @Override
  public void clear() throws CacheAccessException {

  }

  @Override
  public void disableStoreEventNotifications() {

  }

  @Override
  public Iterator<Entry<K, ValueHolder<V>>> iterator() throws CacheAccessException {
    return null;
  }

  @Override
  public ValueHolder<V> compute(K key, BiFunction<? super K, ? super V, ? extends V> mappingFunction)
      throws CacheAccessException {
    return null;
  }

  @Override
  public ValueHolder<V> compute(K key,
      BiFunction<? super K, ? super V, ? extends V> mappingFunction,
      NullaryFunction<Boolean> replaceEqual) throws CacheAccessException {
    return null;
  }

  @Override
  public ValueHolder<V> computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
      throws CacheAccessException {
    return null;
  }

  @Override
  public ValueHolder<V> computeIfPresent(K key,
      BiFunction<? super K, ? super V, ? extends V> remappingFunction) throws CacheAccessException {
    return null;
  }

  @Override
  public ValueHolder<V> computeIfPresent(K key,
      BiFunction<? super K, ? super V, ? extends V> remappingFunction,
      NullaryFunction<Boolean> replaceEqual) throws CacheAccessException {
    return null;
  }

  @Override
  public Map<K, ValueHolder<V>> bulkCompute(
      Set<? extends K> keys,
      Function<Iterable<? extends java.util.Map.Entry<? extends K, ? extends V>>, Iterable<? extends java.util.Map.Entry<? extends K, ? extends V>>> remappingFunction)
      throws CacheAccessException {
    return null;
  }

  @Override
  public Map<K, ValueHolder<V>> bulkCompute(
      Set<? extends K> keys,
      Function<Iterable<? extends java.util.Map.Entry<? extends K, ? extends V>>, Iterable<? extends java.util.Map.Entry<? extends K, ? extends V>>> remappingFunction,
      NullaryFunction<Boolean> replaceEqual) throws CacheAccessException {
    return null;
  }

  @Override
  public Map<K, ValueHolder<V>> bulkComputeIfAbsent(
      Set<? extends K> keys,
      Function<Iterable<? extends K>, Iterable<? extends java.util.Map.Entry<? extends K, ? extends V>>> mappingFunction)
      throws CacheAccessException {
    return null;
  }

  @Override
  public ValueHolder<V> getAndFault(K key) throws CacheAccessException {
    return null;
  }

  @Override
  public ValueHolder<V> computeIfAbsentAndFault(K key,
      Function<? super K, ? extends V> mappingFunction) throws CacheAccessException {
    return null;
  }

  @Override
  public boolean flush(K key, ValueHolder<V> valueHolder) {
    return false;
  }

  public static class Provider implements Store.Provider, AuthoritativeTier.Provider {

    private volatile ServiceProvider serviceProvider;
    private volatile EhcacheManagerEntity cacheManagerEntity;

    @Override
    public void start(ServiceConfiguration<?> config, ServiceProvider serviceProvider) {
      this.serviceProvider = serviceProvider;
      this.cacheManagerEntity = serviceProvider.findService(CacheManagerEntityProvider.class)
          .createCacheManagerEntity("testCacheManager");

    }

    @Override
    public void stop() {

    }

    @Override
    public <K, V> AuthoritativeTier<K, V> createAuthoritativeTier(Configuration<K, V> storeConfig,
        ServiceConfiguration<?>... serviceConfigs) {
      return null;
    }

    @Override
    public void releaseAuthoritativeTier(AuthoritativeTier<?, ?> resource) {

    }

    @Override
    public void initAuthoritativeTier(AuthoritativeTier<?, ?> resource) {

    }

    @Override
    public <K, V> Store<K, V> createStore(Configuration<K, V> storeConfig,
        ServiceConfiguration<?>... serviceConfigs) {
      // TimeSourceConfiguration timeSourceConfig =
      // findSingletonAmongst(TimeSourceConfiguration.class, (Object[]) serviceConfigs);
      // TimeSource timeSource = timeSourceConfig != null ? timeSourceConfig.getTimeSource() :
      // SystemTimeSource.INSTANCE;

      SerializationProvider serializationProvider = serviceProvider
          .findService(SerializationProvider.class);
      Serializer<K> keySerializer = serializationProvider.createKeySerializer(
          storeConfig.getKeyType(), storeConfig.getClassLoader());
      Serializer<V> valueSerializer = serializationProvider.createValueSerializer(
          storeConfig.getValueType(), storeConfig.getClassLoader());

      // change this for cluster
      ResourcePool clusteredPool = storeConfig.getResourcePools().getPoolForResource(
          ResourceType.Core.OFFHEAP);
      MemoryUnit unit = (MemoryUnit) clusteredPool.getUnit();

      ClusteredStore<K, V> store = new ClusteredStore<K, V>(storeConfig, keySerializer,
          valueSerializer, null, unit.toBytes(clusteredPool.getSize()),
          cacheManagerEntity.createClusteredCacheStoreView(new ClusteredCacheConfiguration<K, V>() {

            @Override
            public Class<K> getKeyType() {
              // TODO Auto-generated method stub
              return null;
            }

            @Override
            public Class<V> getValueType() {
              // TODO Auto-generated method stub
              return null;
            }

            @Override
            public Long getSize() {
              // TODO Auto-generated method stub
              return null;
            }

            @Override
            public Consistency getConsistency() {
              // TODO Auto-generated method stub
              return null;
            }

            @Override
            public CacheIdentifier getIdentifier() {
              // TODO Auto-generated method stub
              return null;
            }

            @Override
            public int getSegments() {
              // TODO Auto-generated method stub
              return 0;
            }

            @Override
            public Unit getUnit() {
              // TODO Auto-generated method stub
              return null;
            }
          }, false));

      return store;
    }

    @Override
    public void releaseStore(Store<?, ?> resource) {

    }

    @Override
    public void initStore(Store<?, ?> resource) {

    }

  }

  @Override
  public void enableStoreEventNotifications(StoreEventListener<K, V> listener) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<CacheConfigurationChangeListener> getConfigurationChangeListeners() {
    // TODO Auto-generated method stub
    return null;
  }

}
