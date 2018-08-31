package com.warehouse.boot.test.reservation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {

	@Id
	@Column(name = "room_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roomId;

	@Column(name = "room_name")
	private String roomName;

	@Column(name = "room_number")
	private String roomNumber;

	@Column(name = "bed_info")
	private String bedInfo;

	/** Getters and Setters */

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getBedInfo() {
		return bedInfo;
	}

	public void setBedInfo(String bedInfo) {
		this.bedInfo = bedInfo;
	}

	/** Constructors */
	
	public Room() {
		super();
	}
	
	public Room(Integer roomId, String roomName, String roomNumber, String bedInfo) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
		this.bedInfo = bedInfo;
	}

}
