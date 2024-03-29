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

package org.yes.cart.impl {
import mx.collections.ArrayCollection;

[Bindable]
[RemoteClass(alias="org.yes.cart.domain.dto.impl.CustomerDTOImpl")]
public class CustomerDTOImpl {

    public var customerId:Number;

    public var email:String;

    public var firstname:String;

    public var lastname:String;

    public var middlename:String;

    public var attributes:ArrayCollection;

    public var tag : String;

    public function CustomerDTOImpl() {
    }
}

}