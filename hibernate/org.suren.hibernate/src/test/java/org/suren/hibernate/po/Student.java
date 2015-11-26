/**
* Copyright © 1998-2015, surenpi.com. All Rights Reserved.
*/
package org.suren.hibernate.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.suren.hibernate.annotation.Encrypt;

/**
 * @author suren
 * @since jdk1.6
 * 2015年11月23日
 */
@Entity
public class Student {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String id;
	@Encrypt
	private String name;
	private String room;
	private String location;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoom()
	{
		return room;
	}
	public void setRoom(String room)
	{
		this.room = room;
	}
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	@Override
	public String toString()
	{
		return "Student [id=" + id + ", name=" + name + ", room=" + room
				+ ", location=" + location + "]";
	}
}
