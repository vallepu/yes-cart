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

package org.yes.cart.web.support.service.impl;

import org.springframework.cache.CacheManager;
import org.yes.cart.constants.AttributeNamesKeys;
import org.yes.cart.constants.Constants;
import org.yes.cart.web.support.service.AttributableImageService;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 8/8/11
 * Time: 2:40 PM
 */
public class ProductImageServiceImpl extends AbstractImageServiceImpl implements AttributableImageService {

    public ProductImageServiceImpl(final CacheManager cacheManager) {
        super(cacheManager);
    }

    /** {@inheritDoc}
     * @param attributableOrStrategy*/
    protected String getImageRepositoryUrlPattern(final Object attributableOrStrategy) {
        return Constants.PRODUCT_IMAGE_REPOSITORY_URL_PATTERN;
    }


    /** {@inheritDoc}
     * @param attributableOrStrategy*/
    protected String getImageAttributePrefix(final Object attributableOrStrategy) {
        return AttributeNamesKeys.Product.PRODUCT_IMAGE_ATTR_NAME_PREFIX;
    }


}
