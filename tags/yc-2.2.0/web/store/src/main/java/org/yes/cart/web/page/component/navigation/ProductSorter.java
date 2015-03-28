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

package org.yes.cart.web.page.component.navigation;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.yes.cart.constants.AttributeNamesKeys;
import org.yes.cart.domain.entity.AttrValue;
import org.yes.cart.domain.entity.Shop;
import org.yes.cart.domain.entity.bridge.SkuPriceBridge;
import org.yes.cart.domain.misc.Pair;
import org.yes.cart.domain.query.ProductSearchQueryBuilder;
import org.yes.cart.shoppingcart.ShoppingCart;
import org.yes.cart.web.application.ApplicationDirector;
import org.yes.cart.web.page.component.BaseComponent;
import org.yes.cart.web.service.wicketsupport.LinksSupport;
import org.yes.cart.web.service.wicketsupport.PaginationSupport;
import org.yes.cart.web.support.constants.WebParametersKeys;

import java.math.BigDecimal;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 7/14/11
 * Time: 8:55 PM
 */
public class ProductSorter extends BaseComponent {

    /**
     * Product sorting
     */
    private static final String PRODUCT_SORT_BY_NAME_ASC = "orderByNameA";
    private static final String PRODUCT_SORT_BY_NAME_DESC = "orderByNameD";
    private static final String PRODUCT_SORT_BY_PRICE_ASC = "orderByPriceA";
    private static final String PRODUCT_SORT_BY_PRICE_DESC = "orderByPriceD";
    private static final String PRODUCT_SORT_BY_CODE_ASC = "orderByCodeA";
    private static final String PRODUCT_SORT_BY_CODE_DESC = "orderByCodeD";
    private static final String PRODUCT_SORT_BY_MANUFACTURER_CODE_ASC = "orderByManCodeA";
    private static final String PRODUCT_SORT_BY_MANUFACTURER_CODE_DESC = "orderByManCodeD";

    private static final SkuPriceBridge SKU_PRICE_BRIDGE = new SkuPriceBridge();

    /**
     * Construct product sorter.
     * @param id component id.
     */
    public ProductSorter(final String id) {
        super(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onBeforeRender() {

        final ShoppingCart cart = ApplicationDirector.getShoppingCart();

        final boolean isManufacturerCode = useManufactureCode();

        final Pair<String, String> priceSort = SKU_PRICE_BRIDGE.objectToString(cart.getShoppingContext().getShopId(), cart.getCurrencyCode(), null);

        add(getSortLink(PRODUCT_SORT_BY_NAME_ASC, WebParametersKeys.SORT, ProductSearchQueryBuilder.PRODUCT_NAME_SORT_FIELD));
        add(getSortLink(PRODUCT_SORT_BY_NAME_DESC, WebParametersKeys.SORT_REVERSE, ProductSearchQueryBuilder.PRODUCT_NAME_SORT_FIELD));
        add(getSortLink(PRODUCT_SORT_BY_CODE_ASC, WebParametersKeys.SORT, ProductSearchQueryBuilder.PRODUCT_CODE_FIELD).setVisible(!isManufacturerCode));
        add(getSortLink(PRODUCT_SORT_BY_CODE_DESC, WebParametersKeys.SORT_REVERSE, ProductSearchQueryBuilder.PRODUCT_CODE_FIELD).setVisible(!isManufacturerCode));
        add(getSortLink(PRODUCT_SORT_BY_MANUFACTURER_CODE_ASC, WebParametersKeys.SORT, ProductSearchQueryBuilder.PRODUCT_MANUFACTURER_CODE_FIELD).setVisible(isManufacturerCode));
        add(getSortLink(PRODUCT_SORT_BY_MANUFACTURER_CODE_DESC, WebParametersKeys.SORT_REVERSE, ProductSearchQueryBuilder.PRODUCT_MANUFACTURER_CODE_FIELD).setVisible(isManufacturerCode));
        add(getSortLink(PRODUCT_SORT_BY_PRICE_ASC, WebParametersKeys.SORT, priceSort.getFirst()));
        add(getSortLink(PRODUCT_SORT_BY_PRICE_DESC, WebParametersKeys.SORT_REVERSE, priceSort.getFirst()));


        super.onBeforeRender();
    }

    private boolean useManufactureCode() {
        final Shop shop = ApplicationDirector.getCurrentShop();
        final AttrValue displayAttrValue = shop.getAttributeByCode(AttributeNamesKeys.Shop.SHOP_PRODUCT_DISPLAY_MANUFACTURER_CODE);
        return displayAttrValue != null && displayAttrValue.getVal() != null && Boolean.valueOf(displayAttrValue.getVal());
    }

    /**
     * Get the product sort link by given sort filed and order.
     *
     * @param sortOrder sort order see {@link org.yes.cart.web.support.constants.WebParametersKeys#SORT}
     *                  and {@link org.yes.cart.web.support.constants.WebParametersKeys#SORT_REVERSE}
     * @param sortField sort by filed see {@link org.yes.cart.domain.query.ProductSearchQueryBuilder#PRODUCT_NAME_FIELD} and
     *                  {@link org.yes.cart.domain.query.ProductSearchQueryBuilder#PRODUCT_PRICE_AMOUNT}
     * @param id        link id
     * @return product sort link
     */
    private Link getSortLink(final String id, final String sortOrder, final String sortField) {

        final LinksSupport links = getWicketSupportFacade().links();
        final PaginationSupport pagination = getWicketSupportFacade().pagination();

        final PageParameters params = links.getFilteredCurrentParameters(getPage().getPageParameters());
        params.remove(WebParametersKeys.SORT);
        params.remove(WebParametersKeys.SORT_REVERSE);
        params.set(WebParametersKeys.PAGE, "0");
        params.add(sortOrder, sortField);

        final Link rez = links.newLink(id, params);
        pagination.markSelectedSortLink(rez, getPage().getPageParameters(), sortOrder, sortField);
        return rez;
    }
}
