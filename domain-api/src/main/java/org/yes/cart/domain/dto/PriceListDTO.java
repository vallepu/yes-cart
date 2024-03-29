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

package org.yes.cart.domain.dto;

import org.yes.cart.domain.entity.Identifiable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Lightweight price object.
 * <p/>
 * User: dogma
 * Date: Jan 22, 2011
 * Time: 11:11:44 PM
 */
public interface PriceListDTO extends Identifiable {


    /**
     * Get currency code.
     *
     * @return currency code
     */
    String getCurrency();

    /**
     * set currency code.
     *
     * @param currency curr code
     */
    void setCurrency(String currency);

    /**
     * Get price quantity.
     *
     * @return quantity
     */
    BigDecimal getQuantity();

    /**
     * Set quantity.
     *
     * @param quantity quantity to set
     */
    void setQuantity(BigDecimal quantity);

    /**
     * Get regular/list price.
     *
     * @return regular price.
     */
    BigDecimal getRegularPrice();

    /**
     * Set regular price.
     *
     * @param regularPrice regular price.
     */
    void setRegularPrice(BigDecimal regularPrice);

    /**
     * Get sale price.
     *
     * @return sale price.
     */
    BigDecimal getSalePrice();

    /**
     * Set sale price.
     *
     * @param salePrice sale price.
     */
    void setSalePrice(BigDecimal salePrice);

    /**
     * Get minimal price to use in discount per day or name your price strategy.
     *
     * @return minimal price
     */
    BigDecimal getMinimalPrice();

    /**
     * Set minimal price
     *
     * @param minimalPrice
     */
    void setMinimalPrice(BigDecimal minimalPrice);

    /**
     * Set sale from date.
     *
     * @return sale from date.
     */
    Date getSalefrom();

    /**
     * Get sale from date.
     *
     * @param salefrom sale from date.
     */
    void setSalefrom(Date salefrom);

    /**
     * Get sale to date.
     *
     * @return sale to date.
     */
    Date getSaleto();

    /**
     * Set sale to date
     *
     * @param saleto sale to date
     */
    void setSaleto(Date saleto);

    /**
     * Primary key.
     *
     * @return pk value
     */
    long getSkuPriceId();

    /**
     * Set pk value
     *
     * @param skuPriceId pk value.
     */
    void setSkuPriceId(long skuPriceId);

    /**
     * Get sku name.
     *
     * @return sku name.
     */
    String getSkuName();

    /**
     * Set sku name.
     *
     * @param skuName sku name.
     */
    void setSkuName(String skuName);

    /**
     * Get sku code.
     *
     * @return sku code.
     */
    String getSkuCode();


    /**
     * Set sku code.
     *
     * @param code sku code.
     */
    void setSkuCode(String code);


    /**
     * Get shop code.
     *
     * @return shop code.
     */
    String getShopCode();


    /**
     * Set shop code.
     *
     * @param code shop code.
     */
    void setShopCode(String code);

    /**
     * Tag allows classification of price entries. E.g. It is hard to understand
     * price with salefrom/to: 01/12/12 - 25/12/12, but it is easier if it has a tag
     * Christmas sales 2012.
     *
     * @return tag or null.
     */
    String getTag();

    /**
     * Set tag value.
     *
     * @param tag price tag
     */
    void setTag(String tag);

}
