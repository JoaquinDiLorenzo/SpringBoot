package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1,"Juan", "Perez", "2342"),
            new Customer(2, "Pablo", "Graca", "234"),
            new Customer(23,"Jsua", "jsuu", "8999"),
            new Customer(45, "iiu", "usjs", "uuids")
    ));

    @RequestMapping(method = RequestMethod.GET)
    //@GetMapping
    public List<Customer> getCustomers() {
        return customers;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    //@GetMapping("/{username}")
    public Customer getCliente(@PathVariable String username) {
        for (Customer c: customers) {
            if (c.getUsername().equalsIgnoreCase(username)) return c;
        }
        return null;
    }

    @PostMapping
    public Customer postCliente(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @PutMapping
    public Customer putCliente(@RequestBody Customer customer){
        for(Customer c:customers) {
            if (c.getID()==customer.getID()) {
                c.setName(customer.getName());
                c.setPassword(customer.getPassword());
                c.setUsername(customer.getUsername());

                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable int id) {
        for(Customer c: customers) {
            if (c.getID() == id) {
                customers.remove(c);
                return c;
            }
        }
        return null;
    }

    @PatchMapping
    public Customer patchCliente(@RequestBody Customer customer) {
        for(Customer c: customers) {
            if(c.getID() == customer.getID()) {
                if (customer.getName() != null) {
                    c.setName(customer.getName());
                }
                if (customer.getUsername() != null) {
                    c.setUsername(customer.getUsername());
                }
                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                }
                return c;
            }
        }
        return null;
    }

}