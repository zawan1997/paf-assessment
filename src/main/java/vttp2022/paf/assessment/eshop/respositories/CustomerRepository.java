package vttp2022.paf.assessment.eshop.respositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Customer;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public Optional<Customer> findCustomerByName(String name) {
		// TODO: Task 3 
		final List<Customer> customers = new LinkedList<>();
		final SqlRowSet rs =  jdbcTemplate.queryForRowSet(SQL_SEARCH_CUSTOMER_BY_NAME, name);
		if(rs != null)	{
			while (rs.next()) {
				customers.add((Customer) rs);
			}
		}
			return Optional.of(customers.get(0));

	}

	public boolean insertCustomer(Customer customer) {
		return jdbcTemplate.update(SQL_INSERT_CUSTOMER,
			customer.getName(), customer.getEmail(), customer.getAddress()) > 0;

	}
}
