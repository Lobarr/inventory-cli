-- Invoice
INSERT INTO Invoice (
  [id], [name], [email], [date]
)
VALUES (
  'ea47e732-2133-4252-b230-6070ae7054be', 'Boyd Heller', 'Bradly74@gmail.com', '1553472948'
);

INSERT INTO Invoice (
  [id], [name], [email], [date]
)
VALUES (
  '76759e93-9d98-4ed6-9bf2-d4657ace99aa', 'Heidi Funk', 'Lamar_Dare10@yahoo.com', '1353472948'
);

INSERT INTO Invoice (
  [id], [name], [email], [date]
)
VALUES (
  '26c3f5db-a0ef-451a-b48f-5c45b9d6143a', 'Shanel Gleichner', 'Jennyfer_Wisoky@gmail.com', '1553432948'
);

-- Product 

INSERT INTO Product (
  [id], [name], [stock], [price] 
)
VALUES (
  '191c5d12-46db-4f4b-825c-b515a1da3c9f', 'Sennheiser HD 569 Closed Back Headphone', 35, 190.00
);

INSERT INTO Product (
  [id], [name], [stock], [price] 
)
VALUES (
  'b337fc04-19d8-41d4-a544-db31133aa50d', 'Bose SoundTouch 10 wireless speaker', 15, 259.99
);

INSERT INTO Product (
  [id], [name], [stock], [price] 
)
VALUES (
  'c0cabd37-1bfa-4dd6-8d90-46d269ba5347', 'Anker PowerCore 26800 Portable Charger', 18, 89.99
);


-- Category

INSERT INTO Category (
  [id], [name]
)
VALUES (
  'eea0ef66-1107-405f-b577-88926a263279', 'Speakers'
);

INSERT INTO Category (
  [id], [name]
)
VALUES (
  'c2ea10e2-2630-4242-8b3e-aa4afc4ba974', 'Power Banks'
);

INSERT INTO Category (
  [id], [name]
)
VALUES (
  '0a8d9795-a46d-4422-86f3-992a191a437f', 'Headphones'
);


-- Brand
INSERT INTO Brand (
 [id], [name]
)
VALUES (
  'e2d46bd2-3b2c-40cf-ab9d-e989314b3aa1', 'Anker'
);

INSERT INTO Brand (
 [id], [name]
)
VALUES (
  'c446c809-824e-4992-afa5-e5ab1a8aaa20', 'Bose'
);

INSERT INTO Brand (
 [id], [name]
)
VALUES (
  '36b7fb0c-4b64-4c32-8c22-5367705f9172', 'Sennheiser'
);

-- Supplier 

INSERT INTO Supplier (
  [id], [name], [email]
) 
VALUES (
  '20c0985e-71ad-41a4-b5c1-f5abe880e6ef', 'Caden Kautzer', 'Don_Upton97@gmail.com'
);

INSERT INTO Supplier (
  [id], [name], [email]
) 
VALUES (
  '84be1009-0828-4574-9dd0-6e1afcda00ad', 'Leif Hoeger', 'Perry15@hotmail.com'
);

INSERT INTO Supplier (
  [id], [name], [email]
) 
VALUES (
  '003279c6-21d3-4794-a1e4-ea3a87fa91d1', 'Natalie Rippin', 'Mikayla5@hotmail.com'
);

-- Shipping

INSERT INTO Shipping (
  [id], [address]
)
VALUES (
  '1a895041-1016-470c-80f2-03d39b9af8f9', '603 Metz Mews, Corkeryhaven, Taiwan'
);

INSERT INTO Shipping (
  [id], [address]
)
VALUES (
  '4898edfb-4c49-4784-bbe5-8e1087f7192a', '023 Lauryn Viaduct, Huelsview, Monaco'
);

INSERT INTO Shipping (
  [id], [address]
)
VALUES (
  'b23d6f08-2bf6-4526-9374-2177e7f6e6c3', '4773 Bechtelar Pine, Lake Melvina, United Arab Emirates'
);

-- supplies

INSERT INTO supplies (
  [supplier_id], [product_id]
) 
VALUES (
  '20c0985e-71ad-41a4-b5c1-f5abe880e6ef', '191c5d12-46db-4f4b-825c-b515a1da3c9f'
);

INSERT INTO supplies (
  [supplier_id], [product_id]
) 
VALUES (
  '84be1009-0828-4574-9dd0-6e1afcda00ad', 'b337fc04-19d8-41d4-a544-db31133aa50d'
);

INSERT INTO supplies(
  [supplier_id], [product_id]
)
VALUES (
  '003279c6-21d3-4794-a1e4-ea3a87fa91d1', 'c0cabd37-1bfa-4dd6-8d90-46d269ba5347'
);

-- product_category 

INSERT INTO product_category (
  [product_id], [category_id]
)
VALUES (
  '191c5d12-46db-4f4b-825c-b515a1da3c9f', '0a8d9795-a46d-4422-86f3-992a191a437f'
);

INSERT INTO product_category (
  [product_id], [category_id]
)
VALUES (
  'b337fc04-19d8-41d4-a544-db31133aa50d', 'eea0ef66-1107-405f-b577-88926a263279'
);

INSERT INTO product_category (
  [product_id], [category_id]
)
VALUES (
  'c0cabd37-1bfa-4dd6-8d90-46d269ba5347', 'c2ea10e2-2630-4242-8b3e-aa4afc4ba974'
);


-- brand_category
INSERT INTO brand_category (
 [brand_id], [category_id]
) 
VALUES (
  'e2d46bd2-3b2c-40cf-ab9d-e989314b3aa1', 'c2ea10e2-2630-4242-8b3e-aa4afc4ba974'
);

INSERT INTO brand_category (
 [brand_id], [category_id]
) 
VALUES (
  'c446c809-824e-4992-afa5-e5ab1a8aaa20', 'eea0ef66-1107-405f-b577-88926a263279'
);

INSERT INTO brand_category (
 [brand_id], [category_id]
) 
VALUES (
  '36b7fb0c-4b64-4c32-8c22-5367705f9172', '0a8d9795-a46d-4422-86f3-992a191a437f'
);

--product_brand
INSERT INTO product_brand (
  [product_id], [brand_id]
) 
VALUES (
  '191c5d12-46db-4f4b-825c-b515a1da3c9f', '36b7fb0c-4b64-4c32-8c22-5367705f9172'
);

INSERT INTO product_brand (
  [product_id], [brand_id]
) 
VALUES (
  'b337fc04-19d8-41d4-a544-db31133aa50d', 'c446c809-824e-4992-afa5-e5ab1a8aaa20'
);

INSERT INTO product_brand (
  [product_id], [brand_id]
) 
VALUES (
  'c0cabd37-1bfa-4dd6-8d90-46d269ba5347', 'e2d46bd2-3b2c-40cf-ab9d-e989314b3aa1'
);

-- customer_invoice

INSERT INTO customer_invoice (
  [product_id], [invoice_id]
)
VALUES (
  '191c5d12-46db-4f4b-825c-b515a1da3c9f', 'ea47e732-2133-4252-b230-6070ae7054be'
);

INSERT INTO customer_invoice (
  [product_id], [invoice_id]
)
VALUES (
  'b337fc04-19d8-41d4-a544-db31133aa50d', '76759e93-9d98-4ed6-9bf2-d4657ace99aa'
);

INSERT INTO customer_invoice (
  [product_id], [invoice_id]
) 
VALUES (
  'c0cabd37-1bfa-4dd6-8d90-46d269ba5347', '26c3f5db-a0ef-451a-b48f-5c45b9d6143a'
);

-- supplier_category

INSERT INTO supplier_category (
  [supplier_id], [category_id]
) 
VALUES (
  '20c0985e-71ad-41a4-b5c1-f5abe880e6ef', '0a8d9795-a46d-4422-86f3-992a191a437f'
);

INSERT INTO supplier_category (
  [supplier_id], [category_id]
) 
VALUES (
  '84be1009-0828-4574-9dd0-6e1afcda00ad', 'eea0ef66-1107-405f-b577-88926a263279'
);

INSERT INTO supplier_category (
  [supplier_id], [category_id]
) 
VALUES (
  '003279c6-21d3-4794-a1e4-ea3a87fa91d1', 'c2ea10e2-2630-4242-8b3e-aa4afc4ba974'
);

-- product_order
INSERT INTO product_order (
  [supplier_id], [product_id], [stock]
) 
VALUES (
  '20c0985e-71ad-41a4-b5c1-f5abe880e6ef', '191c5d12-46db-4f4b-825c-b515a1da3c9f', 20
);

INSERT INTO product_order (
  [supplier_id], [product_id], [stock]
) 
VALUES (
  '84be1009-0828-4574-9dd0-6e1afcda00ad', 'b337fc04-19d8-41d4-a544-db31133aa50d', 25
);

INSERT INTO product_order (
  [supplier_id], [product_id], [stock]
) 
VALUES (
  '003279c6-21d3-4794-a1e4-ea3a87fa91d1', 'c0cabd37-1bfa-4dd6-8d90-46d269ba5347', 15
);

-- customer_shipment
INSERT INTO customer_shipment (
  [shipping_id], [invoice_id]
)
VALUES (
  '1a895041-1016-470c-80f2-03d39b9af8f9', 'ea47e732-2133-4252-b230-6070ae7054be'
);

INSERT INTO customer_shipment (
  [shipping_id], [invoice_id]
)
VALUES (
  '4898edfb-4c49-4784-bbe5-8e1087f7192a', '76759e93-9d98-4ed6-9bf2-d4657ace99aa'
);

INSERT INTO customer_shipment (
  [shipping_id], [invoice_id]
)
VALUES (
  'b23d6f08-2bf6-4526-9374-2177e7f6e6c3', '26c3f5db-a0ef-451a-b48f-5c45b9d6143a'
);


