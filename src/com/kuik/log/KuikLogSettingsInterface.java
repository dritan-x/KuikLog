package com.kuik.log;

/**
 * Interface defining settings to use.
 * 
 * @author dritan.xhabija@gmail.com
 * Copyright (C) 2013 Dritan Xhabija
 */
public interface KuikLogSettingsInterface {

	/**
	 * @return whether or not to show debugging log messages.
	 */
	public boolean showDlog();
	/**
	 * @return whether or not to show error log messages.
	 */
	public boolean showElog();
	/**
	 * @return whether or not to show info log messages.
	 */
	public boolean showIlog();
	
	/**
	 * @return the default tag to use with KLog.m().
	 */
	public String getDefaultTag();
	
	/**
	 * @return array containing tag strings that should not be queued
	 * to Android's logging system.
	 */
	public String [] getDisabledTags();
}
