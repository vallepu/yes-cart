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

import org.yes.cart.domain.dto.PromotionDTO;
import org.yes.cart.service.federation.FederationFilter;
import org.yes.cart.service.federation.ShopFederationStrategy;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * User: denispavlov
 * Date: 16/09/2014
 * Time: 14:27
 */
public class PromotionFederationFilterImpl implements FederationFilter {

    private final ShopFederationStrategy shopFederationStrategy;

    public PromotionFederationFilterImpl(final ShopFederationStrategy shopFederationStrategy) {
        this.shopFederationStrategy = shopFederationStrategy;
    }

    /**
     * {@inheritDoc}
     */
    public void applyFederationFilter(final Collection list, final Class objectType) {
        final Set<String> manageableShopIds = shopFederationStrategy.getAccessibleShopCodesByCurrentManager();

        final Iterator<PromotionDTO> promoIt = list.iterator();
        while (promoIt.hasNext()) {
            final PromotionDTO promo = promoIt.next();
            if (!manageableShopIds.contains(promo.getShopCode())) {
                promoIt.remove();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isManageable(final Object object, final Class objectType) {
        throw new UnsupportedOperationException("Use shop filter and promo.shopCode instead");
    }
}
