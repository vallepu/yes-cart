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

import org.yes.cart.domain.entity.ShopWarehouse;
import org.yes.cart.domain.entity.SkuWarehouse;
import org.yes.cart.domain.entity.Warehouse;
import org.yes.cart.service.federation.FederationFilter;
import org.yes.cart.service.federation.ShopFederationStrategy;

import java.util.List;
import java.util.Set;

/**
 * User: denispavlov
 * Date: 16/09/2014
 * Time: 14:27
 */
public class SkuWarehouseFederationFilterImpl extends AbstractImpexFederationFilterImpl implements FederationFilter {

    private final ShopFederationStrategy shopFederationStrategy;

    public SkuWarehouseFederationFilterImpl(final ShopFederationStrategy shopFederationStrategy,
                                            final List<String> roles) {
        super(shopFederationStrategy, roles);
        this.shopFederationStrategy = shopFederationStrategy;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isManageable(final Object object, final Class objectType) {

        if (!hasAccessRole()) {
            return false;
        }

        final Set<Long> manageableShopIds = shopFederationStrategy.getAccessibleShopIdsByCurrentManager();

        final SkuWarehouse skuWarehouse = (SkuWarehouse) object;
        final Warehouse warehouse = skuWarehouse.getWarehouse();

        for (final ShopWarehouse shopWarehouse : warehouse.getWarehouseShop()) {
            if (manageableShopIds.contains(shopWarehouse.getShop().getShopId())) {
                return true;
            }
        }

        return false;
    }

}
