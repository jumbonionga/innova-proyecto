package dev.fernando.proyecto.services;

import dev.fernando.proyecto.interfaces.IRoomRepository;
import dev.fernando.proyecto.interfaces.IRoomService;
import dev.fernando.proyecto.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    
    private final IRoomRepository roomRepository;
    
    @Autowired
    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    
    @Override
    public Room save(Room entity) {
        return roomRepository.save(entity);
    }
    
    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
    
    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
    
    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
    
    @Override
    public void delete(Room entity) {
        roomRepository.delete(entity);
    }
}