package lex_yacc_syntax_analyzer.Grammars;

import gi.Grammar;

public class JavaGrammar extends Grammar
{
	public JavaGrammar()
	{
		/**
		 * Grammar for Java 2.0.
		 * Nonterminal - first letter uppercase
		 * TERMINAL - all letters uppercase
		 * keyword - all letters lowercase
		 */
		int INFINITY = -1;
		
		/**
		 * 19.3 Terminals from section 3.6: White Space: [[:space:]]
		 */
		this.put("WHITE_SPACE", PosixClass.space());
		
		/**
		 * 19.3 Terminals from section 3.7: Comment
		 */
		this.put("COMMENT",
			new Union(
			
			//
			// Traditional Comment: /\*[^*]+(\*([^*/][^*]*)?)*\*/
			//
				new Concatenation(new Singleton("/*"), new Concatenation(
					new Repetition(new NonMatch("*"), 1, INFINITY),
					new Concatenation(new Repetition(new Concatenation(
								new Singleton("*"), new Repetition(new Concatenation(
									new NonMatch("*/"), new Repetition(
								new NonMatch("*"), 0, INFINITY)), 0, 1)), 0,
						INFINITY), new Singleton("*/")))), new Union(
				
				/**
				 * End Of Line Comment: //[^\n]*\n
				 */
				new Concatenation(new Singleton("//"), new Concatenation(
					new Repetition(new NonMatch("\n"), 0, INFINITY),
					new Singleton("\n"))),
				
				//
				// Documentation Comment: /\*\*(([^*/][^*]*)?\*)*/
				//
					new Concatenation(new Singleton("/**"), new Concatenation(
						new Repetition(new Concatenation(
							new Repetition(new Concatenation(
								new NonMatch("*/"), new Repetition(
									new NonMatch("*"), 0, INFINITY)), 0, 1),
							new Singleton("*")), 0, INFINITY), new Singleton(
							"/"))))));
		
		this.put("IDENTIFIER", new Concatenation(new Union(PosixClass.alpha(),
			new Match("_$")), new Repetition(new Union(PosixClass.alnum(),
			new Match("_$")), 0, INFINITY)));
		
		/**
		 * 19.3 Terminals from section 3.9: Keyword (recognized but not in the
		 * Java grammar)
		 */
		this.put("KEYWORD", new Union(new Singleton("const"), new Singleton(
			"goto")));
		
		/**
		 * 19.3 Terminals from section 3.10.1: Integer Literal
		 */
		this.put(
			"INTEGER_LITERAL",
			new Concatenation(new Union(
			/**
			 * Decimal Integer Literal: 0|[1-9][[:digit:]]*
			 */
			new Singleton("0"), new Union(
			
			new Concatenation(new Range('1', '9'), new Repetition(PosixClass
					.digit(), 0, INFINITY)), new Union(
			
			/**
			 * Hexadecimal Integer Literal: 0[xX][[:xdigit:]]+
			 */
			new Concatenation(new Singleton("0"), new Concatenation(new Match(
				"xX"), new Repetition(PosixClass.xdigit(), 1, INFINITY))),
			
			/**
			 * Octal Integer Literal: 0[0-7]+
			 */
			new Concatenation(new Singleton("0"), new Repetition(new Range('0',
				'7'), 1, INFINITY))))), new Repetition(new Match("lL"), 0, 1)));
		
		/**
		 * 19.3 Terminals from section 3.10.2: Floating-Point Literal
		 */
		this.put("FLOATING_POINT_LITERAL", new Union(
		
		/**
		 * [[:digit:]]+\.[[:digit:]]*([eE][-+]?[[:digit:]]+)?[fFdD]?
		 */
		new Concatenation(new Repetition(PosixClass.digit(), 1, INFINITY),
			new Concatenation(new Singleton("."), new Concatenation(
						new Repetition(PosixClass.digit(), 0, INFINITY),
				new Concatenation(
					new Repetition(new Concatenation(new Match("eE"),
						new Concatenation(
							new Repetition(new Match("-+"), 0, 1),
							new Repetition(PosixClass.digit(), 1, INFINITY))),
						0, 1), new Repetition(new Match("fFdD"), 0, 1))))),
			new Union(
			
			/**
			 * \.[[:digit:]]+([eE][-+]?[[:digit:]]+)?[fFdD]?
			 */
									new Concatenation(new Singleton("."), new Concatenation(
											new Repetition(PosixClass.digit(), 1, INFINITY),
				new Concatenation(
					new Repetition(new Concatenation(new Match("eE"),
						new Concatenation(
							new Repetition(new Match("-+"), 0, 1),
							new Repetition(PosixClass.digit(), 1, INFINITY))),
						0, 1), new Repetition(new Match("fFdD"), 0, 1)))),
				new Union(
				
				/**
				 * [[:digit:]]+[eE][-+]?[[:digit:]]+[fFdD]?
				 */
				new Concatenation(new Repetition(PosixClass.digit(), 1,
					INFINITY),
					new Concatenation(new Match("eE"),
						new Concatenation(
							new Repetition(new Match("-+"), 0, 1),
							new Concatenation(new Repetition(
								PosixClass.digit(), 1, INFINITY),
								new Repetition(new Match("fFdD"), 0, 1))))),
				
				/**
				 * [[:digit:]]+([eE][-+]?[[:digit:]]+)?[fFdD]
				 */
				new Concatenation(new Repetition(PosixClass.digit(), 1,
					INFINITY), new Concatenation(new Repetition(
					new Concatenation(new Match("eE"), new Concatenation(
						new Repetition(new Match("-+"), 0, 1), new Repetition(
							PosixClass.digit(), 1, INFINITY))), 0, 1),
					new Match("fFdD")))))));
		
		/**
		 * 19.3 Terminals from section 3.10.3: Boolean Literal
		 */
		this.put("BOOLEAN_LITERAL", new Union(new Singleton("true"),
			new Singleton("false")));
		
		/**
		 * 19.3 Terminals from section 3.10.4: Character Literal
		 */
		this.put("CHARACTER_LITERAL", new Concatenation(new Singleton("'"),
			new Concatenation(new Union(
			
			/**
			 * Single Character: [^\r\n'\\]
			 */
			new NonMatch("\r\n'\\"),
			
			/**
			 * Escape Sequence: \\([btnfr\"'\\]|[0-3]?[0-7]{1,2})
			 */
			new Concatenation(new Singleton("\\"), new Union(new Match(
				"btnfr\"'\\"), new Concatenation(new Repetition(new Range('0',
				'3'), 0, 1), new Repetition(new Range('0', '7'), 1, 2))))),
				new Singleton("'"))));
		
		/**
		 * 19.3 Terminals from section 3.10.5: String Literal
		 */
		this.put("STRING_LITERAL", new Concatenation(new Singleton("\""),
			new Concatenation(new Repetition(new Union(
			
			/**
			 * Single Character: [^\r\n"\\]
			 */
			new NonMatch("\r\n\"\\"),
			
			/**
			 * Escape Sequence: \\([btnfr\"'\\]|[0-3]?[0-7]{1,2})
			 */
			new Concatenation(new Singleton("\\"), new Union(new Match(
				"btnfr\"'\\"), new Concatenation(new Repetition(new Range('0',
				'3'), 0, 1), new Repetition(new Range('0', '7'), 1, 2))))), 0,
				INFINITY), new Singleton("\""))));
		
		/**
		 * 19.3 Terminals section 3.10.7: Null Literal
		 */
		this.put("NULL_LITERAL", new Singleton("null"));
		
		/**
		 * Syntax Specification
		 */
		this.put("CompilationUnit", new Object[][] {
			{
				"ImportDeclarations", "TypeDeclarations"
			},
			{
				"package", "QualifiedIdentifier", ";", "ImportDeclarations",
				"TypeDeclarations"
			},
		});
		this.put("QualifiedIdentifier", new Object[][] {
			{
				"IDENTIFIER"
			}, {
				"QualifiedIdentifier", ".", "IDENTIFIER"
			},
		});
		this.put("Literal", new Object[][] {
			{
				"INTEGER_LITERAL"
			}, {
				"FLOATING_POINT_LITERAL"
			}, {
				"CHARACTER_LITERAL"
			}, {
				"STRING_LITERAL"
			}, {
				"BOOLEAN_LITERAL"
			}, {
				"NULL_LITERAL"
			},
		});
		this.put("Expression", new Object[][] {
			{
				"Expression1"
			}, {
				"Expression1", "AssignmentOperator", "Expression1"
			},
		});
		this.put("AssignmentOperator", new Object[][] {
			{
				"="
			}, {
				"+="
			}, {
				"-="
			}, {
				"*="
			}, {
				"/="
			}, {
				"&="
			}, {
				"|="
			}, {
				"^="
			}, {
				"%="
			}, {
				"<<="
			}, {
				">>="
			}, {
				">>>="
			},
		});
		this.put("Type", new Object[][] {
			{
				"QualifiedIdentifier", "BracketsOpt"
			}, {
				"BasicType"
			},
		});
		this.put("StatementExpression", new Object[][] {
			{
				"Expression"
			}
		});
		this.put("ConstantExpression", new Object[][] {
			{
				"Expression"
			}
		});
		this.put("Expression1", new Object[][] {
			{
				"Expression2", "Expression1Rest"
			}
		});
		this.put("Expression1Rest", new Object[][] {
			{}, {
				"?", "Expression", ":", "Expression1"
			},
		});
		this.put("Expression2", new Object[][] {
			{
				"Expression3", "Expression2Rest"
			}
		});
		this.put("Expression2Rest", new Object[][] {
			{
				"Infixops"
			}, {
				"instanceof", "Type"
			},
		});
		this.put("Infixops", new Object[][] {
			{}, {
				"Infixop", "Expression3", "Infixops"
			},
		});
		this.put("Infixop", new Object[][] {
			{
				"||"
			}, {
				"&&"
			}, {
				"|"
			}, {
				"^"
			}, {
				"&"
			}, {
				"=="
			}, {
				"!="
			}, {
				"<"
			}, {
				">"
			}, {
				"<="
			}, {
				">="
			}, {
				"<<"
			}, {
				">>"
			}, {
				">>>"
			}, {
				"+"
			}, {
				"-"
			}, {
				"*"
			}, {
				"/"
			}, {
				"%"
			},
		});
		this.put("Expression3", new Object[][] {
			{
				"PrefixOp", "Expression3"
			}, {
				"(", "Expression", ")", "Expression3"
			}, {
				"(", "Type", ")", "Expression3"
			}, {
				"Primary", "Selectors", "PostfixOps"
			},
		});
		this.put("Selectors", new Object[][] {
			{}, {
				"Selector", "Selectors"
			},
		});
		this.put("PostfixOps", new Object[][] {
			{}, {
				"PostfixOp", "PostfixOps"
			},
		});
		this.put("Primary", new Object[][] {
			{
				"(", "Expression", ")"
			}, {
				"this", "ArgumentsOpt"
			}, {
				"super", "SuperSuffix"
			}, {
				"Literal"
			}, {
				"new", "Creator"
			}, {
				"QualifiedIdentifier"
			}, {
				"QualifiedIdentifier", "IdentifierSuffix"
			}, {
				"BasicType", "BracketsOpt", ".", "class"
			}, {
				"void", ".", "class"
			},
		});
		this.put("IdentifierSuffix", new Object[][] {
			{
				"[", "]", "BracketsOpt", ".", "class"
			}, {
				"[", "Expression", "]"
			}, {
				"Arguments"
			}, {
				".", "class"
			}, {
				".", "this"
			}, {
				".", "super", "Arguments"
			}, {
				".", "new", "InnerCreator"
			},
		});
		this.put("PrefixOp", new Object[][] {
			{
				"++"
			}, {
				"--"
			}, {
				"!"
			}, {
				"~"
			}, {
				"+"
			}, {
				"-"
			},
		});
		this.put("PostfixOp", new Object[][] {
			{
				"++"
			}, {
				"--"
			},
		});
		this.put("Selector", new Object[][] {
			{
				".", "IDENTIFIER", "ArgumentsOpt"
			}, {
				".", "this"
			}, {
				".", "super", "SuperSuffix"
			}, {
				".", "new", "InnerCreator"
			}, {
				"[", "Expression", "]"
			},
		});
		this.put("SuperSuffix", new Object[][] {
			{
				"Arguments"
			}, {
				".", "IDENTIFIER", "ArgumentsOpt"
			},
		});
		this.put("BasicType", new Object[][] {
			{
				"byte"
			}, {
				"short"
			}, {
				"char"
			}, {
				"int"
			}, {
				"long"
			}, {
				"float"
			}, {
				"double"
			}, {
				"boolean"
			},
		});
		this.put("ArgumentsOpt", new Object[][] {
			{}, {
				"Arguments"
			},
		});
		this.put("Arguments", new Object[][] {
			{
				"(", ")"
			}, {
				"(", "Expressions", ")"
			},
		});
		this.put("Expressions", new Object[][] {
			{
				"Expression"
			}, {
				"Expressions", ",", "Expression"
			},
		});
		this.put("BracketsOpt", new Object[][] {
			{}, {
				"[", "]", "BracketsOpt"
			},
		});
		this.put("Creator", new Object[][] {
			{
				"QualifiedIdentifier", "ArrayCreatorRest"
			}, {
				"QualifiedIdentifier", "ClassCreatorRest"
			},
		});
		this.put("InnerCreator", new Object[][] {
			{
				"IDENTIFIER", "ClassCreatorRest"
			}
		});
		this.put("ArrayCreatorRest", new Object[][] {
			{
				"[", "]", "BracketsOpt", "ArrayInitializer"
			}, {
				"[", "Expression", "]", "BracketExpressions", "BracketsOpt"
			},
		});
		this.put("BracketExpressions", new Object[][] {
			{}, {
				"[", "Expression", "]", "BracketExpressions"
			},
		});
		this.put("ClassCreatorRest", new Object[][] {
			{
				"Arguments"
			}, {
				"Arguments", "ClassBody"
			},
		});
		this.put("ArrayInitializer", new Object[][] {
			{
				"{", "}"
			}, {
				"{", "VariableInitializers", "}"
			}, {
				"{", "VariableInitializers", ",", "}"
			},
		});
		this.put("VariableInitializers", new Object[][] {
			{
				"VariableInitializer"
			}, {
				"VariableInitializers", ",", "VariableInitializer"
			},
		});
		this.put("VariableInitializer", new Object[][] {
			{
				"ArrayInitializer"
			}, {
				"Expression"
			},
		});
		this.put("ParExpression", new Object[][] {
			{
				"(", "Expression", ")"
			}
		});
		this.put("Block", new Object[][] {
			{
				"{", "BlockStatements", "}"
			}
		});
		this.put("BlockStatements", new Object[][] {
			{}, {
				"BlockStatement", "BlockStatements"
			},
		});
		this.put("BlockStatement", new Object[][] {
			{
				"LocalVariableDeclarationStatement"
			}, {
				"ClassOrInterfaceDeclaration"
			}, {
				"Statement"
			}, {
				"IDENTIFIER", ":", "Statement"
			},
		});
		this.put("LocalVariableDeclarationStatement", new Object[][] {
			{
				"Type", "VariableDeclarators", ";"
			}, {
				"final", "Type", "VariableDeclarators", ";"
			},
		});
		this.put("Statement", new Object[][] {
			{
				"Block"
			},
			{
				"if", "ParExpression", "Statement"
			},
			{
				"if", "ParExpression", "Statement", "else", "Statement"
			},
			{
				"for", "(", "ForInitOpt", ";", ";", "ForUpdateOpt", ")",
				"Statement"
			},
			{
				"for", "(", "ForInitOpt", ";", "Expression", ";",
				"ForUpdateOpt", ")", "Statement"
			},
			{
				"while", "ParExpression", "Statement"
			},
			{
				"do", "Statement", "while", "ParExpression", ";"
			},
			{
				"try", "Block", "Catches"
			},
			{
				"try", "Block", "finally", "Block"
			},
			{
				"try", "Block", "Catches", "finally", "Block"
			},
			{
				"switch", "ParExpression", "{", "SwitchBlockStatementGroups",
				"}"
			}, {
				"synchronized", "ParExpression", "Block"
			}, {
				"return", ";"
			}, {
				"return", "Expression", ";"
			}, {
				"throw", "Expression", ";"
			}, {
				"break"
			}, {
				"break", "IDENTIFIER"
			}, {
				"continue"
			}, {
				"continue", "IDENTIFIER"
			}, {
				";"
			}, {
				"StatementExpression", ";"
			}, {
				"IDENTIFIER", ":", "Statement"
			},
		});
		this.put("Catches", new Object[][] {
			{
				"CatchClause"
			}, {
				"Catches", "CatchClause"
			},
		});
		this.put("CatchClause", new Object[][] {
			{
				"catch", "(", "FormalParameter", ")", "Block"
			}
		});
		this.put("SwitchBlockStatementGroups", new Object[][] {
			{}, {
				"SwitchBlockStatementGroup", "SwitchBlockStatementGroups"
			},
		});
		this.put("SwitchBlockStatementGroup", new Object[][] {
			{
				"SwitchLabel", "BlockStatements"
			}
		});
		this.put("SwitchLabel", new Object[][] {
			{
				"case", "ConstantExpression", ":"
			}, {
				"default", ":"
			},
		});
		this.put("MoreStatementExpressions", new Object[][] {
			{}, {
				",", "StatementExpression", "MoreStatementExpressions"
			},
		});
		this.put("ForInitOpt", new Object[][] {
			{}, {
				"StatementExpression", "MoreStatementExpressions"
			}, {
				"Type", "VariableDeclarators"
			}, {
				"final", "Type", "VariableDeclarators"
			},
		});
		this.put("ForUpdateOpt", new Object[][] {
			{}, {
				"StatementExpression", "MoreStatementExpressions"
			},
		});
		this.put("ModifiersOpt", new Object[][] {
			{}, {
				"Modifier", "ModifiersOpt"
			},
		});
		this.put("Modifier", new Object[][] {
			{
				"public"
			}, {
				"protected"
			}, {
				"private"
			}, {
				"static"
			}, {
				"abstract"
			}, {
				"final"
			}, {
				"native"
			}, {
				"synchronized"
			}, {
				"transient"
			}, {
				"volatile"
			}, {
				"strictfp"
			},
		});
		this.put("VariableDeclarators", new Object[][] {
			{
				"VariableDeclarator"
			}, {
				"VariableDeclarators", ",", "VariableDeclarator"
			},
		});
		this.put("VariableDeclaratorsRest", new Object[][] {
			{
				"VariableDeclaratorRest"
			}, {
				"VariableDeclaratorsRest", ",", "VariableDeclarator"
			},
		});
		this.put("ConstantDeclaratorsRest", new Object[][] {
			{
				"ConstantDeclaratorRest"
			}, {
				"ConstantDeclaratorsRest", ",", "ConstantDeclarator"
			},
		});
		this.put("VariableDeclarator", new Object[][] {
			{
				"IDENTIFIER", "VariableDeclaratorRest"
			}
		});
		this.put("ConstantDeclarator", new Object[][] {
			{
				"IDENTIFIER", "ConstantDeclaratorRest"
			}
		});
		this.put("VariableDeclaratorRest", new Object[][] {
			{
				"BracketsOpt"
			}, {
				"BracketsOpt", "=", "VariableInitializer"
			},
		});
		this.put("ConstantDeclaratorRest", new Object[][] {
			{
				"BracketsOpt", "=", "VariableInitializer"
			}
		});
		this.put("VariableDeclaratorId", new Object[][] {
			{
				"IDENTIFIER", "BracketsOpt"
			}
		});
		this.put("ImportDeclarations", new Object[][] {
			{}, {
				"ImportDeclaration", "ImportDeclarations"
			},
		});
		this.put("TypeDeclarations", new Object[][] {
			{}, {
				"TypeDeclaration", "TypeDeclarations"
			},
		});
		this.put("ImportDeclaration", new Object[][] {
			{
				"import", "QualifiedIdentifier", ";"
			}, {
				"import", "QualifiedIdentifier", ".", "*", ";"
			},
		});
		this.put("TypeDeclaration", new Object[][] {
			{
				"ClassOrInterfaceDeclaration"
			}, {
				";"
			},
		});
		this.put("ClassOrInterfaceDeclaration", new Object[][] {
			{
				"ClassDeclaration"
			}, {
				"InterfaceDeclaration"
			},
		// {"ModifiersOpt", "ClassDeclaration"},
		// {"ModifiersOpt", "InterfaceDeclaration"},
			});
		this.put("ClassDeclaration", new Object[][] {
			{
				"class", "IDENTIFIER", "ClassBody"
			},
			{
				"class", "IDENTIFIER", "extends", "Type", "ClassBody"
			},
			{
				"class", "IDENTIFIER", "implements", "TypeList", "ClassBody"
			},
			{
				"class", "IDENTIFIER", "extends", "Type", "implements",
				"TypeList", "ClassBody"
			},
		});
		this.put("InterfaceDeclaration", new Object[][] {
			{
				"interface", "IDENTIFIER", "InterfaceBody"
			},
			{
				"interface", "IDENTIFIER", "extends", "TypeList",
				"InterfaceBody"
			},
		});
		this.put("TypeList", new Object[][] {
			{
				"Type"
			}, {
				"TypeList", ",", "Type"
			},
		});
		this.put("ClassBody", new Object[][] {
			{
				"{", "ClassBodyDeclarations", "}"
			}
		});
		this.put("ClassBodyDeclarations", new Object[][] {
			{}, {
				"ClassBodyDeclaration", "ClassBodyDeclarations"
			},
		});
		this.put("InterfaceBody", new Object[][] {
			{
				"{", "InterfaceBodyDeclarations", "}"
			}
		});
		this.put("InterfaceBodyDeclarations", new Object[][] {
			{}, {
				"InterfaceBodyDeclaration", "InterfaceBodyDeclarations"
			},
		});
		this.put("ClassBodyDeclaration", new Object[][] {
			{
				";"
			}, {
				"Block"
			}, {
				"static", "Block"
			}, {
				"ModifiersOpt", "MemberDecl"
			},
		});
		this.put("MemberDecl", new Object[][] {
			{
				"MethodOrFieldDecl"
			}, {
				"void", "IDENTIFIER", "VoidMethodDeclaratorRest"
			}, {
				"IDENTIFIER", "ConstructorDeclaratorRest"
			}, {
				"ClassOrInterfaceDeclaration"
			},
		});
		this.put("MethodOrFieldDecl", new Object[][] {
			{
				"Type", "IDENTIFIER", "MethodOrFieldRest"
			}
		});
		this.put("MethodOrFieldRest", new Object[][] {
			// {"VariableDeclaratorRest"},
			{
				"VariableDeclaratorRest", ";"
			}, {
				"MethodDeclaratorRest"
			},
		});
		this.put("InterfaceBodyDeclaration", new Object[][] {
			{
				";"
			}, {
				"ModifiersOpt", "InterfaceMemberDecl"
			},
		});
		this.put("InterfaceMemberDecl", new Object[][] {
			{
				"InterfaceMethodOrFieldDecl"
			}, {
				"void", "IDENTIFIER", "VoidInterfaceMethodDeclaratorRest"
			}, {
				"ClassOrInterfaceDeclaration"
			},
		});
		this.put("InterfaceMethodOrFieldDecl", new Object[][] {
			{
				"Type", "IDENTIFIER", "InterfaceMethodOrFieldRest"
			}
		});
		this.put("InterfaceMethodOrFieldRest", new Object[][] {
			{
				"ConstantDeclaratorsRest", ";"
			}, {
				"InterfaceMethodDeclaratorRest"
			},
		});
		this.put("MethodDeclaratorRest", new Object[][] {
			{
				"FormalParameters", "BracketsOpt", "MethodBody"
			},
			{
				"FormalParameters", "BracketsOpt", ";"
			},
			{
				"FormalParameters", "BracketsOpt", "throws",
				"QualifiedIdentifierList", "MethodBody"
			},
			{
				"FormalParameters", "BracketsOpt", "throws",
				"QualifiedIdentifierList", ";"
			},
		});
		this.put("VoidMethodDeclaratorRest", new Object[][] {
			{
				"FormalParameters", "MethodBody"
			},
			{
				"FormalParameters", ";"
			},
			{
				"FormalParameters", "throws", "QualifiedIdentifierList",
				"MethodBody"
			}, {
				"FormalParameters", "throws", "QualifiedIdentifierList", ";"
			},
		});
		this.put("InterfaceMethodDeclaratorRest", new Object[][] {
			{
				"FormalParameters", "BracketsOpt", ";"
			},
			{
				"FormalParameters", "BracketsOpt", "throws",
				"QualifiedIdentifierList", ";"
			},
		});
		this.put("VoidInterfaceMethodDeclaratorRest", new Object[][] {
			{
				"FormalParameters", ";"
			}, {
				"FormalParameters", "throws", "QualifiedIdentifierList", ";"
			},
		});
		this.put("ConstructorDeclaratorRest", new Object[][] {
			{
				"FormalParameters", "MethodBody"
			},
			{
				"FormalParameters", "throws", "QualifiedIdentifierList",
				"MethodBody"
			},
		});
		this.put("QualifiedIdentifierList", new Object[][] {
			{
				"QualifiedIdentifier"
			}, {
				"QualifiedIdentifierList", ",", "QualifiedIdentifier"
			},
		});
		this.put("FormalParameters", new Object[][] {
			{
				"(", ")"
			}, {
				"(", "FormalParameter", "FormalParametersRest", ")"
			},
		});
		this.put("FormalParametersRest", new Object[][] {
			{}, {
				",", "FormalParameter", "FormalParametersRest"
			},
		});
		this.put("FormalParameter", new Object[][] {
			{
				"Type", "VariableDeclaratorId"
			}, {
				"final", "Type", "VariableDeclaratorId"
			},
		});
		this.put("MethodBody", new Object[][] {
			{
				"Block"
			}
		});
	}
}
