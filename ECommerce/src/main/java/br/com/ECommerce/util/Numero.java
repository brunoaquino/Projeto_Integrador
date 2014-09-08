package br.com.ECommerce.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.NumberFormatter;

public class Numero {

	/**
	 * Máscara para o campo MOEDA. <br>
	 * Essa constante pode ser usado com o método formatarGenerico. <br>
	 * <b>ex.: </b>
	 * <code>Numero.formatarGenerico(cep, Numero.MASCARA_MOEDA);</code>
	 */
	public static final String MASCARA_MOEDA = "###.###.###.###.###.###,#0";

	/**
	 * Máscara para o campo CEP. <br>
	 * Essa constante pode ser usado com o método formatarGenerico. <br>
	 * <b>ex.: </b>
	 * <code>Numero.formatarGenerico(cep, Numero.MASCARA_CEP);</code>
	 */
	public static final String MASCARA_CEP = "00000-000";

	/**
	 * Máscara para o campo CNPJ. <br>
	 * Essa constante pode ser usado com o método formatarGenerico. <br>
	 * <b>ex.: </b>
	 * <code>Numero.formatarGenerico(cnpj, Numero.MASCARA_CNPJ);</code>
	 */
	public static final String MASCARA_CNPJ = "00.000.000/0000-00";
	/**
	 * Máscara para o campo CPF. <br>
	 * Essa constante pode ser usado com o método formatarGenerico. <br>
	 * <b>ex.: </b>
	 * <code>Numero.formatarGenerico(cpf, Numero.MASCARA_CPF);</code>
	 */
	public static final String MASCARA_CPF = "000.000.000-00";
	/**
	 * Máscara para o campo Número Inscrição. <br>
	 * Essa constante pode ser usado com o método formatarGenerico. <br>
	 * <b>ex.: </b>
	 * <code>Numero.formatarGenerico(numeroInscricao, Numero.MASCARA_INSCRICAO);</code>
	 */
	public static final String MASCARA_INSCRICAO = "00000000-0";

	/**
	 * Retorna o Numero de Casas decimais existentes após a virgula desprezando
	 * ZEROS a direita.
	 * 
	 * @param valor
	 * @return
	 * @author CIRO
	 */
	public static final int getCasasDecimais(Double valor) {
		String tm = valor.toString();
		tm = tm.substring(tm.indexOf('.') + 1, tm.length());
		int contador = tm.length();
		for (int i = tm.length(); i > 0; i--) {
			String posicao = tm.substring(i - 1, i);
			if (!posicao.equals("0")) {
				contador = i;
				break;
			}
		}
		return contador;
	}

	/**
	 * 
	 * Retorna o Numero de Casas decimais existentes após a virgula desprezando
	 * ZEROS a direita.
	 * 
	 * @param valor
	 * @return
	 * @author CIRO
	 */
	public static final int getCasasDecimais(BigDecimal valor) {
		return getCasasDecimais(valor.doubleValue());
	}

	/**
	 * Retorna a diferença em Segundos.
	 * 
	 * @param milisegundosInicio
	 * @param milisegundosFim
	 * @return
	 */
	public static final long retornaDiferencaEmSegundos(
			long milisegundosInicio, long milisegundosFim) {
		long diff = milisegundosFim - milisegundosInicio;
		return diff / 1000;
	}

	/**
	 * Formata valores decimais.
	 * 
	 * @param valor
	 *            double - valor decimal
	 * @param decimais
	 *            int - quantidade de casas decimais
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatarDecimal(long valor, int decimais) {
		DecimalFormat Formatador = null;
		String Padrao = "";

		for (int i = 1; i <= decimais; i++)
			Padrao = Padrao + "0";
		if (decimais > 0)
			Padrao = "." + Padrao;
		try {
			Formatador = (DecimalFormat) NumberFormat
					.getInstance(Locale.GERMAN);
		} catch (ClassCastException e) {
			return ("");
		}

		Formatador.applyPattern("##,###,###,###,###,##0" + Padrao);

		return (Formatador.format(valor));
	}

	/**
	 * Formata valores decimais.
	 * 
	 * @param valor
	 *            double - valor decimal
	 * @param decimais
	 *            int - quantidade de casas decimais
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatarDecimal(double valor, int decimais) {
		DecimalFormat Formatador = null;
		String Padrao = "";

		for (int i = 1; i <= decimais; i++)
			Padrao = Padrao + "0";
		if (decimais > 0)
			Padrao = "." + Padrao;
		try {
			Formatador = (DecimalFormat) NumberFormat
					.getInstance(Locale.GERMAN);
		} catch (ClassCastException e) {
			return ("");
		}

		Formatador.applyPattern("##,###,###,###,###,##0" + Padrao);

		return (Formatador.format(valor));
	}

	/**
	 * Formata MOEDA
	 * 
	 * @param valor
	 * @return
	 */
	public static final String formatarMoeda(double valor) {
		try {
			NumberFormatter formatter = new NumberFormatter(new DecimalFormat(
					"#,##0.00"));
			return (formatter.valueToString(valor));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Formata MOEDA
	 * 
	 * @param valor
	 * @return
	 */
	public static final String formatarMoeda(BigDecimal valor) {
		try {
			NumberFormatter formatter = new NumberFormatter(new DecimalFormat(
					"#,##0.00"));
			return (formatter.valueToString(valor.doubleValue()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Formata valores decimais.
	 * 
	 * @param valor
	 *            String - valor formato String
	 * @param decimais
	 *            int - quantidade de casas decimais
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatarDecimal(String valor, int decimais) {
		return formatarDecimal(Double.valueOf(valor.replace(',', '.'))
				.doubleValue(), decimais);
	}

	/**
	 * Converte um valor formatado com pontos e v�rgula em um double.
	 * 
	 * @param valor
	 *            String
	 * @return double
	 * @author ciro.macedo
	 */
	public static double desformatarDecimal(String valor) {
		try {
			return Double.parseDouble(valor.replaceAll("\\.", "").replaceAll(
					",", "."));
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * Resolve problema de ponto flutuante.
	 * 
	 * @param valor
	 *            double
	 * @return double
	 * @author ciro.macedo
	 */
	public static double acertarDouble(double valor) {
		return Double
				.parseDouble(formatarSemMilhar(valor, 2).replace(',', '.'));
	}

	/**
	 * Formata para duas casas decimais
	 * 
	 * @param valor
	 *            - double
	 * @return String
	 */
	public static String formatarDuasCasasDecimais(double valor) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(valor);
	}

	/**
	 * Formata valores decimais sem o ponto de milhar
	 * 
	 * @param valor
	 *            BigDecimal - Valor a ser formadado
	 * @param decimais
	 *            int - quantidade de casas decimais
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatarSemMilhar(BigDecimal valor, int decimais) {
		if (valor == null) {
			return "";
		}
		return formatarSemMilhar(valor.doubleValue(), decimais);
	}

	/**
	 * Formata valores decimais sem o ponto de milhar
	 * 
	 * @param valor
	 *            double - Valor a ser formadado
	 * @param decimais
	 *            int - quantidade de casas decimais
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatarSemMilhar(Double valor, int decimais) {
		if (valor == null) {
			return "";
		}

		DecimalFormat Formatador = null;
		String mascara = "################0";
		String padrao = "";

		for (int i = 1; i <= decimais; i++)
			padrao = padrao + "0";

		if (decimais > 0)
			padrao = "." + padrao;

		try {
			Formatador = (DecimalFormat) NumberFormat
					.getInstance(Locale.GERMAN);
		} catch (ClassCastException e) {
			return ("");
		}

		Formatador.applyPattern(mascara + padrao);

		return (Formatador.format(valor));
	}

	/**
	 * Formata valores que contem quantidade.
	 * 
	 * @param valor
	 *            double - Valor a ser formadado
	 * @param decimais
	 *            int - quantidade de casas decimais
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatarQtde(double valor, int decimais) {
		DecimalFormat Formatador = null;
		String Padrao = "";

		for (int i = 1; i <= decimais; i++)
			Padrao = Padrao + "#";

		if (decimais > 0)
			Padrao = "." + Padrao;

		try {
			Formatador = (DecimalFormat) NumberFormat
					.getInstance(Locale.GERMAN);
		} catch (ClassCastException e) {
			return ("");
		}

		Formatador.applyPattern("###,###,###,###,###" + Padrao);

		return (Formatador.format(valor));
	}

	/**
	 * Formata valores que contem quantidade
	 * 
	 * @param valor
	 *            String - Valor a ser formatado
	 * @param decimais
	 *            int - quantidade de casas decimais
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatarQtde(String valor, int decimais) {
		return formatarQtde(Double.valueOf(valor.replace(',', '.'))
				.doubleValue(), decimais);
	}

	/**
	 * Verifica se a string informada no parâmetros "valor" � um valor numérico
	 * ou não.
	 * 
	 * @param valor
	 *            String - Valor a ser testado
	 * @return boolean - True para se é numérico e False para se não é numérico
	 * @author ciro.macedo
	 */
	public static boolean isNumeric(String valor) {
		try {
			new Double(valor.trim());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Retorna verdadeiro ser for Diferente de NULL e diferente de Zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean existe(Long valor) {
		return (valor != null && valor.doubleValue() != 0);
	}

	/**
	 * Retorna verdadeiro ser for Diferente de NULL e diferente de Zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean existe(BigDecimal valor) {
		return (valor != null && valor.doubleValue() != 0);
	}

	/**
	 * Retorna verdadeiro ser for Diferente de NULL e diferente de Zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean existe(Double valor) {
		return (valor != null && valor.doubleValue() != 0);
	}

	/**
	 * Formata números quaisquer com uma máscara qualquer.
	 * 
	 * @param numero
	 *            double - Número a ser formatado
	 * @param mascara
	 *            String - Máscara que será aplicada ao número
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatar(double numero, String mascara) {
		DecimalFormat formatador = null;

		try {
			formatador = (DecimalFormat) NumberFormat
					.getInstance(Locale.GERMAN);
		} catch (ClassCastException e) {
			return ("");
		}

		formatador.applyPattern(mascara);

		return (formatador.format(numero));
	}

	/**
	 * Formata números quaisquer com uma máscara qualquer.
	 * 
	 * @param numero
	 *            String - Número a ser formatado
	 * @param mascara
	 *            String - Máscara que será aplicada ao número
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatar(String numero, String mascara) {
		if (numero.trim().equals("")) {
			numero = "0";
		}

		try {
			return formatar(Double.parseDouble(numero), mascara);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Formata números quaisquer com uma máscara qualquer.
	 * 
	 * @param numero
	 *            int - Numero a ser formatado
	 * @param mascara
	 *            String - Mascara que será aplicada ao número
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatar(int numero, String mascara) {
		return formatar((double) numero, mascara);
	}

	/**
	 * Formata números quaisquer com uma máscara qualquer.
	 * 
	 * @param numero
	 *            long - Numero a ser formatado
	 * @param mascara
	 *            String - Mascara que será aplicada ao número
	 * @return String
	 * @author ciro.macedo
	 */
	public static final String formatar(long numero, String mascara) {
		return formatar((double) numero, mascara);
	}

	/**
	 * Formata valores em CNPJ.
	 * 
	 * @param numero
	 *            String - CNPJ a ser formatado
	 * @return String
	 * @author ciro.macedo
	 */
	public String formatarCNPJ(String numero) {
		String AuxCNPJ = numero.trim();
		if (!AuxCNPJ.equals("") && !AuxCNPJ.equals("00000000000000")) {
			while (AuxCNPJ.length() < 14)
				AuxCNPJ = "0" + AuxCNPJ;

			AuxCNPJ = AuxCNPJ.substring(0, 2) + "." + AuxCNPJ.substring(2, 5)
					+ "." + AuxCNPJ.substring(5, 8) + "/"
					+ AuxCNPJ.substring(8, 12) + "-"
					+ AuxCNPJ.substring(12, 14);
		} else {
			AuxCNPJ = "";
		}
		return AuxCNPJ;
	}

	/**
	 * Formata valores de CPF
	 * 
	 * @param numero
	 *            String - CPF a ser formatado
	 * @return String
	 * @author ciro.macedo
	 */
	public String formatarCPF(String numero) {
		String AuxCPF = numero.trim();

		while (AuxCPF.length() < 11)
			AuxCPF = "0" + AuxCPF;

		AuxCPF = AuxCPF.substring(0, 3) + "." + AuxCPF.substring(3, 6) + "."
				+ AuxCPF.substring(6, 9) + "-" + AuxCPF.substring(9, 11);

		return AuxCPF;
	}

	/**
	 * Converte uma String em um LONG removedo todo e qualquer caracter que
	 * esteja fora do universo "0123456789".
	 * 
	 * Significado de caracteres para utilização com RegularExpressions. []
	 * (colchetes) - Agrupadores. ^ (acento circunflexo) - Negação. . (ponto) -
	 * Qualquer caracter. - (hífen) - Indica faixa de caracteres. 0-4 equivale a
	 * 0,1,2,3,4, por exemplo. d - Todos osdígitos de uma String. Equivale a
	 * [0-9]. D - Todos os não-dígitos de uma String. Equivale a [^0-9]. s -
	 * Caracteres "whitespace" (espaço, tab, retorno de carro, etc). S -
	 * Caracteres "não-whitespace". Equivale a [^\\s]. w - Letras, números e
	 * "_". Equivale a [_0-9a-zA-Z]. W - Outros caracteres. Equivale a [^\\w]
	 * 
	 * @param string
	 *            - String que sera convertida
	 * @return Long
	 */
	public static Long converteStringToLong(String string) {
		return new Long(string.replaceAll("[^0-9]", ""));
	}

	/**
	 * Converte uma String em um DOUBLE removedo todo e qualquer caracter que
	 * esteja fora do universo "0123456789".
	 * 
	 * Significado de caracteres para utilização com RegularExpressions. []
	 * (colchetes) - Agrupadores. ^ (acento circunflexo) - Negação. . (ponto) -
	 * Qualquer caracter. - (hífen) - Indica faixa de caracteres. 0-4 equivale a
	 * 0,1,2,3,4, por exemplo. d - Todos osdígitos de uma String. Equivale a
	 * [0-9]. D - Todos os não-dígitos de uma String. Equivale a [^0-9]. s -
	 * Caracteres "whitespace" (espaço, tab, retorno de carro, etc). S -
	 * Caracteres "não-whitespace". Equivale a [^\\s]. w - Letras, números e
	 * "_". Equivale a [_0-9a-zA-Z]. W - Outros caracteres. Equivale a [^\\w]
	 * 
	 * @param string
	 *            - String que sera convertida
	 * @return Long
	 */
	public static Double converteStringToDouble(String string) {
		return new Double(string.replaceAll("[^0-9]", ""));
	}

	/**
	 * Converte String para Double.
	 * 
	 * @param string
	 * @param decimais
	 * @return
	 * @author CIRO
	 */
	public static Double converteStringToDouble(String string, int decimais) {
		string = string.substring(0, string.length() - decimais)
				+ "."
				+ string.substring((string.length() - decimais),
						string.length());
		return new Double(string);
	}

	/**
	 * Converte uma String em um INTEGER removedo todo e qualquer caracter que
	 * esteja fora do universo "0123456789".
	 * 
	 * Significado de caracteres para utilização com RegularExpressions. []
	 * (colchetes) - Agrupadores. ^ (acento circunflexo) - Negação. . (ponto) -
	 * Qualquer caracter. - (hífen) - Indica faixa de caracteres. 0-4 equivale a
	 * 0,1,2,3,4, por exemplo. d - Todos osdígitos de uma String. Equivale a
	 * [0-9]. D - Todos os não-dígitos de uma String. Equivale a [^0-9]. s -
	 * Caracteres "whitespace" (espaço, tab, retorno de carro, etc). S -
	 * Caracteres "não-whitespace". Equivale a [^\\s]. w - Letras, números e
	 * "_". Equivale a [_0-9a-zA-Z]. W - Outros caracteres. Equivale a [^\\w]
	 * 
	 * @param string
	 *            - String que sera convertida
	 * @return Long
	 */
	public static Integer converteStringToInteger(String string) {
		return new Integer(string.replaceAll("[^0-9]", ""));
	}

	/**
	 * Formata número de telefone - (00)0000-0000
	 * 
	 * @param fone
	 *            String - Número de telefone a ser formatado
	 * @return String - Número de telefone formatado
	 * @author ciro.macedo
	 */
	public String formatarFone(String fone) {
		String foneAux = fone.trim();
		String lsFone;

		if (foneAux != null) {
			if (foneAux.length() < 7)
				foneAux = formatar(foneAux, "0000000");

			if (foneAux.length() <= 8)
				lsFone = foneAux.substring(0, foneAux.length() - 4) + "-"
						+ foneAux.substring(foneAux.length() - 4);
			else
				lsFone = "(" + foneAux.substring(0, 2) + ")"
						+ foneAux.substring(2, foneAux.length() - 4) + "-"
						+ foneAux.substring(foneAux.length() - 4);
		} else
			lsFone = "";

		return lsFone;
	}

	/**
	 * Formata número do CEP - 00.000-000
	 * 
	 * @param CEP
	 *            String - Número de CEP a ser formatado
	 * @return String - Número do CEP formatado
	 * @author ciro.macedo
	 */
	public String formatarCEP(String CEP) {
		String CEPAux = CEP.trim();
		if (CEPAux.length() == 8) {
			return CEPAux.substring(0, 2) + "." + CEPAux.substring(2, 5) + "-"
					+ CEPAux.substring(5);
		} else {
			return CEPAux;
		}
	}

	/**
	 * Formata uma String representando um valor numérico decimal inteiro de
	 * acordo a máscara informada. Diferente do método formatar dessa classe,
	 * esse método simplesmente substitui os caracteres 0 e # da máscara
	 * pelosdígitos do número deixando todos os outros caracteres da máscara
	 * como estão. <br>
	 * Observação:
	 * <ul>
	 * <li>os 0 significa valor que será preenchido a esquerda durante a
	 * formatação. Exemplo: máscara=0000.00.00 número=123 será formatado para
	 * 0000.01.23
	 * <li>os # significa valor opcional na máscara. Exemplo: máscara=####.##.##
	 * número=123 será formatado para 1.23
	 * </ul>
	 * 
	 * @param sNumero
	 *            String
	 * @param mascara
	 *            String
	 * @return String
	 * @author ciro.macedo
	 */
	public static String formatarGenerico(String sNumero, String mascara) {
		if (sNumero.trim().replaceAll("[^\\d#]", "").equals(""))
			return "";

		if (sNumero.length() > mascara.replaceAll("[^\\d#]", "").length())
			sNumero = sNumero.substring(0, mascara.replaceAll("[^\\d#]", "")
					.length());

		String result = "";
		for (int i = mascara.length() - 1, j = sNumero.length() - 1; i >= 0; i--) {
			char cMasc = mascara.charAt(i);
			char cNum = j >= 0 ? sNumero.charAt(j) : '\u0000';
			switch (cMasc) {
			case '#':
				result = cNum + result;
				j--;
				break;
			case '0':
				result = j >= 0 ? cNum + result : '0' + result;
				j--;
				break;
			default:
				result = cMasc + result;
				break;
			}
		}
		return result;
	}

	/**
	 * Formata um número de acordo a máscara informada. Diferente do método
	 * formatar dessa classe, esse método simplesmente substitui os caracteres 0
	 * e # da máscara pelosdígitos do número deixando todos os outros caracteres
	 * da máscara como estão. <br>
	 * Observação:
	 * <ul>
	 * <li>os 0 significa valor que será preenchido a esquerda durante a
	 * formatação. Exemplo: máscara=0000.00.00 número=123 será formatado para
	 * 0000.01.23
	 * <li>os # significa valor opcional na máscara. Exemplo: máscara=####.##.##
	 * número=123 será formatado para 1.23
	 * </ul>
	 * 
	 * @param numero
	 *            long
	 * @param mascara
	 *            String
	 * @return String
	 * @author ciro.macedo
	 */
	public static String formatarGenerico(long numero, String mascara) {
		if (numero == 0)
			return "";
		else
			return formatarGenerico(String.valueOf(numero), mascara);
	}

	/**
	 * Formata um número de acordo a máscara informada. Diferente do método
	 * formatar dessa classe, esse método simplesmente substitui os caracteres 0
	 * e # da máscara pelosdígitos do número deixando todos os outros caracteres
	 * da máscara como estão. <br>
	 * Observação:
	 * <ul>
	 * <li>os 0 significa valor que será preenchido a esquerda durante a
	 * formatação. Exemplo: máscara=0000.00.00 número=123 será formatado para
	 * 0000.01.23
	 * <li>os # significa valor opcional na máscara. Exemplo: máscara=####.##.##
	 * número=123 será formatado para 1.23
	 * </ul>
	 * 
	 * @param numero
	 *            Number
	 * @param mascara
	 *            String
	 * @return String
	 * @author ciro.macedo
	 */
	public static String formatarGenerico(Number numero, String mascara) {
		if (numero == null)
			return "";
		else
			return formatarGenerico(numero.longValue(), mascara);
	}

	/**
	 * Verifica se cada posição da string informada no parâmetros "valor" � um
	 * número ou não.
	 * 
	 * @param valor
	 *            String - Valor a ser testado
	 * @return boolean - True para se são números e False para se não são
	 *         números.
	 * @author ciro.macedo
	 */
	public static boolean isNumero(String valor) {
		for (int i = 0; i <= valor.length() - 1; i++) {
			int c = valor.charAt(i);
			// Se não estiver entre "0" e "9"
			if (c < 48 || c > 57) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifica se a string digitada no atributo "value" do objeto passado no
	 * parâmetros "obj" a uma placa veicular válida.
	 * 
	 * @param placa
	 *            String
	 * @return boolean
	 * @author ciro.macedo
	 */
	public static boolean validarPlaca(String placa) {
		if (placa.length() != 0)
			if (placa.length() != 7)
				return false;
			else if (!Texto.eLetra(placa.substring(0, 3))
					|| !Numero.isNumero(placa.substring(3, 7)))
				return false;

		return true;
	}

	/**
	 * Formata número de inscrição estadual - 00000000-0
	 * 
	 * @param inscricaoEstadual
	 *            String Número de inscrição estadual
	 * @return String Inscrição estadual formatada
	 * @author ciro.macedo
	 */
	public String formatarInscricaoEstadual(String inscricaoEstadual) {
		String inscricaoEstadualAux = inscricaoEstadual.trim();
		return inscricaoEstadualAux.substring(0, 8) + "-"
				+ inscricaoEstadualAux.substring(8);
	}

	/**
	 * Formata número de telefone - (00)0000-0000
	 * 
	 * @param fone
	 *            String - Número de telefone a ser formatado
	 * @return String - Número de telefone formatado
	 * @author ciro.macedo
	 */
	public static String formatarFone(String ddd, String fone) {
		String foneAux = fone == null ? "" : fone.trim();
		String lsFone;
		String lsDDD = "";

		if (ddd != null && !ddd.equals("")) {
			lsDDD = "(" + ddd + ")";
		}
		if (foneAux != null && !foneAux.equals("")) {

			if (foneAux.length() == 5) {
				lsFone = lsDDD + " " + foneAux.substring(0, 1) + "-"
						+ foneAux.substring(1, 5);
			} else if (foneAux.length() == 6) {
				lsFone = lsDDD + " " + foneAux.substring(0, 2) + "-"
						+ foneAux.substring(2, 6);
			} else if (foneAux.length() == 7) {
				lsFone = lsDDD + " " + foneAux.substring(0, 3) + "-"
						+ foneAux.substring(3, 7);
			} else if (foneAux.length() == 8) {
				lsFone = lsDDD + " " + foneAux.substring(0, 4) + "-"
						+ foneAux.substring(4, 8);
			} else {
				lsFone = lsDDD + " " + fone;
			}
		} else
			lsFone = "";

		return lsFone;
	}

	/**
	 * Valida se o item inicial de uma faixa de valores a menor que o item final
	 * 
	 * @param valorMinimo
	 * @param valorMaximo
	 * @return
	 */
	public static Boolean isFaixaDeValoresOk(String valorMinimo,
			String valorMaximo) {

		if ((valorMinimo != null && !valorMinimo.trim().equals(""))
				&& (valorMaximo != null && !valorMaximo.trim().equals(""))) {
			Double primeiroValor = new Double(valorMinimo);
			Double segundoValor = new Double(valorMaximo);

			if (primeiroValor <= segundoValor)
				return Boolean.TRUE;
			else
				return Boolean.FALSE;
		} else
			return Boolean.FALSE;

	}

	/**
	 * Retira '.' e ',' da string Formata para do Double e retorna o valor
	 * 
	 * @param valor
	 *            - String
	 * @return Double
	 */
	public static Double formatarDouble(String valor) {
		if (valor.indexOf(".") > 0 && valor.indexOf(",") > 0) {
			valor = valor.replace(".", "").replace(",", ".");
		} else if (valor.indexOf(",") > 0) {
			valor = valor.replace(",", ".");
		}
		return new Double(valor);
	}

	/**
	 * Retorna um Double igual a 0 caso NULL.
	 * 
	 * @param value
	 * @return
	 */
	public static Double getDoubleValueNotNull(Double value) {
		if (value == null) {
			return new Double(0);
		}
		return value;
	}

	/**
	 * Retorna um Double igual a 0 caso NULL.
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal getBigDecimalValueNotNull(BigDecimal value) {
		if (value == null) {
			return new BigDecimal(0);
		}
		return value;
	}

	/**
	 * Retorna um valor DOUBLE contido em um MAP
	 * 
	 * @param key
	 * @param parametros
	 * @return Double
	 */
	public static Double getDoubleValueInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}
		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return null;
		}
		Double retorno = new Double(0);
		if (parametros.get(key) instanceof String) {
			retorno = new Double((String) parametros.get(key));
		} else if (parametros.get(key) instanceof Integer) {
			retorno = ((Integer) parametros.get(key)).doubleValue();
		} else if (parametros.get(key) instanceof Long) {
			retorno = ((Long) parametros.get(key)).doubleValue();
		} else if (parametros.get(key) instanceof Float) {
			retorno = ((Float) parametros.get(key)).doubleValue();
		} else if (parametros.get(key) instanceof BigDecimal) {
			retorno = ((BigDecimal) parametros.get(key)).doubleValue();
		} else {
			retorno = (Double) parametros.get(key);
		}
		return retorno;
	}

	/**
	 * Converte valor para Double.
	 * 
	 * @param valor
	 * @return
	 */
	public static Double converterParaDouble(Object valor) {
		if (valor == null) {
			return null;
		}
		if (!isNumeric(valor.toString())) {
			return null;
		}

		Double retorno = new Double(0);
		if (valor instanceof String) {
			retorno = new Double((String) valor);
		} else if (valor instanceof Integer) {
			retorno = ((Integer) valor).doubleValue();
		} else if (valor instanceof Long) {
			retorno = ((Long) valor).doubleValue();
		} else if (valor instanceof Float) {
			retorno = ((Float) valor).doubleValue();
		} else if (valor instanceof BigDecimal) {
			retorno = ((BigDecimal) valor).doubleValue();
		} else {
			retorno = (Double) valor;
		}
		return retorno;
	}

	/**
	 * Converte valor para BigDecimal.
	 * 
	 * @param valor
	 * @return
	 */
	public static BigDecimal converterParaBigDecimal(Object valor) {
		if (valor == null) {
			return null;
		}

		if (!isNumeric(valor.toString())) {
			return null;
		}

		BigDecimal retorno = null;
		if (valor instanceof String) {
			retorno = Numero.normatizar(new BigDecimal((String) valor), 4);
		} else if (valor instanceof Integer) {
			retorno = Numero.normatizar(new BigDecimal(valor.toString()), 4);
		} else if (valor instanceof Long) {
			retorno = Numero.normatizar(new BigDecimal((Long) valor), 4);
		} else if (valor instanceof Float) {
			retorno = Numero.normatizar(new BigDecimal((Float) valor), 4);
		} else if (valor instanceof Double) {
			retorno = Numero.normatizar(new BigDecimal((Double) valor), 4);
		} else {
			retorno = Numero.normatizar(new BigDecimal(valor.toString()), 4);
		}
		return retorno;
	}

	/**
	 * Retorna um valor BigDecimal contido em um MAP
	 * 
	 * @param key
	 * @param parametros
	 * @return Double
	 */
	public static BigDecimal getBigDecimalValueInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}
		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return null;
		}
		BigDecimal retorno = null;
		if (parametros.get(key) instanceof String) {
			retorno = Numero.normatizar(
					new BigDecimal((String) parametros.get(key)), 4);
		} else if (parametros.get(key) instanceof Integer) {
			retorno = Numero.normatizar(new BigDecimal(parametros.get(key)
					.toString()), 4);
		} else if (parametros.get(key) instanceof Long) {
			retorno = Numero.normatizar(
					new BigDecimal((Long) parametros.get(key)), 4);
		} else if (parametros.get(key) instanceof Float) {
			retorno = Numero.normatizar(
					new BigDecimal((Float) parametros.get(key)), 4);
		} else if (parametros.get(key) instanceof Double) {
			retorno = Numero.normatizar(
					new BigDecimal((Double) parametros.get(key)), 4);
		} else {
			retorno = Numero.normatizar(new BigDecimal(parametros.get(key)
					.toString()), 4);
		}
		return retorno;
	}

	public static BigDecimal getBigDecimalValueNotNullInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return new BigDecimal(0);
		}
		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return new BigDecimal(0);
		}
		BigDecimal retorno = null;
		if (parametros.get(key) instanceof String) {
			retorno = Numero.normatizar(
					new BigDecimal((String) parametros.get(key)), 4);
		} else if (parametros.get(key) instanceof Integer) {
			retorno = Numero.normatizar(new BigDecimal(parametros.get(key)
					.toString()), 4);
		} else if (parametros.get(key) instanceof Long) {
			retorno = Numero.normatizar(
					new BigDecimal((Long) parametros.get(key)), 4);
		} else if (parametros.get(key) instanceof Float) {
			retorno = Numero.normatizar(
					new BigDecimal((Float) parametros.get(key)), 4);
		} else if (parametros.get(key) instanceof Double) {
			retorno = Numero.normatizar(
					new BigDecimal((Double) parametros.get(key)), 4);
		} else {
			retorno = Numero.normatizar(new BigDecimal(parametros.get(key)
					.toString()), 4);
		}
		return retorno;
	}

	/**
	 * Retorna um valor BigDecimal contido em um MAP
	 * 
	 * @param key
	 * @param parametros
	 * @param precisao
	 * @return Double
	 */
	public static BigDecimal getBigDecimalValueInMAP(String key,
			Map<String, Object> parametros, int precisao) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}
		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return null;
		}
		BigDecimal retorno = null;
		if (parametros.get(key) instanceof String) {
			retorno = Numero.normatizar(
					new BigDecimal((String) parametros.get(key)), precisao);
		} else if (parametros.get(key) instanceof Integer) {
			retorno = Numero.normatizar(new BigDecimal(parametros.get(key)
					.toString()), precisao);
		} else if (parametros.get(key) instanceof Long) {
			retorno = Numero.normatizar(
					new BigDecimal((Long) parametros.get(key)), precisao);
		} else if (parametros.get(key) instanceof Float) {
			retorno = Numero.normatizar(
					new BigDecimal((Float) parametros.get(key)), precisao);
		} else if (parametros.get(key) instanceof Double) {
			retorno = Numero.normatizar(
					new BigDecimal((Double) parametros.get(key)), precisao);
		} else {
			retorno = Numero.normatizar(new BigDecimal(parametros.get(key)
					.toString()), precisao);
		}
		return retorno;
	}

	/**
	 * Retorna um LONG
	 * 
	 * @param vetor
	 * @param indice
	 * @return
	 */
	public static Long getLongValueInArray(Object[] vetor, int indice) {
		Object vl = vetor[indice];
		if (vl == null) {
			return null;
		}
		if (vl instanceof Long) {
			return (Long) vl;
		} else if (vl instanceof BigInteger) {
			return ((BigInteger) vl).longValue();
		} else {
			throw new RuntimeException("Erro na Conversão de LONG. ["
					+ vl.getClass().getName() + "]");
		}
	}

	/**
	 * Recupera valor SHORT de um vetor.
	 * 
	 * @param vetor
	 * @param indice
	 * @return
	 */
	public static Short getShortValueInArray(Object[] vetor, int indice) {
		Object vl = vetor[indice];
		if (vl == null) {
			return null;
		}
		if (vl instanceof Long) {
			return ((Long) vl).shortValue();
		} else if (vl instanceof Short) {
			return (Short) vl;
		} else if (vl instanceof BigInteger) {
			return ((BigInteger) vl).shortValue();
		} else if (vl instanceof Integer) {
			return ((Integer) vl).shortValue();
		} else if (vl instanceof Double) {
			return ((Double) vl).shortValue();
		} else if (vl instanceof Float) {
			return ((Float) vl).shortValue();
		} else {
			throw new RuntimeException("Erro na Conversão de LONG. ["
					+ vl.getClass().getName() + "]");
		}
	}

	/**
	 * Retorna um Integer
	 * 
	 * @param vetor
	 * @param indice
	 * @return
	 */
	public static Integer getIntegerValueInArray(Object[] vetor, int indice) {
		Object vl = vetor[indice];
		if (vl == null) {
			return null;
		}
		if (vl instanceof Integer) {
			return (Integer) vl;
		} else if (vl instanceof BigInteger) {
			return ((BigInteger) vl).intValue();
		} else if (vl instanceof BigDecimal) {
			return ((BigDecimal) vl).intValue();
		} else if (vl instanceof Short) {
			return ((Short) vl).intValue();
		} else {
			throw new RuntimeException("Erro na Conversão de INTEGER. ["
					+ vl.getClass().getName() + "]");
		}
	}

	/**
	 * Retorna um LONG
	 * 
	 * @param vetor
	 * @param indice
	 * @return
	 */
	public static BigDecimal getBigDecimalValueInArray(Object[] vetor,
			int indice) {
		Object vl = vetor[indice];
		if (vl == null) {
			return null;
		}

		if (vl instanceof BigDecimal) {
			return normatizar((BigDecimal) vl, 4);
		} else if (vl instanceof Double) {
			return normatizar((Double) vl, 4);
		} else if (vl instanceof Integer) {
			return normatizar(((Integer) vl).doubleValue(), 4);
		} else {
			throw new RuntimeException("Erro na Conversão de BigDecimal. ["
					+ vl.getClass().getName() + "]");
		}
	}

	/**
	 * Retorna um LONG
	 * 
	 * @param vetor
	 * @param indice
	 * @return
	 */
	public static BigDecimal getBigDecimalValueNotNullInArray(Object[] vetor,
			int indice) {
		Object vl = vetor[indice];
		if (vl == null) {
			return new BigDecimal(0);
		}
		return getBigDecimalValueInArray(vetor, indice);
	}

	/**
	 * Retrona um double
	 * 
	 * @param vetor
	 * @param indice
	 * @return
	 */
	public static Double getDoubleValueInArray(Object[] vetor, int indice) {
		Object vl = vetor[indice];
		if (vl == null) {
			return null;
		}

		if (vl instanceof BigDecimal) {
			return normatizar((BigDecimal) vl, 4).doubleValue();
		} else if (vl instanceof Double) {
			return normatizar((Double) vl, 4).doubleValue();
		} else if (vl instanceof Integer) {
			return normatizar(((Integer) vl).doubleValue(), 4).doubleValue();
		} else {
			throw new RuntimeException("Erro na Conversão para Double. ["
					+ vl.getClass().getName() + "]");
		}
	}

	/**
	 * Retrona um double
	 * 
	 * @param vetor
	 * @param indice
	 * @return
	 */
	public static Double getDoubleValueNotNullInArray(Object[] vetor, int indice) {
		Object vl = vetor[indice];
		if (vl == null) {
			return new Double(0);
		}

		if (vl instanceof BigDecimal) {
			return normatizar((BigDecimal) vl, 4).doubleValue();
		} else if (vl instanceof Double) {
			return normatizar((Double) vl, 4).doubleValue();
		} else if (vl instanceof Integer) {
			return normatizar(((Integer) vl).doubleValue(), 4).doubleValue();
		} else {
			throw new RuntimeException("Erro na Conversão para Double. ["
					+ vl.getClass().getName() + "]");
		}
	}

	/**
	 * Retorna um valor DOUBLE contido em um MAP
	 * 
	 * @param key
	 * @param parametros
	 * @return Double
	 */
	public static Long getLongValueInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}

		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return null;
		}
		Long retorno = new Long(0);
		if (parametros.get(key) instanceof String) {
			retorno = new Long((String) parametros.get(key));
		} else if (parametros.get(key) instanceof Integer) {
			retorno = ((Integer) parametros.get(key)).longValue();
		} else if (parametros.get(key) instanceof Long) {
			retorno = ((Long) parametros.get(key)).longValue();
		} else if (parametros.get(key) instanceof Float) {
			retorno = ((Float) parametros.get(key)).longValue();
		} else {
			retorno = (Long) parametros.get(key);
		}
		return retorno;
	}

	public static Short getShortValueInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}

		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return null;
		}
		Short retorno;
		if (parametros.get(key) instanceof String) {
			retorno = new Short((String) parametros.get(key));
		} else if (parametros.get(key) instanceof Integer) {
			retorno = ((Integer) parametros.get(key)).shortValue();
		} else if (parametros.get(key) instanceof Long) {
			retorno = ((Long) parametros.get(key)).shortValue();
		} else if (parametros.get(key) instanceof Float) {
			retorno = ((Float) parametros.get(key)).shortValue();
		} else {
			retorno = (Short) parametros.get(key);
		}
		return retorno;
	}

	/**
	 * Retorna um valor DOUBLE contido em um MAP
	 * 
	 * @param key
	 * @param parametros
	 * @return Integer
	 */
	public static Integer getIntegerValueInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}
		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return null;
		}
		Integer retorno = new Integer(0);
		if (parametros.get(key) instanceof String) {
			retorno = new Integer((String) parametros.get(key));
		} else if (parametros.get(key) instanceof Long) {
			retorno = ((Long) parametros.get(key)).intValue();
		} else if (parametros.get(key) instanceof Float) {
			retorno = ((Float) parametros.get(key)).intValue();
		} else if (parametros.get(key) instanceof Double) {
			retorno = ((Double) parametros.get(key)).intValue();
		} else if (parametros.get(key) instanceof Short) {
			return ((Short) parametros.get(key)).intValue();
		} else {
			retorno = (Integer) parametros.get(key);
		}
		return retorno;
	}

	/**
	 * Retorna um valor DOUBLE contido em um MAP
	 * 
	 * @param key
	 * @param parametros
	 * @return Integer
	 */
	public static Integer getIntegerValueNotNullInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return new Integer(0);
		}
		String valor = parametros.get(key).toString();
		if (!isNumeric(valor)) {
			return new Integer(0);
		}
		Integer retorno = new Integer(0);
		if (parametros.get(key) instanceof String) {
			retorno = new Integer((String) parametros.get(key));
		} else if (parametros.get(key) instanceof Long) {
			retorno = ((Long) parametros.get(key)).intValue();
		} else if (parametros.get(key) instanceof Float) {
			retorno = ((Float) parametros.get(key)).intValue();
		} else if (parametros.get(key) instanceof Double) {
			retorno = ((Double) parametros.get(key)).intValue();
		} else if (parametros.get(key) instanceof Short) {
			return ((Short) parametros.get(key)).intValue();
		} else {
			retorno = (Integer) parametros.get(key);
		}
		return retorno;
	}

	/**
	 * Retorna um valor DOUBLE contido em um MAP
	 * 
	 * @param key
	 * @param parametros
	 * @return Integer
	 */
	@SuppressWarnings("unchecked")
	public static List<Long> getListLongValueInMAP(String key,
			Map<String, Object> parametros) {
		if (!Util.containsInMap(key, parametros)) {
			return null;
		}
		List<Long> retorno = new ArrayList<Long>();
		List<Object> ls = (List<Object>) parametros.get(key);
		for (Object obj : ls) {
			if (obj instanceof Long) {
				retorno.add((Long) obj);
			} else if (obj instanceof Float) {
				retorno.add(Float.valueOf(obj.toString()).longValue());
			} else if (obj instanceof Double) {
				retorno.add(Double.valueOf(obj.toString()).longValue());
			} else if (obj instanceof Integer) {
				retorno.add(Integer.valueOf(obj.toString()).longValue());
			}
		}
		return retorno;
	}

	/**
	 * Extrai de uma Lista de Maps propriedades do Tipo Integer contidas no
	 * mesmo
	 * 
	 * @param key
	 * @param lst
	 * @return
	 */
	public static List<Integer> extractIntegerPropertiesFromList(String key,
			List<Map<String, Object>> lst) {
		List<Integer> retorno = new ArrayList<Integer>();
		for (Map<String, Object> it : lst) {
			if (!Util.containsInMap(key, it)) {
				retorno.add(getIntegerValueInMAP(key, it));
			}
		}
		return retorno;
	}

	/**
	 * Converte um OBJECT para um Long
	 * 
	 * @param obj
	 * @return
	 */
	public static Long parseToLong(Object obj) {
		if (obj instanceof Long) {
			return (Long) obj;
		} else if (obj instanceof Float) {
			return ((Float) obj).longValue();
		} else if (obj instanceof Double) {
			return ((Double) obj).longValue();
		} else if (obj instanceof Integer) {
			return ((Integer) obj).longValue();
		}
		if (!(obj instanceof Number)) {
			throw new RuntimeException("[" + obj.toString() + "] não é NÚMERO.");
		}
		return new Long(0);
	}

	/**
	 * Retorna um texto indicando o valor por extenso. EX: Cento e Viente e Dois
	 * Reais e Treze Centavos. R$ 111.122,13
	 * 
	 * @param valor
	 * @param nomeMoeda
	 *            - Real, Dolar, Euros, etc
	 * @return
	 */
	public static String getValorPorExtenso(Double valor) {
		return new NumeroExtensoUtil(new BigDecimal(valor.doubleValue()))
				.toString();
	}

	/**
	 * Soma o valor de um conjunto de propriedades contidas em um objeto MAP.
	 * 
	 * @param keys
	 * @param lista
	 * @return
	 */
	public static BigDecimal somarAtributos(String[] keys,
			List<Map<String, Object>> lista) {
		Double soma = new Double(0);
		if (lista != null && !lista.isEmpty()) {
			for (Map<String, Object> it : lista) {
				for (String key : keys) {
					if (Util.containsInMap(key, it)) {
						Double valor = getDoubleValueInMAP(key, it);
						soma = soma + valor;
					}
				}
			}
		}
		return normatizar(soma, 4);
	}

	/**
	 * Soma os atributos de um array.
	 * 
	 * @param vetor
	 * @param posicao
	 * @return
	 */
	public static BigDecimal somarPosicaoArray(List<Object[]> vetor, int posicao) {
		Double soma = new Double(0);
		if (vetor != null && !vetor.isEmpty()) {
			for (Object[] it : vetor) {
				soma = soma + getDoubleValueNotNullInArray(it, posicao);
			}
		}
		return normatizar(soma, 4);
	}

	/**
	 * Retorna um valor normatizado com o numero de casas decimais passados como
	 * parametro utilizando RoundingMode.HALF_UP como arredondamento.
	 * 
	 * @param valor
	 * @param casasDecimais
	 * @return
	 */
	public static BigDecimal normatizar(BigDecimal valor, int casasDecimais) {
		if (valor == null) {
			return null;
		}
		return normatizar(valor.doubleValue(), casasDecimais);
	}

	/**
	 * Soma dois valores utilizando a normatização passada de acordo com o Nº de
	 * casas decimais.
	 * 
	 * @param valor1
	 * @param valor2
	 * @param casasDecimais
	 * @return
	 */
	public static BigDecimal somar(BigDecimal valor1, BigDecimal valor2,
			int casasDecimais) {
		if (valor1 == null || valor2 == null) {
			return null;
		}
		valor1 = normatizar(valor1.doubleValue(), casasDecimais);
		valor2 = normatizar(valor2.doubleValue(), casasDecimais);
		return normatizar(
				new Double(valor1.doubleValue() + valor2.doubleValue()),
				casasDecimais);
	}

	/**
	 * Retorna um valor normatizado com o numero de casas decimais passados como
	 * parametro utilizando RoundingMode.HALF_UP como arredondamento.
	 * 
	 * @param valor
	 * @param casasDecimais
	 * @return
	 */
	public static BigDecimal normatizar(Double valor, int casasDecimais) {
		if (valor == null) {
			return null;
		}
		return new BigDecimal(valor).setScale(casasDecimais,
				RoundingMode.HALF_UP);
	}

	/**
	 * Calcula o valor do ajuste para a última parcela
	 * 
	 * @param valorTotal
	 * @param valorParcela
	 * @param numeroParcelas
	 * @return
	 */
	public static BigDecimal calcularValorAjusteDiferencaArredondamento(
			Double valorTotal, Double valorParcela, int numeroParcelas) {
		Double totalParcelas = new Double(valorParcela * numeroParcelas);
		if (totalParcelas != valorTotal) {
			return normatizar(new BigDecimal(valorTotal - totalParcelas), 2);
		}
		return new BigDecimal(0);
	}

	/**
	 * Recupera uma lista de LONG's contidas nos Map's da lista passada como
	 * parametro
	 * 
	 * @param parametros
	 * @param key
	 * @return
	 */
	public static List<Long> extrairLongs(List<Map<String, Object>> parametros,
			String key) {
		if (parametros == null || parametros.isEmpty()) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		for (Map<String, Object> it : parametros) {
			if (Util.containsInMap(key, it))
				ids.add(new Long(it.get(key).toString()));
		}
		return ids;
	}

	/**
	 * Extrai lista de LONG's
	 * 
	 * @param parametros
	 * @param indice
	 * @return
	 */
	public static List<Long> extrairLongs(List<Object[]> parametros, int indice) {
		if (parametros == null || parametros.isEmpty()) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		for (Object[] it : parametros) {
			Long numero = getLongValueInArray(it, indice);
			ids.add(numero);
		}
		return ids;
	}

	/**
	 * Extrai propriedades LONG's inserindo-as em um Set retornando Long's
	 * distintos.
	 * 
	 * @param parametros
	 * @param indice
	 * @param unique
	 * @return
	 */
	public static List<Long> extrairLongsNaoRepetidos(
			List<Object[]> parametros, int indice) {
		if (parametros == null || parametros.isEmpty()) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		for (Object[] it : parametros) {
			Long numero = getLongValueInArray(it, indice);
			if (!ids.contains(numero)) {
				ids.add(numero);
			}
		}
		return ids;
	}
}