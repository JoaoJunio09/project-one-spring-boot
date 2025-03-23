package services;

import br.com.joaojunio.project_one.data.dto.PersonDTO;
import br.com.joaojunio.project_one.model.Person;
import br.com.joaojunio.project_one.repositories.PersonRepository;
import br.com.joaojunio.project_one.services.PersonService;
import mocks.PersonMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    PersonMocks input;

    @Mock
    PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @BeforeEach
    void setUp() {
        input = new PersonMocks();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                );

        personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET"
                ));

        personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                );

        personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("PUT")
                );

        personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/person/v1/1")
                        && link.getType().equals("DELETE")
                );

        assertEquals("First Name1", personOne.getFirstName());
        assertEquals("Last Name1", personOne.getLastName());
        assertEquals("Address1", personOne.getAddress());
        assertEquals("Feminino", personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                );

        personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET"
                ));

        personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                );

        personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("PUT")
                );

        personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/person/v1/4")
                        && link.getType().equals("DELETE")
                );

        assertEquals("First Name4", personFour.getFirstName());
        assertEquals("Last Name4", personFour.getLastName());
        assertEquals("Address4", personFour.getAddress());
        assertEquals("Masculino", personFour.getGender());
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        result.getLinks().stream()
            .anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("GET")
        );

        result.getLinks().stream()
            .anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("GET"
        ));

        result.getLinks().stream()
            .anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("POST")
        );

        result.getLinks().stream()
            .anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("api/person/v1")
                && link.getType().equals("PUT")
        );

        result.getLinks().stream()
            .anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("api/person/v1/1")
                && link.getType().equals("DELETE")
        );

        assertEquals("First Name1", person.getFirstName());
        assertEquals("Last Name1", person.getLastName());
        assertEquals("Address1", person.getAddress());
        assertEquals("Feminino", person.getGender());
    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                );

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET"
                ));

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                );

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("PUT")
                );

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/person/v1/1")
                        && link.getType().equals("DELETE")
                );

        assertEquals("First Name1", person.getFirstName());
        assertEquals("Last Name1", person.getLastName());
        assertEquals("Address1", person.getAddress());
        assertEquals("Feminino", person.getGender());
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                );

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET"
                ));

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                );

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("PUT")
                );

        result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/person/v1/1")
                        && link.getType().equals("DELETE")
                );

        assertEquals("First Name1", person.getFirstName());
        assertEquals("Last Name1", person.getLastName());
        assertEquals("Address1", person.getAddress());
        assertEquals("Feminino", person.getGender());
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }
}
