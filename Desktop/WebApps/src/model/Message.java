package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
//import java.util.List;
//import java.util.Set;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity
@Table(name="messages")
@NamedQueries(value = { @NamedQuery(name = "Message.findAll", query = "SELECT ms FROM Message ms"),
						@NamedQuery(name = "Message.getAllUsersReceivedMessages", query = "SELECT ms FROM Message ms, ms.receiver receiver WHERE ms.id= ?1 AND receiver.id= ?2"),
						@NamedQuery(name = "Message.getAllUsersSendedMessages", query = "SELECT ms FROM Message ms, ms.receiver sender WHERE ms.id= ?1 AND sender.id= ?2"),
						@NamedQuery(name = "Message.editMessage", query = "UPDATE Message ms SET ms.text = ?1 , ms.subject=?2 WHERE ms.id=?3 "),
						@NamedQuery(name = "Message.searchById", query = "SELECT ms FROM Message ms WHERE ms.id = ?1"),
						@NamedQuery(name = "Message.delete", query = "DELETE FROM Message ms WHERE ms.id= ?1"),
						})
public class Message implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;
	
	private String text;

	private String subject;
	
	private Boolean is_read;
	
//	 bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	private User sender ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User receiver ;
	
	public Message() {
		
	}
	
//	Id's Methods
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
//	Text's Methods
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
//	Subject's Methods
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String message_subject) {
		this.subject = message_subject;
	}
	
//	DateCreated Methods
	public Date getDateCreated() {
		return this.date_created;
	}
	
	public void setDateCreated(Date creation_date) {
		this.date_updated = creation_date;
	}
//	IsRead Methods
	public Boolean getIsRead(){
		return this.is_read;
	}
	
	public void setIsRead(Boolean state){
		this.is_read = state;
	}
	
//	DateUpdated Methods
	public Date getDateUpdated() {
		return this.date_updated;
	}

	public void setDateUpdated(Date update_date) {
		this.date_updated = update_date;
	}
//  Sender Methods	
	public User getSender(){
		return this.sender;
	}
	public void setSender(User user) {
		this.sender = user;
		
	}
//  Receiver Methods
	public User getReceiver(){
		return this.receiver;
	}
	public void setReceiver(User user) {
		this.receiver = user;
		
	}
   
}
