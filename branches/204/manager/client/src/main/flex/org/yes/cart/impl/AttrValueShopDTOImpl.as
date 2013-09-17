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

package org.yes.cart.impl {

[Bindable]
[RemoteClass(alias="org.yes.cart.domain.dto.impl.AttrValueShopDTOImpl")]
public class AttrValueShopDTOImpl {
    
    
    public var attrvalueId:Number;

    public var val:String;
    public var displayVals:Object;

    public var attributeDTO:AttributeDTOImpl;

    public var shopId:Number;
    
    public function AttrValueShopDTOImpl() {
    }


    public function toString():String {
        return "AttrValueBrandDTOImpl{attrvalueId=" + String(attrvalueId) +
               ",val=" + String(val) +
               ",attributeDTO=" + String(attributeDTO) +
               ",shopId=" + String(shopId) + "}";
    }
}

}