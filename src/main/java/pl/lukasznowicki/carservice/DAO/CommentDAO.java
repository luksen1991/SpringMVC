package pl.lukasznowicki.carservice.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveTypeAwareAssigner;

import javax.persistence.Id;

@Entity
@Table(name="Comments")
public class CommentDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long Id;
	@Column(name="Tittle")
	private String tittle;
	@Column(name="Content")
	private String content;
	
	
	public long getId() {
		return Id;
	}
	public String getTittle() {
		return tittle;
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
