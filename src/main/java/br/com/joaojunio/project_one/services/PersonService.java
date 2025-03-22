package br.com.joaojunio.project_one.services;

import br.com.joaojunio.project_one.data.dto.PersonDTO;
import br.com.joaojunio.project_one.exceptions.ResourceNotFoundException;
import br.com.joaojunio.project_one.mapper.ObjectMapper;
import br.com.joaojunio.project_one.model.Person;
import br.com.joaojunio.project_one.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonDTO findById(Long id) {

        logger.info("Person Find by ID!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found this ID : " + id));
        var dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        return dto;
    }

    public List<PersonDTO> findAll() {

        logger.info("Person Finds All!");

        var list = repository.findAll();
        var listDto = ObjectMapper.parseListObjects(list, PersonDTO.class);
        return listDto;
    }

    public PersonDTO create(PersonDTO personDTO) {

        logger.info("Person Create!");

        var entity = ObjectMapper.parseObject(personDTO, Person.class);
        var dto = ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }

    public PersonDTO update(PersonDTO personDTO) {

        logger.info("Person Update!");

        Person entity = repository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found this ID : " + personDTO.getId()));
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        var dto = ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Person Delete!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found this ID : " + id));
        repository.delete(entity);
    }
}
