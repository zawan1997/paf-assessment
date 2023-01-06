package vttp2022.paf.assessment.eshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderStatusRepository;

@Service
public class WarehouseService {

	@Autowired
	private OrderRepository ordRepo;

	@Autowired
	private CustomerRepository cusRepo;

	@Autowired
	private OrderStatusRepository statRepo;

	// You cannot change the method's signature
	// You may add one or more checked exceptions
// TODO: Task 4
	public OrderStatus dispatch(Order order) {
		WarehouseService warehouse = new WarehouseService();
		OrderStatus orderstatus = null;

		//If successful return nothing. If failed return the orderstatus?
		if (cusRepo.findCustomerByName(order.getName()).isPresent()){
			while(warehouse.saveOrder(order)!=false);
			statRepo.insertOrderStatus(orderstatus, order.getOrderId());
			return null;
		} else {
			return orderstatus;
		}
		//come back to figure out pending/dispatched and timestamp
	}

	//For task 3 part d
	public boolean saveOrder(Order order) {
		ordRepo.saveOrder(order);
		ordRepo.saveLineItems(order.getLineItems(), order.getOrderId());
		return true;
	}
}
