package com.codenoted.uploader.utils;

import java.text.Format;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.helpers.Transform;

public class HTMLLayout extends Layout {

	protected final int BUF_SIZE = 256;
	protected final int MAX_CAPACITY = 1024;

	static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
	
	
	private static final Format FORMATTER = FastDateFormat.getInstance("HH:mm:ss, EEE, MMM dd", Locale.UK);
	
   
 
	public static final String TITLE_OPTION = "Title";

	// Print no location info by default
	boolean locationInfo = false;

	String title = "Log4J Log Messages";

	public void setLocationInfo(boolean flag){
		locationInfo = flag;
	}

	/**
	 * Returns the current value of the <b>LocationInfo</b> option.
	 */
	public boolean getLocationInfo() {
		return locationInfo;
	}

	/**
	 * The <b>Title</b> option takes a String value. This option sets the document
	 * title of the generated HTML document.
	 * 
	 * <p>
	 * Defaults to 'Log4J Log Messages'.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the current value of the <b>Title</b> option.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the content type output by this layout, i.e "text/html".
	 */
	public String getContentType() {
		return "text/html";
	}

	/**
	 * No options to activate.
	 */
	public void activateOptions() {
	}

	public String format(LoggingEvent event) {
		
		StringBuilder sb = new StringBuilder(BUF_SIZE);
		
		if (sb.capacity() > MAX_CAPACITY) {
			sb = new StringBuilder(BUF_SIZE);
		} else {
			sb.setLength(0);
		}

		sb.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);

		sb.append("<td bgcolor=\"#cadce3\" >");
		sb.append(FORMATTER.format(new Date(event.timeStamp)));
		sb.append("</td>" + Layout.LINE_SEP);

		String escapedThread = Transform.escapeTags(event.getThreadName());
		sb.append("<td bgcolor=\"#e4edf1\" title=\"" + escapedThread + " thread\">");
		sb.append(escapedThread);
		sb.append("</td>" + Layout.LINE_SEP);

		sb.append("<td bgcolor=\"#e4edf1\" title=\"Level\">");
		if (event.getLevel().equals(Level.DEBUG)) {
			sb.append("<font color=\"#339933\">");
			sb.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sb.append("</font>");
		} else if (event.getLevel().isGreaterOrEqual(Level.WARN)) {
			sb.append("<font color=\"#993300\"><strong>");
			sb.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sb.append("</strong></font>");
		} else {
			sb.append(Transform.escapeTags(String.valueOf(event.getLevel())));
		}
		sb.append("</td>" + Layout.LINE_SEP);

		String escapedLogger = Transform.escapeTags(event.getLoggerName());
		sb.append("<td bgcolor=\"#e4edf1\" title=\"" + escapedLogger + " category\">");
		sb.append(escapedLogger);
		sb.append("</td>" + Layout.LINE_SEP);

		if (locationInfo) {
			LocationInfo locInfo = event.getLocationInformation();
			sb.append("<td bgcolor=\"#e4edf1\" >");
			sb.append(Transform.escapeTags(locInfo.getFileName()));
			sb.append(':');
			sb.append(locInfo.getLineNumber());
			sb.append("</td>" + Layout.LINE_SEP);
		}

		sb.append("</tr><tr>" + Layout.LINE_SEP);
		
		sb.append("<td colspan = \"5\" title=\"Message\">");
		sb.append(Transform.escapeTags(event.getRenderedMessage()));
		sb.append("</td></tr>" + Layout.LINE_SEP);
		

		if (event.getNDC() != null) {
			sb.append("<tr><td  style=\"font-size : x-small;\" colspan=\"5\" title=\"Nested Diagnostic Context\">");
			sb.append("NDC: " + Transform.escapeTags(event.getNDC()));
			sb.append("</td></tr>" + Layout.LINE_SEP);
		}

		String[] s = event.getThrowableStrRep();
		if (s != null) {
			sb.append("<tr><td style=\"font-size : x-small;\" colspan=\"5\">");
			appendThrowableAsHTML(s, sb);
			sb.append("</td></tr>" + Layout.LINE_SEP);
		}

		return sb.toString();
	}

	void appendThrowableAsHTML(String[] s, StringBuilder sb) {
		if (s != null) {
			int len = s.length;
			if (len == 0)
				return;
			sb.append(Transform.escapeTags(s[0]));
			sb.append(Layout.LINE_SEP);
			for (int i = 1; i < len; i++) {
				sb.append(TRACE_PREFIX);
				sb.append(Transform.escapeTags(s[i]));
				sb.append(Layout.LINE_SEP);
			}
		}
	}

	/**
	 * Returns appropriate HTML headers.
	 */
	public String getHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"+ Layout.LINE_SEP);
		sb.append("<html>" + Layout.LINE_SEP);
		sb.append("<head>" + Layout.LINE_SEP);
		sb.append("<title>" + title + "</title>" + Layout.LINE_SEP);
		sb.append("<style type=\"text/css\">" + Layout.LINE_SEP);
		sb.append("<!--" + Layout.LINE_SEP);
		sb.append("body {font-family: verdana,sans-serif; font-size: x-small;}" + Layout.LINE_SEP);
		sb.append("table { border-width: 1px 1px 1px 1px; border-spacing: 0; border-collapse: collapse; font-family: verdana,sans-serif; font-size: x-small;}" + Layout.LINE_SEP);
		sb.append("th {background:#95B9C7; color: #FFFFFF; text-align: left;}"	+ Layout.LINE_SEP);
		sb.append("td {margin: 0; padding: 4px; border-width: 1px 1px 0 0; }"	+ Layout.LINE_SEP);
		sb.append("-->" + Layout.LINE_SEP);
		sb.append("</style>" + Layout.LINE_SEP);
		sb.append("</head>" + Layout.LINE_SEP);
		sb.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">"	+ Layout.LINE_SEP);
		sb.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
		sb.append("Log session start time " + new java.util.Date() + "<br>"	+ Layout.LINE_SEP);
		sb.append("<br>" + Layout.LINE_SEP);
		sb.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">"	+ Layout.LINE_SEP);
		sb.append("<tr>" + Layout.LINE_SEP);
		sb.append("<th>Time</th>" + Layout.LINE_SEP);
		sb.append("<th>Thread</th>" + Layout.LINE_SEP);
		sb.append("<th>Level</th>" + Layout.LINE_SEP);
		sb.append("<th>Category</th>" + Layout.LINE_SEP);
		if (locationInfo) {
			sb.append("<th>File:Line</th>" + Layout.LINE_SEP);
		}
		//sb.append("<th>Message</th>" + Layout.LINE_SEP);
		sb.append("</tr>" + Layout.LINE_SEP);
		return sb.toString();
	}

	/**
	 * Returns the appropriate HTML footers.
	 */
	public String getFooter() {
		StringBuilder sb = new StringBuilder();
		sb.append("</table>" + Layout.LINE_SEP);
		sb.append("<br>" + Layout.LINE_SEP);
		sb.append("</body></html>");
		return sb.toString();
	}

	/**
	 * The HTML layout handles the throwable contained in logging events. Hence,
	 * this method return <code>false</code>.
	 */
	public boolean ignoresThrowable() {
		return false;
	}
}
