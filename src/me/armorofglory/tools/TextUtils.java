package me.armorofglory.tools;

import org.apache.commons.lang3.StringUtils;

public class TextUtils extends StringUtils {
	
	public static final int AVERAGE_WIDTH = 6;
	
	public static int pixelWidth(String str) {
		int width = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '§') {
				i++;
			} else {
				width += pixelWidth(c);
			}
		}
		return width;
	}
	
	public static int pixelWidth(char c) {
		switch (c) {
			case '§':
				return -1;
			case '!':
			case '.':
			case ',':
			case ':':
			case ';':
			case 'i':
			case '|':
				return 2;
			case '\'':
			case '`':
			case 'l':
				return 3;
			case ' ':
			case 'I':
			case '[':
			case ']':
			case 't':
				return 4;
			case '"':
			case '(':
			case ')':
			case '*':
			case '<':
			case '>':
			case 'f':
			case 'k':
			case '{':
			case '}':
				return 5;
			case '@':
			case '~':
				return 7;
			default:
				return 6;
		}
	}
	
	public static String pixelRepeat(char c, int width) {
		if (width <= 0)
			return "";
		return repeat(c, width / pixelWidth(c));
	}
	
	public static String pixelRepeat(String s, int width) {
		if (width <= 0)
			return "";
		return repeat(s, width / pixelWidth(s));
	}
	
	public static String pixelPadRight(String str, int width, char c) {
		return str + pixelRepeat(c, width - pixelWidth(str));
	}
	
	public static String pixelPadRight(String str, int width) {
		return pixelPadRight(str, width, ' ');
	}
	
	public static String pixelPadLeft(String str, int width, char c) {
		return pixelRepeat(c, width - pixelWidth(str)) + str;
	}
	
	public static String pixelPadLeft(String str, int width) {
		return pixelPadLeft(str, width, ' ');
	}
	
	public static int pixelIndex(String str, int width) {
		//TODO FIXME
		
		if (str.length() == 0) {
			return 0;
		}
		
		int i = 0;
		int pw = pixelWidth(str.charAt(0));
		
		while (width >= pw && i < str.length() - 1) {
			width -= pw;
			i++;
			pw = pixelWidth(str.charAt(i));
		}
		
		return i;
	}
	
	public static String pixelSubstring(String str, int start, int stop) {
		return "";
	}
	
}
