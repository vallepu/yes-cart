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

package org.yes.cart.web.theme;

import org.apache.lucene.search.BooleanQuery;
import org.yes.cart.web.page.component.AbstractCentralView;

/**
 * User: denispavlov
 * Date: 10/11/2014
 * Time: 10:31
 */
public interface WicketCentralViewProvider {

    /**
     * Central view resolution.
     *
     * @param rendererLabel render view type
     * @param wicketComponentId component id
     * @param categoryId category id
     * @param booleanQuery boolean query
     *
     * @return central vie component
     */
    AbstractCentralView getCentralPanel(String rendererLabel,
                                        String wicketComponentId,
                                        long categoryId,
                                        BooleanQuery booleanQuery);

}
