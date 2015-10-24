package com.mtjwy.tpop.generator;

import java.io.File;
/**
 * This program is to generate random English text that reads  well.
 * The approach is to use an existing text to construct a statistical
 * model. The model presents the frequency of appearance of whole 
 * phrases used in the chosen text. From the model, this program generate new text
 * using MarkovChain Algorithm.
 * 
 * reference: The Practice of Programming
 *  
 */
public class MainApplication {
	public static final int MAX_WORDS_GENERATED = 1000;

	public static void main(String[] args) {
		MarkovChain chain = new MarkovChain();
		File file = new File("GoneWithTheWind.txt");
		chain.build(file);
		String newText = chain.generate(MAX_WORDS_GENERATED);
		
		System.out.println(newText);
	}

}
