-- products with stocks higher than 10

SELECT *
FROM Product
WHERE Product.stock > 10;

-- supplier that supplies the highest stocked item

SELECT Supplier.name, Supplier.email
FROM Supplier
JOIN supplies ON Supplier.id=supplies.supplier_id
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

SELECT *, COUNT(customer_invoice.product_id) as bought_count
FROM customer_invoice
GROUP BY customer_invoice.product_id
ORDER BY bought_count DESC
LIMIT 1;

-- inventory sorted from least expensive

SELECT *
FROM Product
ORDER BY Product.price ASC;