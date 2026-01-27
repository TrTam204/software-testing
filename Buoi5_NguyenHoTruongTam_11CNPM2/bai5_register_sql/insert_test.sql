-- Test insert dữ liệu
INSERT INTO Customers (CustomerID, CustomerName, Email, Phone, Address, Password, BirthDate, Gender)
VALUES ('1150080156', 'Nguyễn Hồ Trường Tam', 'tam@mail.com', '0325620501', 'Abc', 'abc123456', '2004-05-01', 'Nam');

-- Kiểm tra
SELECT * FROM Customers WHERE CustomerID = '1150080156';
