package com.warehouse.boot.test.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.boot.test.reservation.entity.Room;

@RestController
@RequestMapping(value = "/api/rooms")
public class RoomAction {

	@Autowired
	private RoomService roomService;

	@RequestMapping(method=RequestMethod.GET)
	public List<Room> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		return rooms;
	}

}
