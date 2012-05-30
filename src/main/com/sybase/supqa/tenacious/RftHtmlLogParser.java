package com.sybase.supqa.tenacious;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.Period;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RftHtmlLogParser {
	private Document document;
	private Elements noteElements;
	private Elements timeElements;
	private Elements failElements;
	
	private static final String[] MONTHS={"Jan", "Feb", "Mar", "Apr", "May", 
		"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

	public RftHtmlLogParser(String logFileName) {
		try {
			document = Jsoup.parse(new File(logFileName), "UTF-8");
			noteElements = document.getElementsByClass("note");
			timeElements = document.getElementsByClass("time");
			failElements = document.getElementsByClass("fail");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	List<String> getAllNotes(){
		List<String> records = new ArrayList<String>();
		for(int i=0;i<noteElements.size();i++){
			Element element = noteElements.get(i);
			System.out.println(element.text());
			records.add(element.text());
		}
		return records;
	}
	
	public List<String> getAllFailures(){
		List<String> failures = new ArrayList<String>();
		for(int i=0;i<failElements.size();i++){
			Element element = failElements.get(i);
			Element timeElement = element.nextElementSibling();
			Element noteElement = timeElement.nextElementSibling();
			System.out.println(element.text());
			System.out.println(timeElement.text());
			System.out.println(noteElement.text());
			failures.add(element.text());
		}
		return failures;
	}

	public String getStartTimeString() {
		return timeElements.get(0).text();
	}
	
	public String getEndTimeString(){
		return timeElements.get(timeElements.size()-1).text();
	}

	public List<String> getVerificationPoints() {
		List<String> vp = new ArrayList<String>();
		for(int i=0;i<noteElements.size();i++){
			String text = noteElements.get(i).text();
			if(text.startsWith("Verification Point")){
				vp.add(text.replace("Verification Point ", ""));
			}
		}
		return vp;
	}
	
	public List<String> getFailedVerificationPoints() {
		List<String> failedvp = new ArrayList<String>();
		List<String> vp = getVerificationPoints();
		for(String str:vp){
			if(str.endsWith("failed.")){
				failedvp.add(str.replace("Verification Point ", "").replace(" failed.", ""));
			}
		}
		return failedvp;
	}

	public Period getExecutionPeriod() {
		long start = parseDateString(getStartTimeString()).getTime();
		long end = parseDateString(getEndTimeString()).getTime();
		return new Period(end -start);
	}

	//25-May-2012 07:25:25.421 PM
	Date parseDateString(String dateString) {
		String str = convertMonth(dateString);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy hh:mm:ss.SSS a", Locale.US);
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to parse date string");
		}
	}

	String convertMonth(String dateString) {
		DecimalFormat format = new DecimalFormat("00");
		for(int i=0;i<12;i++){
			dateString = dateString.replace(MONTHS[i], format.format(i+1));
		}
		return dateString;
	}

	public String getException() {
		for(int i=0;i<failElements.size();i++){
			Element element = failElements.get(i);
			Element timeElement = element.nextElementSibling();
			Element noteElement = timeElement.nextElementSibling();
			if(!noteElement.text().startsWith("Verification Point")){
				return noteElement.text();
			}
		}
		return null;
	}


}
