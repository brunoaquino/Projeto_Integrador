package br.com.ECommerce.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HoraUtil {

	private static final String FMT24H = "HH:mm";

	public static String formatarHoras24H(Date data) {
		return new SimpleDateFormat(FMT24H).format(data);
	}

}
