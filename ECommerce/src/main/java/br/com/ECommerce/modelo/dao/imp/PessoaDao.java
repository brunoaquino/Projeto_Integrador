package br.com.ECommerce.modelo.dao.imp;

import org.hibernate.Session;

import br.com.ECommerce.modelo.dao.HibernateDAO;
import br.com.ECommerce.modelo.entidade.Pessoa;

public class PessoaDao extends HibernateDAO<Pessoa> {

	private static final long serialVersionUID = -2575035353490647340L;

	public PessoaDao(Class<Pessoa> classe, Session session) {
		super(classe, session);
	}

}
