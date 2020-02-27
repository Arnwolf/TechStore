create table categories
(
    id                 int(4) auto_increment
        primary key,
    name               varchar(40) not null,
    parent_category_id int(4)      null,
    constraint categories_Name_uindex
        unique (name)
);

create table category_parameters
(
    id               int(4) auto_increment
        primary key,
    category_id      int(4)                 not null,
    parameter_name   varchar(50)            not null,
    parameter_symbol varchar(10) default '' not null,
    searchable       tinyint(1)  default 0  not null,
    changeable       tinyint(1)  default 0  not null,
    constraint categories_details_categories_ID_fk
        foreign key (category_id) references categories (id)
            on delete cascade
);

create table items
(
    id           int(4) auto_increment
        primary key,
    manufacturer varchar(20)                 not null,
    name         varchar(50)                 not null,
    price        decimal(10, 2)              not null,
    main_photo   longtext                    not null,
    category_id  int(4)                      not null,
    new          tinyint(1)     default 0    not null,
    discount     decimal(10, 2) default 0.00 not null,
    availability int(3)         default 0    not null,
    constraint items_categories_ID_fk
        foreign key (category_id) references categories (id)
);

create table category_parameters_item_values
(
    id                    int(4) auto_increment
        primary key,
    item_id               int(4)      not null,
    category_parameter_id int(4)      not null,
    value                 varchar(80) not null,
    constraint category_details_item_details_category_details_ID_fk
        foreign key (category_parameter_id) references category_parameters (id),
    constraint category_details_item_details_items_ID_fk
        foreign key (item_id) references items (id)
            on delete cascade
);

create table item_details
(
    id           int(4) auto_increment
        primary key,
    viewed_times int(4) not null,
    sold_times   int(3) not null,
    item_id      int(3) not null,
    constraint item_details_ItemID_uindex
        unique (item_id),
    constraint item_details_items_ID_fk
        foreign key (item_id) references items (id)
            on delete cascade
);

create table item_photos
(
    id      int(4) auto_increment
        primary key,
    item_id int(4)   not null,
    photo   longtext not null,
    constraint item_photos_items_ID_fk
        foreign key (item_id) references items (id)
            on delete cascade
);

create table orders
(
    id            int(4) auto_increment
        primary key,
    total_amount  decimal(10, 2)                not null,
    city          varchar(10)                   not null,
    street        varchar(20)                   not null,
    phone_number  varchar(13)                   not null,
    client_name   varchar(40)                   not null,
    creation_date datetime                      not null,
    status        varchar(20) default 'pending' not null,
    email         varchar(35)                   not null
);

create table orders_items
(
    id       int(4) auto_increment
        primary key,
    order_id int(4)        not null,
    item_id  int(4)        not null,
    quantity int default 1 not null,
    constraint orders_items_items_ID_fk
        foreign key (item_id) references items (id),
    constraint orders_items_orders_ID_fk
        foreign key (order_id) references orders (id)
            on delete cascade
);

create table subscription
(
    id    int(4) auto_increment
        primary key,
    email varchar(40) not null
);

create table users
(
    id           int(4) auto_increment
        primary key,
    name         varchar(20)  default '' not null,
    password     text                    not null,
    email        varchar(35)             not null,
    city         varchar(10)  default '' not null,
    street       varchar(15)  default '' not null,
    phone_number varchar(13)  default '' not null,
    hashed_id    varchar(100) default '' not null,
    subscribe    tinyint(1)   default 1  not null,
    constraint users_Email_uindex
        unique (email),
    constraint users_Name_uindex
        unique (name)
);

create table item_reviews
(
    id            int(4) auto_increment
        primary key,
    user_id       int(4)       not null,
    item_id       int(4)       not null,
    rating        int(1)       not null,
    description   varchar(200) not null,
    creation_date datetime     not null,
    constraint item_reviews_items_ID_fk
        foreign key (item_id) references items (id)
            on delete cascade,
    constraint item_reviews_users_ID_fk
        foreign key (user_id) references users (id)
            on delete cascade
);

create table users_wishes
(
    id      int(4) auto_increment
        primary key,
    user_id int(3) not null,
    item_id int(3) not null,
    constraint users_wishes_items_ID_fk
        foreign key (item_id) references items (id)
            on delete cascade,
    constraint users_wishes_users_ID_fk
        foreign key (user_id) references users (id)
            on delete cascade
);


