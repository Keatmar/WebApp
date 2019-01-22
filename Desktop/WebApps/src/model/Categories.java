package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="categories")
@NamedQueries(value = { @NamedQuery(name = "Categories.getFirstGrade", query = "SELECT c FROM Categories c WHERE c.grade= ?1"),
		@NamedQuery(name = "Categories.getNextGrade", query = "SELECT c FROM Categories c WHERE c.grade= ?1 AND c.parent= ?2"),
		@NamedQuery(name = "Categories.find", query = "SELECT c FROM Categories c WHERE c.name= ?1") })
public class Categories implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int grade;

	private String name;

	private String parent;

	public Categories() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

}