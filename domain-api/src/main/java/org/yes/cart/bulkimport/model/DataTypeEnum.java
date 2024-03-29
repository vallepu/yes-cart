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

package org.yes.cart.bulkimport.model;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 08-May-2011
 * Time: 11:12:54
 */
public enum DataTypeEnum {

    /**
     * String value (default for all import data).
     */
    STRING,
    /**
     * Boolean value (e.g. for PK's).
     */
    BOOLEAN,
    /**
     * Long value (e.g. for PK's).
     */
    LONG,
    /**
     * Integer value.
     */
    INT,
    /**
     * BigDecimal value.
     */
    DECIMAL,
    /**
     * Date value. For date format see {@link org.yes.cart.constants.Constants#DEFAULT_IMPORT_DATE_TIME_FORMAT}
     */
    DATETIME,

}
