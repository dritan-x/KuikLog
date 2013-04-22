package com.kuik.log;

import java.util.HashMap;

import android.util.Log;

/**
 * Kuik Log 
 * Quickly log and restrict logs without having to do all the work while being extensible 
 * for your modifications.
 * @author dritan.xhabija@gmail.com
 * @version 1.0
 * 
 * Copyright (C) 2013 Dritan Xhabija
 */
public class KLog {

	//log types constants
	private static final int d = 0;
	private static final int e = 1;
	private static final int i = 2;
	
	//instantiate the default settings
	private static KuikLogSettingsInterface settings = new KuikLogSettings();
	
	private static HashMap <String, Integer> disabledTags;
	/**
	 * Set custom settings as per app needs.
	 * @param client settings object implementing KuikLogSettingsInterface
	 */
	public static void setup(KuikLogSettingsInterface client){
		settings = client;
		disabledTags = null;
	}

	/**
	 * Output a debugging log message to Android's log. 
	 * @param tag an object instance for extracting the class name or tag name as a String.
	 * @param message the log message to display.
	 */
	public static void d(Object tag, String message){
		m(d, tag, message);
	}

	/**
	 * Output an error log message to Android's log. 
	 * @param tag an object instance for extracting the class name or tag name as a String.
	 * @param message the log message to display.
	 */
	public static void e(Object tag, String message){
		m(e, tag, message);
	}
	
	/**
	 * Output an info log message to Android's log. 
	 * @param tag an object instance for extracting the class name or tag name as a String.
	 * @param message the log message to display.
	 */
	public static void i(Object tag, String message){
		m(i, tag, message);
	}
	
	/**
	 * Output an info log message to Android's log by using the default tag specified in the settings.
	 * @param message the log message to display.
	 */
	public static void m(String message){
		m(i, settings.getDefaultTag(), message);
	}
	
	/**
	 * Message method responsible for adding a log message to Android's logging system as defined
	 * in the settings class.
	 * @param type either d, e, i which are defined as constants above.
	 * @param otag object tag - outputs the class name of an object or Strings for tag.
	 * @param message the log message to display.
	 */
	private static void m(int type, Object otag, String message){
		String tag = otag instanceof String ? (String) otag : otag.getClass().getCanonicalName();
		
		//early termination if the tag is not allowed to be shown
		if (!isPermittedToShow(tag)){
			return;
		}
		
		if (type == d && settings.showDlog()){
			Log.d(tag, message);
		} else if (type == e && settings.showElog()){
			Log.e(tag, message);
		}  else if (type == i && settings.showIlog()){
			Log.i(tag, message);
		} 
	}
	
	/**
	 * Whether or not the specified tag is allowed to be shown,
	 * as per the defined settings.
	 * @param tag to check against disabled tags specified in String [] array.
	 * @return whether or not the tag is permitted to be shown.
	 */
	private static boolean isPermittedToShow(String tag){
		//if no disabled tags defined, permit all tags to go through
		if (settings.getDisabledTags()==null || settings.getDisabledTags().length==0){
			return true; //early termination
		}
		
		//re-create the hashmap if size is not the same as the disabled-tags array defined in settings
		if (disabledTags==null || (disabledTags.size()!=settings.getDisabledTags().length)){
			disabledTags = new HashMap<String, Integer>();
			for (String stag : settings.getDisabledTags()){
				disabledTags.put(stag,0);
			}
		}
		
		//simply check index for tag
		return !disabledTags.containsKey(tag);
	}
}
