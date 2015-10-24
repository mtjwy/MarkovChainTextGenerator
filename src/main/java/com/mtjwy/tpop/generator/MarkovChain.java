package com.mtjwy.tpop.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MarkovChain {

	public static final int PREFIX_SIZE = 2;
	public static final String MARKER = "\n";

	private Prefix prefix;
	private Map<Prefix, List<String>> stateMap; // key = prefix, 
												// value = suffix list

	public MarkovChain() {
		prefix = new Prefix(PREFIX_SIZE, MARKER);
		stateMap = new HashMap<>();
	}

	/*
	 * build the stateMap from a file
	 */
	public void build(File f) {
		Scanner sc2 = null;
		try {
			sc2 = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine());
			while (s2.hasNext()) {
				String s = s2.next();
				add(s);
			}
		}
		add(MARKER);
	}

	/*
	 * add word to suffix list, update prefix
	 */
	private void add(String word) {
		// retrieves the suffix list for the current prefix from the stateMap
		List<String> suf = stateMap.get(prefix);
		
		if (suf == null) {
			suf = new ArrayList<String>();
			stateMap.put(new Prefix(prefix), suf);// make a deep copy of prefix,
												// and use it as key
		}

		// add word to suf list
		suf.add(word);

		// advance the prefix
		prefix.getPref().remove(0);
		prefix.getPref().add(word);
	}

	/*
	 * generate output string from stateMap
	 */
	public String generate(int numWords) {
		prefix = stateMap.keySet().iterator().next();
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numWords; i++) {
			List<String> s = stateMap.get(prefix);
			int r = rand.nextInt(s.size());
			String suf = s.get(r);
			if (suf.equals(MARKER)) {
				break;
			}
			sb.append(suf).append(" ");
			if (i != 0 && i % 8 == 0) {
				sb.append("\n");
			}
			prefix.getPref().remove(0);
			prefix.getPref().add(suf);
		}
		return sb.toString();
	}

	public Map<Prefix, List<String>> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<Prefix, List<String>> stateMap) {
		this.stateMap = stateMap;
	}

	public Prefix getPrefix() {
		return prefix;
	}

	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}

}
