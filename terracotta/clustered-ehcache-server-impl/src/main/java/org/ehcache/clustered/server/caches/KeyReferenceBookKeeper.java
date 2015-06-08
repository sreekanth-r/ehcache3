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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.terracotta.entity.ClientDescriptor;

/**
 * 
 * @author Abhilash
 *
 */

public class KeyReferenceBookKeeper {
  // use the ported BitSetObjectIDset to store longs - hash
  private Map<ClientDescriptor, Set<Long>> map = new ConcurrentHashMap<ClientDescriptor, Set<Long>>();
  private Set<ClientDescriptor> connectedClients = new HashSet<ClientDescriptor>();

  public void addReference(Long key, ClientDescriptor descriptor) {
    map.get(descriptor).add(key);
  }

  public void addReferences(Set<Long> keys, ClientDescriptor descriptor) {
    map.get(descriptor).addAll(keys);
  }

  public boolean hasReference(Long key, ClientDescriptor descriptor) {
    return false;
  }

  public void removeReference(Long key, ClientDescriptor descriptor) {

  }

  public void removeReferences(Set<Long> keys, ClientDescriptor descriptor) {

  }

  public void addClient(ClientDescriptor descriptor) {

  }

  public void removeClient(ClientDescriptor descriptor) {

  }

}
