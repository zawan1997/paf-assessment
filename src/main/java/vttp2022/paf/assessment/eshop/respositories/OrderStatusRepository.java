package vttp2022.paf.assessment.eshop.respositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.OrderStatus;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;


@Repository
public class OrderStatusRepository {

    @Autowired
    private JdbcTemplate template;
    
    public boolean insertOrderStatus(OrderStatus status, String orderId) {
        return template.update(SQL_INSERT_ORDER_STATUS,
        status.getDeliveryId(),
        status.getStatus(),
        orderId) >0;
    }
}
