package sg.edu.nus.iss.vtpp5a_ssf_day_13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vtpp5a_ssf_day_13.model.Person;
import sg.edu.nus.iss.vtpp5a_ssf_day_13.services.PersonService;
import org.springframework.web.bind.annotation.RequestBody;




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
public String createPerson(@Valid @ModelAttribute("persons") Person person,  BindingResult result, Model model) {
    if (result.hasErrors()) {
        return "createperson";  // Show the form again with validation errors
    }
    // Save the person or perform other actions
    Person p = new Person(person.getFirstName(),person.getLastName(),person.getSalary(),person.getEmail(),person.getDateOfBirth(),person.getPhoneNumber(),person.getPostalCode());
    personService.create(p);
    return "redirect:/persons";  // Redirect after successful creation
}

//   @GetMapping("/{id}")
//   public String getPersonbyId(@PathVariable String id,Model model) {
//     Person person = personService.findById(id);
//     if (person == null) {
//       return "error"; // Handle "not found" with an error page or appropriate message
//   }

//   model.addAttribute("person", person);
//   return "persondetail"; // Return a detail view
//   // return "personlist"; // Return a personlist

// }
// @GetMapping("/findByName")
// public String findByName(@RequestParam String name, Model model) {
//     List<Person> persons = personService.findByName(name); // List of persons returned from service
//     model.addAttribute("persons", persons); // Add the list to the model
//     return "personlist"; // The view should handle the list of persons
// }



@GetMapping("/delete/{person-id}")
public String deletePerson(@PathVariable("person-id") String personId) {
    Person p = personService.findById(personId);
    personService.delete(p);

    return "redirect:/persons";
}


@GetMapping("/update/{person-id}")
public String updateForm(@PathVariable("person-id") String personId, Model model) {
    Person p = personService.findById(personId);
    if (p == null) {
        return "error"; // Return an error page if the person doesn't exist
    }
    model.addAttribute("person", p);
    return "personupdate";
}



@PostMapping("/update")
public String posteditPerson(@Valid @ModelAttribute("persons") Person person,  BindingResult result, Model model) {
  if (result.hasErrors()) 
    return "personupdate";  // Show the form again with validation errors

personService.update(person);

    return "redirect:/persons";

}
}