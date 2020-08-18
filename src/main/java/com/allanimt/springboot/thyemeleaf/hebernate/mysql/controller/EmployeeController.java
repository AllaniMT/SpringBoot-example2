package com.allanimt.springboot.thyemeleaf.hebernate.mysql.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.allanimt.springboot.thyemeleaf.hebernate.mysql.entity.Employee;
import com.allanimt.springboot.thyemeleaf.hebernate.mysql.service.EmployeeService;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addEmployee";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRegistration(@Valid Employee employee, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            System.out.println("has errors");
            return "addEmployee";
        }
        employeeService.insertEmployee(employee);
        return "redirect:/viewEmployees";
    }

    @RequestMapping(value = "/viewEmployees")
    public ModelAndView getAll() {
        List<Employee> list = employeeService.findAllEmployee();
        return new ModelAndView("viewEmployees", "list", list);
    }

    @RequestMapping(value = "/editEmployee/{id}")
    public String edit(@PathVariable int id, ModelMap model) {
        Employee employee = employeeService.findEmployeeByID(id);
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("employee") Employee employee) {

        Employee theEmployee = employeeService.findEmployeeByID(employee.getId());

        theEmployee.setFirstName(employee.getFirstName());
        theEmployee.setLastName(employee.getLastName());
        theEmployee.setCountry(employee.getCountry());
        theEmployee.setEmail(employee.getEmail());
        theEmployee.setSection(employee.getSection());
        theEmployee.setSex(employee.getSex());

        employeeService.insertEmployee(theEmployee);
        return new ModelAndView("redirect:/viewEmployees");
    }

    @RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id) {
        Employee employee = employeeService.findEmployeeByID(id);
        employeeService.deleteEmployee(employee);
        return new ModelAndView("redirect:/viewEmployees");
    }

    @ModelAttribute("sections")
    public List<String> intializeSections() {
        List<String> sections = new ArrayList<String>();
        sections.add("Finance");
        sections.add("Management");
        sections.add("Marketing");
        return sections;
    }

    @ModelAttribute("countries")
    public List<String> initializeCountries() {
        List<String> countries = new ArrayList<String>();
        countries.add("Tunisia");
        countries.add("CANADA");
        countries.add("FRANCE");
        countries.add("GERMANY");
        countries.add("ITALY");
        countries.add("OTHER");
        return countries;
    }


}

