package com.warehouse.boot.test.reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.boot.test.reservation.entity.Room;

@Service
public class RoomService {

	@Autowired
	private RoomRepository repository;

	public List<Room> getAllRooms() {
		Iterable<Room> values = repository.findAll();
		List<Room> rooms = new ArrayList<>();
		if (values != null)
			values.forEach(room -> rooms.add(room));
		return rooms;
	}

}
