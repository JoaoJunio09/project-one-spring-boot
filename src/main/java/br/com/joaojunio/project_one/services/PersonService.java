package br.com.joaojunio.project_one.services;

import br.com.joaojunio.project_one.controllers.PersonController;
import br.com.joaojunio.project_one.data.dto.PersonDTO;
import br.com.joaojunio.project_one.exceptions.ResourceNotFoundException;
import br.com.joaojunio.project_one.mapper.ObjectMapper;
import br.com.joaojunio.project_one.model.Person;
import br.com.joaojunio.project_one.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {

        logger.info("Person Finds All!");

        var list = repository.findAll();
        var listDto = ObjectMapper.parseListObjects(list, PersonDTO.class);
        listDto.forEach(this::addHeteoasLinks);
        return listDto;
    }

    public PersonDTO findById(Long id) {

        logger.info("Person Find by ID!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found this ID : " + id));
        var dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        addHeteoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO personDTO) {

        logger.info("Person Create!");

        var entity = ObjectMapper.parseObject(personDTO, Person.class);
        var dto = ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
        addHeteoasLinks(dto);
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
        addHeteoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Person Delete!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found this ID : " + id));
        repository.delete(entity);
    }

    private void addHeteoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
