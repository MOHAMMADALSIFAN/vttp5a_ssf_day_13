package sg.edu.nus.iss.vtpp5a_ssf_day_13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sg.edu.nus.iss.vtpp5a_ssf_day_13.services.PersonService;

@Controller
public class PersonController {

  @Autowired
  PersonService personService;
  


}
