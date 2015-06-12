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

package org.yes.cart.service.payment;


import org.yes.cart.domain.entity.CustomerOrder;
import org.yes.cart.payment.PaymentGateway;
import org.yes.cart.payment.dto.Payment;

import java.util.List;
import java.util.Map;

/**
 *
 * Payment processor delegate payment calls to payment gateway and persists
 * information about calls. The spring scope of implementor bean must be an prototype,
 * because of underlaying payment gateways are not thread safe. 
 *
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public interface PaymentProcessor {


    /**
     * Authorize a payment. The response from a card issuing bank to a
     * merchant's transaction authorization request indicating that payment information is valid
     * and funds are available on the customer's credit card. In case if authorize operation ot supported by
     * payment gateway the auth_capture operation will be use instead.
     *
     * @param order to authorize payments.
     * @param params for payment gateway to create template from. Also if this map contains key
     * forceSinglePayment, only one payment will be created (hack to support pay pal express).
     * @return status of operation.
     */
    String authorize(CustomerOrder order, Map params);


    /**
     * Cancel order. All authorized payments will be reversed, captured funds will be void of refunded.
     * No rollback operations will be performed if order already has cancel state
     *
     * @param order order to cancel.
     * @param params for payment gateway to create template from.
     * @return status of operation.
     */
    String cancelOrder(CustomerOrder order, Map params);


    /**
     * Particular shipment is complete. Funds can be captured.
     *
     * @param order order
     * @param orderShipmentNumber internal shipment number. Each order has at least one delivery.
     * @param params for payment gateway to create template from.
     * @return status of operation.
     */
    String shipmentComplete(CustomerOrder order, String orderShipmentNumber, Map params);


    /**
     * Set payment gateway to use.
     * @param paymentGateway {see PaymentGatewayInternalForm to use.
     */
    void setPaymentGateway(PaymentGateway paymentGateway);

    /**
     * Get current payment gateway to use.
     * @return {@link PaymentGateway}
     */
    PaymentGateway getPaymentGateway();

    /**
     * Create list of payment to authorize.
     *
     * @param order              order
     * @param params for payment gateway to create template from. Also if this map contains key
     * forceSinglePayment, only one payment will be created (hack to support pay pal express).
     * @param forceSinglePayment flag is true for authCapture operation, when payment gateway not supports several payments per   order
     * @param transactionOperation operation name on YC payment processor
     * @return list of  {@link Payment} to process
     */
    List<Payment> createPaymentsToAuthorize(
            CustomerOrder order,
            boolean forceSinglePayment,
            final Map params,
            final String transactionOperation);


}
