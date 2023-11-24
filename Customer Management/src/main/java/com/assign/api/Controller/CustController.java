package com.assign.api.Controller;

import com.assign.api.Service.CustService;
import com.assign.api.Service.Impl.DuplicateName;
import com.assign.api.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustController {

    @Autowired
    final CustService custService;
    public CustController(CustService custService){
        super();
        this.custService=custService;
    }

    // handler method to handle list customers and return mode and view
    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customer", custService.getAllCustomers());
        return "customer";
    }

    @GetMapping("/customers/new")
    public String createCustomerForm(Model model) {
//        try{
            Customer customer = new Customer();
            model.addAttribute("customer", customer);
            return "add_customer";
//        } catch (DuplicateName ex) {
//            model.addAttribute("error", "Duplicate customer name. Please choose a different name.");
//            return "/customers";
//        }
    }

//    @PostMapping("/customers")
//    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
//        custService.saveCustomer(customer);
//        return "redirect:/customers";
//    }

    @PostMapping("/customers")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            custService.saveCustomer(customer);
            return "redirect:/customers";
        } catch (DuplicateName s) {
            model.addAttribute("error", "Duplicate customer name. Please choose a different name.");
            return "add_customer";
        }
    }


    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", custService.getCustomerById(id));
        return "editcust";
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute("customer") Customer customer, Model model) {
        try{
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
            return "redirect:/customers";
        }catch (DuplicateName ex) {
            model.addAttribute("error", "Duplicate customer name. Please choose a different name.");
            return "error";
        }
    }

    @GetMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        custService.deleteCustomerById(id);
        return "redirect:/customers";
    }


}
