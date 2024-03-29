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

package org.yes.cart.payment.impl;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 12/14/11
 * Time: 3:10 PM
 */
public abstract class AbstractPayPalPaymentGatewayImpl extends AbstractPayPalBasePaymentGatewayImpl {

    public static final String ORDER_GUID = "orderGuid";  //this id our order guid
    public static final String PP_EC_TOKEN = "TOKEN";     //this will be return from pay pall ec
    public static final String PP_EC_PAYERID  = "PAYERID";     // the payer id on paypal  side

    protected static final String PP_API_USER_NAME = "API_USER_NAME";
    protected static final String PP_API_USER_PASSWORD = "API_USER_PASSWORD";
    protected static final String PP_SIGNATURE = "SIGNATURE";

    protected static final String PP_SUBMIT_BTN = "PP_SUBMIT_BTN";

    protected static final String PP_ENVIRONMENT = "ENVIRONMENT";
    /*
    private static final String PP_KEY_PASSWORD = "KEY_PASSWORD";
    private static final String PP_KEY_PATH = "KEY_PATH";
    */


    protected static final String PP_EC_VERSION = "VERSION";
    protected static final String PP_EC_PAYMENTREQUEST_0_AMT = "AMT";
    protected static final String PP_EC_PAYMENTREQUEST_0_PAYMENTACTION = "PAYMENTACTION";
    protected static final String PP_EC_PAYMENTREQUEST_0_CURRENCYCODE = "CURRENCYCODE";
    protected static final String PP_EC_RETURNURL = "RETURNURL";
    protected static final String PP_EC_CANCELURL = "CANCELURL";
    protected static final String PP_EC_METHOD = "METHOD";
    protected static final String PP_EC_NOSHIPPING = "NOSHIPPING";


    /**
     * "https://api-3t.sandbox.paypal.com/nvp";
     * "https://api-3t.paypal.com/nvp";
     */
    protected static final String PP_EC_API_URL = "PP_EC_API_URL";

    /*
    https://www.sandbox.paypal.com/webscr   &cmd=_express-checkout&token=XXXX
    https://www.paypal.com/cgi-bin/webscr   ?cmd=_express-checkout&token=XXXX
    */
    protected static final String PP_EC_PAYPAL_URL = "PP_EC_PAYPAL_URL";


}
