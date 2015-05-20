package lex_yacc_syntax_analyzer.Grammars;

import gi.Grammar;

public class CGrammar extends Grammar
{
	public CGrammar()
	{
		int INFINITY = -1;
		
		this.put("WHITE_SPACE", PosixClass.space());
		
		this.put("COMMENT", new Union(
		
		new Concatenation(new Singleton("/*"), new Concatenation(
					new Repetition(new NonMatch("*"), 1, INFINITY), new Concatenation(
				new Repetition(
					new Concatenation(new Singleton("*"), new Repetition(
						new Concatenation(new NonMatch("*/"), new Repetition(
							new NonMatch("*"), 0, INFINITY)), 0, 1)), 0,
					INFINITY), new Singleton("*/")))), new Union(
		
		new Concatenation(new Singleton("//"), new Concatenation(
										new Repetition(new NonMatch("\n"), 0, INFINITY),
										new Singleton("\n"))),
		
		new Concatenation(new Singleton("/**"), new Concatenation(
													new Repetition(new Concatenation(new Repetition(new Concatenation(
				new NonMatch("*/"), new Repetition(new NonMatch("*"), 0,
					INFINITY)), 0, 1), new Singleton("*")), 0, INFINITY),
															new Singleton("/"))))));
		
		this.put("IDENTIFIER", new Concatenation(new Union(PosixClass.alpha(),
				new Match("_$")), new Repetition(new Union(PosixClass.alnum(),
			new Match("_$")), 0, INFINITY)));
		
		this.put(
			"INTEGER_LITERAL",
			new Concatenation(new Union(new Singleton("0"), new Union(
			
			new Concatenation(new Range('1', '9'), new Repetition(PosixClass
					.digit(), 0, INFINITY)), new Union(
			
			new Concatenation(new Singleton("0"), new Concatenation(new Match(
				"xX"), new Repetition(PosixClass.xdigit(), 1, INFINITY))),
			
			new Concatenation(new Singleton("0"), new Repetition(new Range('0',
				'7'), 1, INFINITY))))), new Repetition(new Match("lL"), 0, 1)));
		
		this.put("FLOATING_POINT_LITERAL", new Union(
		
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
			
			new Concatenation(new Singleton("."), new Concatenation(
				new Repetition(PosixClass.digit(), 1, INFINITY),
				new Concatenation(
					new Repetition(new Concatenation(new Match("eE"),
						new Concatenation(
							new Repetition(new Match("-+"), 0, 1),
							new Repetition(PosixClass.digit(), 1, INFINITY))),
						0, 1), new Repetition(new Match("fFdD"), 0, 1)))),
				new Union(
				
				new Concatenation(new Repetition(PosixClass.digit(), 1,
					INFINITY),
					new Concatenation(new Match("eE"),
						new Concatenation(
							new Repetition(new Match("-+"), 0, 1),
							new Concatenation(new Repetition(
								PosixClass.digit(), 1, INFINITY),
								new Repetition(new Match("fFdD"), 0, 1))))),
				
				new Concatenation(new Repetition(PosixClass.digit(), 1,
					INFINITY), new Concatenation(new Repetition(
					new Concatenation(new Match("eE"), new Concatenation(
						new Repetition(new Match("-+"), 0, 1), new Repetition(
							PosixClass.digit(), 1, INFINITY))), 0, 1),
					new Match("fFdD")))))));
		
		this.put("BOOLEAN_LITERAL", new Union(new Singleton("true"),
			new Singleton("false")));
		
		this.put("CHARACTER_LITERAL", new Concatenation(new Singleton("'"),
			new Concatenation(new Union(
			
			new NonMatch("\r\n'\\"),
			
			new Concatenation(new Singleton("\\"), new Union(new Match(
				"btnfr\"'\\"), new Concatenation(new Repetition(new Range('0',
				'3'), 0, 1), new Repetition(new Range('0', '7'), 1, 2))))),
				new Singleton("'"))));
		
		this.put("STRING_LITERAL", new Concatenation(new Singleton("\""),
			new Concatenation(new Repetition(new Union(
			
			new NonMatch("\r\n\"\\"),
			
			new Concatenation(new Singleton("\\"), new Union(new Match(
				"btnfr\"'\\"), new Concatenation(new Repetition(new Range('0',
				'3'), 0, 1), new Repetition(new Range('0', '7'), 1, 2))))), 0,
				INFINITY), new Singleton("\""))));
		
		this.put("File", new Object[][] {
			{
				"CProgram"
			}
		});
		this.put("CProgram", new Object[][] {
			{
				"Data/FunctionDescription", "ProgramContinuation"
			}
		});
		this.put("ProgramContinuation", new Object[][] {
			{
				"CProgram"
			}, {}
		});
		
		this.put("ExternalMemorySpecification", new Object[][] {
			{
				"extern"
			}, {
				"static"
			}, {}
		});
		this.put("MemorySpecification", new Object[][] {
			{
				"auto"
			}, {
				"extern"
			}, {
				"static"
			}, {
				"register"
			}
		});
		
		this.put("TypeSpecification", new Object[][] {
			{
				"StandardTypeSpecification"
			}, {
				"StructuralTypeSpecification"
			}
		});
		this.put("StandardTypeSpecification", new Object[][] {
			{
				"unsigned", "UnsignedTypeElaboration"
			}, {
				"char"
			}, {
				"long", "LongTypeElaboration"
			}, {
				"short", "Long/ShortElaboration"
			}, {
				"int"
			}, {
				"float"
			}, {
				"double"
			}, {
				"TypeIdentifier"
			}, {
				"void"
			}
		});
		this.put("TypeIdentifier", new Object[][] {
			{
				"TYPE"
			}
		});
		this.put("UnsignedTypeElaboration", new Object[][] {
			{
				"char"
			}, {
				"long", "Long/ShortElaboration"
			}, {
				"short", "Long/ShortElaboration"
			}, {
				"int"
			}, {}
		});
		this.put("Long/ShortElaboration", new Object[][] {
			{
				"int"
			}, {}
		});
		this.put("LongTypeElaboration", new Object[][] {
			{
				"float"
			}, {
				"int"
			}, {}
		});
		this.put("StructuralTypeSpecification", new Object[][] {
			{
				"struct", "Continuation1_struct/union"
			}, {
				"union", "Continuation1_struct/union"
			}, {
				"enum", "Continuation1_enum"
			}
		});
		this.put("Continuation1_enum", new Object[][] {
			{
				"{", "ConstantsList_enum", "}"
			}, {
				"IDENTIFIER", "Continuation2_enum"
			}
		});
		this.put("Continuation2_enum", new Object[][] {
			{
				"{", "ConstantsList_enum", "}"
			}, {}
		});
		this.put("Continuation1_struct/union", new Object[][] {
			{
				"{", "RecordFieldsDescription", "}"
			}, {
				"IDENTIFIER", "Continuation2_struct/union"
			}
		});
		this.put("Continuation2_struct/union", new Object[][] {
			{
				"{", "RecordFieldsDescription", "}"
			}, {}
		});
		this.put("ConstantsList_enum", new Object[][] {
			{
				"IDENTIFIER", "Continuation1_Constant_enum"
			}
		});
		this.put("Continuation1_Constant_enum", new Object[][] {
			{
				"=", "ConditionalExpression", "Continuation2_Constant_enum"
			}, {
				"Continuation2_Constant_enum"
			}
		});
		this.put("Continuation2_Constant_enum", new Object[][] {
			{
				",", "ConstantsList_enum"
			}, {}
		});
		
		this.put("Data/FunctionDescription", new Object[][] {
			{
				"ExternalMemorySpecification",
				"Continuation0_Data/FunctionDescription"
			}, {
				"Description_typedef"
			}
		});
		this.put("Continuation0_Data/FunctionDescription", new Object[][] {
			{
				"StandardTypeSpecification",
				"Continuation11_Data/FunctionDescription"
			}, {
				"struct", "Continuation1_Data/FunctionDescription"
			}, {
				"union", "Continuation1_Data/FunctionDescription"
			}, {
				"enum", "Continuation10_Data/FunctionDescription"
			}, {
				"Continuation11_Data/FunctionDescription"
			}
		});
		this.put("Continuation1_Data/FunctionDescription", new Object[][] {
			{
				"IDENTIFIER", "Continuation2_Data/FunctionDescription"
			},
			{
				"{", "RecordFieldsDescription", "}",
				"Continuation12_Data/FunctionDescription"
			},
		});
		this.put("Continuation2_Data/FunctionDescription", new Object[][] {
			{
				"{", "RecordFieldsDescription", "}",
				"Continuation12_Data/FunctionDescription"
			}, {
				"Continuation11_Data/FunctionDescription"
			},
		});
		this.put("Continuation3_Data/FunctionDescription", new Object[][] {
			{
				",", "Descriptor", "Initiator",
				"Continuation3_Data/FunctionDescription"
			}, {
				";"
			},
		});
		this.put("Continuation10_Data/FunctionDescription", new Object[][] {
			{
				"IDENTIFIER", "Continuation20_Data/FunctionDescription"
			},
			{
				"{", "ConstantsList_enum", "}",
				"Continuation12_Data/FunctionDescription"
			},
		});
		this.put("Continuation11_Data/FunctionDescription", new Object[][] {
			{
				"Descriptor", "Continuation13_Data/FunctionDescription"
			},
		});
		this.put("Continuation12_Data/FunctionDescription", new Object[][] {
			{
				";"
			}, {
				"Continuation11_Data/FunctionDescription"
			},
		});
		this.put("Continuation13_Data/FunctionDescription", new Object[][] {
			{
				"Initiator", "Continuation3_Data/FunctionDescription"
			}, {
				"ParametersDescriptionList", "CompoundStatement"
			},
		});
		
		this.put("RecordFieldsDescription", new Object[][] {
			{
				"RecordFieldDescription", "Continuation_FieldsDescription"
			}
		});
		this.put("Continuation_FieldsDescription", new Object[][] {
			{
				"RecordFieldsDescription"
			}, {}
		});
		this.put("RecordFieldDescription", new Object[][] {
			{
				"TypeSpecification", "RecordDescriptor",
				"Continuation1_RecordIdentifier"
			}
		});
		this.put("Continuation1_RecordIdentifier", new Object[][] {
			{
				",", "RecordDescriptor", "Continuation1_RecordIdentifier"
			}, {
				";"
			}
		});
		this.put("RecordDescriptorElaboration", new Object[][] {
			{
				":", "ConditionalExpression"
			}, {}
		});
		this.put("RecordDescriptor", new Object[][] {
			{
				"Descriptor", "RecordDescriptorElaboration"
			}, {
				":", "ConditionalExpression"
			}
		});
		
		this.put("Description_typedef", new Object[][] {
			{
				"typedef", "TypeSpecification", "Descriptor",
				"typedef_Continuation"
			},
		});
		this.put("typedef_Continuation", new Object[][] {
			{
				";"
			}, {
				",", "Descriptor", "typedef_Continuation"
			},
		});
		
		this.put("DataIdentification", new Object[][] {
			{
				"DataElemIdentification", "DataIdentification"
			}, {}
		});
		this.put("DataElemIdentification", new Object[][] {
			{
				"MemorySpecification", "Continuation_DataIdentification"
			},
			{
				"StandardTypeSpecification", "Descriptor", "Initiator",
				"Continuation_Identification"
			}, {
				"struct", "struct/unionElaboration"
			}, {
				"union", "struct/unionElaboration"
			}, {
				"enum", "EnumElaboration"
			}, {
				"Description_typedef"
			}
		});
		this.put("Continuation_DataIdentification", new Object[][] {
			{
				"StandardTypeSpecification", "Descriptor", "Initiator",
				"Continuation_Identification"
			}, {
				"struct", "struct/unionElaboration"
			}, {
				"union", "struct/unionElaboration"
			}, {
				"enum", "EnumElaboration"
			}
		});
		this.put("EnumElaboration", new Object[][] {
			{
				"IDENTIFIER", "EnumElaboration1"
			}, {
				"{", "ConstantsList_enum", "}", "struct/unionElaboration2"
			}
		});
		this.put("EnumElaboration1", new Object[][] {
			{
				"Descriptor", "Initiator", "Continuation_Identification"
			}, {
				"{", "ConstantsList_enum", "}", "struct/unionElaboration2"
			}
		});
		this.put("struct/unionElaboration", new Object[][] {
			{
				"IDENTIFIER", "struct/unionElaboration1"
			}, {
				"{", "RecordFieldsDescription", "}", "struct/unionElaboration2"
			}
		});
		this.put("struct/unionElaboration1", new Object[][] {
			{
				"Descriptor", "Initiator", "Continuation_Identification"
			}, {
				"{", "RecordFieldsDescription", "}", "struct/unionElaboration2"
			}
		});
		this.put("struct/unionElaboration2", new Object[][] {
			{
				";"
			}, {
				"Descriptor", "Initiator", "Continuation_Identification"
			}
		});
		this.put("Continuation_Identification", new Object[][] {
			{
				",", "Descriptor", "Initiator", "Continuation_Identification"
			}, {
				";"
			}
		});
		
		this.put("Descriptor", new Object[][] {
			{
				"DescriptorsPrefix", "Identifier_Option",
				"Array/Function_Option"
			}
		});
		this.put("Array/Function_Option", new Object[][] {
			{
				"(", "FormalParametersList", ")"
			}, {
				"MultidimensionalArray"
			}
		});
		this.put("MultidimensionalArray", new Object[][] {
			{
				"[", "ConstantExpression", "]", "MultidimensionalArray"
			}, {}
		});
		this.put("DescriptorsPrefix", new Object[][] {
			{
				"*", "DescriptorsPrefix"
			}, {}
		});
		this.put("Identifier_Option", new Object[][] {
			{
				"IDENTIFIER"
			}, {
				"(", "OptionElaboration", ")"
			}
		});
		this.put("OptionElaboration", new Object[][] {
			{
				"(", "OptionElaboration", ")", "Array/Function_Option"
			}, {
				"DescriptorsPrefix", "IDENTIFIER"
			}
		});
		
		this.put("FormalParametersList", new Object[][] {
			{
				"ParametersDescription", "Continuation_FormalParamDescription"
			}, {}
		});
		this.put("Continuation_FormalParamDescription", new Object[][] {
			{
				",", "Continuation1_FormalParamDescription"
			}, {}
		});
		this.put("Continuation1_FormalParamDescription", new Object[][] {
			{
				"ParametersDescription", "Continuation_FormalParamDescription"
			}, {
				"..."
			}
		});
		this.put("ParametersDescription", new Object[][] {
			{
				"TypeSpecification", "ParameterDescriptor"
			}, {
				"IDENTIFIER"
			}
		});
		this.put("ParameterDescriptor", new Object[][] {
			{
				"ParameterDescriptorsPrefix",
				"Continuation_ParameterDescriptor"
			}, {
				"Continuation_ParameterDescriptor"
			}
		});
		this.put("ParameterDescriptorsPrefix", new Object[][] {
			{
				"*", "Continuation_ParameterDescriptorsPrefix"
			}
		});
		this.put("Continuation_ParameterDescriptorsPrefix", new Object[][] {
			{
				"*", "ParameterDescriptorsPrefix"
			}, {}
		});
		this.put("Continuation_ParameterDescriptor", new Object[][] {
			{
				"Identifier_Option", "Array/Function_Option"
			}, {}
		});
		
		this.put("ParametersDescriptionList", new Object[][] {
			{
				"ParametersGroupDescription",
				"Continuation_ParametersDescriptionList"
			}, {}
		});
		this.put("Continuation_ParametersDescriptionList", new Object[][] {
			{
				"ParametersDescriptionList"
			}
		});
		this.put("ParametersGroupDescription", new Object[][] {
			{
				"TypeSpecification", "Descriptor",
				"Continuation_ParametersGroupDescription"
			}
		});
		this.put("Continuation_ParametersGroupDescription", new Object[][] {
			{
				",", "Descriptor", "Continuation_ParametersGroupDescription"
			}, {
				";"
			}
		});
		
		this.put("Initiator", new Object[][] {
			{
				"=", "InitiationList"
			}, {}
		});
		this.put("InitiationList", new Object[][] {
			{
				"ConditionalExprpression", "Continuation_InitiationList"
			}, {
				"{", "InitiationList", "}", "Continuation_InitiationList"
			}
		});
		this.put("Continuation_InitiationList", new Object[][] {
			{
				",", "InitiationList"
			}, {}
		});
		
		this.put("Expression", new Object[][] {
			{
				"AssignmentExpression", "ExpressionExtention"
			}
		});
		this.put("ExpressionExtention", new Object[][] {
			{
				",", "AssignmentExpression", "ExpressionExtention"
			}, {}
		});
		this.put("AssignmentExpression", new Object[][] {
			{
				"ConditionalExpression", "AssignmentExtention"
			}
		});
		this.put("AssignmentExtention", new Object[][] {
			{
				"AssignmentOperation", "AssignmentExpression"
			}, {}
		});
		this.put("ConditionalExpression", new Object[][] {
			{
				"Logical_OR_Expression", "ConditionalExtention"
			}
		});
		this.put("ConditionalExtention", new Object[][] {
			{
				"?", "Expression", ":", "ConditionalExpression"
			}, {}
		});
		this.put("Logical_OR_Expression", new Object[][] {
			{
				"Logical_AND_Expression", "Logical_OR_Extention"
			}
		});
		this.put("Logical_OR_Extention", new Object[][] {
			{
				"||", "Logical_AND_Expression", "Logical_OR_Extention"
			}, {}
		});
		this.put("Logical_AND_Expression", new Object[][] {
			{
				"InclusiveExpression", "Logical_AND_Extention"
			}
		});
		this.put("Logical_AND_Extention", new Object[][] {
			{
				"&&", "InclusiveExpression", "Logical_AND_Extention"
			}, {}
		});
		this.put("InclusiveExpression", new Object[][] {
			{
				"ExclusiveExpression", "InclusiveExtention"
			}
		});
		this.put("InclusiveExtention", new Object[][] {
			{
				"|", "ExclusiveExpression", "InclusiveExtention"
			}, {}
		});
		this.put("ExclusiveExpression", new Object[][] {
			{
				"AND_Expression", "ExclusiveExtention"
			}
		});
		this.put("ExclusiveExtention", new Object[][] {
			{
				"^", "AND_Expression", "ExclusiveExtention"
			}, {}
		});
		this.put("AND_Expression", new Object[][] {
			{
				"EqualityExpression", "AND_Extention"
			}
		});
		this.put("AND_Extention", new Object[][] {
			{
				"&", "EqualityExpression", "AND_Extention"
			}, {}
		});
		this.put("EqualityExpression", new Object[][] {
			{
				"RelationExpression", "EqualityExtention"
			}
		});
		this.put("EqualityExtention", new Object[][] {
			{
				"==", "RelationExpression", "EqualityExtention"
			}, {
				"!=", "RelationExpression", "EqualityExtention"
			}, {}
		});
		this.put("RelationExpression", new Object[][] {
			{
				"ShiftExpression", "RelationExtention"
			}
		});
		this.put("RelationExtention", new Object[][] {
			{
				"RelationOperation", "ShiftExpression", "RelationExtention"
			}, {}
		});
		this.put("ShiftExpression", new Object[][] {
			{
				"AdditiveExpression", "ShiftExtention"
			}
		});
		this.put("ShiftExtention", new Object[][] {
			{
				">>", "AdditiveExpression", "ShiftExtention"
			}, {
				"<<", "AdditiveExpression", "ShiftExtention"
			}, {}
		});
		this.put("AdditiveExpression", new Object[][] {
			{
				"MultyplyExpression", "AdditiveExtention"
			}
		});
		this.put("AdditiveExtention", new Object[][] {
			{
				"+", "MultyplyExpression", "AdditiveExtention"
			}, {
				"-", "MultyplyExpression", "AdditiveExtention"
			}, {}
		});
		this.put("MultyplyExpression", new Object[][] {
			{
				"CastExpression", "MultyplyExtention"
			}
		});
		this.put("MultyplyExtention", new Object[][] {
			{
				"*", "CastExpression", "MultyplyExtention"
			}, {
				"/", "CastExpression", "MultyplyExtention"
			}, {
				"%", "CastExpression", "MultyplyExtention"
			}, {}
		});
		this.put("UnaryExpression", new Object[][] {
			{
				"PostfixExpression"
			}, {
				"++", "UnaryExpression"
			}, {
				"--", "UnaryExpression"
			}, {
				"UnaryOperation", "CastExpression"
			}, {
				"sizeof", "SizeOfExtention"
			}
		});
		this.put("CastExpression", new Object[][] {
			{
				"(", "TypeName", ")", "CastExpression"
			}, {
				"UnaryExpression"
			}
		});
		this.put("SizeOfExtention", new Object[][] {
			{
				"(", "TypeName", ")", "CastExpression"
			}, {
				"UnaryExpression"
			}
		});
		
		this.put("PostfixExpression", new Object[][] {
			{
				"PrimeryExpression", "PostfixExtention"
			}
		});
		this.put("PostfixExtention", new Object[][] {
			{
				"[", "Expression", "]", "PostfixExtention"
			}, {
				"(", "ExpressinList", ")", "PostfixExtention"
			}, {
				".", "IDENTIFIER", "PostfixExtention"
			}, {
				"->", "IDENTIFIER", "PostfixExtention"
			}, {
				"++", "PostfixExtention"
			}, {
				"--", "PostfixExtention"
			}, {}
		});
		this.put("ExpressinList", new Object[][] {
			{
				"AssignmentExpression", "ExpressinListExtention"
			}, {}
		});
		this.put("ExpressinListExtention", new Object[][] {
			{
				",", "AssignmentExpression", "ExpressinListExtention"
			}, {}
		});
		this.put("PrimeryExpression", new Object[][] {
			{
				"IDENTIFIER"
			}, {
				"(", "Expression", ")"
			}, {
				"Constant"
			}, {
				"STRING_LITERAL"
			}
		});
		this.put("Constant", new Object[][] {
			{
				"NonString"
			}, {
				"Sign", "NonString"
			}, {
				"STRING_LITERAL"
			}
		});
		this.put("Sign", new Object[][] {
			{
				"+"
			}, {
				"-"
			}
		});
		this.put("NonString", new Object[][] {
			{
				"IDENTIFIER"
			}, {
				"INTEGER_LITERAL"
			}, {
				"FLOATING_POINT_LITERAL"
			}, {
				"CHARACTER_LITERAL"
			}, {
				"BOOLEAN_LITERAL"
			}
		});
		
		this.put("TypeName", new Object[][] {
			{
				"TypeSpecification", "AbstractDescriptor"
			}
		});
		this.put("AbstractDescriptor", new Object[][] {
			{
				"DescriptorsPrefix", "Option1", "Array/Function_Option"
			}
		});
		this.put("Option1", new Object[][] {
			{
				"(", "OptionElaboration1", ")"
			}, {}
		});
		this.put("OptionElaboration1", new Object[][] {
			{
				"(", "OptionElaboration1", ")", "Array/Function_Option"
			}, {
				"*", "DescriptorsPrefix"
			}
		});
		
		this.put("ConstantExpression", new Object[][] {
			{
				"ConditionalExpression"
			}, {}
		});
		
		this.put("CompoundStatement", new Object[][] {
			{
				"{", "DataIdentification", "OperatorsList", "}"
			}
		});
		this.put("OperatorsList", new Object[][] {
			{
				"Operator", "OperatorsList"
			}, {}
		});
		
		this.put("Operator", new Object[][] {
			{
				"CompoundStatement"
			},
			{
				"IDENTIFIER", ":", "Operator"
			},
			{
				"Expression", ";"
			},
			{
				"if", "(", "Expression", ")", "Operator", "else", "Operator"
			},
			{
				"while", "(", "Expression", ")", "Operator"
			},
			{
				"do", "Operator", "while", "(", "Expression", ")"
			},
			{
				"for", "(", "Expression1", ";", "Expression2", ";",
				"Expression3", ")", "Operator"
			},
			{
				"switch", "(", "Expression", ")", "{", "CaseConstructionList",
				"DefaultExpression", "}"
			}, {
				"break", ";"
			}, {
				"continue", ";"
			}, {
				"return", "ReturnCondition", ";"
			}, {
				"goto", "IDENTIFIER", ";"
			}, {
				";"
			}
		});
		
		this.put("ReturnCondition", new Object[][] {
			{
				"Expression"
			}, {}
		});
		this.put("Expression1", new Object[][] {
			{
				"Expression"
			}, {}
		});
		this.put("Expression2", new Object[][] {
			{
				"Expression"
			}, {}
		});
		this.put("Expression3", new Object[][] {
			{
				"Expression"
			}, {}
		});
		this.put("CaseConstructionList", new Object[][] {
			{
				"CaseConstruction", "CaseConstructionList"
			}, {}
		});
		this.put("CaseConstruction", new Object[][] {
			{
				"case", "ConditionalExpression", ":", "OperatorsList"
			}
		});
		this.put("DefaultExpression", new Object[][] {
			{
				"default", ":", "OperatorsList"
			}
		});
		
		this.put("UnaryOperation", new Object[][] {
			{
				"&"
			}, {
				"*"
			}, {
				"-"
			}, {
				"~"
			}, {
				"!"
			}
		});
		this.put("AssignmentOperation", new Object[][] {
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
				"%="
			}, {
				">>="
			}, {
				"<<="
			}, {
				"&="
			}, {
				"^="
			}, {
				"|="
			}
		});
		this.put("RelationOperation", new Object[][] {
			{
				">"
			}, {
				">="
			}, {
				"<"
			}, {
				"<="
			}
		});
		
		this.debug = Grammar.PARSE_TREE;
	}
}
