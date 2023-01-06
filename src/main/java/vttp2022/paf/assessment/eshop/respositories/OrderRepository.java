package vttp2022.paf.assessment.eshop.respositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class OrderRepository {
	@Autowired
	private JdbcTemplate template;
	// TODO: Task 3



	// saving all order items first
	public boolean saveOrder(Order order) {
		// List<LineItem> items = new LinkedList<>();
		// //LineItem it = new LineItem();
		// for(LineItem it:items){
		// 	it.getItem(); it.getQuantity();
		// }
		
		return template.update(SQL_INSERT_ORDER,
				order.getOrderId(),
				order.getAddress(),
				//order.getDeliveryId(),
				order.getEmail(),
				order.getName(),
				//order.getStatus(),
				order.getOrderDate()) > 0;
	}
	//mapping the items and attaching order_id foreign key to tag it
	public void saveLineItems(List<LineItem> lineItems, String orderId) {
		List<Object[]> data = lineItems.stream()
				.map(li -> {
					Object[] l = new Object[3];
					l[0] = li.getItem();
					l[1] = li.getQuantity();
					l[2] = orderId;
					return l;
				})
				.toList();

		template.batchUpdate(SQL_INSERT_LINE_ITEM, data);

	}



}
