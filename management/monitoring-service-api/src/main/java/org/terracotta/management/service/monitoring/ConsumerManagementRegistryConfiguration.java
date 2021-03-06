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
package org.terracotta.management.service.monitoring;

import com.tc.classloader.CommonComponent;
import org.terracotta.entity.ServiceConfiguration;

import java.util.Objects;

/**
 * @author Mathieu Carbou
 */
@CommonComponent
public class ConsumerManagementRegistryConfiguration implements ServiceConfiguration<ConsumerManagementRegistry> {

  private final EntityMonitoringService entityMonitoringService;
  private boolean addServerManagementProviders;

  public ConsumerManagementRegistryConfiguration(EntityMonitoringService entityMonitoringService) {
    this.entityMonitoringService = Objects.requireNonNull(entityMonitoringService);
  }

  public EntityMonitoringService getEntityMonitoringService() {
    return entityMonitoringService;
  }

  public ConsumerManagementRegistryConfiguration addServerManagementProviders() {
    this.addServerManagementProviders = true;
    return this;
  }

  public boolean wantsServerManagementProviders() {
    return addServerManagementProviders;
  }

  @Override
  public Class<ConsumerManagementRegistry> getServiceType() {
    return ConsumerManagementRegistry.class;
  }
}
