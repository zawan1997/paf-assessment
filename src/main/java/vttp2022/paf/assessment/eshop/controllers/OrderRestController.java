package vttp2022.paf.assessment.eshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@RestController
@RequestMapping(path = "/https://paf.chuklee.com/dispatch")
public class OrderRestController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping(path="{/orderId}")
	public ResponseEntity<String> dispatchMessage(@RequestBody Order order ) {

	 if (warehouseService.dispatch(order)!=null) {
        JsonObject success = Json.createObjectBuilder()
        .add("orderId:" + order.getOrderId(), ",deliveryId:" + order.getDeliveryId() + ",status: dispatched")
        .build();
     return ResponseEntity
     .status(HttpStatus.OK)
     .contentType(MediaType.APPLICATION_JSON)
     .body(success.toString());
     } else {
        JsonObject err = Json.createObjectBuilder()
        .add("orderId:" +order.getOrderId(), ",status: Pending")
        .build();
        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(err.toString());
     }
}
}
