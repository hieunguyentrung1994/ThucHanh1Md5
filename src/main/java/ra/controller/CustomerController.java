package ra.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Customer;
import ra.service.ICustomerService;


@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/")
    public ModelAndView listCustomers(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customerForm",customer);
        System.out.println("name"+customer.getName());
        return "/customer/add";
    }

    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("customerForm") Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

}
