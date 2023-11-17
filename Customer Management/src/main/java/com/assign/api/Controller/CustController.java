package com.assign.api.Controller;

import com.assign.api.Service.CustService;
import com.assign.api.entity.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CustController {

    private CustService custService;
    public CustController(CustService custService){
        super();
        this.custService=custService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/customers")
    public String listStudents(Model model) {
        model.addAttribute("customer", custService.getAllCustomers());
        return "customer";
    }

    @GetMapping("/customers/new")
    public String createCustomerForm(Model model) {
        // create customer object to hold student form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "addcust";

    }

    @PostMapping("/customers")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        custService.saveCustomer(customer);
        return "redirect:/customer";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        model.addAttribute("customert", custService.getCustomerById(id));
        return "editcust";
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id,
                                @ModelAttribute("customer") Customer customer,
                                Model model) {

        // get customer from database by id
        Customer existingCustomer = custService.getCustomerById(id);
        existingCustomer.setId(id);
        existingCustomer.setFirst_name(customer.getFirst_name());
        existingCustomer.setLast_name(customer.getLast_name());
        existingCustomer.setStreet(customer.getStreet());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setState(customer.getState());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhno(customer.getPhno());

        // save updated customer object
        custService.updateCustomer(existingCustomer);
        return "redirect:/customer";
    }

    // handler method to handle delete student request

    @GetMapping("/students/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        custService.deletecustById(id);
        return "redirect:/customer";
    }


}
