USE master;
GO

-- 1. Xóa Database cũ nếu đã tồn tại và tạo mới
IF DB_ID('NET_MANAGER_001') IS NOT NULL
BEGIN
    ALTER DATABASE NET_MANAGER_001 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE NET_MANAGER_001;
END
GO

CREATE DATABASE NET_MANAGER_001;
GO

USE NET_MANAGER_001;
GO

-- ====================================================
-- 2. TẠO CÁC BẢNG DỮ LIỆU
-- ====================================================

-- Bảng Admin (Tài khoản quản lý & nhân viên)
CREATE TABLE Admin(
    Id NVARCHAR(20) PRIMARY KEY,
    Ten NVARCHAR(100) NOT NULL,
    MatKhau NVARCHAR(100) NOT NULL,
    VaiTro INT NOT NULL,         
    TrangThai BIT NOT NULL DEFAULT 1,
    NamSinh INT NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    SoDienThoai NVARCHAR(20),
    NgayTao DATE NOT NULL DEFAULT GETDATE(),
    Anh NVARCHAR(MAX) NULL
);
GO

-- Bảng MayTinh (Danh sách máy trạm)
CREATE TABLE MayTinh(
    Id NVARCHAR(20) PRIMARY KEY,
    TenMay NVARCHAR(50) NOT NULL,
    GiaTheoGio FLOAT(18) NOT NULL,
    TrangThai NVARCHAR(20) NOT NULL
);
GO

-- Bảng SDMAY (Phiên sử dụng máy)
CREATE TABLE SDMAY(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    MaMay NVARCHAR(20),
    TenMay NVARCHAR(50),
    TrangThai NVARCHAR(20),
    NgayChoi DATE NULL,
    NgayKetThuc DATE NULL,
    GioBatDau TIME NULL,
    GioKetThuc TIME NULL,
    GiaTheoGio FLOAT(18),
    TongTien FLOAT(18) NULL,
    FOREIGN KEY (MaMay) REFERENCES MayTinh(Id)
);
GO

-- Bảng MonAn (Thực đơn đồ ăn/nước uống)
CREATE TABLE MonAn (
    Id NVARCHAR(20) PRIMARY KEY,
    TenMon NVARCHAR(100), 
    GiaTien FLOAT(18),
    SoLuong INT,
    HinhAnh NVARCHAR(MAX)
);
GO

-- Bảng Menu (Món đã gọi theo từng phiên máy)
CREATE TABLE Menu(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    MaSDMay INT NULL,
    TenMay NVARCHAR(50),
    MaMon NVARCHAR(20) NOT NULL,
    TenMon NVARCHAR(100),
    GiaTien FLOAT(18),
    NgayMua DATE,
    SoLuong INT,
    TongTien FLOAT(18),
    FOREIGN KEY (MaSDMay) REFERENCES SDMAY(Id),
    FOREIGN KEY (MaMon) REFERENCES MonAn(Id)
);
GO

-- Bảng ThanhToan (Hóa đơn kết thúc phiên)
CREATE TABLE ThanhToan(
    Id INT IDENTITY(1,1) PRIMARY KEY, 
    MaSDMay INT NOT NULL,
    NgayChoi DATE NOT NULL,
    ThoiGianChoi DATETIME NOT NULL,
    GiaTienTheoGio FLOAT(18),
    TongGio FLOAT,
    TongTienMay FLOAT(18),
    TongTienMon FLOAT(18),
    NgayThanhToan DATE,
    TongTien FLOAT(18),
    FOREIGN KEY (MaSDMay) REFERENCES SDMAY(Id)
);
GO

-- Bảng ThongKe (Doanh thu tháng/năm)
CREATE TABLE ThongKe (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Thang INT NOT NULL,
    Nam INT NOT NULL,
    TongDoanhThuMay DECIMAL(18,2),
    TongDoanhThuMon DECIMAL(18,2),
    TongDoanhThu DECIMAL(18,2),
    UNIQUE (Thang, Nam)
);
GO

-- ====================================================
-- 3. TẠO CÁC TRIGGER TỰ SINH ID (Hỗ trợ cả Insert đơn & Insert hàng loạt)
-- ====================================================

-- Trigger tự sinh ID cho MayTinh (MT001, MT002...)
CREATE TRIGGER trg_AutoID_MayTinh
ON MayTinh
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @MaxNum INT;
    SELECT @MaxNum = ISNULL(MAX(CAST(SUBSTRING(Id, 3, LEN(Id)) AS INT)), 0)
    FROM MayTinh;

    ;WITH Numbered AS (
        SELECT 
            ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
            TenMay, GiaTheoGio, TrangThai
        FROM inserted
    )
    INSERT INTO MayTinh (Id, TenMay, GiaTheoGio, TrangThai)
    SELECT 
        'MT' + RIGHT('000' + CAST(@MaxNum + RowNum AS NVARCHAR), 3),
        TenMay, GiaTheoGio, TrangThai
    FROM Numbered;
END;
GO

-- Trigger tự sinh ID cho MonAn (TD001, TD002...)
CREATE TRIGGER trg_AutoID_MonAn
ON MonAn
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @MaxNum INT;
    SELECT @MaxNum = ISNULL(MAX(CAST(SUBSTRING(Id, 3, LEN(Id)) AS INT)), 0)
    FROM MonAn;

    ;WITH Numbered AS (
        SELECT 
            ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
            TenMon, GiaTien, SoLuong, HinhAnh
        FROM inserted
    )
    INSERT INTO MonAn (Id, TenMon, GiaTien, SoLuong, HinhAnh)
    SELECT 
        'TD' + RIGHT('000' + CAST(@MaxNum + RowNum AS NVARCHAR), 3),
        TenMon, GiaTien, SoLuong, HinhAnh
    FROM Numbered;
END;
GO

-- Trigger tự sinh ID cho Admin (AD001, AD002...)
CREATE TRIGGER trg_AutoID_Admin
ON Admin
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @MaxNum INT;
    SELECT @MaxNum = ISNULL(MAX(CAST(SUBSTRING(Id, 3, LEN(Id)) AS INT)), 0)
    FROM Admin;

    ;WITH Numbered AS (
        SELECT 
            ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
            Ten, MatKhau, VaiTro, TrangThai, NamSinh, Email, SoDienThoai, 
            ISNULL(NgayTao, GETDATE()) AS NgayTao, Anh
        FROM inserted
    )
    INSERT INTO Admin (Id, Ten, MatKhau, VaiTro, TrangThai, NamSinh, Email, SoDienThoai, NgayTao, Anh) 
    SELECT 
        'AD' + RIGHT('000' + CAST(@MaxNum + RowNum AS NVARCHAR), 3),
        Ten, MatKhau, VaiTro, TrangThai, NamSinh, Email, SoDienThoai, NgayTao, Anh 
    FROM Numbered;
END;
GO

-- ====================================================
-- 4. CHÈN DỮ LIỆU CỨNG MẪU (SEED DATA)
-- ====================================================

-- 4.1. Tài khoản hệ thống (Admin & Nhân viên)
INSERT INTO Admin (Ten, MatKhau, VaiTro, TrangThai, NamSinh, Email, SoDienThoai, Anh)
VALUES 
(N'admin', 'Admin123', 1, 1, 1995, 'admin0@gmail.com', '0901234567', NULL),
(N'nhanvien1', '123456', 0, 1, 2002, 'nv1@gmail.com', '0987654321', NULL);
GO

-- 4.2. Danh sách máy phòng net có sẵn (Phòng thường, VIP, Thi đấu)
-- Trạng thái: 'Trống' để sẵn sàng mở máy, 'Bảo trì' cho máy đang sửa
INSERT INTO MayTinh (TenMay, GiaTheoGio, TrangThai)
VALUES
(N'Máy 01', 8000, N'Trống'),
(N'Máy 02', 8000, N'Trống'),
(N'Máy 03', 8000, N'Trống'),
(N'Máy 04', 8000, N'Trống'),
(N'Máy 05', 8000, N'Trống'),
(N'Máy 06', 10000, N'Trống'),
(N'Máy 07', 10000, N'Trống'),
(N'Máy 08', 10000, N'Trống'),
(N'Máy VIP 01', 12000, N'Trống'),
(N'Máy VIP 02', 12000, N'Trống'),
(N'Máy VIP 03', 15000, N'Trống'),
(N'Máy Thi Đấu 01', 20000, N'Trống'),
(N'Máy 13', 8000, N'Bảo trì');
GO

-- 4.3. Danh mục thực đơn F&B mẫu (Đồ ăn, đồ uống kèm ảnh)
INSERT INTO MonAn (TenMon, GiaTien, SoLuong, HinhAnh)
VALUES
(N'Sting đỏ ướp lạnh', 12000, 50, 'Sting.jpg'),
(N'Sting vàng', 12000, 50, 'stingVang.jpg'),
(N'Coca Cola', 12000, 50, 'CocaCola.jpg'),
(N'Trà đào cam sả', 18000, 30, 'tradao.jpg'),
(N'Bò húc Thái', 20000, 40, 'bo.jpg'),
(N'Mì tôm trứng xúc xích', 20000, 50, 'MiTomTrung.jpg'),
(N'Mì xào bò trứng', 35000, 30, 'Mi xao bo trung.jpg'),
(N'Bánh mì Pate đặc biệt', 25000, 20, 'BanhMiPate.jpg'),
(N'Khoai lang kén chiên', 25000, 30, 'khoailangchien.jpg');
GO