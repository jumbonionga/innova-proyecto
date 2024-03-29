package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBStudentRepository;
import dev.fernando.proyecto.persistence.mongodb.document.StudentMongoDB;
import dev.fernando.proyecto.interfaces.IStudentGenericService;
import dev.fernando.proyecto.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class MongoDBStudentService implements IStudentGenericService<StudentDTO,String> {
    
    final private IMongoDBStudentRepository repository;
    
    @Autowired
    public MongoDBStudentService(IMongoDBStudentRepository repository) {
        this.repository = repository;
    }
    @Override
    public StudentDTO save(StudentDTO entity) {
        StudentMongoDB newStudent = dtoConverter(entity);
        StudentMongoDB result = repository.save(newStudent);
        return entityConverter(result);
    }
    
    @Override
    public Optional<StudentDTO> findById(String id) {
        Optional<StudentMongoDB> student = repository.findById(id);
        if(student.isPresent()) {
            return Optional.of(entityConverter(student.get()));
        }
        return Optional.empty();
    }
    
    @Override
    public List<StudentDTO> findAll() {
        List<StudentMongoDB> result = repository.findAll();
        List<StudentDTO> resultDto = new ArrayList<>();
        for (StudentMongoDB student : result) {
            StudentDTO dtoEntry = entityConverter(student);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(StudentDTO entity) {
        repository.delete(dtoConverter(entity));
    }
    
    private StudentMongoDB dtoConverter(StudentDTO dto) {
        if(dto.getId() != null) {
            return new StudentMongoDB(
                    (String) dto.getId(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getDob().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                    dto.getGender(),
                    dto.getPersonalEmail(),
                    dto.getContactPhone()
            );
        } else {
            return new StudentMongoDB(
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getDob().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                    dto.getGender(),
                    dto.getPersonalEmail(),
                    dto.getContactPhone()
            );
        }
    }
    
    private StudentDTO entityConverter(StudentMongoDB entity) {
        return new StudentDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                entity.getGender(),
                entity.getPersonalEmail(),
                entity.getContactPhone()
        );
    }
}
