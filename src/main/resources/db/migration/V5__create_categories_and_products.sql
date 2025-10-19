create table categories
(
    id          BIGINT AUTO_INCREMENT not null
        primary key,
    name        VARCHAR(255)          not null,
    description TEXT                  null
);

create table products
(
    id          BIGINT AUTO_INCREMENT   not null
        primary key,
    name        VARCHAR(255)            not null,
    description TEXT                    null,
    price       DECIMAL(10, 2)          not null,
    category_id BIGINT                  not null,
    constraint products_categories_fk
        foreign key (category_id) references categories (id)
);

