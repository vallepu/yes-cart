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

package org.yes.cart.web.page.component.product;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.yes.cart.constants.AttributeNamesKeys;
import org.yes.cart.domain.dto.ProductSearchResultDTO;
import org.yes.cart.domain.dto.ProductSkuSearchResultDTO;
import org.yes.cart.domain.entity.AttrValue;
import org.yes.cart.domain.entity.ProductAvailabilityModel;
import org.yes.cart.domain.entity.Shop;
import org.yes.cart.domain.entity.SkuPrice;
import org.yes.cart.domain.misc.Pair;
import org.yes.cart.util.ShopCodeContext;
import org.yes.cart.web.application.ApplicationDirector;
import org.yes.cart.web.page.component.BaseComponent;
import org.yes.cart.web.page.component.price.PriceView;
import org.yes.cart.web.service.wicketsupport.LinksSupport;
import org.yes.cart.web.support.constants.StorefrontServiceSpringKeys;
import org.yes.cart.web.support.constants.WebParametersKeys;
import org.yes.cart.web.support.service.AttributableImageService;
import org.yes.cart.web.support.service.ProductServiceFacade;
import org.yes.cart.web.util.WicketUtil;

import java.math.BigDecimal;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 8/8/11
 * Time: 11:43 AM
 */
public class ProductInListView extends BaseComponent {

    // ------------------------------------- MARKUP IDs BEGIN ---------------------------------- //
    private final static String PRODUCT_LINK_SKU = "productLinkSku";
    private final static String SKU_CODE_LABEL = "skuCode";
    private final static String PRODUCT_LINK_NAME = "productLinkName";
    private final static String PRODUCT_LINK_ALT = "productLinkAlternative";
    private final static String NAME_LABEL = "name";
    private final static String DESCRIPTION_LABEL = "description";
    private final static String ADD_TO_CART_LINK = "addToCartLink";
    private final static String ADD_TO_CART_LINK_LABEL = "addToCartLinkLabel";
    private final static String VIEW_ALT_LABEL = "viewAlternativesLinkLabel";
    private final static String PRICE_VIEW = "priceView";
    private final static String PRODUCT_LINK_IMAGE = "productLinkImage";
    private final static String PRODUCT_IMAGE = "productDefaultImage";
    // ------------------------------------- MARKUP IDs END ------------------------------------ //

    private final ProductSearchResultDTO product;
    private final ProductSkuSearchResultDTO sku;

    @SpringBean(name = StorefrontServiceSpringKeys.PRODUCT_IMAGE_SERVICE)
    private AttributableImageService attributableImageService;

    @SpringBean(name = StorefrontServiceSpringKeys.PRODUCT_SERVICE_FACADE)
    private ProductServiceFacade productServiceFacade;

    private final Pair<String, String> defImgSize;


    /**
     * Construct product view, that show product in grid.
     *
     * @param id         view id
     * @param product    product model
     * @param defImgSize image size in given category
     */
    public ProductInListView(final String id, final ProductSearchResultDTO product, final Pair<String, String> defImgSize) {
        super(id);
        this.product = product;
        this.sku = resolveDefaultSku(product);
        this.defImgSize = defImgSize;
    }

    private ProductSkuSearchResultDTO resolveDefaultSku(final ProductSearchResultDTO product) {
        if (product.isMultisku() && !CollectionUtils.isEmpty(product.getSkus())) {
            for (final ProductSkuSearchResultDTO sku : product.getSkus()) {
                if (sku.getCode().equals(product.getDefaultSkuCode())) {
                    return sku;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onBeforeRender() {

        final LinksSupport links = getWicketSupportFacade().links();

        final String selectedLocale = getLocale().getLanguage();
        final PageParameters linkToProductParameters = links.getFilteredCurrentParameters(getPage().getPageParameters());
        linkToProductParameters.set(WebParametersKeys.PRODUCT_ID, product.getId());


        final String width = defImgSize.getFirst();
        final String height = defImgSize.getSecond();

        final Link skuCodeLink;
        if (sku != null) {
            skuCodeLink = links.newProductSkuLink(PRODUCT_LINK_SKU, sku.getId(), getPage().getPageParameters());
        } else {
            skuCodeLink = links.newProductLink(PRODUCT_LINK_SKU, product.getId(), getPage().getPageParameters());
        }
        add(skuCodeLink.add(new Label(SKU_CODE_LABEL, getDisplaySkuCode(ApplicationDirector.getCurrentShop(), product))));

        add(new Label(DESCRIPTION_LABEL, product.getDescription(selectedLocale)).setEscapeModelStrings(false));

        final Link nameLink;
        if (sku != null) {
            nameLink = links.newProductSkuLink(PRODUCT_LINK_NAME, sku.getId(), getPage().getPageParameters());
        } else {
            nameLink = links.newProductLink(PRODUCT_LINK_NAME, product.getId(), getPage().getPageParameters());
        }
        add(nameLink.add(new Label(NAME_LABEL, product.getName(selectedLocale))));

        final Link imageLink;
        if (sku != null) {
            imageLink = links.newProductSkuLink(PRODUCT_LINK_IMAGE, sku.getId(), getPage().getPageParameters());
        } else {
            imageLink = links.newProductLink(PRODUCT_LINK_IMAGE, product.getId(), getPage().getPageParameters());
        }
        add(imageLink.add(
                        new ContextImage(PRODUCT_IMAGE, getDefaultImage(width, height, selectedLocale))
                                .add(new AttributeModifier(HTML_WIDTH, width))
                                .add(new AttributeModifier(HTML_HEIGHT, height))
                )
        );


        final ProductAvailabilityModel skuPam = productServiceFacade.getProductAvailability(product, ShopCodeContext.getShopId());

        final boolean ableToAddDefault = skuPam.isAvailable() && skuPam.getDefaultSkuCode().equals(skuPam.getFirstAvailableSkuCode());

        add(links.newAddToCartLink(ADD_TO_CART_LINK, skuPam.getDefaultSkuCode(), null, getPage().getPageParameters())
                        .add(new Label(ADD_TO_CART_LINK_LABEL, skuPam.isInStock() || skuPam.isPerpetual() ?
                                getLocalizer().getString("addToCart", this) :
                                getLocalizer().getString("preorderCart", this)))
                        .setVisible(ableToAddDefault)
        );

        add(links.newProductLink(PRODUCT_LINK_ALT, product.getId(), getPage().getPageParameters())
                        .add(new Label(VIEW_ALT_LABEL, getLocalizer().getString("viewAllVariants", this)))
                        .setVisible(!ableToAddDefault)
        );

        add(
                new PriceView(PRICE_VIEW, new Model<SkuPrice>(getSkuPrice(skuPam.getDefaultSkuCode())), true, true)
        );


        super.onBeforeRender();
    }


    private String getDisplaySkuCode(final Shop shop, final ProductSearchResultDTO product) {

        if (StringUtils.isNotBlank(product.getManufacturerCode())) {
            final AttrValue displayAttrValue = shop.getAttributeByCode(AttributeNamesKeys.Shop.SHOP_PRODUCT_DISPLAY_MANUFACTURER_CODE);
            if (displayAttrValue != null && displayAttrValue.getVal() != null && Boolean.valueOf(displayAttrValue.getVal())) {
                return product.getManufacturerCode();
            }
        }
        return product.getCode();

    }


    /**
     * Get product or his sku price.
     * In case of multisku product the minimal regular price from multiple sku was used for single item.
     *
     * @param firstAvailableSkuCode first available sku code.
     * @return {@link org.yes.cart.domain.entity.SkuPrice}
     */
    private SkuPrice getSkuPrice(final String firstAvailableSkuCode) {
        return productServiceFacade.getSkuPrice(
                null,
                firstAvailableSkuCode,
                BigDecimal.ONE,
                ApplicationDirector.getShoppingCart().getCurrencyCode(),
                ApplicationDirector.getCurrentShop().getShopId()
        );
    }

    private String getDefaultImage(final String width, final String height, final String lang) {

        final Logger log = ShopCodeContext.getLog(this);

        final String result = attributableImageService.getImageURI(
                product, getWicketUtil().getHttpServletRequest().getContextPath(), lang, width, height, product.getDefaultImage()
        );

        if (log.isInfoEnabled()) {

            log.info("Default image is [" + product.getDefaultImage() + "]  result is [" + result + "]");

        }



        return  result;
    }


}
