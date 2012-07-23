-- Igor Azarny iazarny@yahoo.com.
-- Initial data.
--

-- SET character_set_client=utf8;
-- SET character_set_connection=utf8;


INSERT INTO TASSOCIATION(ASSOCIATION_ID, CODE, NAME, DESCRIPTION)  VALUES (1, 'accessories' , 'Accessories' , 'Product accessories');
INSERT INTO TASSOCIATION(ASSOCIATION_ID, CODE, NAME, DESCRIPTION)  VALUES (2, 'up' , 'Up sell' , 'Up sell');
INSERT INTO TASSOCIATION(ASSOCIATION_ID, CODE, NAME, DESCRIPTION)  VALUES (3, 'cross' , 'Cross sell' , 'Cross sell');
INSERT INTO TASSOCIATION(ASSOCIATION_ID, CODE, NAME, DESCRIPTION)  VALUES (4, 'buywiththis' , 'Buy with this products' , 'Shoppers also buy with this product');
INSERT INTO TASSOCIATION(ASSOCIATION_ID, CODE, NAME, DESCRIPTION)  VALUES (5, 'expendable' , 'Expendable materials' , 'Expendable materials. Example inc for printer');


INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1000, 'java.lang.String', 'String');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1001, 'java.lang.String', 'URI');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1002, 'java.lang.String', 'URL');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1003, 'java.lang.String', 'Image');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1004, 'java.lang.String', 'CommaSeparatedList');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1005, 'java.lang.Float', 'Float');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1006, 'java.lang.Integer', 'Integer');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1007, 'java.lang.String', 'Phone');
INSERT INTO TETYPE (ETYPE_ID, JAVATYPE, BUSINESSTYPE) VALUES (1008, 'java.lang.Boolean', 'Boolean');


INSERT INTO TATTRIBUTEGROUP (ATTRIBUTEGROUP_ID, CODE, NAME, DESCRIPTION) VALUES (1000, 'SYSTEM', 'System settings.', 'System wide settings');
INSERT INTO TATTRIBUTEGROUP (ATTRIBUTEGROUP_ID, CODE, NAME, DESCRIPTION) VALUES (1001, 'SHOP', 'Shop settings.', '');
INSERT INTO TATTRIBUTEGROUP (ATTRIBUTEGROUP_ID, CODE, NAME, DESCRIPTION) VALUES (1002, 'CATEGORY', 'Category settings.', '');
INSERT INTO TATTRIBUTEGROUP (ATTRIBUTEGROUP_ID, CODE, NAME, DESCRIPTION) VALUES (1003, 'PRODUCT', 'Product settings.', '');
INSERT INTO TATTRIBUTEGROUP (ATTRIBUTEGROUP_ID, CODE, NAME, DESCRIPTION) VALUES (1004, 'SKU', 'Product SKU settings.', '');
INSERT INTO TATTRIBUTEGROUP (ATTRIBUTEGROUP_ID, CODE, NAME, DESCRIPTION) VALUES (1005, 'BRAND', 'Brand settings.', '');
INSERT INTO TATTRIBUTEGROUP (ATTRIBUTEGROUP_ID, CODE, NAME, DESCRIPTION) VALUES (1006, 'CUSTOMER', 'Customer settings.', '');


INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  999,  'CURRENCY',  0,  NULL,  'Currencies',  'Supported currencies by shop. First one is the default',  1004, 1001);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1000,  'SYSTEM_DEFAULT_SHOP',  1,  NULL,  'System. Default shop',
  'This value will be used for redirections when shop can not be resolved by http request', 1002,  1000);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1040,  'SHOP_B2B',  1,  NULL,  'Is B2B profile for this shop',  'Is B2B profile for this shop',  1000, 1001);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1007,  'SYSTEM_IMAGE_VAULT',  1,  NULL,  'Root directory for image repository',
  'Root directory for image repository', 1000,  1000);



INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1001,  'BRAND_IMAGE',  1,  NULL,  'Brand image',  null,  1003, 1005);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1002,  'CATEGORY_ITEMS_PER_PAGE',  0,  NULL,  'Category item per page settings',
   'Category item per page settings with fail over',  1004, 1002);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1004,  'CATEGORY_IMAGE',  0,  NULL,  'Category image',   'Category image',  1003, 1002);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1005,  'CATEGORY_IMAGE_RETRIEVE_STRATEGY',  0,  NULL,  'Strategy to retrieve image',
  'Strategy to retrieve images. Allowed values: [ATTRIBUTE] i.e. use CATEGORY_IMAGE attribute or [RANDOM_PRODUCT] i.e. random product image will be used',  1000, 1002);


INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  999,  'CURRENCY',  0,  NULL,  'Currencies',  'Supported currencies by shop. First one is default',  1004, 1001);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  2000,  'PRODUCT_IMAGE_WIDTH',  0,  NULL,  'Product image width in category',   'Product image width in category',  1006, 1002);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  2001,  'PRODUCT_IMAGE_HEIGHT',  0,  NULL,  'Product image height in category',   'Product image height in category',  1006, 1002);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  2050,  'PRODUCT_IMAGE_TUMB_WIDTH',  0,  NULL,  'Product thumbnail image width',   'Product thumbnail image width',  1006, 1002);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  2051,  'PRODUCT_IMAGE_TUMB_HEIGHT',  0,  NULL,  'Product thumbnail image height',   'Product thumbnail image height',  1006, 1002);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)  VALUES (  1998,  'CATEGORY_IMAGE_WIDTH',  0,  NULL,  'Category image  width ',   'Category image width thumbnail ',  1006, 1002);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)  VALUES (  1999,  'CATEGORY_IMAGE_HEIGHT',  0,  NULL,  'Category image   height',   'Category image height thumbnail ',  1006, 1002);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)  VALUES (  2000,  'PRODUCT_IMAGE_WIDTH',  0,  NULL,  'Product image width in category',   'Product image width in category',  1006, 1002);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)  VALUES (  2001,  'PRODUCT_IMAGE_HEIGHT',  0,  NULL,  'Product image height in category',   'Product image height in category',  1006, 1002);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1008,  'IMAGE0',  1,  NULL,  'Product default image',  'Product default image',  1003, 1003);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1009,  'IMAGE1',  0,  NULL,  'Product alternative image 1',  'Product alternative image 1',  1003, 1003);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1010,  'IMAGE2',  0,  NULL,  'Product alternative image 2',  'Product alternative image 2',  1003, 1003);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1011,  'IMAGE3',  0,  NULL,  'Product alternative image 3',  'Product alternative image 3',  1003, 1003);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1012,  'IMAGE4',  0,  NULL,  'Product alternative image 4',  'Product alternative image 4',  1003, 1003);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1013,  'IMAGE5',  0,  NULL,  'Product alternative image 5',  'Product alternative image 5',  1003, 1003);

INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1014,  'SKUIMAGE0',  0,  NULL,  'Product SKU default image',  'Product SKU default image',  1003, 1004);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1015,  'SKUIMAGE1',  0,  NULL,  'Product SKU alternative image 1',  'Product SKU alternative image 1',  1003, 1004);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1016,  'SKUIMAGE2',  0,  NULL,  'Product SKU alternative image 2',  'Product SKU alternative image 2',  1003, 1004);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1017,  'SKUIMAGE3',  0,  NULL,  'Product SKU alternative image 3',  'Product SKU alternative image 3',  1003, 1004);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1018,  'SKUIMAGE4',  0,  NULL,  'Product SKU alternative image 4',  'Product SKU alternative image 4',  1003, 1004);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1019,  'SKUIMAGE5',  0,  NULL,  'Product SKU alternative image 5',  'Product SKU alternative image 5',  1003, 1004);


INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1050,  'CUSTOMER_PHONE',  1,  NULL,  'Phone',  'Phone', 1007,  1006);
INSERT INTO TATTRIBUTE (ATTRIBUTE_ID, CODE, MANDATORY, VAL, NAME, DESCRIPTION, ETYPE_ID, ATTRIBUTEGROUP_ID)
  VALUES (  1051,  'MARKETING_OPT_IN',  0,  NULL,  'Marketing Opt in',  'If true then customer opted in for marketing contact', 1007,  1006);

INSERT INTO TPRODUCTTYPE (PRODUCTTYPE_ID , NAME, DESCRIPTION, UISEARCHTEMPLATE, SERVICE, ENSEMBLE, SHIPABLE)
  VALUES (500,'Default Product','Default Product','default', 0,0,1);
INSERT INTO TPRODUCTTYPE (PRODUCTTYPE_ID , NAME, DESCRIPTION, UISEARCHTEMPLATE, SERVICE, ENSEMBLE, SHIPABLE)
  VALUES (501,'Default Accessory','Default Accessory','default', 0,0,1);


INSERT INTO TSYSTEM (SYSTEM_ID, CODE, NAME, DESCRIPTION)  VALUES (100,'SYSTEM','Yes e-commerce platform', 'System table');

INSERT INTO TSYSTEMATTRVALUE ( ATTRVALUE_ID,  VAL,  CODE, SYSTEM_ID)   VALUES (1000,'http://testdevshop.yes-cart.org:8080/yes-shop','SYSTEM_DEFAULT_SHOP',100);
INSERT INTO TSYSTEMATTRVALUE ( ATTRVALUE_ID,  VAL,  CODE, SYSTEM_ID)   VALUES (1002,'10,20,40','SEARCH_ITEMS_PER_PAGE',100);
INSERT INTO TSYSTEMATTRVALUE ( ATTRVALUE_ID,  VAL,  CODE, SYSTEM_ID)  VALUES (1003,'common/imagevault','SYSTEM_IMAGE_VAULT',100);
INSERT INTO TSYSTEMATTRVALUE ( ATTRVALUE_ID,  VAL,  CODE, SYSTEM_ID)  VALUES (1011,'basePaymentModule,cappPaymentModule,gswmPaymentModule','SYSTEM_PAYMENT_MODULES_URLS',100);
INSERT INTO TSYSTEMATTRVALUE ( ATTRVALUE_ID,  VAL,  CODE, SYSTEM_ID)  VALUES (1012,'testPaymentGatewayLabel,courierPaymentGatewayLabel,cyberSourcePaymentGatewayLabel,authorizeNetAimPaymentGatewayLabel,authorizeNetSimPaymentGatewayLabel,payflowPaymentGatewayLabel,payPalNvpPaymentGatewayLabel,payPalExpressPaymentGatewayLabel,liqPayPaymentGatewayLabel','SYSTEM_ACTIVE_PAYMENT_GATEWAYS_LABELS',100);


INSERT INTO TSHOP (SHOP_ID, NAME, DESCRIPTION, FSPOINTER, IMGVAULT, CODE)  VALUES (10, 'YesCart shop', 'YesCart shop', 'default', '/default/imagevault', 'SHOP10');
INSERT INTO TSHOPATTRVALUE(ATTRVALUE_ID,VAL,CODE,SHOP_ID)  VALUES (10, 'USD,EUR,UAH', 'CURRENCY', 10);
INSERT INTO TSTOREEXCHANGERATE (SHOPEXCHANGERATE_ID, FROMCURRENCY, TOCURRENCY, SHOP_ID, RATE)  VALUES(1,'EUR','UAH',10, 11.38);


INSERT INTO TWAREHOUSE (WAREHOUSE_ID, CODE, NAME, DESCRIPTION) VALUES (1, 'Main', 'Main warehouse', null);
INSERT INTO TSHOPWAREHOUSE (SHOPWAREHOUSE_ID, SHOP_ID, WAREHOUSE_ID, RANK )
  VALUES (10, 10, 1, 10 );


INSERT INTO TSHOP (SHOP_ID, NAME, DESCRIPTION, FSPOINTER, IMGVAULT, CODE)  VALUES (10, 'Gadget universe', 'Gadget universe shop', 'default', '/default/imagevault', 'SHOP10');
INSERT INTO TSHOPATTRVALUE(ATTRVALUE_ID,VAL,CODE,SHOP_ID)  VALUES (10, 'USD,EUR,UAH', 'CURRENCY', 10);
INSERT INTO TSTOREEXCHANGERATE (SHOPEXCHANGERATE_ID, FROMCURRENCY, TOCURRENCY, SHOP_ID, RATE)  VALUES(1,'EUR','UAH',10, 11.38);


INSERT INTO TSHOPURL (STOREURL_ID, SHOP_ID, URL )  VALUES (10, 10, 'testdevshop.yes-cart.org');
INSERT INTO TSHOPURL (STOREURL_ID, SHOP_ID, URL )  VALUES (11, 10, 'www.testdevshop.yes-cart.org');
INSERT INTO TSHOPURL (STOREURL_ID, SHOP_ID, URL )  VALUES (12, 10, 'localhost');

INSERT INTO TCATEGORY(CATEGORY_ID, PARENT_ID, RANK, NAME, DESCRIPTION, UITEMPLATE) VALUES (100, 100, 0, 'root', 'The root category','default');

INSERT INTO TCATEGORY(CATEGORY_ID, PARENT_ID, RANK, NAME, DESCRIPTION, UITEMPLATE) VALUES (100, 100, 0, 'root', 'Master category','default');

INSERT INTO TCATEGORYATTRVALUE(ATTRVALUE_ID, CODE,VAL, CATEGORY_ID) VALUES (9000,'PRODUCT_IMAGE_WIDTH','280',100);
INSERT INTO TCATEGORYATTRVALUE(ATTRVALUE_ID, CODE,VAL, CATEGORY_ID) VALUES (9001,'PRODUCT_IMAGE_HEIGHT','280',100);
INSERT INTO TCATEGORYATTRVALUE(ATTRVALUE_ID, CODE,VAL, CATEGORY_ID) VALUES (9002,'PRODUCT_IMAGE_TUMB_WIDTH','80',100);
INSERT INTO TCATEGORYATTRVALUE(ATTRVALUE_ID, CODE,VAL, CATEGORY_ID) VALUES (9003,'PRODUCT_IMAGE_TUMB_HEIGHT','80',100);

INSERT INTO TCATEGORYATTRVALUE(ATTRVALUE_ID, CODE,VAL, CATEGORY_ID) VALUES (9004,'CATEGORY_IMAGE_WIDTH','80',100);
INSERT INTO TCATEGORYATTRVALUE(ATTRVALUE_ID, CODE,VAL, CATEGORY_ID) VALUES (9005,'CATEGORY_IMAGE_HEIGHT','80',100);


INSERT INTO TROLE (ROLE_ID, CODE, DESCRIPTION) VALUES (1, 'ROLE_SMADMIN', 'System admin');
INSERT INTO TROLE (ROLE_ID, CODE, DESCRIPTION) VALUES (2, 'ROLE_SMSHOPADMIN', 'Shop manager');
INSERT INTO TROLE (ROLE_ID, CODE, DESCRIPTION) VALUES (3, 'ROLE_SMWAREHOUSEADMIN', 'Inventory manager');
INSERT INTO TROLE (ROLE_ID, CODE, DESCRIPTION) VALUES (4, 'ROLE_SMCALLCENTER', 'Call centre operator');

-- default admin password 1234567
INSERT INTO TMANAGER (MANAGER_ID, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES (1, 'admin@yes-cart.com', 'Yes', 'Admin', 'fcea920f7412b5da7be0cf42b8c93759');

INSERT INTO TMANAGERROLE (MANAGERROLE_ID, EMAIL, CODE) VALUES (1, 'admin@yes-cart.com', 'ROLE_SMADMIN');

COMMIT;