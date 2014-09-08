package br.com.ECommerce.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Texto {

	public static final String CRLF = "\r\n";

	private static final String Substituir(String Valor, String Procurar,
			String Novo) {
		String resp = "";
		int pos = 1;
		resp = Valor;
		pos = PosicaoString(resp, Procurar, pos, true);
		while (pos > 0) {
			resp = resp.substring(0, pos - 1) + Novo
					+ resp.substring(pos + Procurar.length() - 1);
			pos = PosicaoString(resp, Procurar, pos + Novo.length(), true);
		}
		return resp;
	}

	public static final String SimNao(Boolean valor) {
		return valor ? "SIM" : "NÃO";
	}

	public static final Boolean TrueFalse(char valor) {
		return String.valueOf(valor).toUpperCase().equals("S") ? true : false;
	}

	public static final String substituir(String Valor, String Procurar,
			String Novo) {
		return Substituir(Valor, Procurar, Novo);
	}

	public static final StringBuffer substituir(StringBuffer Valor,
			String Procurar, String Novo) {
		String str = Substituir(Valor.toString(), Procurar, Novo);
		return new StringBuffer(str);
	}

	public static List<String> split(String s, char c) {
		String valor = s;
		List<String> resultado = new ArrayList<String>();
		if (!valor.isEmpty()) {
			while (valor.indexOf(c) != -1) {
				if (!valor.substring(0, valor.indexOf(c)).equals(""))
					resultado.add(valor.substring(0, valor.indexOf(c)).trim());
				valor = valor.substring(valor.indexOf(c) + 1, valor.length());
			}
			resultado.add(valor.trim());
		}
		return resultado;
	}

	/**
	 * Converte a lista de String em uma String.
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	public static String listToString(List<String> s, char c) {
		StringBuffer retorno = new StringBuffer();
		int count = s.size();
		for (String str : s) {
			if (count > 1) {
				retorno.append(str + c + " ");
			} else {
				retorno.append(str);
			}
			count--;
		}
		return retorno.toString();
	}

	/**
	 * Converte a LIST<STRING> em texto de tamanho máximo previamente definido.
	 * 
	 * @param s
	 * @param c
	 * @param maxSize
	 * @return
	 */
	public static String listToString(List<String> s, char c, int maxSize) {
		StringBuffer retorno = new StringBuffer();
		int count = s.size();
		for (String str : s) {
			if (count > 1) {
				retorno.append(str + c + " ");
			} else {
				retorno.append(str);
			}
			count--;
		}
		if (retorno.length() > maxSize) {
			return retorno.substring(0, maxSize) + " ... ";
		} else {
			return retorno.toString();
		}
	}

	public static String getParteString(String texto, int limite) {
		if (texto == null) {
			return null;
		}
		if (texto.length() > limite) {
			return texto.substring(0, limite);
		} else {
			return texto;
		}
	}

	/**
	 * Retorna a String contida no Map
	 * 
	 * @param key
	 * @param parametros
	 * @return
	 */
	public static String getStringValueInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}
		return parametros.get(key).toString().trim();
	}

	/**
	 * Recupera um valor do tipo String ou Vazio caso for Nulo.
	 * 
	 * @param valor
	 * @return
	 */
	public static String getValor(Object valor) {
		if (valor == null) {
			return "";
		}
		return valor.toString().trim();
	}

	/**
	 * Recupera o valor STRING ou dispara um exception caso NULL ou Vazio.
	 * 
	 * @param valor
	 * @return
	 */
	public static String getValorObrigatorio(Object valor) {
		if (valor == null || valor.toString().isEmpty()) {
			throw new RuntimeException("Campo obrigatório não informado.");
		}
		return valor.toString();
	}

	/**
	 * Retorna verdadeiro se a String for diferente de NULL e não for vazia.
	 * 
	 * @param Valor
	 * @return
	 */
	public static final Boolean isEmpty(String Valor) {
		return Valor == null || Valor.isEmpty();
	}

	private static final int PosicaoString(String Origem, String Procurar,
			int Inicio, boolean SensivelCaso) {
		int i = 0, pos = Procurar.length(), tam = Origem.length();
		String Aux1 = "";
		String AuxOrigem = "";
		String AuxProcurar = "";
		if (SensivelCaso) {
			AuxOrigem = Origem;
			AuxProcurar = Procurar;
		} else {
			AuxOrigem = Origem.toUpperCase();
			AuxProcurar = Procurar.toUpperCase();
		}
		pos += Inicio - 2;
		for (i = Inicio - 1; i < tam && pos < tam && !Aux1.equals(AuxProcurar); i++) {
			pos += 1;
			Aux1 = AuxOrigem.substring(i, pos);
		}
		if (Aux1.equals(AuxProcurar))
			return i;
		else
			return 0;
	}

	/**
	 * Implementa a facilidade Localizar.
	 * 
	 * @return int
	 * @author ciro.macedo
	 */
	public static final int posicaoString(String Origem, String Procurar,
			int Inicio) {
		return PosicaoString(Origem, Procurar, Inicio, false);
	}

	/**
	 * Implementa a facilidade Localizar.
	 * 
	 * @return int
	 * @author ciro.macedo
	 */
	public static final int PosicaoString(String Origem, String Procurar) {
		return PosicaoString(Origem, Procurar, 1, false);
	}

	/**
	 * Define o Alinhamento ao Centro
	 * 
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String alinhaC(String Valor, int Tamanho,
			String Caracter) {
		String AuxValor = Valor;
		for (int i = Valor.length(); i < Tamanho; i++) {
			if ((AuxValor.length() % 2) == 0)
				AuxValor = Caracter + AuxValor;
			else
				AuxValor = AuxValor + Caracter;
		}
		return AuxValor;
	}

	/**
	 * Define o Alinhamento ao Centro
	 * 
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String alinhaC(String Valor, int Tamanho) {
		return alinhaC(Valor, Tamanho, " ");
	}

	/**
	 * Define o Alinhamento ao Centro
	 * 
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String alinhaD(String Valor, int Tamanho,
			String Caracter) {
		String AuxValor = Valor;
		for (int i = Valor.length(); i < Tamanho; i++)
			AuxValor = Caracter + AuxValor;
		return AuxValor;
	}

	/**
	 * Define o alinhamento ao centro.
	 */
	public static final String alinharD(String valor, int tamanho,
			String caracter) {
		if (valor.length() >= tamanho) {
			return valor.substring(0, tamanho);
		} else {
			String AuxValor = valor;
			for (int i = valor.length(); i < tamanho; i++)
				AuxValor = caracter + AuxValor;
			return AuxValor;
		}
	}

	/**
	 * Define o alinhamento a esquerda.
	 */
	public static final String alinharE(String valor, int tamanho,
			String caracter) {
		if (valor == null) {
			return null;
		}
		if (valor.length() >= tamanho) {
			return valor.substring(0, tamanho);
		} else {
			String AuxValor = valor;
			for (int i = valor.length(); i < tamanho; i++)
				AuxValor = AuxValor + caracter;
			return AuxValor;
		}
	}

	/**
	 * Obtem quantas vezes ocorre um String
	 */
	public static final int Ocorrencias(String Valor, String Procurar) {
		int resp = 0;
		int pos = 1;
		pos = PosicaoString(Valor, Procurar, pos, true);
		while (pos > 0) {
			resp++;
			pos = PosicaoString(Valor, Procurar, pos + 1, true);
		}
		return resp;
	}

	/**
	 * Tratamento de Nulo
	 * 
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String trataNull(String strOriginal, String strSubs) {
		if (strOriginal == null || strOriginal.isEmpty()) {
			return strSubs;
		} else {
			return strOriginal;
		}
	}

	/**
	 * Tratamento de Nulo
	 * 
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String trataNull(String strOriginal, String strSubs,
			boolean bRetTrim) {
		if (strOriginal == null) {
			return strSubs;
		} else {
			if (bRetTrim) {
				return strOriginal.trim();
			} else {
				return strOriginal;
			}
		}
	}

	/**
	 * Remove caracteres que não sejam alfanumericos ou espaços
	 * 
	 * @param str
	 *            - String Texto a ser avaliado
	 * @return String - Texto sem caracteres que não sejam alfanumericos ou
	 *         espaços
	 */
	public static final String removerEspacoEmBranco(String str) {
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos

		char[] foncmp = new char[256];
		// matriz de caracteres que armazena o texto original

		char[] fonaux = new char[256];
		// matriz de caracteres que armazena o texto modificado

		int i, j; // contadores
		// caracter: se 1 -> existem, se 0 -> nao existem

		j = 0;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto

		for (i = 0; i < 256; i++)
			foncmp[i] = ' ';
		// branqueia a matriz de caracteres

		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter

			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (((fonaux[i] >= 'A') && (fonaux[i] <= 'Z'))
					|| ((fonaux[i] >= 'a') && (fonaux[i] <= 'z'))
					|| ((fonaux[i] >= '0') && (fonaux[i] <= '9'))
					|| (fonaux[i] == '&') || (fonaux[i] == '_')) {
				foncmp[j] = fonaux[i];
				j++;
			} // if
		} // for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres

		return str.trim();
	} // removeEstranhos

	/**
	 * Remove todos os caracteres numericos presentes na String passada como
	 * parametro.
	 * 
	 * @param str
	 *            - String Texto a ser avaliado
	 * @return String
	 */
	public static final String removerNumeros(String str) {
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos
		char[] foncmp = new char[256];
		// matriz de caracteres que armazena o texto original
		char[] fonaux = new char[256];
		// matriz de caracteres que armazena o texto modificado
		int i, j; // contadores
		// caracter: se 1 -> existem, se 0 -> nao existem
		j = 0;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto
		for (i = 0; i < 256; i++)
			foncmp[i] = ' ';
		// branqueia a matriz de caracteres
		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter
			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (((fonaux[i] >= 'A') && (fonaux[i] <= 'Z'))
					|| ((fonaux[i] >= 'a') && (fonaux[i] <= 'z'))
					|| (fonaux[i] == '&') || (fonaux[i] == '_')) {
				foncmp[j] = fonaux[i];
				j++;
			} // if
		} // for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres
		return str.trim();
	} // removeEstranhos

	/**
	 * Remove todos os caracteres numericos presentes na String passada como
	 * parametro.
	 * 
	 * @param str
	 *            - String Texto a ser avaliado
	 * @return String
	 */
	public static final String removerNumerosManterEspacosContraBarraETraco(
			String str) {
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos
		char[] foncmp = new char[256];
		// matriz de caracteres que armazena o texto original
		char[] fonaux = new char[256];
		// matriz de caracteres que armazena o texto modificado
		int i, j; // contadores
		// caracter: se 1 -> existem, se 0 -> nao existem
		j = 0;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto
		for (i = 0; i < 256; i++)
			foncmp[i] = ' ';
		// branqueia a matriz de caracteres
		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter
			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (((fonaux[i] >= 'A') && (fonaux[i] <= 'Z'))
					|| ((fonaux[i] >= 'a') && (fonaux[i] <= 'z'))
					|| (fonaux[i] == '&') || (fonaux[i] == '_')
					|| fonaux[i] == ' ' || fonaux[i] == '/'
					|| fonaux[i] == '\\' || fonaux[i] == '-') {
				foncmp[j] = fonaux[i];
				j++;
			} // if
		} // for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres
		return str.trim();
	} // removeEstranhos

	/**
	 * Remove todos os caracteres numericos presentes na String passada como
	 * parametro.
	 * 
	 * @param str
	 *            - String Texto a ser avaliado
	 * @return String
	 */
	public static final String removerLetras(String str) {
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos
		char[] foncmp = new char[256];
		// matriz de caracteres que armazena o texto original
		char[] fonaux = new char[256];
		// matriz de caracteres que armazena o texto modificado
		int i, j; // contadores
		// caracter: se 1 -> existem, se 0 -> nao existem
		j = 0;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto
		for (i = 0; i < 256; i++)
			foncmp[i] = ' ';
		// branqueia a matriz de caracteres
		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter
			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (fonaux[i] >= '0' && fonaux[i] <= '9') {
				foncmp[j] = fonaux[i];
				j++;
			} // if
		} // for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres
		return str.trim();
	} // removeEstranhos

	/**
	 * Remove caracteres que não sejam alfanumericos ou espaços
	 * 
	 * @param str
	 *            - String Texto a ser avaliado
	 * @return String - Texto sem caracteres que não sejam alfanumericos ou
	 *         espaços
	 */
	public static final String removeEstranhos(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos

		char[] foncmp = new char[str.length()];
		// matriz de caracteres que armazena o texto original

		char[] fonaux = new char[str.length()];
		// matriz de caracteres que armazena o texto modificado

		int i, j, // contadores
		first; // indica se exitem espacos em branco antes do primeiro
		// caracter: se 1 -> existem, se 0 -> nao existem

		j = 0;
		first = 1;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto

		for (i = 0; i < str.length(); i++)
			foncmp[i] = ' ';
		// branqueia a matriz de caracteres

		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter

			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (((fonaux[i] >= 'A') && (fonaux[i] <= 'Z'))
					|| ((fonaux[i] >= 'a') && (fonaux[i] <= 'z'))
					|| ((fonaux[i] >= '0') && (fonaux[i] <= '9'))
					|| (fonaux[i] == '&') || (fonaux[i] == '_')
					|| ((fonaux[i] == ' ') && first == 0)) {

				foncmp[j] = fonaux[i];
				j++;
				first = 0;

			} // if
		} // for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres
		return str.trim();
	} // removeEstranhos

	/**
	 * Remove caracteres que não sejam alfanumericos ou espaços
	 * 
	 * @param str
	 *            - String Texto a ser avaliado
	 * @return String - Texto sem caracteres que não sejam alfanumericos ou
	 *         espaços
	 */
	public static final String substituirEstranhos(String str, char charSubst) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos

		char[] foncmp = new char[str.length()];
		// matriz de caracteres que armazena o texto original

		char[] fonaux = new char[str.length()];
		// matriz de caracteres que armazena o texto modificado

		int i, j, // contadores
		first; // indica se exitem espacos em branco antes do primeiro
		// caracter: se 1 -> existem, se 0 -> nao existem

		j = 0;
		first = 1;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto

		for (i = 0; i < str.length(); i++)
			foncmp[i] = charSubst;
		// branqueia a matriz de caracteres

		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter

			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (((fonaux[i] >= 'A') && (fonaux[i] <= 'Z'))
					|| ((fonaux[i] >= 'a') && (fonaux[i] <= 'z'))
					|| ((fonaux[i] >= '0') && (fonaux[i] <= '9'))
					|| (fonaux[i] == '&') || (fonaux[i] == '_')
					|| ((fonaux[i] == ' ') && first == 0)) {

				foncmp[j] = fonaux[i];
				j++;
				first = 0;

			} // if
		} // for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres
		return str.trim();
	} // removeEstranhos

	/**
	 * Substitui a ocorrencia de caracteres especiais pelo CHAR passado como
	 * parametro
	 * 
	 * @param str
	 * @param substituir
	 * @return
	 */
	public static final String substituirEstranhosPorString(String str,
			char substituir) {
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos

		char[] foncmp = new char[str.length()];
		// matriz de caracteres que armazena o texto original

		char[] fonaux = new char[str.length()];
		// matriz de caracteres que armazena o texto modificado

		int i, j, // contadores
		first; // indica se exitem espacos em branco antes do primeiro
		// caracter: se 1 -> existem, se 0 -> nao existem

		j = 0;
		first = 1;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto

		for (i = 0; i < str.length(); i++)
			foncmp[i] = ' ';
		// branqueia a matriz de caracteres

		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter

			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (((fonaux[i] >= 'A') && (fonaux[i] <= 'Z'))
					|| ((fonaux[i] >= 'a') && (fonaux[i] <= 'z'))
					|| ((fonaux[i] >= '0') && (fonaux[i] <= '9'))
					|| (fonaux[i] == '&') || (fonaux[i] == '_')
					|| ((fonaux[i] == ' ') && first == 0)) {

				foncmp[j] = fonaux[i];
				j++;
				first = 0;

			} else {
				foncmp[j] = substituir;
				j++;
				first = 0;
			}
		} // for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres
		return str;
	} // removeEstranhos

	/**
	 * Remove o caractere passado como segundo parametro para o método.
	 * 
	 * @param str
	 * @param caractere
	 * @return
	 */
	public static final String removeCaractere(String str, char caractere) {
		String retorno = "";
		for (int i = 1; i <= str.length(); i++) {
			String posicao = str.substring(i - 1, i);
			if (!posicao.equals(String.valueOf(caractere))
					&& !posicao.equals("")) {
				retorno += posicao;
			}
		}
		return retorno.trim();
	}

	/**
	 * Retorna o n� de ocorrencias do caractere na String passada como
	 * parametro.
	 * 
	 * @param str
	 * @param caractere
	 * @return
	 */
	public static final int contarCaractere(String str, char caractere) {
		int retorno = 0;
		for (int i = 1; i <= str.length(); i++) {
			String posicao = str.substring(i - 1, i);
			if (posicao.equals(String.valueOf(caractere))) {
				retorno++;
			}
		}
		return retorno;
	}

	/**
	 * Remove todos os conectores entre as palavras, inclusive letras.
	 * 
	 * @param str
	 *            - String Texto com conectores
	 * @return String - Texto sem conectores
	 */
	public static final String removeConectores(String str) {
		String conectores[] = { "DA", "DE", "DI", "DO", "DU", "SA", "S/A",
				"SA.", "DAS", "DOS", "S.A", "S.A.", "S/A.", "LTDA" };
		boolean conector = false;
		String strAux = "";
		String palavra = "";
		Vector<String> vetStr = strToVector(str);
		for (int i = 0; i < vetStr.size(); i++) {
			palavra = vetStr.elementAt(i).toString();
			if (palavra.length() > 1) {
				/* Se palavra for maior que 01 caracter */
				conector = false;
				for (int j = 0; j < conectores.length; j++) {
					/* Compara conectores */
					if (palavra.equals(conectores[j])) {
						conector = true;
						break;
					}
				}
				if (!conector) { /* Se palavra não é um conector */
					strAux = strAux + " " + palavra;
				}
			}
		}
		return strAux.trim();
	}

	/**
	 * Converte String para Vetor
	 * 
	 * @param str
	 *            - String Texto a ser convertido
	 * @return Vector - Vetor contendo as palavras do texto
	 */
	public static final Vector<String> strToVector(String str) {
		// armazena o texto de um string em um vetor onde
		// cada palavra do texto ocupa uma posicao do vetor

		str = str.trim();

		char[] fonaux = new char[256];
		// matriz de caracteres que armazena o texto completo

		char[] foncmp = new char[256];
		// matriz de caracteres que armazena cada palavra

		Vector<String> component = new Vector<String>();
		// vetor que armazena o texto

		int i, j, // contadores
		pos, // posicao da matriz
		rep, // indica se eh espaco em branco repetido
		first; // indica se eh o primeiro caracter

		first = 1;
		pos = 0;
		rep = 0;

		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto

		for (j = 0; j < 256; j++)
			foncmp[j] = ' ';
		// branqueia matriz de caracteres

		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter

			// se encontrar um espaco e nao for o primeiro caracter,
			// armazena a palavra no vetor
			if ((fonaux[i] == ' ') && (first != 1)) {
				if (rep == 0) {
					component.addElement(String.copyValueOf(foncmp).trim());
					pos = 0;
					rep = 1;
					for (j = 0; j < 256; j++)
						foncmp[j] = ' ';
				} // if
			} // if

			// forma a palavra, letra a letra, antes de envia-la a uma
			// posicao do vetor
			else {
				foncmp[pos] = fonaux[i];
				first = 0;
				pos++;
				rep = 0;
			} // else
		} // for

		if (foncmp[0] != ' ')
			component.addElement(String.copyValueOf(foncmp).trim());

		return component;
	} // strToVector

	/**
	 * Formata texto com caracteres especiais para o formato Xml.
	 * 
	 * @param s
	 *            - String a ser formatado.
	 * @return String - string gerado.
	 */

	public static synchronized String encodeXml(String s) {
		if (s == null)
			return "";
		String encoded_str = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '&')
				encoded_str += "&amp;";
			else if (s.charAt(i) == '\"')
				encoded_str += "&quot;";
			else if (s.charAt(i) == '>')
				encoded_str += "&gt;";
			else if (s.charAt(i) == '<')
				encoded_str += "&lt;";
			else
				encoded_str += s.charAt(i);
		}
		return encoded_str;
	}

	/**
	 * Quebra um texto em String em várias linhas, de acordo com o tamano de
	 * coluna, permitindo limitar o número de linhas, e também especificar uma
	 * linha de preenchimento caso a quantidade não seja atingida.
	 * 
	 * @param str
	 *            - String a ser formatado.
	 * @param lin
	 *            - int quantidade de linhas. Zero para qualquer quantidade.
	 * @param col
	 *            - int quantidade máxima de caracteres por linha.
	 * @param linhaVazia
	 *            - String conteúdo das linhas que não foram preenchidas.
	 * @return ArrayList Linhas geradas.
	 */
	public static List<Map<String, String>> quebrarLinha(String str, int lin,
			int col, String linhaVazia) {

		List<Map<String, String>> objArray = new ArrayList<Map<String, String>>();
		Map<String, String> linha;

		int ini = 0;
		int fim = 0;
		int l = 0;
		int pos = 0;
		for (; pos >= 0;) {
			pos = str.indexOf(CRLF, ini);
			if (pos < 0)
				fim = str.length() - 1;
			else
				fim = pos - 1;

			int io = ini;
			int fo = fim - 1;
			for (; fo != fim;) {
				fo = io + col - 1;
				if (fo > fim) { // ultima vez
					fo = fim;
				}
				if (fo >= io) {
					l += 1;

					if ((l > lin) && (lin > 0)) { // limite de linhas, sair
						fo = fim;
						pos = -1;
					} else {
						linha = new HashMap<String, String>();
						linha.put("linha",
								Texto.encodeXml(str.substring(io, fo + 1)));
						objArray.add(linha);
					}
				}
				io = fo + 1;
			}
			ini = pos + 2;
		}

		for (; l < lin; l++) { // completar linhas restantes
			linha = new HashMap<String, String>();
			linha.put("linha", linhaVazia);
			objArray.add(linha);
		}

		return objArray;

	}

	/**
	 * Remove a formatação com caracteres que não sejam alfanuméricos.
	 * 
	 * @param valor
	 *            String
	 * @return String
	 */
	public static String removerFormatacao(String valor) {
		return Texto.tratarCampoNulo(valor, "").replaceAll("[^\\d]", "");
	}

	/**
	 * Trata o valor nulo.
	 * 
	 * @param valor
	 *            String
	 * @param valorPadrao
	 *            String
	 * @return String
	 */
	public static String tratarCampoNulo(String valor, String valorPadrao) {
		return valor == null || valor.equals("null") || valor.equals("") ? valorPadrao
				: valor.trim();
	}

	/**
	 * Verifica se todos os caracteres passados no parâmetros "str" são letras
	 * de "A" a "Z" ou "a" a "z".
	 * 
	 * @param valor
	 *            String
	 * @return boolean
	 * @author Diego Miranda
	 */
	public static boolean eLetra(String valor) {
		for (int i = 0; i <= valor.length() - 1; i++) {
			// Transforma sempre em mai�scula primeiro
			int c = valor.toUpperCase().charAt(i);
			// Se não estiver entre "A" e "Z"
			if (c < 65 || c > 90) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Formata String conforme parâmetros passado pelo pMask. apenas o caractere
	 * # será substitu�do pelo texto original
	 * 
	 * Ex.: #####-### => 74000-000
	 * 
	 * @param pMask
	 *            - String
	 * @param pValue
	 *            - String
	 * @return String
	 */
	public static String formataMascara(String pMask, String pValue) {
		String retorno = new String();
		// Formata valor com base no marametro pMask
		for (int i = 0; i < pValue.length(); i++) {
			pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
		}
		retorno = pMask.replaceAll("#", "");

		// Caso o valor seja menor que a máscara, retorna o conteudo original
		if (retorno.length() < pMask.length())
			retorno = pValue;

		return retorno;
	}

	/**
	 * Este método recebe um Long e o converte para uma String com máscara de
	 * CPF
	 * 
	 * @param obj
	 *            - Object
	 * @return String - CPF convertido
	 */
	public static String formatarCPF(Object obj) {
		if (obj == null || obj.toString().isEmpty()) {
			return null;
		}
		String CPF = "";
		if (obj != null && obj instanceof Long) {
			Long cpfLongType = (Long) obj;
			CPF = cpfLongType.toString();
		} else if (obj != null && obj instanceof String) {
			CPF = (String) obj;
		}
		if (CPF != null && CPF.length() < 11) {
			// Ex.: Este CPF 00176881166 no banco é assim: 176881166
			int comprimento = CPF.length();
			int complemento = 11 - comprimento;
			String digitosZero = "";
			for (int i = 0; i < complemento; i++) {
				digitosZero = digitosZero + "0";
			}
			CPF = digitosZero + CPF;
		}
		CPF = Texto.formataMascara("###.###.###-##", CPF);
		return CPF;
	}

	/**
	 * Formata Cartão de Credito.
	 * 
	 * @param obj
	 * @return
	 */
	public static String formatarCartaoCredito(Object obj) {
		if (obj == null || obj.toString().isEmpty()) {
			return null;
		}
		String CARTAO_CREDITO = "";
		if (obj != null && obj instanceof Long) {
			Long cpfLongType = (Long) obj;
			CARTAO_CREDITO = cpfLongType.toString();
		} else if (obj != null && obj instanceof String) {
			CARTAO_CREDITO = (String) obj;
		}
		if (CARTAO_CREDITO != null && CARTAO_CREDITO.length() < 11) {
			// Ex.: Este CPF 00176881166 no banco é assim: 176881166
			int comprimento = CARTAO_CREDITO.length();
			int complemento = 11 - comprimento;
			String digitosZero = "";
			for (int i = 0; i < complemento; i++) {
				digitosZero = digitosZero + "0";
			}
			CARTAO_CREDITO = digitosZero + CARTAO_CREDITO;
		}
		CARTAO_CREDITO = Texto.formataMascara("####.####.####.####",
				CARTAO_CREDITO);
		return CARTAO_CREDITO;
	}

	public static String formatarCNPJ(Object obj) {
		if (obj == null || obj.toString().isEmpty()) {
			return null;
		}
		String CNPJ = "";
		if (obj != null && obj instanceof Long) {
			Long cpfLongType = (Long) obj;
			CNPJ = cpfLongType.toString();
		} else if (obj != null && obj instanceof String) {
			CNPJ = (String) obj;
		}
		if (CNPJ != null && CNPJ.length() < 14) {
			// Ex.: Este CNPJ 00176881166 no banco é assim: 176881166
			int comprimento = CNPJ.length();
			int complemento = 14 - comprimento;
			String digitosZero = "";
			for (int i = 0; i < complemento; i++) {
				digitosZero = digitosZero + "0";
			}
			CNPJ = digitosZero + CNPJ;
		}
		CNPJ = Texto.formataMascara("##.###.###/####-##", CNPJ);
		return CNPJ;
	}

	/**
	 * Formata o registro de CEP
	 * 
	 * @param obj
	 * @return
	 */
	public static String formatarCEP(Object obj) {
		if (obj == null || obj.toString().isEmpty()) {
			return null;
		}
		String CEP = "";
		if (obj != null && obj instanceof Long) {
			Long cpfLongType = (Long) obj;
			CEP = cpfLongType.toString();
		} else if (obj != null && obj instanceof String) {
			CEP = (String) obj;
		}
		CEP = removeEstranhos(CEP);
		CEP = Texto.formataMascara("##.###-###", CEP);
		return CEP;
	}

	/**
	 * Formata o registro de PLACA
	 * 
	 * @param obj
	 * @return
	 */
	public static String formatarPLACA(Object obj) {
		if (obj == null || obj.toString().isEmpty()) {
			return null;
		}
		String PLACA = "";
		if (obj != null && obj instanceof Long) {
			Long cpfLongType = (Long) obj;
			PLACA = cpfLongType.toString();
		} else if (obj != null && obj instanceof String) {
			PLACA = (String) obj;
		}
		PLACA = removeEstranhos(PLACA);
		PLACA = Texto.formataMascara("###-####", PLACA);
		return PLACA;
	}

	/**
	 * Formata o registro de CEP
	 * 
	 * @param obj
	 * @return
	 */
	public static String formatarTELEFONE(Object obj) {
		if (obj == null || obj.toString().isEmpty()) {
			return null;
		}
		String FONE = removeEstranhos(obj.toString());
		FONE = Texto.formataMascara("(##)####-####",
				concatenaZeros(FONE.trim(), 10));
		return FONE;
	}

	/**
	 * Concatena Zeros à String
	 * 
	 * @param nm
	 * @param qtd
	 * @return
	 */
	public static String concatenaZeros(String nm, int qtd) {
		// confirmar para vê se não vêm nulo
		nm = stringOk(nm) ? nm : "";

		while (nm.trim().length() < qtd) {
			nm = "0" + nm;
		}
		return nm.trim();
	}

	/**
	 * Concatena N zeros até a Integer atingir o tamanho especificado no
	 * argumento do método.
	 * 
	 * @param nm
	 * @param qtd
	 * @return
	 */
	public static String concatenaZeros(Number nm, Integer qtd) {
		if (nm == null) {
			if (nm instanceof Integer) {
				nm = new Integer(0);
			} else if (nm instanceof Double) {
				nm = new Double(0);
			} else if (nm instanceof Long) {
				nm = new Long(0);
			}
		}
		return concatenaZeros(String.valueOf(nm), qtd);
	}

	/**
	 * Concatena N zeros até a String atingir o tamanho especificado no
	 * argumento do método.
	 * 
	 * @param nm
	 * @param qtd
	 * @return
	 */
	public static String concatenaEspacosEmBranco(String nm, int qtd) {
		if (nm == null) {
			nm = new String("");
		}
		if ((nm.trim().length() > qtd)) {
			return nm.substring(0, qtd);
		}
		while (nm.length() < qtd) {
			nm = nm + " ";
		}
		return nm;
	}

	/**
	 * Concatena caracteres
	 * 
	 * @param nm
	 * @param qtd
	 * @param caractere
	 * @return
	 */
	public static String concatenaCaractere(String nm, int qtd, char caractere) {
		if (nm == null) {
			nm = new String("");
		}
		if ((nm.trim().length() > qtd)) {
			return nm.substring(0, qtd);
		}
		while (nm.length() < qtd) {
			nm = nm + caractere;
		}
		return nm;
	}

	/**
	 * Verifica se variável não está nula ou vazia.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean stringOk(String value) {
		return value != null && !value.isEmpty();
	}

	/**
	 * Retira os acentos de um nome e o converte para maiúsculo.
	 */
	public static final String retirarAcentos(String texto) {
		if (texto == null || texto.isEmpty()) {
			return null;
		}
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[^\\p{ASCII}]", "");
		return texto;
	}

	/**
	 * Ordena a lista de Strings
	 * 
	 * @param elementos
	 * @return
	 */
	public static final List<String> ordenar(Collection<String> elementos) {
		List<String> itens = new ArrayList<String>(elementos);
		String vetor[] = new String[elementos.size()];
		for (int i = 0; i < itens.size(); i++) {
			vetor[i] = itens.get(i);
		}
		Arrays.sort(vetor);
		// gera o objeto de retorono
		itens = new ArrayList<String>();
		for (int i = 0; i < vetor.length; i++) {
			itens.add(vetor[i]);
		}
		return itens;
	}

	public static byte[] getBytes(String fileName) throws IOException {
		return getBytes(new File(fileName));
	}

	public static byte[] getBytes(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			throw new IOException("Arquivo E muito grande");
		}

		byte[] bytes = new byte[(int) length];
		int tamanho = 0;
		int leitura = 0;
		while (tamanho < bytes.length
				&& (leitura = inputStream.read(bytes, tamanho, bytes.length
						- tamanho)) >= 0) {
			tamanho += leitura;
		}
		if (tamanho < bytes.length) {
			throw new IOException("Falaha na leitura do arquivo"
					+ file.getName());
		}
		inputStream.close();
		return bytes;
	}

	public static String getRandomValue() {
		NumberFormat fmt;
		Calendar cal = new GregorianCalendar();
		GregorianCalendar dtX = new GregorianCalendar();
		cal.setTime(new Date());
		dtX.setTime(new Date());

		GregorianCalendar dtY = new GregorianCalendar(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0,
				0);
		Date d1 = dtX.getTime();
		Date d2 = dtY.getTime();
		long l1 = d1.getTime();
		long l2 = d2.getTime();

		Long secs = new Long((l1 - l2) / 1000);
		Integer dias = new Integer(cal.get(Calendar.DAY_OF_YEAR));
		Integer ano = new Integer(cal.get(Calendar.YEAR));

		fmt = new DecimalFormat("000");
		String s1 = fmt.format(dias);
		fmt = new DecimalFormat("00000");
		String s2 = fmt.format(secs);
		String formato = ano.toString() + s1 + s2;
		Double vl = Math.random();
		String str = vl.toString();
		if (str.indexOf('.') != -1) {
			str = str.substring(str.indexOf('.') + 1, str.length());
		}
		return Long.valueOf(formato + str.substring(0, 4)).toString();
	}

	public static String trim(String[] listaNomes, char separador) {
		StringBuffer retorno = new StringBuffer();
		for (String str : listaNomes) {
			retorno.append(str).append(separador);
		}
		return retorno.substring(0, retorno.toString().lastIndexOf(separador));
	}

	public static String getRandomString(int size) {
		StringBuffer passBuffer = new StringBuffer("");
		for (int x = 0; x < size; x++) {
			int num = (int) (Math.random() * 10);
			int unicode = 0;
			if (num < 3) {
				unicode = (int) (48 + (Math.random() * 10));
			} else if (num >= 3 && num < 6) {
				unicode = (int) (65 + (Math.random() * 26));
			} else if (num >= 6) {
				unicode = (int) (97 + (Math.random() * 26));
			}
			char caracter = (char) unicode;
			passBuffer.append(caracter);
		}
		return passBuffer.toString();
	}

}
