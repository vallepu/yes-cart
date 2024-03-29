/*
 * Copyright 2009 Denys Pavlov, Igor Azarnyi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.yes.cart.service.federation.impl;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.yes.cart.domain.dto.ShopDTO;
import org.yes.cart.service.dto.ManagementService;
import org.yes.cart.service.federation.ShopFederationStrategy;

import java.util.*;

/**
 * User: denispavlov
 * Date: 16/09/2014
 * Time: 14:32
 */
public class TestShopFederationStrategyImpl implements ShopFederationStrategy {

    /**
     * {@inheritDoc}
     */
    public boolean isCurrentUserSystemAdmin() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isCurrentUser(final String role) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isShopAccessibleByCurrentManager(final String shopCode) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Set<Long> getAccessibleShopIdsByCurrentManager() {
        return new HashSet<Long>(Arrays.asList(10L, 20L, 30L, 40L, 50L, 60L));
    }

    /**
     * {@inheritDoc}
     */
    public Set<String> getAccessibleShopCodesByCurrentManager() {
        return new HashSet<String>(Arrays.asList("SHOIP1", "SHOIP2", "SHOIP3", "SHOIP4", "SHOIP5", "JEWEL_SHOP"));
    }

    /**
     * {@inheritDoc}
     */
    public List<ShopDTO> getAccessibleShopsByCurrentManager() {
        return Collections.emptyList();
    }


}
