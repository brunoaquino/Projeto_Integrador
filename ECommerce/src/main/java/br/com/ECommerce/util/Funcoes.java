package br.com.ECommerce.util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
public class Funcoes {

	public Funcoes() {
	}

	/**
	 * Calcula a diferenca entre duas datas e formata o resultado
	 * 
	 * 
	 * @param String
	 *            dataInicial, data inicial
	 * @param String
	 *            dataFinal, data final
	 * @return String
	 */
	public static String diferencaDatasFormatado(String dataFinal,
			String dataInicial) {
		long dif[] = diferencaDatas(dataFinal, dataInicial);
		if (dif == null)
			return "";
		String stRetorno = "";
		if (dif[0] > 0)
			stRetorno = dif[0] + " dias ";
		stRetorno += dif[1] + "h" + dif[2] + "m" + dif[3] + "s";
		return stRetorno;
	}

	public static String iniciaisNome(String nome) {
		String[] stNomes = nome.split(" ");
		StringBuffer stRetorno = new StringBuffer("");

		for (int i = 0; i < stNomes.length; i++) {
			// todas com duas posições
			if (stNomes[i].length() > 2)
				if (!stNomes[i].equalsIgnoreCase("dos")
						|| !stNomes[i].equalsIgnoreCase("das")) {
					stRetorno.append(stNomes[i].substring(0, 1).toUpperCase());
				}
		}
		return stRetorno.toString();
	}

	/**
	 * Calcula a diferenca de duas datas
	 * 
	 * @autor Jair Gomes
	 * 
	 * @param String
	 *            dataInicial, data inicial
	 * @param String
	 *            dataFinal, data final
	 * @return int[]
	 */
	public static long[] diferencaDatas(String dataFinal, String dataInicial) {
		if (dataInicial == null || dataInicial.trim().equals("")
				|| dataFinal == null || dataFinal.trim().equals(""))
			return null;

		return diferencaDatas(DataHora(dataFinal), DataHora(dataInicial));
	}

	/**
	 * Calcula a diferença de duas datas ATENCAO: Nesta funcao assume-se que um
	 * mes possui 30 dias
	 * 
	 * 
	 * @param Date
	 *            dataInicial, data inicial
	 * @param Date
	 *            dataFinal, data final
	 * @return int[]
	 */
	public static long[] diferencaDatas(Date dataFinal, Date dataInicial) {
		long diferenca = dataFinal.getTime() - dataInicial.getTime();

		long dias = diferenca / 86400000;
		if (dias > 0) {
			diferenca -= dias * 86400000;
		}
		int horas = (int) diferenca / 3600000;
		if (horas > 0) {
			diferenca -= horas * 3600000;
		}
		int minutos = (int) diferenca / 60000;
		if (minutos > 0) {
			diferenca -= minutos * 60000;
		}
		int segundos = (int) diferenca / 1000;
		if (segundos > 0) {
			diferenca -= segundos * 1000;
		}
		return new long[] { dias, horas, minutos, segundos };
	}

	public static String formatarTempo(long tempo) {

		long dias = tempo / 86400000;

		if (dias > 0) {
			tempo -= dias * 86400000;
		}
		int horas = (int) tempo / 3600000;
		if (horas > 0) {
			tempo -= horas * 3600000;
		}
		int minutos = (int) tempo / 60000;
		if (minutos > 0) {
			tempo -= minutos * 60000;
		}
		int segundos = (int) tempo / 1000;
		if (segundos > 0) {
			tempo -= segundos * 1000;
		}
		return dias + " dia(s) " + horas + " hora(s) " + minutos
				+ " minuto(s) " + segundos + " segundo(s)";
	}

	/**
	 * Calcula a diferença entre datas e retorna a quantidade de dias
	 * 
	 * @param dataInicial
	 *            , dataFinal
	 * @return quantidade de dias
	 * @throws Exception
	 */
	public static int calculaDiferencaEntreDatas(String dataInicial,
			String dataFinal) throws Exception {
		long DAY = 24L * 60L * 60L * 1000L;

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = df.parse(dataInicial);
			d2 = df.parse(dataFinal);
		} catch (Exception e) {
			throw new Exception(
					"<{Erro ao Calcular a Diferen�a Entre Datas para Retornar a Quantidade de Dias.}> Local Exception: Funcoes.calculaDiferencaEntreDatas(): "
							+ e.getMessage(), e);
		}
		int dif = (int) ((d2.getTime() - d1.getTime()) / DAY);
		return dif;
	}

	public static String completarZeros(String valor, int qtd) {
		StringBuffer stTemp = new StringBuffer(valor);

		for (int i = 0; i < (qtd - valor.length()); i++)
			stTemp.insert(0, "0");

		return stTemp.toString();
	}

	public static String calcula_mod97(long NNNNNNN, long AAAA, long JTR,
			long OOOO) {

		String valor2 = "";
		String valor3 = "";

		valor2 = (NNNNNNN % 97) + preencheZeros(AAAA, 4)
				+ preencheZeros(JTR, 3);

		valor3 = (Long.parseLong(valor2) % 97) + preencheZeros(OOOO, 4) + "00";

		return preencheZeros(98 - (Long.parseLong(valor3) % 97), 2);
	}

	public static boolean valida_mod97(long NNNNNNN, long DD, long AAAA,
			long JTR, long OOOO) {
		String valor1 = "";
		long resto1 = 0;
		String valor2 = "";
		long resto2 = 0;
		String valor3 = "";
		valor1 = preencheZeros(NNNNNNN, 7);
		resto1 = Long.parseLong(valor1) % 97;
		valor2 = preencheZeros(resto1, 2) + preencheZeros(AAAA, 4)
				+ preencheZeros(JTR, 3);
		resto2 = Long.parseLong(valor2) % 97;
		valor3 = preencheZeros(resto2, 2) + preencheZeros(OOOO, 4)
				+ preencheZeros(DD, 2);
		return ((Long.parseLong(valor3) % 97) == 1);
	}

	public static String preencheZeros(long numero, int quantidade) {
		String temp = String.valueOf(numero);
		String retorno = "";
		if (quantidade < temp.length())
			return temp;
		else {
			for (int i = 0; i < (quantidade - temp.length()); i++)
				retorno = "0" + retorno;
			return retorno + temp;
		}
	}

	/**
	 * Este tratamento serve tanto para impedir ataques como tambem possibilitar
	 * os usuarios a armazenar alguns caracteres especiais em alguns dados
	 * 
	 * 
	 * @return String
	 */
	public static String tratamento(String valor) {
		// Contra barras
		String contraBarra[] = { "\\", "\"", "'" };

		for (int i = 0; i < contraBarra.length; i++) {
			valor = valor.replace(contraBarra[i], "\\" + contraBarra[i]);
		}

		return valor;
	}

	public static String SenhaMd5(String senha) throws Exception {
		String tempSenha;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(senha.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			tempSenha = hash.toString(64);
		} catch (NoSuchAlgorithmException ns) {
			throw ns;
		}
		return tempSenha;
	}

	public static Date StringToDate(String data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.parse(data);

	}

	public static Date StringToDateTime(String data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		return sdf.parse(data);

	}

	public static String BancoString(String valor) throws Exception {

		String stRetorno = valor.toLowerCase();

		String[] stPalavrasChaves = { "select ", "delete ", "update ", "from ",
				"alter table", "alter column", "alter database", "alter view",
				"alter procedure", "drop ", "onblur(", "onclick(",
				"ondblclick(", "onfocus(", "onkeydown(", "onkeypress(",
				"onkeyup(", "onmousedown(", "onmousemove(", "onmouseout(",
				"onmouseover(", "onmouseup(", "onload(", "<script" };

		for (int i = 0; i < stPalavrasChaves.length; i++)
			if (stRetorno.indexOf(stPalavrasChaves[i]) != -1)
				throw new Exception(
						"<{Tentativa de utilização de palavra não permitida: "
								+ stPalavrasChaves[i]
								+ "}> Local Exception: Funcoes.BancoString(): Tentativa de utilização de palavra não permitida: "
								+ stPalavrasChaves[i]);

		valor = tratamento(valor);

		if (valor.length() >= 1)
			stRetorno = "'" + valor.trim() + "'";
		else
			stRetorno = "Null";

		// caracteres reservados
		stRetorno = stRetorno.replace("@", "�");
		stRetorno = stRetorno.replace("#", "�");
		stRetorno = stRetorno.replace("$", "�");

		return stRetorno;
	}

	public static String BancoStringSimples(String valor) throws Exception {
		String stRetorno = "Null";

		valor = tratamento(valor);

		if (valor.length() >= 1)
			stRetorno = "'" + valor.trim() + "'";

		return stRetorno;
	}

	public static String BancoInteiro(String valor) {
		String stRetorno = "Null";
		if (valor.length() >= 1)
			stRetorno = valor;
		return stRetorno;
	}

	public static String BancoDecimal(String valor) {
		String stRetorno = "Null";
		if (valor.length() >= 1)
			stRetorno = valor.replace(".", "").replace(",", ".").trim();
		return stRetorno;
	}

	/**
	 * Retorna valor no formato 9.999,99
	 */
	public static String FormatarDecimal(String valor) {
		String stRetorno = "";
		if (valor != null && valor.length() >= 1 && !valor.equals("0.00")) {
			stRetorno = valor.replace(".", ",");
			stRetorno = new DecimalFormat("###,###.00").format(Double
					.parseDouble(valor));
		} else {
			if (valor != null && valor.equals("0.00"))
				stRetorno = "0,00";
		}
		return stRetorno;
	}

	public static String BancoLogico(String valor) {
		String stRetorno = "0";

		if (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("t"))
			stRetorno = "1";
		else if (valor.equalsIgnoreCase("false") || valor.equalsIgnoreCase("f"))
			stRetorno = "0";
		else if (valor.equalsIgnoreCase("1") || valor.equalsIgnoreCase("0"))
			stRetorno = valor;
		return stRetorno;
	}

	public static boolean BancoLogicoBoolean(String valor) {
		boolean boRetorno = false;

		if (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("t"))
			boRetorno = true;
		else if (valor.equalsIgnoreCase("false") || valor.equalsIgnoreCase("f"))
			boRetorno = false;
		return boRetorno;
	}

	public static String FormatarLogico(String valor) {
		String stRetorno = "";
		if ((valor != null) && (valor.equalsIgnoreCase("1")))
			stRetorno = "true";
		else
			stRetorno = "false";
		return stRetorno;
	}

	public static String FormatarData(String valor) {
		String stRetorno = "";
		if (valor != null)
			if (valor != "")
				stRetorno = valor.substring(8, 10) + "/"
						+ valor.substring(5, 7) + "/" + valor.substring(0, 4);
		return stRetorno;
	}

	/**
	 * Método para formatar a data no estilo: 20101231 para o código de barra.
	 * 
	 * @param String
	 *            valor
	 * @return String
	 */
	public static String FormatarDataCodigoBarraGuia(String valor) {
		String stRetorno = "";
		if (valor != null) {
			if (!valor.equals("")) {
				stRetorno = valor.substring(6) + valor.substring(3, 5)
						+ valor.substring(0, 2);
			}
		}

		return stRetorno;
	}

	/**
	 * Método para formatar o número da guia e a sórie para apresentação na
	 * guia.
	 * 
	 * @param String
	 *            numeroGuia
	 * @return String numeroGuiaFormatada
	 */
	public static String FormatarNumeroSerieGuia(String numeroGuia) {
		String stRetorno = "";
		if (numeroGuia != null && numeroGuia.length() > 0) {
			int digito_Serie = numeroGuia.length() - 3;

			String numero = numeroGuia.substring(0, digito_Serie);
			String digito = numeroGuia.substring(digito_Serie).substring(0, 1);
			String serie = numeroGuia.substring(numeroGuia.length() - 2);

			stRetorno = numero + "-" + digito + "/" + serie;
		}
		return stRetorno;
	}

	/**
	 * Método para formatar a sequ�ncia do código de barra para adicionar
	 * osdígitos verificadores a cada 11 caracteres.
	 * 
	 * @param String
	 *            codigoBarra
	 * @param String
	 *            digito1
	 * @param String
	 *            digito2
	 * @param String
	 *            digito3
	 * @param String
	 *            digito4
	 * @return String
	 */
	public static String FormatarCodigoBarraGuiaDigito(String codigoBarra,
			String digito1, String digito2, String digito3, String digito4) {
		String stRetorno = "";

		if (codigoBarra != null) {
			if (!codigoBarra.equals("")) {
				stRetorno = codigoBarra.substring(0, 11) + "-" + digito1 + " "
						+ codigoBarra.substring(11, 22) + "-" + digito2 + " "
						+ codigoBarra.substring(22, 33) + "-" + digito3 + " "
						+ codigoBarra.substring(33) + "-" + digito4;
			}
		}

		return stRetorno;
	}

	/**
	 * Retorna somente a hora para um Data passada (completa), no formato
	 * HH:mm:ss ou HH:mm
	 * 
	 * @param valor
	 * @return
	 */
	public static String FormatarHora(String valor) {
		String stRetorno = "";
		if (valor != null && !valor.equalsIgnoreCase("")) {
			if (valor.length() == 16) { // Data passada sem os segundos (HH:mm)
				stRetorno = valor.substring(11, 13) + ":"
						+ valor.substring(14, 16);
			} else {
				stRetorno = valor.substring(11, 13) + ":"
						+ valor.substring(14, 16) + ":"
						+ valor.substring(17, 19);
			}
		}
		return stRetorno;
	}

	public static String BancoData(String valor) {
		String stRetorno = "Null";

		String stAno;
		String stMes;
		String stDia;

		if (valor != null) {
			if (valor.length() >= 10) {

				if (valor.substring(2, 3).equalsIgnoreCase("/")
						&& valor.substring(5, 6).equalsIgnoreCase("/")) {
					stAno = valor.substring(6, 10);
					stMes = valor.substring(3, 5);
					stDia = valor.substring(0, 2);
					stRetorno = "'" + stAno + "-" + stMes + "-" + stDia + "'";
				}

			}
		}
		return stRetorno;
	}

	// TODO Fred: Ver o nome deste método
	public static String BancoDataLiteral(String valor) {
		String stRetorno = "Null";

		String stAno;
		String stMes;
		String stDia;

		if (valor != null) {
			if (valor.length() == 8) {
				stAno = valor.substring(4, 8);
				stMes = valor.substring(2, 4);
				stDia = valor.substring(0, 2);
				stRetorno = "'" + stAno + "-" + stMes + "-" + stDia + "'";
			}
		}

		return stRetorno;
	}

	/*
	 * 25/03/1978 dia (0, 1) 2 (1, 2) 5 (2, 3) / mes (3, 4) 0 (4, 5) 3 (5, 6) /
	 * ano (6, 7) 1 (7, 8) 9 (8, 9) 7 (9, 10) 8
	 * 
	 * 1978-03-25 ano (0, 1) 1 (1, 2) 9 (2, 3) 7 (3, 4) 8 (4, 5) - mes (5, 6) 0
	 * (6, 7) 3 (7, 8) - dia (8, 9) 2 (9, 10) 5
	 */

	public static String TelaData(String data) {

		String stRetorno = " ";
		String stAno;
		String stMes;
		String stDia;

		// System.out.println("......Funcoes.TelaData " + data);

		if (data != null)
			if (data.length() >= 10) {
				data = data.trim();
				// System.out.println(data.substring(4, 5));
				// System.out.println(data.substring(7, 8));
				// System.out.println(data.substring(2, 3));
				// System.out.println(data.substring(5, 6));
				if (data.substring(4, 5).equalsIgnoreCase("-")
						&& data.substring(7, 8).equalsIgnoreCase("-")) {
					// System.out.println(" " + data);
					// System.out.println(" " + data.substring(4, 5));
					// System.out.println(" " + data.substring(7, 8));
					// System.out.println(" " + data.substring(0, 4));
					// System.out.println(" " + data.substring(5, 7));
					// System.out.println(" " + data.substring(8, 10));

					stAno = data.substring(0, 4);
					stMes = data.substring(5, 7);
					stDia = data.substring(8, 10);
					stRetorno = stDia + "/" + stMes + "/" + stAno;
				} else if (data.substring(2, 3).equalsIgnoreCase("/")
						&& data.substring(5, 6).equalsIgnoreCase("/")) {
					// System.out.println(" " + data);
					// System.out.println(" " + data.substring(4, 5));
					// System.out.println(" " + data.substring(7, 8));
					// System.out.println(" " + data.substring(0, 4));
					// System.out.println(" " + data.substring(5, 7));
					// System.out.println(" " + data.substring(8, 10));

					stRetorno = data;
				}
			}
		// System.out.println("......." + stRetorno);
		return stRetorno;
	}

	/**
	 * Recebe string no formato dd/MM/yyyy HH:mm:ss e converte para um Date
	 */
	public static Date DataHora(String valor) {
		Date data = null;

		SimpleDateFormat FormatoData = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		try {
			data = FormatoData.parse(valor);
		} catch (ParseException e) {
			// sempre que houver erro mostre onde ele ocorreu
			// System.out.println("..Funcoes.DataHora() Erro na conversão da data "
			// + valor);
		}

		return data;

	}

	public static String DataHora(long valor) {
		String stRetorno = "";

		SimpleDateFormat FormatoData = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");

		Date data = new Date(valor);
		stRetorno = FormatoData.format(data);

		return stRetorno;
	}

	/**
	 * Formata uma data para dia/mes/ano hora:minuto:segundo, todos com 2
	 * digitos exceto o ano com 4
	 * 
	 * 
	 * @param Date
	 *            data
	 * @return String
	 */
	public static String DataHora(Date data) {
		SimpleDateFormat FormatoData = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");

		return FormatoData.format(data);
	}

	/**
	 * Formata uma data para mostrar apenas hora:minuto:segundo, todos com 2
	 * digitos
	 * 
	 * @return String
	 */
	public static String Hora(Date data) {
		SimpleDateFormat FormatoData = new SimpleDateFormat("HH:mm:ss");

		return FormatoData.format(data);
	}

	/*
	 * Funções para converter data para data hora no formado que o bd reconhece.
	 */
	public static String BancoDataHora(Date data) {
		String stRetorno = "Null";
		SimpleDateFormat FormatoData = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		if (data != null) {
			stRetorno = "'" + FormatoData.format(data) + "'";
		}
		return stRetorno;
	}

	/*
	 * Fuções para converter data para data no formado que o bd reconhece. Jesus
	 * Rodrigo 30/10/2009
	 */

	public static String BancoData(Date valor) {
		String stRetorno = "Null";

		if (valor != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			stRetorno = "'" + sdf.format(valor) + "'";
		}
		return stRetorno;
	}

	/*
	 * Fuções para converter data para hora no formado que o bd reconhece. Jesus
	 * Rodrigo 30/10/2009
	 */
	public static String BancoHora(Date valor) {
		String stRetorno = "Null";
		if (valor != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			stRetorno = "'" + sdf.format(valor) + "'";
		}
		return stRetorno;
	}

	/**
	 * Método para gerar a Data de Vencimento da Guia. último dia do ano.
	 * 
	 * @return String
	 */
	public static String getUltimoDiaAno() {
		return "31/12/" + (new Date().getYear() + 1900);
	}

	/**
	 * Método para gerar a Data de Vencimento da Guia para o Banco do Brasil no
	 * formato ddMMyyyy. último dia do ano.
	 * 
	 * @return String
	 */
	public static String getUltimoDiaAnoFormatoBB() {
		return "3112" + (new Date().getYear() + 1900);
	}

	/**
	 * Recebe string no formato dd/MM/yyyy HH:mm:ss e converte para yyyy-MM-dd
	 * HH:mm:ss ou yyyy-MM-dd HH:mm
	 * 
	 * 
	 */
	public static String BancoDataHora(String valor) {
		String retorno = "Null";
		String ano;
		String mes;
		String dia;
		String hora;
		String minuto;
		String segundo;

		if (valor != null) {
			// Data: dd/mm/yyyy Hora: hh:mm:ss
			if (valor.length() == 19) {
				if (valor.substring(2, 3).equalsIgnoreCase("/")
						&& valor.substring(5, 6).equalsIgnoreCase("/")
						&& (valor.substring(13, 14).equalsIgnoreCase(":"))
						&& (valor.substring(16, 17).equalsIgnoreCase(":"))) {
					ano = valor.substring(6, 10);
					mes = valor.substring(3, 5);
					dia = valor.substring(0, 2);
					hora = valor.substring(11, 13);
					minuto = valor.substring(14, 16);
					segundo = valor.substring(17, 19);

					retorno = "'" + ano + "-" + mes + "-" + dia + " " + hora
							+ ":" + minuto + ":" + segundo + "'";
				}
			}
			// Data: dd/mm/yyyy Hora: hh:mm
			else if (valor.length() == 16) {

				if (valor.substring(2, 3).equalsIgnoreCase("/")
						&& valor.substring(5, 6).equalsIgnoreCase("/")
						&& (valor.substring(13, 14).equalsIgnoreCase(":"))) {
					ano = valor.substring(6, 10);
					mes = valor.substring(3, 5);
					dia = valor.substring(0, 2);
					hora = valor.substring(11, 13);
					minuto = valor.substring(14, 16);

					retorno = "'" + ano + "-" + mes + "-" + dia + " " + hora
							+ ":" + minuto + "'";
				}
			} else if (valor.equals("")) {
				retorno = "Null";
			} else {
				retorno = valor;
			}
		}
		return retorno;
	}

	/**
	 * Recebe string no formato yyyy-MM-dd HH:mm:ss e converte para dd/MM/yyyy
	 * HH:mm:ss
	 * 
	 * 
	 */
	public static String FormatarDataHora(String valor) {
		String retorno = "";
		if ((valor != null) && (!valor.equalsIgnoreCase(""))) {
			retorno = valor.substring(8, 10) + "/" + valor.substring(5, 7)
					+ "/" + valor.substring(0, 4) + " "
					+ valor.substring(11, 13) + ":" + valor.substring(14, 16)
					+ ":" + valor.substring(17, 19);
		}
		return retorno;
	}

	/**
	 * Recebe string no formato yyyy-MM-dd HH:mm:ss e converte para dd/MM/yyyy
	 * HH:mm, desconsiderando os segundos
	 * 
	 * @author msapaula
	 * @return String
	 */
	public static String FormatarDataHoraMinuto(String valor) {
		String retorno = "";
		if ((valor != null) && (!valor.equalsIgnoreCase(""))) {
			retorno = valor.substring(8, 10) + "/" + valor.substring(5, 7)
					+ "/" + valor.substring(0, 4) + " "
					+ valor.substring(11, 13) + ":" + valor.substring(14, 16);
		}
		return retorno;
	}

	/**
	 * Método que valida o valor do pis
	 * 
	 * @param pis
	 *            é o valor que se deseja checar
	 * @return true se o valor é válido, false caso contrário.
	 */
	public static boolean validaPIS(String pis) throws NumberFormatException {
		Long.parseLong(pis);
		if (pis.length() != 11)
			return false;
		int wdv = Integer
				.parseInt(pis.substring(pis.length() - 1, pis.length()));
		int wsoma = 0;
		int wm11 = 2;
		for (int i = 0; i < 10; i++) {
			wsoma += wm11 * Integer.parseInt(pis.substring(9 - i, 10 - i));
			if (wm11 < 9)
				wm11++;
			else
				wm11 = 2;
		}
		int wdigito = 11 - (wsoma % 11);
		if (wdigito > 9)
			wdigito = 0;
		if (wdv == wdigito)
			return true;
		return false;
	}

	/**
	 * Método que faz a validação de um título de eleitor
	 * 
	 * @param titulo
	 *            é o valor do título
	 * @return True se for uma tórulo de eleitor válido, false caso contrário
	 */
	public static boolean validaTituloEleitor(String titulo)
			throws NumberFormatException {
		Long.parseLong(titulo);
		if (titulo.length() < 12)
			return validaTituloEleitor("0" + titulo);
		if (titulo.length() > 12)
			return false;

		int estado = Integer.parseInt(titulo.substring(8, 10));
		if (estado < 1 || estado > 28)
			return false;

		int soma = 0;
		for (int i = 0; i < 8; i++)
			soma += Integer.parseInt(titulo.substring(i, i + 1)) * (9 - i);
		int resto = soma % 11;
		int digito1;
		if (resto == 0)
			if (estado == 1 || estado == 2)
				digito1 = 1;
			else
				digito1 = 0;
		else if (resto == 1)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		if (digito1 != Integer.parseInt(titulo.substring(10, 11)))
			return false;

		soma = 0;
		for (int i = 8; i < 10; i++)
			soma += Integer.parseInt(titulo.substring(i, i + 1)) * (12 - i);
		soma += digito1 * 2;
		resto = soma % 11;
		int digito2;
		if (resto == 0)
			if (estado == 1 || estado == 2)
				digito2 = 1;
			else
				digito2 = 0;
		else if (resto == 1)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		if (digito2 != Integer.parseInt(titulo.substring(11, 12)))
			return false;

		return true;
	}

	/**
	 * Valida se determinado valor é numérico
	 */
	public static boolean validaNumerico(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Valida se uma data está no formato correto dd/mm/yyyy
	 */
	public static boolean validaData(String data) {
		try {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			@SuppressWarnings("unused")
			Date dataFormatada;
			dataFormatada = formatador.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String FormatarMoeda(String valor) {
		String stRetorno = "";
		if (valor.length() >= 1)
			stRetorno = NumberFormat
					.getCurrencyInstance(new Locale("pt", "BR")).format(
							Double.parseDouble(valor));
		return stRetorno;
	}

	/**
	 * Retorna o número do processo formatado Exemplo: 001.2005.900.001-0
	 */
	public static String formataNumeroProcesso(String numeroProcesso) {
		// if (numeroProcesso != null && numeroProcesso.length() > 0) {
		// if (numeroProcesso.length() == 12)
		// numeroProcesso = "00" + numeroProcesso;
		// if (numeroProcesso.length() == 13)
		// numeroProcesso = "0" + numeroProcesso;
		// numeroProcesso = numeroProcesso.substring(0, 3) + "." +
		// numeroProcesso.substring(3, 7) + "." + numeroProcesso.substring(7,
		// 10) + "." + numeroProcesso.substring(10, 13) + "-" +
		// numeroProcesso.substring(13, 14);
		// }
		return numeroProcesso;
	}

	/**
	 * Desformata um numero de processo
	 * 
	 * @author Ronneesley Moura Teles
	 * @since 08/05/2008 - 14:58
	 * @param String
	 *            numeroProcesso, numero do processo
	 * @return String
	 */
	public static String desformataNumeroProcesso(String numeroProcesso) {
		return numeroProcesso.replace(".", "").replace("-", "");
	}

	/**
	 * Método para retirar o ponto e a v�rgula do valor. Método utilizado para
	 * gerar código de barra e passar como parâmetros para bancos.
	 * 
	 * @param valor
	 * @return String
	 */
	public static String retiraVirgulaPonto(String valor) {
		return valor.trim().replaceAll("[,.]", "");
	}

	/**
	 * @author jpcpresa Compara duas Strings ignorando acentos e caixa.
	 * @param String
	 *            string1, string2
	 * @return boolean true - se são iguais ignorando caixa e acentos. false -
	 *         se são diferentes ignorando caixa e acentos.
	 */

	public static boolean equalsIgnoraAcentoCaixa(String st1, String st2) {
		Collator collator = Collator.getInstance(new Locale("pt", "BR"));
		collator.setStrength(Collator.PRIMARY);
		return collator.compare(st1, st2) == 0;
	}

	/**
	 * @author jpcpresa Retorna os nomes sem acento e �
	 * @param String
	 *            nome para ser limpo.
	 * @return retorna a string alterada.
	 */

	public static String limparNome(String nome) {
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		return nome;
	}

	/**
	 * @author jpcpresa Remove o excesso de espaços em branco em um nome,
	 *         deixando apenas 1 espaço em branco entre cada nome e nenhum antes
	 *         ou depois do nome.
	 * @param String
	 *            nome que ter� os espaços em branco excessivos removidos.
	 * @return String nome sem espaços em brancos em excesso.
	 */

	public static String removeEspacosExcesso(String nome) {
		nome = nome.trim();
		nome = nome.replaceAll("[ ]+", " ");
		return nome;
	}

	public static String limparNome2(String nome) {
		String stNome = nome.toLowerCase();

		String[] stTrocaA = { ",", ")", "&", "�", "�", "(", "_", "\\", "/",
				".", "-", "ltda", " sc", " ss", " sa", " me" };
		String[] stTrocaB = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "" };

		// troco palavras
		for (int i = 0; i < stTrocaA.length; i++)
			stNome = stNome.replace(stTrocaA[i], stTrocaB[i]);

		stNome = stNome.replaceAll("[1234567890]", "");

		return stNome;
	}

	public static String retirarPreposicao(String nome) {
		String stNome = null;
		stNome = nome.replace(" em ", " ").replace(" do ", " ")
				.replace(" dos ", " ").replace(" da ", " ")
				.replace(" das ", " ").replace(" de ", " ").replace(" e ", " ");
		return stNome;
	}

	public static String RetirarAssinantes(String assinantes) {

		Pattern paTeste01 = Pattern.compile(",CN=(.*?),L=");
		Matcher maTeste01 = paTeste01.matcher(assinantes);
		StringBuffer assinador = new StringBuffer("");
		if (maTeste01.find())
			assinador.append(maTeste01.group().replace(",CN=", "")
					.replace(",L=", ""));
		while (maTeste01.find()) {
			assinador.append(" ; "
					+ maTeste01.group().replace(",CN=", "").replace(",L=", ""));
		}

		return assinador.toString();
	}

	public static String nomeArquivoFormatado(String nomeArquivo,
			boolean assinado) {
		// Verifica se e um arquivo fisico
		// if (this.isArquivoFisico())
		int pos = nomeArquivo.lastIndexOf(".");

		// Se nao encontrou o ultimo ponto ou se o arquivo nao e assinado
		if (pos == -1 || !assinado)
			return nomeArquivo;

		// Retira segunda extensao .p7s
		return nomeArquivo.substring(0, nomeArquivo.lastIndexOf("."));
	}

	public static String RetirarLetras(String valor, String caracter) {
		return valor.replaceAll("[sSzZtTdDvVfFbBpP]", caracter).replace(
				caracter + caracter, caracter);
	}

	/**
	 * Func�o para permitir inserir uma String como BLOB - 16/09/2008 17:06
	 * Modificacoes para tratamento dos caracteres da string
	 * 
	 * 
	 */
	public static String BancoByte(String valor) throws Exception {
		String stRetorno = "null"; // Ate que se prove o contrario

		if (valor != null && valor.length() > 0)
			stRetorno = "_binary'" + tratamento(valor) + "'";

		// caracteres reservados
		stRetorno = stRetorno.replace("@", "�");
		stRetorno = stRetorno.replace("#", "�");
		stRetorno = stRetorno.replace("$", "�");

		return stRetorno;
	}

	public static List converterSetParaList(Set set) {
		List lista = null;
		if (set != null)
			lista = new ArrayList(set);

		return lista;
	}

	public static Set converterListParaSet(List lista) {
		Set set = null;
		if (lista != null) {
			set = new HashSet();
			set.addAll(lista);
		}
		return set;
	}

	public static List converterMapParaList(Map map) {
		List lista = null;
		if (map != null) {
			lista = new ArrayList();
			lista.addAll(map.values());
		}
		return lista;
	}

	public static int horaToMinuto(String horaMinuto) {
		String[] horasMinutos = horaMinuto.split(":", -1);

		return (Integer.valueOf(horasMinutos[0]) * 60)
				+ Integer.valueOf(horasMinutos[1]);
	}

	public static List GerarAgenda(String dataInicial, String dataFinal,
			String qtd, String[] configuracoes) throws Exception {

		List lisDatas = new ArrayList();
		Date dtFinal = StringToDate(dataFinal);
		int[] inPosicao = { 0, 3, 6, 9, 12, 15, 18 };

		Calendar calCalendarioGeral = Calendar.getInstance();

		Calendar calCalendarioInicial = Calendar.getInstance();
		Calendar calCalendarioFinal = Calendar.getInstance();

		calCalendarioGeral.setTime(StringToDate(dataInicial));
		do {
			// while (!calCalendarioGeral.after(dtFinal.getTime())){

			int inDia = inPosicao[(calCalendarioGeral
					.get(GregorianCalendar.DAY_OF_WEEK) - 1)];

			if (!configuracoes[inDia].equals("")) {
				calCalendarioInicial.setTime(calCalendarioGeral.getTime());
				calCalendarioFinal.setTime(calCalendarioGeral.getTime());

				String stHoraInicial = configuracoes[inDia];
				String stHoraFinal = configuracoes[inDia + 1];
				int inDuracao = Integer.valueOf(configuracoes[inDia + 2]);

				calCalendarioInicial.add(Calendar.MINUTE,
						horaToMinuto(stHoraInicial));
				// System.out.println(DataHora(calCalendarioInicial.getTime()));

				calCalendarioFinal.add(Calendar.MINUTE,
						horaToMinuto(stHoraFinal));
				// System.out.println(DataHora(calCalendarioFinal.getTime()));

				while (calCalendarioInicial.getTime().getTime() < calCalendarioFinal
						.getTime().getTime()) {
					for (int j = 0; j < Integer.valueOf(qtd); j++)
						lisDatas.add(new Date(calCalendarioInicial.getTime()
								.getTime()));
					calCalendarioInicial.add(Calendar.MINUTE, inDuracao);
				}
			}

			calCalendarioGeral.add(Calendar.DAY_OF_WEEK, 1);
			// System.out.println(DataHora(calCalendarioGeral.getTime()));

		} while (calCalendarioGeral.getTimeInMillis() <= dtFinal.getTime());

		return lisDatas;
	}

	/**
	 * Método responsável em receber uma string e convert�-la em horário, ou
	 * seja, convert�-la em Date que corresponder� ao horário
	 * 
	 * 
	 */
	public static Date stringToTime(String horario) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.parse(horario);
	}

	/**
	 * Método responsável por receber uma data completa e convert�-la para uma
	 * string, considerando apenas a data (dia + más + ano) e desconsiderando o
	 * horário
	 * 
	 * 
	 * @return String = data
	 */
	public static String dateToStringSoData(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(data);
	}

	/**
	 * Método responsável por receber uma data completa e convert�-la para uma
	 * string, considerando apenas o horário e desconsiderando a data (dia + más
	 * + ano)
	 * 
	 * 
	 * @param data
	 * @return String = horário
	 */
	public static String dateToStringSoHorario(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(data);
	}

	/**
	 * Saudacoes quanto ao dia para um visitante
	 * 
	 * 
	 * 
	 * @return String
	 */
	public static String saudacaoDia() {
		String s[] = { "Bom Dia", "Boa Tarde", "Boa Noite" };

		int i = 0;

		Calendar c = Calendar.getInstance();

		if (c.get(Calendar.HOUR_OF_DAY) < 12) {
			i = 0;
		} else {
			if (c.get(Calendar.HOUR_OF_DAY) < 18) {
				i = 1;
			} else {
				i = 2;
			}
		}

		return s[i];
	}

	/**
	 * Converte quantidade de ano/mes/dia em dias
	 * 
	 * 
	 * 
	 * @return String
	 */
	public static String converterParaDias(String qtdeAnos, String qtdeMeses,
			String qtdeDias) {
		if (qtdeAnos.length() == 0)
			qtdeAnos = "0";
		if (qtdeMeses.length() == 0)
			qtdeMeses = "0";
		if (qtdeDias.length() == 0)
			qtdeDias = "0";

		double ano = (double) Integer.parseInt(qtdeAnos) * 365.25;
		double mes = (double) Integer.parseInt(qtdeMeses) * 30.44;
		int dias = (int) (Integer.parseInt(qtdeDias) + mes + ano);

		return Integer.toString(dias);
	}

	/**
	 * Converte quantidade de dias em ano/mes/dia
	 * 
	 * 
	 * @return int
	 */
	public static String converterParaAnoMesDia(int qtdeDias) {
		double tempo = qtdeDias;

		int ano = (int) (tempo / 365.25);
		tempo = tempo % 365.25;

		int mes = (int) (tempo / 30.44);
		if (mes == 12) {
			ano += 1;
			mes -= 12;
		}

		tempo = tempo % 30.44;
		qtdeDias = (int) Math.ceil(tempo);

		if (qtdeDias >= 30) {
			mes += 1;
			qtdeDias = 0;
		}
		if (mes == 12) {
			ano += 1;
			mes -= 12;
		}

		StringBuffer retorno = new StringBuffer();
		if (String.valueOf(ano).length() <= 2)
			retorno.append("00".substring(0, 2 - String.valueOf(ano).length())
					+ ano + " - ");
		else
			retorno.append(ano + " - ");
		retorno.append("00".substring(0, 2 - String.valueOf(mes).length())
				+ mes + " - ");
		retorno.append("00".substring(0, 2 - String.valueOf(qtdeDias).length())
				+ qtdeDias);

		return retorno.toString();
	}

	/**
	 * Verifica o campo de entrada se está de acordo com o parâmetros, se
	 * estiver substitui pelo Retorno informado. No caso da lista de eventos
	 * quando o campo estiver nulo, será substitu�do por "-".
	 * 
	 * @param campo
	 *            , o campo a ser verificado.
	 * @param parametro
	 *            , o parametro que o campo será comparado
	 * @return retorno, se o campo for igual ao parametro retorna o valor
	 *         desejado
	 * 
	 */
	public static String verificarCampo(String campo, String parametro,
			String retorno) {
		if (campo == parametro) {
			return retorno;
		} else
			return campo;
	}

	/**
	 * Método que recebe o número do Más e retorna o nome.
	 * 
	 * @param numeroMes
	 *            - número do más (de 01 a 12)
	 * @return Strin - nome do más (de Janeiro a Dezembro)
	 * 
	 */
	public static String identificarNomeMes(Integer numeroMes) {
		String nomeMes = null;
		switch (numeroMes) {
		case 1:
			nomeMes = "Janeiro";
			break;
		case 2:
			nomeMes = "Fevereiro";
			break;
		case 3:
			nomeMes = "Mar�o";
			break;
		case 4:
			nomeMes = "Abril";
			break;
		case 5:
			nomeMes = "Maio";
			break;
		case 6:
			nomeMes = "Junho";
			break;
		case 7:
			nomeMes = "Julho";
			break;
		case 8:
			nomeMes = "Agosto";
			break;
		case 9:
			nomeMes = "Setembro";
			break;
		case 10:
			nomeMes = "Outubro";
			break;
		case 11:
			nomeMes = "Novembro";
			break;
		case 12:
			nomeMes = "Dezembro";
			break;
		}
		return nomeMes;
	}

	/**
	 * Valida a numeração de processo no formato: NNNNNNN.DD.AAAA.JTR.OOOO Onde:
	 * NNNNNNN = Número do processo DD = Dígito AAAA = Ano JTR = Constante 8.09
	 * OOOO = Código do Forum Ex: 5000280.28.2010.8.09.0059
	 */
	public static boolean validaProcessoNumero(String numeroProcesso)
			throws Exception {

		// Verifica os parâmetross informados...
		if (numeroProcesso == null)
			return false;
		if (numeroProcesso.length() != 25)
			return false;

		// Extrai do número do processo as informações necessórias para
		// validação...
		long NNNNNNN = longTryParse(numeroProcesso.substring(0, 7));
		long DD = longTryParse(numeroProcesso.substring(8, 10));
		long AAAA = longTryParse(numeroProcesso.substring(11, 15));
		long JTR = longTryParse((numeroProcesso.substring(16, 20)).replace(".",
				""));
		long OOOO = longTryParse(numeroProcesso.substring(21, 25));

		// Se o número não for válido é retornado...
		return Funcoes.valida_mod97(NNNNNNN, DD, AAAA, JTR, OOOO);

	}

	/**
	 * Tenta converter uma string para long, caso não consiga o valor zero é
	 * retornado.
	 * 
	 */
	public static long longTryParse(String valor) {
		try {
			return Long.parseLong(valor);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * Retorna o conteúdo da execeção incluindo stack, de toda a pilha de
	 * exceções
	 * 
	 * @param excecao
	 * @return
	 */
	public static String obtenhaConteudoExcecao(Throwable excecao) {
		return obtenhaConteudoExcecao(excecao, false, "");
	}

	/**
	 * Retorna o conteúdo da primeira execeção da pilha incluindo stack
	 * 
	 * @param excecao
	 * @return
	 */
	public static String obtenhaConteudoPrimeiraExcecao(Throwable excecao) {
		if (excecao == null)
			return "";
		return obtenhaConteudoExcecao(excecao, true, excecao.getMessage());
	}

	/**
	 * Obtem o conteúdo da exceção, incluindo stacktrace.
	 * 
	 * Caso seja enviado true para o parametro somentePrimeiraExcecao, é
	 * realizado um desemplilhamento obtendo o conteúdo apenas da exceção
	 * origin�ria.
	 * 
	 * Caso seja enviado false para o parametro somentePrimeiraExcecao, é
	 * realizado um desemplilhamento obtendo o conteúdo de todas as exceções
	 * empilhadas.
	 * 
	 * @param excecao
	 * @param somentePrimeiraExcecao
	 * @param mensagemOriginal
	 * 
	 * @return
	 * 
	 * @autor Jair Gomes
	 */
	private static String obtenhaConteudoExcecao(Throwable excecao,
			boolean somentePrimeiraExcecao, String mensagemOriginal) {
		if (excecao == null)
			return "";
		StringBuffer sb = new StringBuffer();

		try {

			if (somentePrimeiraExcecao && (excecao.getCause() != null)) {
				sb.append(obtenhaConteudoExcecao(excecao.getCause(),
						somentePrimeiraExcecao, mensagemOriginal));
				return sb.toString();
			}

			if (excecao.getClass() != null)
				sb.append("\n CLASS: " + excecao.getClass().toString());
			sb.append("\n LOCALIZE MESSAGE: " + excecao.getLocalizedMessage());

			sb.append("\n MESSAGE: ");
			if (mensagemOriginal != null
					&& mensagemOriginal.trim().length() > 0)
				sb.append(mensagemOriginal);
			else
				sb.append(excecao.getMessage());

			sb.append("\n STACK TRACE: ");

			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			excecao.printStackTrace(printWriter);
			sb.append("\n " + writer.toString());

			if (excecao.getCause() != null) {
				sb.append("\n InnerException: \n");
				sb.append(obtenhaConteudoExcecao(excecao.getCause(),
						somentePrimeiraExcecao, mensagemOriginal));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String BancoAno(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		return simpleDateFormat.format(date);
	}

}