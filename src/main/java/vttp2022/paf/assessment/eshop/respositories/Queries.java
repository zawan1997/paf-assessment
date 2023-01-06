package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    
    public static final String SQL_SEARCH_CUSTOMER_BY_NAME = "select name where name = ?";
    public static final String SQL_INSERT_ORDER = "insert into orders(order_id, address, delivery_id, email, name, status, date) values (?, ?, ?, ?, ?, ?,SYSDATE()";
    public static final String SQL_INSERT_CUSTOMER = "insert into customers(name, address, email) values (?, ?, ?)";
    public static final String SQL_INSERT_LINE_ITEM = "insert into line_item(item, quantity, order_id) values (?, ?, ?)";
    public static final String SQL_INSERT_ORDER_STATUS = "insert into order_status(delivery_id, status, order_id) values (?, ?, ?)";
}
