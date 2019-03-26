-- decr product stock
UPDATE Product
SET stock = stock - 1
WHERE id = '191c5d12-46db-4f4b-825c-b515a1da3c9f';

-- remove product from invoice
DELETE FROM CustomerInvoice 
WHERE CustomerInvoice.product_id = '191c5d12-46db-4f4b-825c-b515a1da3c9f' AND CustomerInvoice.invoice_id = 'ea47e732-2133-4252-b230-6070ae7054be';

-- decrease product price by 10 percent
UPDATE Product 
SET price = price - (0.1*price)
WHERE id = '191c5d12-46db-4f4b-825c-b515a1da3c9f';

-- add product to customer invoice
INSERT INTO CustomerInvoice (
  [product_id], [invoice_id]
)
VALUES (
  'b337fc04-19d8-41d4-a544-db31133aa50d', 'ea47e732-2133-4252-b230-6070ae7054be'
);

-- update order stock
UPDATE ProductOrder
SET stock = 100
WHERE supplier_id = '20c0985e-71ad-41a4-b5c1-f5abe880e6ef' AND product_id = '191c5d12-46db-4f4b-825c-b515a1da3c9f'