package vttp2022.paf.assessment.eshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@RestController
public class OrderController {

	@Autowired
	private CustomerRepository cusRepo;

	@Autowired
	private WarehouseService warehouseSvc;

	@GetMapping
	public ResponseEntity<String> findCustomer(@RequestParam(required = true) String name) {
		Optional<Customer> customer = cusRepo.findCustomerByName(name);

		if (customer.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.contentType(MediaType.APPLICATION_JSON)
					.body("{error: Customer " + name + "not found}");
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(customer.toString());
	}

	@PostMapping
	public ResponseEntity<String> saveCartToDB(@RequestBody String json, Exception exception) {
		Order order = null;
		if (warehouseSvc.saveOrder(order) == false) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.contentType(MediaType.APPLICATION_JSON)
					.body("error: Save Failed");
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body("save successsful");

	}
}
// TODO: Task 3
