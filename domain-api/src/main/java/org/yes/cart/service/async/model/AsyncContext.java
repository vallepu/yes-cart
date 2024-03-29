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

package org.yes.cart.service.async.model;

import java.util.Map;

/**
 * Web services context. This is used to hold special values (such as authentication
 * details) when we run web service in the background.
 *
 * User: denispavlov
 * Date: 12-11-09
 * Time: 8:31 AM
 */
public interface AsyncContext {

    String SECURITY_CTX = "SECURITY_CTX";
    String USERNAME = "USERNAME";
    String CREDENTIALS = "CREDENTIALS";
    String FLEX_SESSION_CREDENTIALS = "pwd";
    String TIMEOUT_KEY = "TIMEOUT_KEY";

    /**
     * Convenience method for retrieving attributes.
     *
     * @param name name of the attribute
     * @param <T> cast
     * @return attribute value or null.
     */
    <T> T getAttribute(String name);

    /**
     * @return context parameters
     */
    Map<String, Object> getAttributes();
}
