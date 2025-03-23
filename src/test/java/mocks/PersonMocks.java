package mocks;

import br.com.joaojunio.project_one.data.dto.PersonDTO;
import br.com.joaojunio.project_one.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMocks {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonDTO mockDTO() {
        return mockDTO(0);
    }

    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setId(Long.valueOf(number));
        person.setFirstName("First Name" + number);
        person.setLastName("Last Name" + number);
        person.setAddress("Address" + number);
        person.setGender(((number  % 2 ) == 0) ? "Masculino" : "Feminino");
        return person;
    }

    public PersonDTO mockDTO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setId(Long.valueOf(number));
        person.setFirstName("First Name" + number);
        person.setLastName("Last Name" + number);
        person.setAddress("Address" + number);
        person.setGender(((number  % 2 ) == 0) ? "Masculino" : "Feminino");
        return person;
    }

    public List<Person> mockEntityList() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add(mockEntity(i));
        }
        return list;
    }

    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add(mockDTO(i));
        }
        return list;
    }
}
