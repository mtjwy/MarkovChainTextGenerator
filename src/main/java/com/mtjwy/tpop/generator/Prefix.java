package com.mtjwy.tpop.generator;


import java.util.ArrayList;
import java.util.List;

/**
 * Here a Prefix is a phrase consisted of a sequence of adjacent words in a text.
 */
public class Prefix {
	private static final int MULTIPLIER = 31; // for hashCode()

	private List<String> pref;//store each word of prefix in list
	
	public Prefix(Prefix p) {
		//make deep copy 
		pref = new ArrayList<String>();
		for (String s : p.getPref()) {
			pref.add(s);
		}
	}
	
	public Prefix(int n, String str) {
		pref = new ArrayList<String>();
		for(int i = 0; i < n; i++) {
			pref.add(str);
		}
	}

	public List<String> getPref() {
		return pref;
	}

	public void setPref(List<String> pref) {
		this.pref = pref;
	}
	
	/*
	 * Prefix object will be used as key in HashMap,
	 * so need to rewrite hashCode for it. 
	 */
	@Override
	public int hashCode() {
		int h = 17;
		for (String s : pref) {
			h = MULTIPLIER * h + s.hashCode();
		}
		return h;
	}
	
	/*
	 * Need to rewrite hashCode() and equals() at the same time
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		final Prefix p = (Prefix) o;
		for (int i = 0; i < pref.size(); i++) {
			if (!pref.get(i).equals(p.getPref().get(i))) {
				return false;
			}
		}
		return true;
	}
}
