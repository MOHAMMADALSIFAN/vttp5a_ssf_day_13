package sg.edu.nus.iss.vtpp5a_ssf_day_13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.vtpp5a_ssf_day_13.model.Person;
import sg.edu.nus.iss.vtpp5a_ssf_day_13.services.PersonService;



@Controller
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  PersonService personService;

  @GetMapping("")
  public String personListing(Model model) {
    List<Person> persons = personService.findAll();
    model.addAttribute("persons", persons);

    return "personlist";
  }

  @GetMapping("/create")
  public String createForm(Model model) {
    Person p = new Person();
    model.addAttribute("persons", p);
      return "createperson";
  }
  
  @PostMapping("/create")
public String savePerson(@ModelAttribute Person person, Model model) {
    personService.create(person);
    return "redirect:/persons";
}

  @GetMapping("/{id}")
  public String getPersonbyId(@PathVariable String id,Model model) {
    Person person = personService.findById(id);
    if (person == null) {
      return "error"; // Handle "not found" with an error page or appropriate message
  }
  model.addAttribute("person", person);
  return "persondetail"; // Return a detail view
  // return "personlist"; // Return a personlist

}
// @GetMapping("/findByName")
// public String findByName(@RequestParam String name, Model model) {
//     List<Person> persons = personService.findByName(name); // List of persons returned from service
//     model.addAttribute("persons", persons); // Add the list to the model
//     return "personlist"; // The view should handle the list of persons
// }



}