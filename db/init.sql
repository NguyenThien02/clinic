GRANT ALL PRIVILEGES ON clinic.* TO 'user_clinic'@'%';
FLUSH PRIVILEGES;

create database clinic;
use clinic;

CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
);

INSERT INTO roles (name)
VALUES
    ('USER'),
    ('DOCTOR'),
    ('ADMIN');

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    birthday DATE,
    address VARCHAR(255),
    role_id INT,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE specialties (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);


CREATE TABLE services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    specialty_id INT,
    FOREIGN KEY (specialty_id) REFERENCES specialties(id),
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    insurance_price FLOAT NOT NULL
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    specialty_id INT NOT NULL,
    FOREIGN KEY (specialty_id) REFERENCES specialties(id),
    training_process TEXT,
    job_description TEXT,
    image_url VARCHAR(100)
);
CREATE TABLE time_slots(
	id INT AUTO_INCREMENT PRIMARY KEY,
	start_end VARCHAR(255) NOT NULL
);

INSERT INTO time_slots (start_end)
VALUES
	('7h-8h'),
	('8h-9h'),
	('9h-10h'),
	('10h-11h'),
	('11h-12h'),
	('12h-13h'),
	('13h-14h'),
	('14h-15h'),
	('15h-16h'),
	('16h-17h');

CREATE TABLE schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    doctor_id INT,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    time_slot_id INT,
    FOREIGN KEY (time_slot_id) REFERENCES time_slots(id),
    user_name VARCHAR(255) NOT NULL,
    user_phone VARCHAR(20),
    date DATE NOT NULL
);

CREATE TABLE profiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    schedule_id INT,
    diagnosis TEXT,
    treatment TEXT,
    medications TEXT,
    total_money FLOAT,
    total_insurance_money FLOAT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (schedule_id) REFERENCES schedules(id)
);


CREATE TABLE profile_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profile_id INT,
    service_id INT,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (service_id) REFERENCES services(id)
);

-- =============================================================================
-- chuyên khoa
INSERT INTO specialties (name) VALUES ('Khoa Khám bệnh');
INSERT INTO specialties (name) VALUES ('Khoa Nội tổng hợp');
INSERT INTO specialties (name) VALUES ('Khoa Ngoại - Gây mê hồi sức');
INSERT INTO specialties (name) VALUES ('Khoa Phụ sản - Kế hoạch hóa gia đình');
INSERT INTO specialties (name) VALUES ('Khoa Chẩn đoán hình ảnh');
INSERT INTO specialties (name) VALUES ('Khoa Xét nghiệm');
INSERT INTO specialties (name) VALUES ('Khoa Phẫu thuật - Tạo hình thẩm mỹ');
INSERT INTO specialties (name) VALUES ('Khoa Dược');
INSERT INTO specialties (name) VALUES ('Chuyên Khoa Tai mũi họng');
INSERT INTO specialties (name) VALUES ('Chuyên Khoa Răng Hàm Mặt');
INSERT INTO specialties (name) VALUES ('Đơn nguyên Hỗ trợ sinh sản');

-- Dịch vụ
INSERT INTO services (specialty_id, name, price, insurance_price)
VALUES
-- Khoa Khám bệnh
	(1, 'Khám tổng quát', 600.00, 500.00),
    (1, 'Khám chuyên khoa', 700.00, 550.00),
    (1, 'Xét nghiệm nhanh', 300.00, 250.00),
	(1, 'Khám sức khỏe định kỳ', 750.00, 600.00),
    (1, 'Khám bệnh mãn tính', 800.00, 650.00),
    (1, 'Khám sức khỏe cho trẻ em', 500.00, 400.00),

-- Khoa Nội tổng hợp
    (2, 'Đái tháo đường', 200.00, 150.00),
    (2, 'Tiểu đường thai kỳ', 180.00, 135.00),
    (2, 'Bướu, nhân tuyến giáp', 250.00, 200.00),
    (2, 'Viêm giáp cấp/bán cấp/tự miễn', 220.00, 170.00),
    (2, 'Suy giáp', 210.00, 160.00),
    (2, 'Cường giáp', 230.00, 180.00),
    (2, 'Chẩn đoán u tuyến yên, u tuyến thượng thận', 300.00, 250.00),
    (2, 'Khám và điều trị bệnh huyết áp cao', 800.00, 650.00),
    (2, 'Khám tổng quát cho người cao tuổi', 850.00, 700.00),

-- Khoa Ngoại - Gây mê hồi sức
    (3, 'Phẫu thuật nội soi', 1200.00, 950.00),
    (3, 'Phẫu thuật mở ổ bụng', 1500.00, 1200.00),
    (3, 'Gây mê toàn thân', 700.00, 500.00),
    (3, 'Gây tê ngoài màng cứng', 600.00, 450.00),
    (3, 'Hồi sức sau phẫu thuật', 800.00, 650.00),
    (3, 'Phẫu thuật chỉnh hình', 1300.00, 1000.00),
    (3, 'Phẫu thuật khâu vết thương', 400.00, 300.00),
    (3, 'Phẫu thuật cắt bỏ khối u', 1600.00, 1300.00),
    (3, 'Chăm sóc sau phẫu thuật', 500.00, 400.00),
    (3, 'Phẫu thuật cắt ruột thừa', 2200.00, 1800.00),
    (3, 'Phẫu thuật tái tạo mô', 3000.00, 2500.00),
    (3, 'Nội soi bàng quang', 1300.00, 1100.00),

-- Khoa Phụ sản - Kế hoạch hóa gia đình
    (4, 'Khám phụ khoa', 800.00, 600.00),
    (4, 'Siêu âm thai', 1000.00, 800.00),
    (4, 'Tư vấn kế hoạch hóa gia đình', 500.00, 400.00),
    (4, 'Phẫu thuật cắt buồng trứng', 1800.00, 1400.00),
    (4, 'Thụ tinh trong ống nghiệm', 2500.00, 2000.00),
    (4, 'Khám thai định kỳ', 850.00, 700.00),
    (4, 'Phẫu thuật cắt cổ tử cung', 2000.00, 1600.00),
    (4, 'Tư vấn sức khỏe sinh sản', 600.00, 500.00),

-- Khoa Chẩn đoán hình ảnh
	(5, 'Chụp CT Scan', 3500.00, 3000.00),
    (5, 'Chụp X-quang phổi', 800.00, 650.00),
    (5, 'Siêu âm tim', 1200.00, 1000.00),
    (5, 'Chụp X-quang', 700.00, 550.00),
    (5, 'Siêu âm tổng quát', 800.00, 650.00),
    (5, 'MRI', 3000.00, 2500.00),

-- Khoa Xét nghiệm
	(6, 'Xét nghiệm vi sinh', 700.00, 550.00),
    (6, 'Xét nghiệm huyết học', 500.00, 400.00),
    (6, 'Xét nghiệm dị ứng', 600.00, 500.00),
    (6, 'Xét nghiệm máu', 400.00, 300.00),
    (6, 'Xét nghiệm nước tiểu', 200.00, 150.00),
    (6, 'Xét nghiệm sinh hóa', 600.00, 500.00),

-- Khoa Phẫu thuật - Tạo hình thẩm mỹ
	(7, 'Phẫu thuật thu nhỏ bụng', 12000.00, 10000.00),
    (7, 'Phẫu thuật nâng mông', 13000.00, 11000.00),
    (7, 'Xóa nếp nhăn', 4000.00, 3500.00),
     (7, 'Nâng ngực', 10000.00, 9000.00),
    (7, 'Cắt mí mắt', 5000.00, 4500.00),
    (7, 'Phẫu thuật tạo hình mặt', 15000.00, 12000.00),

-- Khoa Dược
	(8, 'Thực hiện tư vấn dùng thuốc an toàn', 300.00, 250.00),
    (8, 'Hướng dẫn sử dụng thuốc', 250.00, 200.00),
    (8, 'Quản lý tương tác thuốc', 350.00, 300.00),
    (8, 'Tư vấn thuốc', 200.00, 150.00),
    (8, 'Kê đơn thuốc', 100.00, 80.00),
    (8, 'Quản lý thuốc', 300.00, 250.00),

-- Chuyên Khoa Tai mũi họng
	(9, 'Điều trị viêm xoang', 800.00, 650.00),
    (9, 'Phẫu thuật nội soi mũi', 2000.00, 1600.00),
    (9, 'Điều trị ngạt mũi', 600.00, 500.00),
    (9, 'Khám tai mũi họng', 700.00, 550.00),
    (9, 'Nội soi tai', 800.00, 600.00),
    (9, 'Phẫu thuật chỉnh hình mũi', 3000.00, 2500.00),

-- Chuyên Khoa Răng Hàm Mặt
	(10, 'Khám và điều trị sâu răng', 700.00, 600.00),
    (10, 'Lấy cao răng', 500.00, 400.00),
    (10, 'Chữa tủy răng', 1000.00, 800.00),
    (10, 'Khám răng miệng', 600.00, 500.00),
    (10, 'Tẩy trắng răng', 1500.00, 1200.00),
    (10, 'Nhổ răng', 800.00, 600.00),

-- Đơn nguyên Hỗ trợ sinh sản
	(11, 'Tư vấn vô sinh', 700.00, 600.00),
    (11, 'Thực hiện kỹ thuật hỗ trợ sinh sản', 5000.00, 4000.00),
    (11, 'Xét nghiệm tiền hôn nhân', 800.00, 600.00),
    (11, 'Thụ tinh trong ống nghiệm', 2500.00, 2000.00),
    (11, 'Tư vấn sinh sản', 500.00, 400.00),
    (11, 'Chữa hiếm muộn', 3000.00, 2500.00);

-- User Bác sĩ
INSERT INTO users (full_name, phone_number, password, birthday, address, role_id)
VALUES
-- Khoa khám bệnh
	('Phạm Thị Hồng Hoa', '0123456789', '123','1980-05-15','123 Đường ABC, Hà Nội',2),

-- Khoa nội tổng hợp
	('Trương Quốc Trung', '0987654321', '123', '1985-06-10', '456 Đường XYZ, Hà Nội', 2),
    ('Phạm Thị Quỳnh Hương', '0981234567','123', '1990-12-25', '789 Đường DEF, TP Hồ Chí Minh', 2),
    ('Lưu Quang Chung', '0976543210', '123', '1978-08-20', '321 Đường GHI, Đà Nẵng', 2),

-- Khoa Ngoại - Gây mê hồi sức
	('Nguyễn Việt Trung', '0987654321', '123', '1990-03-25', '456 Đường XYZ, Hà Nội', 2),
    ('Dương Đình Toàn', '0912345678', '123', '1985-07-10', '789 Đường LMN, Hà Nội', 2),

-- Khoa phụ sản - kế hoạch hóa gia đình
	('Nguyễn Đức Thuấn', '0912335678', '123', '1985-10-10', '678 Đường LMN, Thanh Hóa', 2),
    ('Nguyễn Hữu Cốc', '0987654321', '123', '1990-01-01', 'Hà Nội', 2),
	('Đỗ Thị Huệ', '0912345678', '123', '1995-05-15', 'Hồ Chí Minh', 2),
	('Trần Anh Tuấn', '0345678912', '123', '1988-11-20', 'Đà Nẵng', 2),
	('Nguyễn Hoài Chân', '0987654321', '123', '2000-03-10', 'Hải Phòng', 2),
	('Đoàn Thị Lan', '0912345678', '123', '1992-07-25', 'Cần Thơ', 2),
	('VŨ THỊ PHƯỢNG', '0345678912', '123', '1998-09-05', 'Hạ Long', 2),
	('NGÔ TRỌNG THẾ', '0987654321', '123', '1985-12-31', 'Nha Trang', 2),
	('Lưu Thị Phương Thanh', '0912345678', '123', '2002-04-15', 'Huế', 2),
	('Đoàn Mạnh Tiến', '0345678912', '123', '1997-06-20', 'Vũng Tàu', 2),

-- Khoa Chẩn đoán hình ảnh
	('Vi Đình Tuấn', '0987654321', '123', '1995-03-15', 'Hà Nội', 2),
	('Nguyễn Thị Phương Thảo', '0345678912', '123', '1998-11-28', 'Hồ Chí Minh', 2),

-- Khoa Xét nghiệm
	('Vũ Đình Tuấn', '09876223321', '123', '1995-03-30', 'Hà Nội', 2),
	('Phạm Thị Thảo', '0345678912', '123', '1998-11-08', 'Hồ Chí Minh', 2),

-- Khoa Phẫu thuật - Tạo hình thẩm mỹ
	('Nguyễn Hải An', '0987654321', '123', '1992-04-18', 'Hà Nội', 2),

-- Khoa dược
	('Vũ Thị Lý', '0987654442', '123', '1990-01-01', 'Thanh Hóa', 2),

-- Khoa Tai mũi họng
	('Nguyễn Văn Lý', '0987654321', '123', '1984-01-01', 'Hà Nội', 2),
	('Nguyễn Song Hào', '0345678912', '123', '1995-05-15', 'Hồ Chí Minh', 2),

-- Khoa Răng Hàm Mặt
('Lê Thị Hồng', '0987654000', '123', '1990-01-01', 'Hà Nội', 2),

-- Đơn nguyên Hỗ trợ sinh sản
('Nguyễn Thị Tho', '0987654321', '123', '1990-01-01', 'Hà Nội', 2),
('Trần Thị Thu Hằng', '0345678912', '123', '1995-05-15', 'Hồ Chí Minh', 2),
('Vũ Thị Thu Trang', '0912345678', '123', '1998-11-20', 'Đà Nẵng', 2),
('Khổng Thị Vân', '0987654321', '123', '2000-03-10', 'Hải Phòng', 2);

-- Doctor
INSERT INTO doctors (user_id, specialty_id, training_process, job_description,image_url)
VALUES
-- Khoa khám bệnh
	(1,1,
    '1977: Tốt nghiệp ĐH Y Hà Nội ngành Đa khoa - Hóa sinh
	 1999: Tốt nghiệp Thạc sỹ chuyên ngành Nội tiết tại ĐH Y Hà Nội
     2009: Tốt nghiệp Tiến sỹ chuyên ngành Nội - Nội tiết tại ĐH Quân y 103',

     'Khám và điều trị bệnh lý đái tháo đường, biến chứng đái tháo đường
     Khám và điều trị các bệnh lý tuyến giáp
     Khám và điều trị các bệnh lý tuyến thượng thận
     Khám và điều trị các bệnh lý u tuyến yên
     Khám và điều trị bệnh lý tuyến sinh dục
     Các rối loạn chuyển hóa khác',

     '1.jpg'),
 -- Khoa Nội tổng hợp
	(2,2,
    '1981: Tốt nghiệp Học viện Quân Y
	1991: Tốt nghiệp CKI tại Học viện quân y',
    'Khám bệnh, chữa bệnh chuyên khoa Nội',
    '2.jpg'),

    (3,2,
    '2014: Tốt nhiệp bác sĩ đa khoa - Đại học Y Dược Hải Phòng',
    'Khám chữa bệnh nội khoa và chuyên khoa lao
	Hồi sức cấp cứu, siêu âm, phục hồi chứ năng hô hấp',
    '3.jpg'),


    (4,2,
    '1985: Tốt nghiệp Bác sĩ Đa khoa tại Học viện Quân y
	2006: Tốt nghiệp CKI tại Đại học Y Hà Nội',
    'Khám bệnh, chữa bệnh chuyên khoa Nội tổng hợp',
    '4.jpg'),

    -- Khoa Ngoại - Gây mê hồi sức
    (5,3,
    'Tốt nghiệp Đại học y Hà Nội.',
    '',
    '5.jpg'),

    (6,3,
    'Năm 2004: Tốt nghiệp Bác sỹ nội trú trường Đại học Y Hà Nội.
    Năm 2010: Học phẫu thuật khớp và Chấn thương chỉnh hình tại Australia.
	Năm 2011: Học phẫu thuật nội soi khớp tại Singarpore.
	Năm 2015: Học nâng cao về phẫu thuật thay khớp tại Đức.
	Năm 2016: Học nâng cao về phẫu thuật thay khớp tại Hàn Quốc.
	Năm 2016: Bảo vệ luận án Tiến sỹ trường Đại học Y Hà Nội.
	Năm 2017: Học tập nâng cao về phẫu thuật nội soi khớp tại Pháp.
	Năm 2022: Được Nhà nước phong hàm Phó giáo sư chuyên ngành Chấn thương chỉnh hình',
    'Khám bệnh chuyên khoa Ngoại: Cơ - Xương - Khớp - Cột sống - Chấn thương thể thao
	Tiêm nội khớp huyết thanh giàu tiểu cầu (PRP); tế bào gốc; các chế phẩm sinh học giúp bảo tồn sụn khớp, điều trị thoái hóa khớp
	Phẫu thuật chấn thương - Bệnh lý khớp (Nội soi khớp và thay khớp)
	Phẫu thuật Chấn thương chỉnh hình, chấn thương thể thao
	Điều trị bảo tồn gãy xương trật khớp, chấn thương thể thao
	Sửa di chứng
	Phục hồi chức năng sau mổ thay khớp và mổ nội soi
	Phẫu thuật bệnh lý khối u',
    '6.jpg'),

 -- Khoa Phụ sản - Kế hoạch hóa gia đình
    (7,4,
    'Tốt nghiệp trường Đại học Y Thái Bình (1986 – 1989)
    Phó Giám đốc Bệnh viện – Trưởng khoa Phụ sản, Bệnh viện Quốc tế DoLife (2023)
	Phó Giám đốc – Trưởng khoa Phụ Sản, Bệnh viện Đa khoa Phương Đông (2018 - 2023)
	Giám đốc Bệnh viện đa khoa Bảo Sơn II (2017 - 2018)
	Chuyên gia y tế của Bệnh viện Vinmec, Bệnh viện phụ sản An thịnh (2016 - 2018)',
    'Khám thai quản lý thai nghén, điều trị các trường hợp thai sản bệnh lý.
	Khám và điều trị các bệnh lý phụ khoa.
	Siêu âm sản phụ khoa: siêu âm 2D, 4D phát hiện sớm dị tật bẩm sinh thai nhi và các bệnh lý sản phụ khoa.
	Soi cổ tử cung, sinh thiết cổ tử cung phát hiện sớm ung thư cổ tử cung, điều trị các bệnh lý ở cổ tử cung âm đạo.
	Phẫu thuật lấy thai kể cả các loại mổ lấy thai khó: mổ cũ nhiều lần, mổ lấy thai trong rau tiền đạo, rau cài răng lược, rau bong non, ngôi ngang, tiền sản giật, thai sản bệnh lý.
	Mổ phụ khoa (mổ mở và mổ nội soi) kể cả những phẫu thuật khó phức tạp, người có bệnh lý nội khoa phải phẫu thuật phụ khoa.
	Phẫu thuật thu hẹp âm đạo, làm đẹp tầng sinh môn, phẫu thuật sa sinh dục.
	Điều trị vô sinh và thực hiện một số kỹ thuật hỗ trợ sinh sản (ngoại trừ IVF).
	Và một số kỹ thuật khác trong sản phụ khoa.',
    '7.jpg'),

    (8,4,
    'Tốt nghiệp Đại học Y Hà Nội năm 1977
	Tu nghiệp tại Thái Lan về Chuyên ngành Sản phụ khoa
	Viết sách giáo khoa, giáo trình giảng dạy về chuyên khoa Sản phụ khoa cho Đại học Y Hà Nội',
    'Khám thai, theo dõi thai định kì
	Khám và điều trị các bệnh phụ khoa
	Tư vấn, chăm sóc sức khỏe phụ nữ tiền mãn kinh và sau mãn kinh
	Điều trị vô sinh và hiếm muộn
	Phát hiện sớm ung thư vú và ung thư cổ tử cung
	Siêu âm thai và theo dõi thai kỳ
	Khám và quản lý thai nghén
	Chẩn đoán trước sinh
	Double test, triple test
	Hỗ trợ sinh sản
	Sàng lọc, chẩn đoán trước sinh, các bệnh lý di truyền
	Phẫu thuật sản phụ khoa
	Tư vấn, chăm sóc sức khỏe sinh sản vị thành niên
	Khám và tư vấn sức khỏe tiền hôn nhân',
    '8.jpg'),

    (9,4,
    '1990: Tốt nghiệp Đại học Y Hà Nội - Đa khoa Ngoại sản
	2003: Tốt nghiệp cấp 1 chuyên khoa Sản - Đại học Y Hải Phòng
	2009: Tốt nghiệp chuyên khoa cấp II - Sản phụ khoa Đại học Y Hà Nội
	2012:Chứng chỉ hành nghề khám chữa bệnh chuyên khoa sản phụ khoa ',
    'Khám bệnh, chữa bệnh chuyên khoa Phụ Sản - KHHGĐ
	Khám phụ khoa
	Quản lý thai nghén
	Điều trị vô sinh
	Mổ cấp cứu sản khoa, phụ khoa
	Mổ nội soi chửa ngoài tử cung, u nang buồng trứng, cắt hai phần phụ',
    '9.jpg'),

    (10,4,
    '2003: Tốt nghiệp Bác sĩ đa khoa - Đại học Y Thái Bình
	2010: Tốt nghiệp Bác sĩ chuyên khoa cấp I - Đại học Y Thái Bình - chuyên ngành: Sản phụ khoa
	2022: Học Bác sĩ chuyên khoa cấp II - Đại học Y Hà Nội',
    'Khám bệnh, chữa bệnh chuyên khoa Phụ sản',
    '10.jpg'),

	(11,4,
    'Tốt nghiệp bác sĩ Chuyên khoa Nhi tại Đại học Y Hà Nội',
    'Bệnh lý hô hấp: Hen phế quản, viêm phế quản, viêm phổi, viêm tai giữa...
	Bệnh lý tiêu hóa - Dinh dưỡng: Táo bón, viêm dạ dày tá tràng, hội chứng trào ngược...
	Bệnh lý tiết niệu: Nhiễm khuẩn tiết niệu, viêm cầu thận cấp...
	Các bệnh lý cấp tính: Sốt virus, sốt phát ban, sốt xuất huyết, chân tay miệng...
	Tư vấn tiêm chủng cho trẻ em và người lớn ',
    '11.jpg'),

    (12,4,
    'Tốt nghiệp Bs Đa khoa tại Đại học Y Hà Nội',
    'Tư vấn tiêm chủng cho trẻ em và người lớn ',
    '12.jpg'),

    (13,4,
    'Tốt nghiệp Đại học Y Hà Nội
	Chứng chỉ Bác sĩ Nhi khoa cơ bản - Bệnh viện Nhi Trung ương
	Chứng chỉ Bác sĩ Nhi đa khoa - Bệnh viện Nhi Trung ương',
    'Bác sĩ chuyên khoa Nhi',
    '13.jpg'),

    (14,4,
    'Tốt nghiệp Đại học Y Hà Nội
	Chứng chỉ Bác sĩ Nhi khoa cơ bản - Bệnh viện Nhi Trung ương
	Chứng chỉ Bác sĩ Nhi đa khoa - Bệnh viện Nhi Trung ương',
    'Bác sĩ chuyên khoa Nhi',
    '14.jpg'),

    (15,4,
    '2011: Tốt nghiệp Bác sỹ đa khoa tại Đai học Y Hà Nội
	2019: Tốt nghiệp CKI chuyên ngành Nhi khoa tại Đai học Y Hà Nội',
    'Khám bệnh, chữa bệnh chuyên khoa Nhi',
    '15.jpg'),


    (16,4,
    '2014: Tốt nghiệp Đại học Y Dược Thái Bình
	2016: Chứng chỉ siêu âm sản phụ khoa - Bệnh viện Phụ sản Trung ương
	2020: Chứng chỉ phẫu thuật nội soi - Bệnh viện Việt Đức
	2020: Chứng chỉ sản phụ khoa nâng cao và soi đốt cổ tử cung - Bệnh viện Phụ sản Trung ương
	2021: Kỹ thuật lọc rửa và bơm tinh trùng và buồng tử cung IUI - Bệnh viện Phụ sản Trung ương
	2021: Tốt nghiệp chuyên khoa cấp 1 chuyên khoa Sản - Đại học Y Hà Nội',
    'Khám bệnh, chữa bệnh chuyên khoa Phụ Sản - KHHGĐ',
    '16.jpg'),

-- Khoa Chẩn đoán hình ảnh
	(17,5,
    'Tốt nghiệp Đại học Y Hà Nội
    Nhiều năm kinh nghiệm công tác tại Khoa Chẩn đoán hình ảnh tại Bệnh viện Thanh Nhàn - Hà Nội
	Có 12 năm kinh nghiệm trong lĩnh vực Chẩn đoán hình ảnh.',
    'Điều trị can thiệp Fus- MRI.
	Chụp Cộng hưởng từ để chẩn đoán các bệnh lý về sọ não, đầu mặt cổ, xương khớp, hệ thống cơ quan trong hố chậu…
	Chụp CT Scanner đa lát cắt chẩn đoán bệnh lý về sọ não, đầu mặt cổ, ngực, bụng, xương khớp…
	Thực hiện chụp X quang kỹ thuật số: các kỹ thuật thường qui, chụp có chất cản quang
	Chụp x quang vú (mammography)
	Siêu âm mầu 4 chiều: siêu âm tổng quát, siêu âm sản phụ khoa, siêu âm tim mạch, siêu âm sản phụ khoa.',
    '17.jpg'),

    (18,5,
    '2008: Chứng chỉ định hướng chuyên khoa chuyên ngành chẩn đoán hình ảnh
	2007: Tốt nghiệp tại Học viện Quân y
	2015: Tốt nghiệp CKI chuyên ngành chẩn đoán hình ảnh',
    'Bác sĩ chẩn đoán hình ảnh',
    '18.jpg'),

-- Khoa xét nghiệm
	(19,6,
    'Tốt nghiệp Đại học Y Hà Nội',
    'Chẩn đoán bệnh: Dựa vào kết quả xét nghiệm, bác sĩ sẽ đưa ra chẩn đoán chính xác về bệnh mà bệnh nhân đang mắc phải.',
    '19.jpg'),

    (20,6,
    'Tốt nghiệp Đại học Y Hà Nội',
    'Theo dõi diễn biến bệnh: Xét nghiệm giúp theo dõi sự tiến triển của bệnh, hiệu quả điều trị và phát hiện sớm các biến chứng.',
    '20.jpg'),

-- Khoa Phẫu thuật - Tạo hình thẩm mỹ
    (21,7,
    '2015: Tốt nghiệp Bác sĩ đa khoa - Học viện Quân Y
	2022: Tốt nghiệp Bác sĩ chuyên khoa I chuyên ngành Da liễu - Đại học Y Hà Nội',
    'Bệnh da dị ứng: Mày đay, viêm da cơ địa, viêm da tiếp xúc, sẩn ngứa…
	Các bệnh da do vi khuẩn, nấm, ký sinh trùng: Viêm mô bào, viêm nang lông, chốc, chàm vi khuẩn, nấm, ghẻ…
	Các bệnh da do virus: Thủy đậu, zona thần kinh, mụn cóc, u mềm lây…
	Các bệnh lây qua đường tình dục: Lậu, giang mai, sùi mào gà, herpes sinh dục…
	Các bệnh da khác: U ống tuyến mồ hôi, u mềm treo, viêm da tiết bã, sẩn cục, dày sừng da dầu, dày sừng ánh sáng, bớt tuyến bã…
	Thẩm mỹ nội khoa: trứng cá, sẹo lõm trứng cá, sẹo lồi, tiêm Mesotherapy, tiêm Botulinum Toxin, phi kim trẻ hóa, công nghệ HIFU, laser CO2 Fractional, laser điều trị sắc tố…',
    '21.jpg'),

-- Khoa Dược
    (22,8,
    '2014: Tốt nghiệp Đại học Y Dược Thái Bình',
    'Tư vấn về thuốc
    Theo dõi tác dụng phụ của thuốc',
    '22.jpg'),

-- 	Khoa Tai mũi họng
    (23,9,
    'Tốt nghiệp trường Học viện Quân y
	Đào tạo bác sĩ CK Tai mũi họng tại CHDC Đức (1987-1990)',
    'Ù tai, nghe kém, điếc đột ngột
	Chẩy mủ tai, viêm tai giữa cấp, mạn.
	Vá màng nhĩ nội soi
	Phát hiện sớm và điều trị tốt bệnh viêm tai giữa màng nhĩ đóng kín, không chẩy mủ ra ngoài
	Viêm mũi xoang dị ứng, viêm mũi vận mạch.
	Viêm mũi ngạt tắc mũi mạn tính
	Vêm đa xoang mạn lâu ngày khó khỏi, polyp mũi xoang
	Nấm mũi xoang
	Đau đầu mạn tính do mũi xoang…',
    '23.jpg'),

    (24,9,
    'Tốt nghiệp trường Đại học Y Hà Nội',
    'Khám và điều trị bệnh lý Tai Mũi Họng: ù tai, điếc đột ngột, viêm mũi dị ứng, viêm đa xoang mạn tính, viêm họng mạn tính, viêm thanh quản, viêm amidan...
	Phẫu thuật mũi xoang: Lệch vách ngăn mũi, cắt polyp mũi xoang...
	Phẫu thuật tai: Vá màng nhĩ nội soi...
	Phẫu thuật họng - thanh quản: Cắt amidan, cắt polyp dây thanh...
	Phẫu thuật cắt thắng lưỡi
	Điều trị các biến chứng của viêm VA (viêm tai thanh dịch, viêm tai giữa cấp, viêm thanh khí phế quản...)  ',
    '24.jpg'),

-- Khoa Răng Hàm Mặt
    (25,10,
    '1990: Tốt nghiệp Học viện Quân y – ngành Y năm 1990
	1997: Tốt nghiệp Bác sỹ Chuyên khoa cấp I – Chuyên ngành Mắt – Học viện Quân y ',
    'Kiểm tra răng: Đánh giá tình trạng răng, tìm kiếm các dấu hiệu sâu răng, mòn men răng.
	Kiểm tra nướu: Kiểm tra tình trạng viêm nướu, chảy máu chân răng.
	X-quang: Đánh giá tình trạng răng bên trong, xương hàm, và các vấn đề tiềm ẩn.
	Vệ sinh răng miệng: Làm sạch cao răng, mảng bám.',
    '25.jpg'),

    -- Đơn nguyên Hỗ trợ sinh sản
    (26,11,
    'Tốt nghiệp Bác sĩ Đa khoa, Trường Đại học Y Hà Nội (2013)
	Chứng chỉ Định hướng Chuyên khoa chuyên ngành Sản Phụ Khoa, Trường ĐH Y Hà Nội cấp (2015)
	Chứng chỉ Siêu âm cơ bản trong Sản Phụ khoa, Trường Đại học Y Hà Nội cấp (2015)
	Chứng chỉ Thực hành lâm sàng trong Hỗ trợ Sinh sản, Bệnh viện Từ Dũ cấp (2017)',
    'Sản phụ khoa, hỗ trợ sinh sản',
    '26.jpg'),

	(27,11,
    'Cử nhân Sinh học, Trường Đại học Khoa học Tự nhiên, Đại học Quốc Gia Hà Nội
	Thạc sĩ Công nghệ Sinh học, Đại học Bách khoa Hà Nội
	Chứng chỉ Các phương pháp Hỗ trợ Sinh sản, Bệnh viện Từ Dũ cấp
	Chứng chỉ Các phương pháp Hỗ trợ Sinh sản, Bệnh viện Phụ sản Trung ương cấp
	Chứng nhận Đông noãn, phôi người bằng phương pháp thủy tinh hóa Kitazato - Cryotech, Nhật bản (2010, 2015)
	Chứng chỉ về Kỹ thuật ICSI, Bệnh viện Đa khoa Vạn Hạnh
	chỉ Đào tạo Sinh thiết phôi PGS, Origio, Đan mạch ',
    'Chuyên gia phôi học',
    '27.jpg'),

	(28,11,
    'Tốt nghiệp Bác sĩ Đa khoa, Trường Đại học Y Dược, Đại học Quốc Gia Hà Nội
	Chứng chỉ Chuyên viên Labo Hỗ trợ Sinh sản, Bệnh viện Từ Dũ
	Chứng nhận Trữ đông noãn, phôi người bằng phương pháp thủy tinh hóa ',
    'Bác sĩ hỗ trợ sinh sản
	Chuyên viên phôi học ',
    '28.jpg'),

	(29,11,
    'Tốt nghiệp Bác sĩ, Trường Học viện Quân Y (2012)
	Tốt nghiệp Bác sĩ Định hướng Chuyên khoa Sản, Bệnh viện Từ Dũ (2014)
	Tốt nghiệp Thạc sĩ Y khoa, Đại học Y Hà Nội (2020)
	Tốt nghiệp Bác sĩ Hỗ trợ Sinh sản, Bệnh viện Từ Dũ (2017)',
    'Hỗ trợ sinh sản – Sản phụ khoa – Siêu âm',
    '29.jpg');





