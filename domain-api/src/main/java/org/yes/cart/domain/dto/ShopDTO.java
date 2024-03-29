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

import java.util.Collection;
import java.util.Map;

/**
 * Shop DTO interface.
 * <p/>
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 07-May-2011
 * Time: 11:12:54
 */
public interface ShopDTO extends Identifiable {

    /**
     * Get pk value.
     *
     * @return pk value
     */
    long getShopId();

    /**
     * Set pk value.
     *
     * @param shopId pk value
     */
    void setShopId(long shopId);

    /**
     * Get shop code.
     *
     * @return shop code.
     */
    String getCode();

    /**
     * Set shop code.
     *
     * @param code shop code.
     */
    void setCode(String code);

    /**
     * Get name.
     *
     * @return shop name.
     */
    String getName();

    /**
     * Set name.
     *
     * @param name shop name.
     */
    void setName(String name);

    /**
     * Get shop description.
     *
     * @return description.
     */
    String getDescription();

    /**
     * Set descrotion.
     *
     * @param description shop description.
     */
    void setDescription(String description);

    /**
     * Get server side pointer to file system where shop templates are stored.
     *
     * @return file system pointer.
     */
    String getFspointer();

    /**
     * Set server side pointer to file system where shop templates are stored.
     *
     * @param fspointer file system pointer.
     */
    void setFspointer(String fspointer);

    /**
     * Get seo uri.
     * @return uri
     */
    String getUri();

    /**
     * Set seo uri;
     * @param uri  seo uri to  use
     */
    void setUri(String uri);

    /**
     * Get title.
     * @return  title
     */

    String getTitle();

    /**
     * Set seo title
     * @param title seo title to use
     */
    void setTitle(String title);

    /**
     * Display title.
     *
     * @return localised locale => name pairs.
     */
    Map<String, String> getDisplayTitles();

    /**
     * Set display title
     *
     * @param titles localised locale => name pairs
     */
    void setDisplayTitles(Map<String, String> titles);

    /**
     * Get meta key words.
     * @return meta key words
     */

    String getMetakeywords();

    /**
     * Set meta key words to use.
      * @param metakeywords      key words
     */
    void setMetakeywords(String metakeywords);

    /**
     * Display metakeywords.
     *
     * @return localised locale => name pairs.
     */
    Map<String, String> getDisplayMetakeywords();

    /**
     * Set display metakeywords
     *
     * @param metakeywords localised locale => name pairs
     */
    void setDisplayMetakeywords(Map<String, String> metakeywords);

    /**
     * Get seo description
     * @return seo description.
     */
    String getMetadescription();

    /**
     * Set seo description.
     * @param metadescription description to use
     */
    void setMetadescription(String metadescription);

    /**
     * Display metadescription.
     *
     * @return localised locale => name pairs.
     */
    Map<String, String> getDisplayMetadescriptions();

    /**
     * Set display metadescription
     *
     * @param metadescription localised locale => name pairs
     */
    void setDisplayMetadescriptions(Map<String, String> metadescription);


    /**
     * Get the shop attributes.
     *
     * @return shop attributes.
     */
    Collection<AttrValueShopDTO> getAttributes();

    /**
     * Set shop attributes.
     *
     * @param attribute shop attributes to set.
     */
    void setAttributes(Collection<AttrValueShopDTO> attribute);


}
