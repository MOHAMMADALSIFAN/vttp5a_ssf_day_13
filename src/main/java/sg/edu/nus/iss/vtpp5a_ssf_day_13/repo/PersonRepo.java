package sg.edu.nus.iss.vtpp5a_ssf_day_13.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vtpp5a_ssf_day_13.model.Person;

@Repository
public class PersonRepo {
  private List<Person> persons = new ArrayList<>();

  public List<Person> findAll() {
    return persons;
  }

  public boolean create(Person person) {
    persons.add(person);
    return true;
  }
  public Boolean delete (Person person){
    int iFoundPerson = persons.indexOf(person);

    if(iFoundPerson >= 0){
      persons.remove(person);
      return true;
    }
    return false;
  }

  public Boolean update (Person person){
   List<Person> filteredPerson = persons.stream().filter(p-> p.getId().equals(person.getId())).collect(Collectors.toList());

    if(filteredPerson.size()> 0){
      persons.remove(filteredPerson.getFirst());
      persons.add(person);
      return true;
    }
    return false;
  }
  public Person findById(String id) {
    return persons.stream()
            .filter(person -> person.getId().equals(id))
            .findFirst()
            .orElse(null);


}
// public List<Person> findByName(String name) {
//   List<Person> result = new ArrayList<>();
//   for (Person person : persons) {
//       if (person.getFirstName().equalsIgnoreCase(name)) {
//           result.add(person);
//       }
//   }
//   return result;
// }


}
