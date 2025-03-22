package br.com.joaojunio.project_one.repositories;

import br.com.joaojunio.project_one.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
