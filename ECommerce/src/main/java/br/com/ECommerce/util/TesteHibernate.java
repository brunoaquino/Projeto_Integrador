package br.com.ECommerce.util;

import org.hibernate.Session;

import br.com.ECommerce.modelo.dao.imp.PessoaDao;
import br.com.ECommerce.modelo.entidade.Pessoa;

public class TesteHibernate {

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Bruno carvalho");

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		sessao.getTransaction().begin();

		PessoaDao dao = new PessoaDao(Pessoa.class, sessao);
		dao.save(pessoa);
		sessao.getTransaction().commit();
		for (Pessoa pesso : dao.getEntities()) {
			System.out.println(pesso.getId() + pesso.getNome());
		}
		sessao.close();
	}

}
