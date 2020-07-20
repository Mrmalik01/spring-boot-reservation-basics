package com.khizar.fil.demo.data.repository;
import com.khizar.fil.demo.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Long represents the ID
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
