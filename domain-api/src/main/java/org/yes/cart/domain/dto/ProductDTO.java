/*
 * Copyright 2009 Igor Azarnyi, Denys Pavlov
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

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Product DTO.
 * <p/>
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 07-May-2011
 * Time: 11:12:54
 */
public interface ProductDTO extends Identifiable {

    /**
     * Get product pk.
     *
     * @return product pk.
     */
    long getProductId();

    /**
     * Set product pk.
     *
     * @param productId product pk.
     */
    void setProductId(long productId);

    /**
     * Get the product code.
     *
     * @return product code.
     */
    String getCode();

    /**
     * Product code.
     * Limitation code must not contains underscore
     *
     * @param code
     */
    void setCode(String code);

    /**
     * Get the {@link org.yes.cart.domain.entity.Brand} of product.
     *
     * @return {@link org.yes.cart.domain.entity.Brand} of product.
     */
    BrandDTO getBrandDTO();

    /**
     * Set {@link BrandDTO} of product.
     *
     * @param brand {@link BrandDTO} of product.
     */
    void setBrandDTO(BrandDTO brand);

    /**
     * Get {@link org.yes.cart.domain.entity.ProductType}
     *
     * @return product type
     */
    ProductTypeDTO getProductTypeDTO();

    /**
     * Set the {@link ProductTypeDTO}
     *
     * @param producttype Get {@link ProductTypeDTO}
     */
    void setProductTypeDTO(ProductTypeDTO producttype);


    /**
     * Get product name.
     *
     * @return product name.
     */
    String getName();

    /**
     * Set product name.
     *
     * @param name product name.
     */
    void setName(String name);

    /**
     * Get product description.
     *
     * @return product description.
     */
    String getDescription();

    /**
     * Set product description.
     *
     * @param description product description.
     */
    void setDescription(String description);


    /**
     * Get start of product availability.
     * Null - product has not start date, means no limitation.
     *
     * @return start of product availability.
     */
    Date getAvailablefrom();

    /**
     * Set start of product availability.
     *
     * @param availablefrom start of product availability.
     */
    void setAvailablefrom(Date availablefrom);

    /**
     * Get end of product availability.
     * Null - product has not end date, means no limitation.
     *
     * @return end of product availability.
     */
    Date getAvailabletill();

    /**
     * Set end of product availability.
     *
     * @param availabletill end of product availability.
     */
    void setAvailabletill(Date availabletill);

    /**
     * Get product availability
     *
     * @return availability
     */
    int getAvailability();

    /**
     * Set productavailability
     *
     * @param availability product
     */
    void setAvailability(int availability);


    /**
     * Get the assigned categories to product.
     *
     * @return assigned categories
     */
    Set<ProductCategoryDTO> getProductCategoryDTOs();

    /**
     * Set assigned categories.
     *
     * @param productCategoryDTOs assigned categories.
     */
    void setProductCategoryDTOs(Set<ProductCategoryDTO> productCategoryDTOs);

    /**
     * Product seo.
     *
     * @return product {@link org.yes.cart.domain.entity.Seo} information or null if seo not set.
     */
    SeoDTO getSeoDTO();

    /**
     * Set product seo information.
     *
     * @param seo product seo.
     */
    void setSeoDTO(SeoDTO seo);


    /**
     * Get the featured flag for product.
     *
     * @return set featured flag.
     */
    Boolean getFeatured();

    /**
     * Set product featured flag.
     *
     * @param featured featured flag.
     */
    void setFeatured(Boolean featured);


    /**
     * Get attributes.
     *
     * @return list of attributes
     */
    Collection<AttrValueProductDTO> getAttribute();

    /**
     * Set list of attributes.
     *
     * @param attribute list of attributes
     */
    void setAttribute(Collection<AttrValueProductDTO> attribute);

    /** Get product tags.
     *
     * @return tag line.
     */
    String getTag();

    /**
     * Set prouct tag.
     *
     * @param tag product tag line
     */
    void setTag(String tag) ;


}