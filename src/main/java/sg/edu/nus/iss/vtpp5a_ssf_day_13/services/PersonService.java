package sg.edu.nus.iss.vtpp5a_ssf_day_13.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vtpp5a_ssf_day_13.model.Person;
import sg.edu.nus.iss.vtpp5a_ssf_day_13.repo.PersonRepo;

@Service
public class PersonService {

  @Autowired
  PersonRepo personRepo;

  public List<Person> findAll(){
    return personRepo.findAll();
  }
  public boolean create(Person person){
    return personRepo.create(person);
  }
  public boolean delete(Person person){
    return personRepo.delete(person);
  }
  public boolean update(Person person){
    return personRepo.update(person);
  }
  public Person findById(String personId) {
    return personRepo.findById(personId);
}

  //   private List<Person> persons = new ArrayList<>(); // Simulated database

  //   public List<Person> findByName(String name) {
  //     return personRepo.findByName(name);
  // }
  
}



