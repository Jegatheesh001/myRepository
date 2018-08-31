package com.warehouse.boot.test.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.boot.test.reservation.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

}
