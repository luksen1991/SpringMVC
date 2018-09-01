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
	private long Id;
	@Column(name="Tittle",length=30,nullable=false)
	private String tittle;
	@Column(name="Content",length=255,nullable=false)
	private String content;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
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
