package com.sybase.supqa.tenacious;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RftHtmlLogParser {
	private Document document;

	public RftHtmlLogParser(String logFileName) {
		try {
			document = Jsoup.parse(new File(logFileName), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//25-May-2012 06:04:06.875 PM
	//yyyy年MM月dd日 HH时mm分ss秒
	public Date parseStartTime() {
		Elements elements = document.getElementsByClass("time");
		String dateString = elements.get(0).text();
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS a", Locale.ENGLISH);
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Failed to get date");
		}
	}

}
