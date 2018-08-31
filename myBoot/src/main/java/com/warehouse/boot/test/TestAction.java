package com.warehouse.boot.test;

import java.io.FileWriter;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.boot.test.reservation.RoomService;
import com.warehouse.boot.test.reservation.entity.Room;

@RestController
@RequestMapping(value = "/test")
public class TestAction {

	@Autowired
	private RoomService roomService;

	@RequestMapping(method = RequestMethod.GET, value = "/generateCSVFile")
	public void generateCSVFile() {
		String COMMA_DELIMITER = ",";
		String NEW_LINE_SEPARATOR = "\n";
		StringJoiner fileHeader = new StringJoiner(COMMA_DELIMITER);
		fileHeader.add("room_id");
		fileHeader.add("room_name");
		fileHeader.add("room_number");
		fileHeader.add("bed_info");

		List<Room> rooms = roomService.getAllRooms();
		String fileName = "MyCSVFile.csv";
		StringBuilder csvContent = new StringBuilder();
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			// Write the CSV file header
			csvContent.append(fileHeader.toString());
			// Add a new line separator after the header
			csvContent.append(NEW_LINE_SEPARATOR);
			rooms.forEach(room -> {
				csvContent.append(room.getRoomId());
				csvContent.append(COMMA_DELIMITER);
				csvContent.append(room.getRoomName());
				csvContent.append(COMMA_DELIMITER);
				csvContent.append(room.getRoomNumber());
				csvContent.append(COMMA_DELIMITER);
				csvContent.append(room.getBedInfo());
				csvContent.append(NEW_LINE_SEPARATOR);
			});
			fileWriter.append(csvContent.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
