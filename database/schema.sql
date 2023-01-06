drop database if exists eshop;

create database eshop;

use eshop;

BULK INSERT dbo.Actors
FROM 'C:\Users\coyot\vttp2022\eshop\eshop\database\data.csv'
WITH
(
        FORMAT='CSV',
        FIRSTROW=1
)
GO

create table customers (
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    
    primary key(name),
    constraint fk_name  
        foreign key(order_id) references orders(order_id)
);

create table orders (
    order_id int not null,
    delivery_id int auto_increment not null,
    status varchar(7),
    order_date date not null,

    primary key(orderid)
);

create table order_status (
order_id int not null,
delivery_id int auto_increment not null,
status varchar(10),
status_update int not null,

primary key(order_id),
 constraint fk_order_id
        foreign key(order_id) references orders(order_id)
);

create table line_item (
item text not null,
quantity int default '1',

primary key (item),
constraint fk_order_id
	foreign key(order_id) references orders(order_id)
);