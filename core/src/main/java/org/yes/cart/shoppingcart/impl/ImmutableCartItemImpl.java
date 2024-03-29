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

package org.yes.cart.shoppingcart.impl;


import org.yes.cart.shoppingcart.CartItem;

import java.math.BigDecimal;

/**
 * Wrapper to prevent modification to cart object.
 * <p/>
 * User: dogma
 * Date: Jan 15, 2011
 * Time: 11:10:28 PM
 */
public class ImmutableCartItemImpl implements CartItem {

    private static final long serialVersionUID = 20100626L;

    private final CartItem cartItem;

    /**
     * Default constructor.
     *
     * @param cartItem cart item to make immutable.
     */
    public ImmutableCartItemImpl(final CartItem cartItem) {
        this.cartItem = cartItem;
    }

    /**
     * @return product sku code.
     */
    public String getProductSkuCode() {
        return cartItem.getProductSkuCode();
    }

    /**
     * {@inheritDoc}
     */
    public BigDecimal getQty() {
        return cartItem.getQty();
    }

    /**
     * {@inheritDoc}
     */    
    public BigDecimal getPrice() {
        return cartItem.getPrice();
    }


    /**
     * {@inheritDoc}
     */
    public BigDecimal getListPrice() {
        return cartItem.getListPrice();
    }

    /**
     * {@inheritDoc}
     */
    public BigDecimal getSalePrice() {
        return cartItem.getSalePrice();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isGift() {
        return cartItem.isGift();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPromoApplied() {
        return cartItem.isPromoApplied();
    }

    /**
     * {@inheritDoc}
     */
    public String getAppliedPromo() {
        return cartItem.getAppliedPromo();
    }

    /**
     * {@inheritDoc}
     */
    public BigDecimal getNetPrice() {
        return cartItem.getNetPrice();
    }

    /**
     * {@inheritDoc}
     */
    public BigDecimal getGrossPrice() {
        return cartItem.getGrossPrice();
    }

    /**
     * {@inheritDoc}
     */
    public String getTaxCode() {
        return cartItem.getTaxCode();
    }

    /**
     * {@inheritDoc}
     */
    public BigDecimal getTaxRate() {
        return cartItem.getTaxRate();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTaxExclusiveOfPrice() {
        return cartItem.isTaxExclusiveOfPrice();
    }

    @Override
    public String toString() {
        return "ImmutableCartItemImpl{" +
                "cartItem=" + cartItem +
                '}';
    }
}
