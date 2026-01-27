-- Test insert dữ liệu - Khớp với schema SQL Server
INSERT INTO dbo.Customers (CustomerID, FullName, Email, Phone, Address, PasswordHash, BirthDate, Gender)
VALUES ('1150080156', N'Nguyễn Hồ Trường Tam', 'tam@mail.com', '0325620501', N'Hà Nội', 'abc123456', '2004-05-01', N'Nam');

-- Kiểm tra dữ liệu vừa insert
SELECT * FROM dbo.Customers WHERE CustomerID = '1150080156';

-- Xem tất cả dữ liệu
SELECT * FROM dbo.Customers;
