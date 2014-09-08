package br.com.ECommerce.util;

import javax.persistence.Table;

public abstract class CoreEntity {

	protected Long id;

	public abstract Long getId();

	public abstract void setId(Long id);

	// VERIFICA SE O ID DO OBJETO E VALIDO
	public boolean isIdValido() {
		return getId() != null && !getId().equals(Long.valueOf(0));
	}

	public String getIdentificador() throws RuntimeException {
		if (this.getId() == null) {
			throw new RuntimeException(this.getClass().getCanonicalName()
					+ " não possui atributo ID preenchido");
		}

		String key = this.getClass().getName();
		if (key.indexOf('$') != -1) {
			key = key.substring(0, key.indexOf('$')) + "-" + this.getId();
		} else {
			key = key + "-" + this.getId();
		}
		return key;
	}

	/*public String getNomeClasse() throws DaoException {
		String key = this.getClass().getName();
		if (key.indexOf('$') != -1) {
			key = key.substring(0, key.indexOf('$'));
		}
		return key;
	}*/

	public String getHibernateTableName() {
		Table tb = this.getClass().getAnnotation(Table.class);
		if (tb == null) {
			tb = this.getClass().getSuperclass().getAnnotation(Table.class);
		}
		return tb.name();
	}
}