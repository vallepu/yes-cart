CREATE TABLE TCUSTOMERORDERPAYMENT (
  CUSTOMERORDERPAYMENT_ID bigint NOT NULL  GENERATED BY DEFAULT AS IDENTITY,
  CARD_EXPIRY_MONTH varchar(2) DEFAULT NULL,
  CARD_EXPIRY_YEAR varchar(4) DEFAULT NULL,
  CARD_HOLDER_NAME varchar(128) DEFAULT NULL,
  CARD_NUMBER varchar(4) DEFAULT NULL,
  CARD_ISSUE_NUMBER varchar(4) DEFAULT NULL,
  CARD_START_DATE timestamp DEFAULT NULL,
  CARD_TYPE varchar(64) DEFAULT NULL,
  CREATED_BY varchar(64) DEFAULT NULL,
  CREATED_TIMESTAMP timestamp DEFAULT NULL,
  GUID varchar(36) DEFAULT NULL,
  ORDER_CURRENCY varchar(3) NOT NULL,
  ORDER_DATE timestamp NOT NULL,
  ORDER_DELIVERY_AMOUNT decimal(19,2) NOT NULL,
  ORDER_NUMBER varchar(128) NOT NULL,
  ORDER_SHIPMENT_NUMBER varchar(128) NOT NULL,
  PP_BATCH_SETTLEMENT int NOT NULL,
  PP_REZCODE varchar(128) NOT NULL,
  TRAN_AUTH_CODE varchar(256) DEFAULT NULL,
  TRAN_PAYMENT_GATEWAY varchar(128) NOT NULL,
  TRAN_PAYMENT_OP varchar(128) NOT NULL,
  TRAN_PAYMENT_REZCODE varchar(128) DEFAULT NULL,
  TRAN_PAYMENT_REZMSG varchar(1024) DEFAULT NULL,
  TRAN_REFERENCE_ID varchar(128) DEFAULT NULL,
  TRAN_REQUEST_TOKEN varchar(256) DEFAULT NULL,
  UPDATED_BY varchar(64) DEFAULT NULL,
  UPDATED_TIMESTAMP timestamp DEFAULT NULL,
  IPADDRESS  varchar(64),
  PRIMARY KEY (CUSTOMERORDERPAYMENT_ID)
);

create index CUSTOMERORDERPAYMENT_ONUM on TCUSTOMERORDERPAYMENT (ORDER_NUMBER);

CREATE TABLE TPAYMENTGATEWAYPARAMETER (
  PAYMENTGATEWAYPARAMETER_ID bigint NOT NULL  GENERATED BY DEFAULT AS IDENTITY,
  CREATED_BY varchar(64) DEFAULT NULL,
  CREATED_TIMESTAMP timestamp DEFAULT NULL,
  P_DESCRIPTION varchar(265) DEFAULT NULL,
  GUID varchar(36) DEFAULT NULL,
  P_LABEL varchar(64) DEFAULT NULL,
  P_NAME varchar(64) DEFAULT NULL,
  PG_LABEL varchar(64) DEFAULT NULL,
  UPDATED_BY varchar(64) DEFAULT NULL,
  UPDATED_TIMESTAMP timestamp DEFAULT NULL,
  P_VALUE varchar(4000) DEFAULT NULL,
  PRIMARY KEY (PAYMENTGATEWAYPARAMETER_ID)
);

create index PAYMENTGATEWAYPARAMETER_PGL on TPAYMENTGATEWAYPARAMETER (PG_LABEL);

