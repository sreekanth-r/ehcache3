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
package org.ehcache.clustered.codecs;

/**
 * 
 * @author Abhilash
 *
 */

public class CacheOperationCodec<K, V> {

//  private final Serializer<K> keySerializer;
//  private final Serializer<V> valueSerializer;
//
//  public CacheOperationCodec(Serializer<K> keySerializer, Serializer<V> valueSerializer) {
//    this.keySerializer = keySerializer;
//    this.valueSerializer = valueSerializer;
//  }
//
//  public ByteBuffer encodeKey(K key) throws IOException {
//    return keySerializer.serialize(key);
//  }
//
//  public K decodeKey(byte[] key) throws IOException, ClassNotFoundException {
//    return this.keySerializer.read(ByteBuffer.wrap(key));
//  }
//
//  public ByteBuffer encodeValue(V value) throws IOException {
//    return valueSerializer.serialize(value);
//  }
//
//  public V decodeValue(byte[] value) throws IOException, ClassNotFoundException {
//    return this.valueSerializer.read(ByteBuffer.wrap(value));
//  }
}
