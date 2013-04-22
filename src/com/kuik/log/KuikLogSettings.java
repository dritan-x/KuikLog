package com.kuik.log;

/**
 * Default settings to use if client does not want to provide custom ones.
 * @author dritan.xhabija@gmail.com
 * Copyright (C) 2013 Dritan Xhabija
 *
 */
public class KuikLogSettings implements KuikLogSettingsInterface {

	@Override
	public boolean showDlog() {
		return true;
	}

	@Override
	public boolean showElog() {
		return true;
	}

	@Override
	public boolean showIlog() {
		return true;
	}

	@Override
	public String getDefaultTag() {
		return "KuikLog";
	}

	@Override
	public String[] getDisabledTags() {
		return null;
	}

	
}
