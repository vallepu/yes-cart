/*
Initialisation SQL for YesCart
@author Denys Pavlov
 */
connect 'jdbc:derby://localhost:1527/yes;create=true;unicode=true';
run '../../../persistence/sql/resources/derby/create-tables.sql';
run '../../../env/setup/dbi/initdata.sql';
disconnect;
connect 'jdbc:derby://localhost:1527/yespay;create=true;unicode=true';
run '../../../core-modules/core-module-payment-base/src/main/resources/sql/derby/create-tables.sql';
run '../../../core-modules/core-module-payment-base/src/main/resources/sql/payinitdata.sql';
run '../../../core-modules/core-module-payment-liqpay/src/main/resources/sql/payinitdata.sql';
run '../../../core-modules/core-module-payment-cybersource/src/main/resources/sql/payinitdata.sql';
run '../../../core-modules/core-module-payment-authorizenet/src/main/resources/sql/payinitdata.sql';
run '../../../core-modules/core-module-payment-paypal/src/main/resources/sql/payinitdata.sql';
disconnect;
