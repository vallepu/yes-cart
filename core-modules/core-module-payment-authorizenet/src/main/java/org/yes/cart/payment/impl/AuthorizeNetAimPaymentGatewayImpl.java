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

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.yes.cart.payment.PaymentGatewayInternalForm;
import org.yes.cart.payment.dto.Payment;
import org.yes.cart.payment.dto.PaymentGatewayFeature;
import org.yes.cart.payment.dto.impl.PaymentGatewayFeatureImpl;
import org.yes.cart.util.ShopCodeContext;


/**
 * Class represent authorize net advanced integration method payment gateway.
 * <p/>
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class AuthorizeNetAimPaymentGatewayImpl extends AbstractAuthorizeNetPaymentGatewayImpl implements PaymentGatewayInternalForm {

    private final static PaymentGatewayFeature paymentGatewayFeature = new PaymentGatewayFeatureImpl(
            true, true, true, true,
            true, true, true,
            false, true, false,
            null,
            false, true
    );

    /**
     * {@inheritDoc}
     */
    public String getLabel() {
        return "authorizeNetAimPaymentGateway";
    }

    /**
     * {@inheritDoc}
     */
    public PaymentGatewayFeature getPaymentGatewayFeatures() {
        return paymentGatewayFeature;
    }

    /**
     * {@inheritDoc}
     */
    public Payment authorize(final Payment paymentIn) {

        final Payment payment = (Payment) SerializationUtils.clone(paymentIn);
        payment.setTransactionOperation(AUTH);


        final net.authorize.Merchant merchant = createMerchant();
        final net.authorize.aim.Transaction transaction = merchant.createAIMTransaction(
                net.authorize.TransactionType.AUTH_ONLY,
                payment.getPaymentAmount()
        );
        transaction.setCustomer(createAnetCustomer(payment));
        transaction.setOrder(createAnetOrder(payment));
        transaction.setCreditCard(createAnetCreditCard(payment));
        transaction.setShippingAddress(createShippingAddress(payment));


        return runTransaction(merchant, transaction, payment);
    }


    /**
     * {@inheritDoc}
     */
    public Payment reverseAuthorization(final Payment paymentIn) {
        final Payment payment = (Payment) SerializationUtils.clone(paymentIn);
        payment.setTransactionOperation(REVERSE_AUTH);


        final net.authorize.Merchant merchant = createMerchant();
        final net.authorize.aim.Transaction transaction = merchant.createAIMTransaction(
                net.authorize.TransactionType.VOID,
                payment.getPaymentAmount()
        );
        transaction.setTransactionId(payment.getTransactionReferenceId()); // prev auth


        return runTransaction(merchant, transaction, payment);
    }


    /**
     * {@inheritDoc}
     */
    public Payment voidCapture(final Payment paymentIn) {
        final Payment payment = (Payment) SerializationUtils.clone(paymentIn);
        payment.setTransactionOperation(VOID_CAPTURE);


        final net.authorize.Merchant merchant = createMerchant();
        final net.authorize.aim.Transaction transaction = merchant.createAIMTransaction(
                net.authorize.TransactionType.VOID,
                payment.getPaymentAmount()
        );
        transaction.setTransactionId(payment.getTransactionReferenceId()); // prev auth


        return runTransaction(merchant, transaction, payment);

    }


    /**
     * {@inheritDoc}
     */
    public Payment authorizeCapture(final Payment paymentIn) {
        final Payment payment = (Payment) SerializationUtils.clone(paymentIn);
        payment.setTransactionOperation(AUTH_CAPTURE);


        final net.authorize.Merchant merchant = createMerchant();
        final net.authorize.aim.Transaction transaction = merchant.createAIMTransaction(
                net.authorize.TransactionType.AUTH_CAPTURE,
                payment.getPaymentAmount()
        );
        transaction.setCustomer(createAnetCustomer(payment));
        transaction.setOrder(createAnetOrder(payment));
        transaction.setCreditCard(createAnetCreditCard(payment));
        transaction.setShippingAddress(createShippingAddress(payment));


        return runTransaction(merchant, transaction, payment);

    }

    public Payment capture(final Payment paymentIn) {
        final Payment payment = (Payment) SerializationUtils.clone(paymentIn);
        payment.setTransactionOperation(CAPTURE);


        final net.authorize.Merchant merchant = createMerchant();
        final net.authorize.aim.Transaction transaction = merchant.createAIMTransaction(
                net.authorize.TransactionType.PRIOR_AUTH_CAPTURE,
                payment.getPaymentAmount()
        );
        transaction.setTransactionId(payment.getTransactionReferenceId()); // prev auth


        return runTransaction(merchant, transaction, payment);

    }


    /**
     * {@inheritDoc}
     */
    public Payment refund(final Payment paymentIn) {
        final Payment payment = (Payment) SerializationUtils.clone(paymentIn);
        payment.setTransactionOperation(REFUND);


        final net.authorize.Merchant merchant = createMerchant();
        final net.authorize.aim.Transaction transaction = merchant.createAIMTransaction(
                net.authorize.TransactionType.CREDIT,
                payment.getPaymentAmount()
        );
        transaction.setCreditCard(createAnetCreditCard(payment)); //need 4 last digits from credit card
        transaction.setTransactionId(payment.getTransactionReferenceId()); // prev auth


        return runTransaction(merchant, transaction, payment);

    }


    /**
     * Run transaction and perform result parsing.
     *
     * @param merchant    merchant
     * @param transaction transaction
     * @param payment     payment
     * @return payment
     */
    private Payment runTransaction(final net.authorize.Merchant merchant,
                                   final net.authorize.aim.Transaction transaction,
                                   final Payment payment) {
        try {
            final net.authorize.aim.Result<net.authorize.aim.Transaction> transTez =
                    (net.authorize.aim.Result<net.authorize.aim.Transaction>) merchant.postTransaction(transaction);


            payment.setTransactionOperationResultCode(
                    String.valueOf(transTez.getReasonResponseCode().getResponseReasonCode())
            );
            payment.setTransactionOperationResultMessage(
                    transTez.getReasonResponseCode().getReasonText() + " " + transTez.getReasonResponseCode().getNotes());

            if (net.authorize.ResponseCode.DECLINED == transTez.getReasonResponseCode().getResponseCode()
                    ||
                    net.authorize.ResponseCode.ERROR == transTez.getReasonResponseCode().getResponseCode()
                    ) {
                payment.setPaymentProcessorResult(Payment.PAYMENT_STATUS_FAILED);
                payment.setPaymentProcessorBatchSettlement(false);
            } else if (
                    net.authorize.ResponseCode.APPROVED == transTez.getReasonResponseCode().getResponseCode()
                    ) {
                payment.setPaymentProcessorResult(Payment.PAYMENT_STATUS_OK);
                payment.setPaymentProcessorBatchSettlement(true);
            } else if (net.authorize.ResponseCode.REVIEW == transTez.getReasonResponseCode().getResponseCode()) {
                payment.setPaymentProcessorResult(Payment.PAYMENT_STATUS_PROCESSING);
                payment.setPaymentProcessorBatchSettlement(false);
            } else {
                payment.setPaymentProcessorResult(Payment.PAYMENT_STATUS_FAILED);
                payment.setPaymentProcessorBatchSettlement(false);
            }
            payment.setTransactionReferenceId(transTez.getTarget().getTransactionId());
            payment.setTransactionAuthorizationCode(transTez.getTarget().getAuthorizationCode());

            final Logger log = ShopCodeContext.getLog(this);
            if (log.isDebugEnabled()) {
                log.debug(payment.getTransactionOperation() + " transaction response code was : "
                        + transTez.getReasonResponseCode().getResponseCode().getCode()
                        + " - "
                        + transTez.getReasonResponseCode().getResponseCode().getDescription()
                );
            }
        } catch (Throwable th) {
            ShopCodeContext.getLog(this).error("Can not execute transaction. Client exception : " + payment, th);
            payment.setPaymentProcessorResult(Payment.PAYMENT_STATUS_FAILED);
            payment.setPaymentProcessorBatchSettlement(false);
            payment.setTransactionOperationResultMessage(th.getMessage());
        }
        return payment;
    }


}
