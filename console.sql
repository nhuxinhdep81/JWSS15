create database ss15_2;
use ss15_2;

CREATE TABLE IF NOT EXISTS product (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       price DOUBLE NOT NULL,
                                       description TEXT
);
INSERT INTO product (name, price, description) VALUES
                                                   ('iPhone 15 Pro', 1199.99, 'Flagship smartphone từ Apple với chip A17 Bionic.'),
                                                   ('Samsung Galaxy S23', 1099.99, 'Điện thoại cao cấp với camera tuyệt vời và màn hình AMOLED.'),
                                                   ('MacBook Air M2', 1299.00, 'Laptop mỏng nhẹ với chip Apple M2, hiệu năng cao.'),
                                                   ('Sony WH-1000XM5', 399.99, 'Tai nghe chống ồn hàng đầu từ Sony, âm thanh tuyệt hảo.'),
                                                   ('Dell XPS 13', 999.99, 'Laptop cao cấp cho dân văn phòng, màn hình sắc nét.');


CREATE TABLE IF NOT EXISTS review (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      id_product INT NOT NULL,
                                      id_user INT NOT NULL,
                                      rating INT CHECK (rating BETWEEN 1 AND 5),
                                      comment TEXT,
                                      FOREIGN KEY (id_product) REFERENCES product(id)

);

INSERT INTO review (id_product, id_user, rating, comment) VALUES
                                                              (1, 101, 5, 'Sản phẩm rất tốt, hiệu năng vượt mong đợi.'),
                                                              (1, 102, 4, 'Máy đẹp, nhưng hơi nóng khi chơi game nặng.'),
                                                              (2, 103, 4, 'Màn hình đẹp, camera tốt nhưng giá hơi cao.'),
                                                              (3, 101, 5, 'MacBook chạy cực mượt, pin rất trâu.'),
                                                              (4, 104, 5, 'Âm thanh cực kỳ chi tiết, chống ồn tuyệt vời.'),
                                                              (5, 105, 3, 'Thiết kế đẹp nhưng bàn phím hơi cứng.');



DELIMITER //

CREATE PROCEDURE get_all_products()
BEGIN
    SELECT * FROM product;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE get_product_by_id(IN p_id INT)
BEGIN
    SELECT * FROM product WHERE id = p_id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE get_reviews_by_product_id(IN p_id_product INT)
BEGIN
    SELECT * FROM review WHERE id_product = p_id_product;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE insert_review(
    IN p_id_product INT,
    IN p_id_user INT,
    IN p_rating INT,
    IN p_comment TEXT
)
BEGIN
    INSERT INTO review(id_product, id_user, rating, comment)
    VALUES (p_id_product, p_id_user, p_rating, p_comment);
END //

DELIMITER ;


-- Bảng lưu giỏ hàng
CREATE TABLE IF NOT EXISTS cart (
                                    id_cart INT AUTO_INCREMENT PRIMARY KEY,
                                    id_user INT NOT NULL,
                                    id_product INT NOT NULL,
                                    quantity INT NOT NULL DEFAULT 1,
                                    FOREIGN KEY (id_product) REFERENCES product(id)
);

-- Thêm hoặc cập nhật sản phẩm trong giỏ hàng
DELIMITER //

CREATE PROCEDURE upsert_cart(
    IN p_id_user INT,
    IN p_id_product INT,
    IN p_quantity INT
)
BEGIN
    DECLARE existing_id INT;
    SELECT id_cart INTO existing_id FROM cart WHERE id_user = p_id_user AND id_product = p_id_product LIMIT 1;
    IF existing_id IS NULL THEN
        INSERT INTO cart(id_user, id_product, quantity) VALUES (p_id_user, p_id_product, p_quantity);
    ELSE
        UPDATE cart SET quantity = quantity + p_quantity WHERE id_cart = existing_id;
    END IF;
END //

DELIMITER ;

-- Lấy danh sách sản phẩm trong giỏ hàng theo user
DELIMITER //

CREATE PROCEDURE get_cart_by_user(IN p_id_user INT)
BEGIN
    SELECT c.id_cart, c.id_user, c.id_product, c.quantity, p.name, p.price
    FROM cart c
             JOIN product p ON c.id_product = p.id
    WHERE c.id_user = p_id_user;
END //

DELIMITER ;


CREATE TABLE IF NOT EXISTS orders (
                                      order_id INT AUTO_INCREMENT PRIMARY KEY,
                                      user_id INT NOT NULL,
                                      recipient_name VARCHAR(255) NOT NULL,
                                      address VARCHAR(500) NOT NULL,
                                      phone_number VARCHAR(20) NOT NULL,
                                      order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_details (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             order_id INT NOT NULL,
                                             product_id INT NOT NULL,
                                             quantity INT NOT NULL,
                                             current_price DOUBLE NOT NULL,
                                             FOREIGN KEY (order_id) REFERENCES orders(order_id),
                                             FOREIGN KEY (product_id) REFERENCES product(id)
);


DELIMITER //

CREATE PROCEDURE insert_order(
    IN p_user_id INT,
    IN p_recipient_name VARCHAR(255),
    IN p_address VARCHAR(500),
    IN p_phone_number VARCHAR(20),
    OUT p_order_id INT
)
BEGIN
    INSERT INTO orders(user_id, recipient_name, address, phone_number)
    VALUES (p_user_id, p_recipient_name, p_address, p_phone_number);

    SET p_order_id = LAST_INSERT_ID();
END //

DELIMITER ;



DELIMITER //

CREATE PROCEDURE insert_order_detail(
    IN p_order_id INT,
    IN p_product_id INT,
    IN p_quantity INT,
    IN p_current_price DOUBLE
)
BEGIN
    INSERT INTO order_details(order_id, product_id, quantity, current_price)
    VALUES (p_order_id, p_product_id, p_quantity, p_current_price);
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE get_orders_by_user(IN p_user_id INT)
BEGIN
    SELECT order_id, user_id, recipient_name, address, phone_number, order_date
    FROM orders
    WHERE user_id = p_user_id
    ORDER BY order_date DESC;
END //

DELIMITER ;
#=================Them CV==============

#bai 10

create table Resume(
    id int primary key auto_increment,
    full_name varchar(100) not null,
    email varchar(100) not null unique,
    phone_number varchar(20),
    education varchar(200),
    experience varchar(200),
    skills varchar(200)
);


INSERT INTO Resume (full_name, email, phone_number, education, experience, skills)
VALUES
    ('Nguyễn Văn An', 'an.nguyen@example.com', '0912345678', 'Cử nhân Công nghệ Thông tin - ĐH Bách Khoa', '2 năm làm lập trình viên tại FPT Software', 'Java, SQL, Git'),

    ('Trần Thị Bình', 'binh.tran@example.com', '0987654321', 'Thạc sĩ Kinh tế - ĐH Kinh tế Quốc dân', '3 năm quản lý dự án tại Viettel', 'Quản lý dự án, MS Office, Giao tiếp'),

    ('Lê Minh Châu', 'chau.le@example.com', '0938123456', 'Cử nhân Thiết kế Đồ họa - Arena Multimedia', '1 năm làm designer tự do', 'Photoshop, Illustrator, UI/UX'),

    ('Phạm Quốc Dũng', 'dung.pham@example.com', '0905123789', 'Kỹ sư Điện tử - ĐH Bách Khoa', '4 năm kỹ sư phần cứng tại Intel Việt Nam', 'Embedded Systems, C++, Debugging'),

    ('Hoàng Thùy Dương', 'duong.hoang@example.com', '0976543210', 'Cử nhân Ngôn ngữ Anh - ĐH Ngoại ngữ', '2 năm giảng dạy tiếng Anh tại trung tâm ILA', 'Tiếng Anh, Giao tiếp, Soạn giáo án');


delimiter //
#=================Them CV==============
create procedure add_resume(
    full_name_in varchar(100),
    email_in varchar(100),
    phone_number_in varchar(20),
    education_in varchar(200),
    experience_in varchar(200),
    skills_in varchar(200)
)
begin
    insert into Resume(full_name, email, phone_number, education, experience, skills)
        values (full_name_in,email_in,phone_number_in,education_in,experience_in,skills_in);
end //

#=================Check Email trung lap==============


create  procedure check_email_exist(
    email_in varchar(100)
)
begin
    select * from Resume
        where email = email_in;
end //

#=================Lay ra danh sach CV==============

create procedure get_all_resume()
begin
    select * from Resume;
end //

#=================Tim kiem CV theo id==============
create procedure find_resume_by_id(
    id_in int
)
begin
    select * from Resume
        where id = id_in;
end //

#=================Cap nhat CV theo id==============

create procedure update_resume_by_id(
    id_in int,
    full_name_in varchar(100),
    email_in varchar(100),
    phone_number_in varchar(20),
    education_in varchar(200),
    experience_in varchar(200),
    skills_in varchar(200)
)
begin
    update Resume
        set full_name = full_name_in,
            email = email_in,
            phone_number = phone_number_in,
            education = education_in,
            experience = experience_in,
            skills = skills_in
    where id = id_in;

end //


#=================Xoa CV theo id==============
create procedure delete_resume_by_id(
    id_in int
)
delete from Resume where id = id_in;
delimiter //









