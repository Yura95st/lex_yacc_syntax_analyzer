package lex_yacc_syntax_analyzer;

import gi.Grammar;

import java.io.File;

import lex_yacc_syntax_analyzer.Grammars.PascalGrammar;

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			File sourceFile = new File(args[0]);

			Grammar grammar = new PascalGrammar();
			// Grammar grammar = new CGrammar();
			// Grammar grammar = new JavaGrammar();
			
			grammar.interpret(sourceFile);
		}
		catch (Exception exception)
		{
			System.err.println("Error occured:");
			System.err.println(exception);
		}
	}

}
