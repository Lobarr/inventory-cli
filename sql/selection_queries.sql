-- products with stocks higher than 10

SELECT *
FROM Product
WHERE Product.stock > 10;

-- supplier that supplies the highest stocked item

SELECT Supplier.name, Supplier.email
FROM Supplier
JOIN Supplies ON Supplier.id=Supplies.supplier_id
JOIN Product ON supplies.product_id=Product.id
ORDER BY Product.stock DESC
LIMIT 1;

-- supplier that supplies the least stocked item
SELECT Supplier.name, Supplier.email
FROM Supplier
JOIN supplies ON Supplier.id=supplies.supplier_id
JOIN Product ON supplies.product_id=Product.id
ORDER BY Product.stock ASC
LIMIT 1;

-- most sold product

SELECT *, COUNT(CustomerInvoice.product_id) as bought_count
FROM CustomerInvoice
GROUP BY CustomerInvoice.product_id
ORDER BY bought_count DESC
LIMIT 1;

-- inventory sorted from least expensive

SELECT *
FROM Product
ORDER BY Product.price ASC;

-- products in an invoice
SELECT Product.id, Product.name, Product.stock, Product.price
FROM CustomerInvoice
JOIN Product ON CustomerInvoice.product_id = Product.id
WHERE CustomerInvoice.invoice_id = '26c3f5db-a0ef-451a-b48f-5c45b9d6143a';