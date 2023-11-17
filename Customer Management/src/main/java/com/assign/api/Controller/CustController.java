package com.assign.api.Controller;

import com.assign.api.Service.CustService;
import com.assign.api.entity.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class CustController {
    @Autowired
    private CustService service;

    @GetMapping("/addcust")
    public String addCustForm() {
        return "customer";
    }

    @PostMapping("/register")
    public String custRegister(@ModelAttribute Customer c, HttpSession session) {
        service.addCust(c);
        session.setAttribute("msg", "Customer Added Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editcust(@PathVariable int id, Model m) {
        Customer c = service.getCustById(id);
        m.addAttribute("emp", c);
        return "editcust";
    }

    @PostMapping("/update")
    public String updateCust(@ModelAttribute Customer c, HttpSession session) {
        service.addCust(c);
        session.setAttribute("msg", "Emp Data Update Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEMp(@PathVariable int id, HttpSession session) {

        service.deleteCust(id);
        session.setAttribute("msg", "Emp Data Delete Sucessfully..");
        return "redirect:/";
    }


}
