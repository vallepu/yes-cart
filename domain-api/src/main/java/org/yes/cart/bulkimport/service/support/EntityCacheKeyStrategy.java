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

package org.yes.cart.bulkimport.service.support;

import org.yes.cart.bulkimport.model.ImportColumn;
import org.yes.cart.bulkimport.model.ImportDescriptor;
import org.yes.cart.bulkimport.model.ImportTuple;
import org.yes.cart.bulkimport.model.ValueAdapter;

/**
 * Cache key generation strategy to improve performance of bulk import
 *
 * User: denispavlov
 * Date: 12-08-08
 * Time: 10:37 AM
 */
public interface EntityCacheKeyStrategy {

    /**
     * @param descriptor import descriptor
     * @param column current column
     * @param masterObject master object
     * @param tuple current data tuple
     * @param adapter value adapter
     *
     * @return key for this entity
     */
    String keyFor(final ImportDescriptor descriptor,
                  final ImportColumn column,
                  final Object masterObject,
                  final ImportTuple tuple,
                  final ValueAdapter adapter);

}
