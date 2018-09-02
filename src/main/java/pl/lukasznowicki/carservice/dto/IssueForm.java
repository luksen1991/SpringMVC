package pl.lukasznowicki.carservice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Issues")
public class IssueForm {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Size(min = 1, max = 50)
	@Column(name="Title")
	private String tittle;

	@Size(min = 20, max = 200)
	@Column(name="Content")
	private String content;

	public String getTittle() {
		return tittle;
	}

	public long getId() {
		return id;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
