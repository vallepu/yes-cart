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

package org.yes.cart.web.support.shoppingcart.tokendriven;

import org.yes.cart.shoppingcart.ShoppingCart;

/**
 * User: denispavlov
 * Date: 21/08/2014
 * Time: 19:27
 */
public interface CartRepository {

    /**
     * Retrieve shopping cart using token.
     *
     * @param token token
     *
     * @return cart object
     */
    ShoppingCart getShoppingCart(String token);

    /**
     * Store shopping cart.
     *
     * @param shoppingCart shopping cart
     *
     * @return token for later retrieval
     */
    String storeShoppingCart(ShoppingCart shoppingCart);

    /**
     * Evict shopping cart.
     *
     * @param shoppingCart shopping cart
     */
    void evictShoppingCart(ShoppingCart shoppingCart);

}
