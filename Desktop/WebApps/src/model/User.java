package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQueries(value = { @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.username= ?1 AND u.password= ?2"),
		@NamedQuery(name = "User.searchById", query = "SELECT u FROM User u WHERE u.id = ?1"),
		@NamedQuery(name = "User.confirm", query = "UPDATE User u SET u.active= TRUE WHERE u.id= ?1"),
		@NamedQuery(name = "User.edit", query = "UPDATE User u SET u.firstName= ?1,"
				+ " u.lastName= ?2, u.username= ?3, u.password= ?4,"
				+ "u.phone= ?5, u.address= ?6, u.city= ?7, u.zip= ?8," + "u.afm= ?9, u.email= ?10,"
				+ "u.new_inbox_messages = ?12, u.new_sended_messages = ?13" + " WHERE u.id= ?11"),
		@NamedQuery(name = "User.delete", query = "DELETE FROM User u WHERE u.id= ?1"),
		@NamedQuery(name = "User.getUserByEmail", query = "SELECT u FROM  User u  WHERE  u.email = ?1 OR u.username= ?1") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Boolean active;

	private String address;

	private int afm;

	private String city;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String password;

	private String phone;

	@Column(name = "ratting_bidder")
	private int rattingBidder;

	@Column(name = "ratting_seller")
	private int rattingSeller;

	private String role;

	private String username;

	private int zip;

	private int new_inbox_messages;

	private int new_sended_messages;

	// bi-directional many-to-one association to Auction
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Auction> auctions;

	// bi-directional many-to-one association to Bid
	@OneToMany(mappedBy = "user")
	private List<Bid> bids;

	// bi-directional many-to-one association to Message
	@OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST)
	private List<Message> sended_messages;

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.PERSIST)
	private List<Message> received_messages;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAfm() {
		return this.afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRattingBidder() {
		return this.rattingBidder;
	}

	public void setRattingBidder(int rattingBidder) {
		this.rattingBidder = rattingBidder;
	}

	public int getRattingSeller() {
		return this.rattingSeller;
	}

	public void setRattingSeller(int rattingSeller) {
		this.rattingSeller = rattingSeller;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getZip() {
		return this.zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	// NewInboxMessages Methods
	public int getNewInboxMessages() {
		return this.new_inbox_messages;
	}

	public void setNewInboxMessages() {
		this.new_inbox_messages = +1;
	}

	public void setNewInboxMessagesToZero() {
		this.new_inbox_messages = 0;
	}

	public void removeNewInboxMessages() {
		this.new_inbox_messages = -1;
	}

	// NewSendedMessages Methods
	public int getNewSendedMessages() {
		return this.new_sended_messages;
	}

	public void setNewSendedMessages() {
		this.new_sended_messages = +1;
	}

	public void setNewSendedMessagesToZero() {
		this.new_sended_messages = 0;
	}

	public void removeNewSendedMessages() {
		this.new_sended_messages = -1;
	}

	// Auction methods
	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public Auction addAuction(Auction auction) {
		getAuctions().add(auction);
		auction.setUser(this);

		return auction;
	}

	public Auction removeAuction(Auction auction) {
		getAuctions().remove(auction);
		auction.setUser(null);

		return auction;
	}

	// Bid methods
	public List<Bid> getBids() {
		return this.bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setUser(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setUser(null);

		return bid;
	}

	// Sended Messages methods
	public List<Message> getSendedMessages() {
		return this.sended_messages;
	}

	public void setSendedMessage(List<Message> message_list) {
		this.sended_messages = message_list;
	}

	public Message addSendedMessage(Message message) {
		getSendedMessages().add(message);
		message.setSender(this);

		return message;

	}

	public Message removeSendedMessage(Message message) {
		getSendedMessages().remove(message);
		message.setSender(null);

		return message;
	}

	// Received Messages methods
	public List<Message> getReceivedMessages() {
		return this.received_messages;
	}

	public void setReceivedMessage(List<Message> message_list) {
		this.received_messages = message_list;
	}

	public Message addReceivedMessage(Message message) {
		getReceivedMessages().add(message);
		message.setReceiver(this);

		return message;

	}

	public Message removeReceivedMessage(Message message) {
		getReceivedMessages().remove(message);
		message.setReceiver(null);

		return message;
	}

}