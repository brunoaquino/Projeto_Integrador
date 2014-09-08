package br.com.ECommerce.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
//@SequenceGenerator(name = "seq_pessoa", sequenceName = "pessoa_id_pessoa_seq", allocationSize = 1, initialValue = 1)
// @GeneratedValue(strategy = GenerationType.IDENTITY)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
//	@GeneratedValue(generator = "seq_pessoa")
	private int id;

	@Column(name = "nome")
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
