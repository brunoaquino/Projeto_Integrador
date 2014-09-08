package br.com.ECommerce.modelo.dao.imp;

import org.hibernate.Session;

import br.com.ECommerce.modelo.dao.HibernateDAO;
import br.com.ECommerce.modelo.entidade.Livro;

public class LivroDao extends HibernateDAO<Livro> {

	private static final long serialVersionUID = -2575035353490647340L;

	public LivroDao(Class<Livro> classe, Session session) {
		super(classe, session);
	}

}