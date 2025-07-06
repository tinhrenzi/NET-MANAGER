-- Tạo cơ sở dữ liệu
CREATE DATABASE NET_MANAGER;
GO

-- Sử dụng cơ sở dữ liệu
USE NET_MANAGER;
GO

-- Bảng User
CREATE TABLE [User] (
    IdUser NVARCHAR(20) PRIMARY KEY,
    TenDangNhap NVARCHAR(100) NOT NULL,
    MatKhau NVARCHAR(100) NOT NULL,
    VaiTro NVARCHAR(20) NOT NULL,        -- Ví dụ: 'Admin', 'NhanVien'
    TrangThai BIT NOT NULL,              -- 1: hoạt động, 0: bị khóa
    NamSinh DATE NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    SoDienThoai NVARCHAR(20) NOT NULL,
    NgayTaoUser DATE NOT NULL
);

-- Bảng máy tính
CREATE TABLE MayTinh (
    MaMayTinh NVARCHAR(20) PRIMARY KEY,
    TenMay NVARCHAR(100) NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL,     -- Ví dụ: 'Đang sử dụng', 'Rảnh', 'Hỏng'
    ViTri NVARCHAR(100) NOT NULL
);

-- Bảng lịch sử sử dụng máy tính
CREATE TABLE LichSu (
    MaLichSu NVARCHAR(20) PRIMARY KEY,
    MaMayTinh NVARCHAR(20) NOT NULL,
    IdUser NVARCHAR(20) NOT NULL,
    ThoiGianBD DATETIME NOT NULL,
    ThoiGianKT DATETIME NOT NULL,
    TongThoiGian INT NOT NULL,
    ChiPhi DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (MaMayTinh) REFERENCES MayTinh(MaMayTinh),
    FOREIGN KEY (IdUser) REFERENCES [User](IdUser)
);

-- Bảng đồ ăn
CREATE TABLE DoAn (
    MaDoAn NVARCHAR(20) PRIMARY KEY,
    TenDoAn NVARCHAR(100) NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    SoLuong INT NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL
);

-- Bảng đồ uống
CREATE TABLE DoUong (
    MaDoUong NVARCHAR(20) PRIMARY KEY,
    TenDoUong NVARCHAR(100) NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    SoLuong INT NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL
);

-- Bảng hóa đơn
CREATE TABLE HoaDon (
    MaHoaDon NVARCHAR(20) PRIMARY KEY,
    MaMay NVARCHAR(20) NOT NULL,
    IdUser NVARCHAR(20) NOT NULL,
    NgayLap DATETIME NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL,
    PhuongThucThanhToan NVARCHAR(50) NOT NULL,
    FOREIGN KEY (MaMay) REFERENCES MayTinh(MaMayTinh),
    FOREIGN KEY (IdUser) REFERENCES [User](IdUser)
);

-- Bảng chi tiết hóa đơn
CREATE TABLE HoaDonChiTiet (
    MaHoaDonChiTiet NVARCHAR(20) PRIMARY KEY,
    MaHoaDon NVARCHAR(20) NOT NULL,
    MaDoAn NVARCHAR(20) NULL,
    MaDoUong NVARCHAR(20) NULL,
    TenDoAn NVARCHAR(100) NULL,
    TenDoUong NVARCHAR(100) NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaDoAn) REFERENCES DoAn(MaDoAn),
    FOREIGN KEY (MaDoUong) REFERENCES DoUong(MaDoUong)
);

-- Bảng thống kê doanh thu
CREATE TABLE ThongKeDoanhThu (
    IdUser NVARCHAR(20) NOT NULL,
    MaHoaDon NVARCHAR(20) NOT NULL,
    NgayThongKe DATE NOT NULL,
    TongTien DECIMAL(18,2) NOT NULL,
    GhiChu NVARCHAR(255) NULL,
    PRIMARY KEY (IdUser, MaHoaDon),
    FOREIGN KEY (IdUser) REFERENCES [User](IdUser),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon)
);