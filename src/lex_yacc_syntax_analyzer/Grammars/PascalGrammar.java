package lex_yacc_syntax_analyzer.Grammars;

import gi.Grammar;

public class PascalGrammar extends Grammar
{
	public PascalGrammar() throws Exception
	{
		put("IDENTIFIER", expression("[[:alpha:]](_?[[:alnum:]])*"));
		put("CHARACTER_STRING", expression("'([^']|'')+'"));
		put("DIGSEQ", expression("[[:digit:]]+"));
		put("REALNUMBER", expression("[[:digit:]]+\\.[[:digit:]]+"));
		put("SPACE", expression("[[:space:]]+"));
		put("COMMENT",
			expression("\\(\\*([^\\*]|\\*[^\\)])*\\*\\)|\\{[^}]*\\}"));
		
		put("File", new Object[][] {
			{
				"Program"
			}, {
				"Module"
			},
		});
		put("Program", new Object[][] {
			{
				"ProgramHeading", ";", "Block", "."
			}
		});
		put("ProgramHeading", new Object[][] {
			{
				"program", "IDENTIFIER"
			}, {
				"program", "IDENTIFIER", "(", "IdentifierList", ")"
			},
		});
		put("IdentifierList", new Object[][] {
			{
				"IdentifierList", ",", "IDENTIFIER"
			}, {
				"IDENTIFIER"
			},
		});
		put("Block", new Object[][] {
			{
				"LabelDeclarationPart", "ConstantDefinitionPart",
				"TypeDefinitionPart", "VariableDeclarationPart",
				"ProcedureAndFunctionDeclarationPart", "StatementPart"
			}
		});
		put("Module", new Object[][] {
			{
				"ConstantDefinitionPart", "TypeDefinitionPart",
				"VariableDeclarationPart",
				"ProcedureAndFunctionDeclarationPart"
			}
		});
		put("LabelDeclarationPart", new Object[][] {
			{
				"label", "LabelList", ";"
			}, {},
		});
		put("LabelList", new Object[][] {
			{
				"LabelList", ",", "Label"
			}, {
				"Label"
			},
		});
		put("Label", new Object[][] {
			{
				"DIGSEQ"
			}
		});
		put("ConstantDefinitionPart", new Object[][] {
			{
				"const", "ConstantList"
			}, {},
		});
		put("ConstantList", new Object[][] {
			{
				"ConstantList", "ConstantDefinition"
			}, {
				"ConstantDefinition"
			},
		});
		put("ConstantDefinition", new Object[][] {
			{
				"IDENTIFIER", "=", "Cexpression", ";"
			}
		});
		put("Cexpression", new Object[][] {
			{
				"CsimpleExpression"
			}, {
				"CsimpleExpression", "Relop", "CsimpleExpression"
			},
		});
		put("CsimpleExpression", new Object[][] {
			{
				"Cterm"
			}, {
				"CsimpleExpression", "Addop", "Cterm"
			},
		});
		put("Cterm", new Object[][] {
			{
				"Cfactor"
			}, {
				"Cterm", "Mulop", "Cfactor"
			},
		});
		put("Cfactor", new Object[][] {
			{
				"Sign", "Cfactor"
			}, {
				"Cexponentiation"
			},
		});
		put("Cexponentiation", new Object[][] {
			{
				"Cprimary"
			}, {
				"Cprimary", "**", "Cexponentiation"
			},
		});
		put("Cprimary", new Object[][] {
			{
				"IDENTIFIER"
			}, {
				"(", "Cexpression", ")"
			}, {
				"UnsignedConstant"
			}, {
				"not", "Cprimary"
			},
		});
		put("Constant", new Object[][] {
			{
				"NonString"
			}, {
				"Sign", "NonString"
			}, {
				"CHARACTER_STRING"
			},
		});
		put("Sign", new Object[][] {
			{
				"+"
			}, {
				"-"
			},
		});
		put("NonString", new Object[][] {
			{
				"DIGSEQ"
			}, {
				"IDENTIFIER"
			}, {
				"REALNUMBER"
			},
		});
		put("TypeDefinitionPart", new Object[][] {
			{
				"type", "TypeDefinitionList"
			}, {},
		});
		put("TypeDefinitionList", new Object[][] {
			{
				"TypeDefinitionList", "TypeDefinition"
			}, {
				"TypeDefinition"
			},
		});
		put("TypeDefinition", new Object[][] {
			{
				"IDENTIFIER", "=", "TypeDenoter", ";"
			}
		});
		put("TypeDenoter", new Object[][] {
			{
				"IDENTIFIER"
			}, {
				"NewType"
			},
		});
		put("NewType", new Object[][] {
			{
				"NewOrdinalType"
			}, {
				"NewStructuredType"
			}, {
				"NewPointerType"
			},
		});
		put("NewOrdinalType", new Object[][] {
			{
				"EnumeratedType"
			}, {
				"SubrangeType"
			},
		});
		put("EnumeratedType", new Object[][] {
			{
				"(", "IdentifierList", ")"
			}
		});
		put("SubrangeType", new Object[][] {
			{
				"Constant", "..", "Constant"
			}
		});
		put("NewStructuredType", new Object[][] {
			{
				"StructuredType"
			}, {
				"packed", "StructuredType"
			},
		});
		put("StructuredType", new Object[][] {
			{
				"ArrayType"
			}, {
				"RecordType"
			}, {
				"SetType"
			}, {
				"FileType"
			},
		});
		put("ArrayType", new Object[][] {
			{
				"array", "[", "IndexList", "]", "of", "ComponentType"
			}
		});
		put("IndexList", new Object[][] {
			{
				"IndexList", ",", "IndexType"
			}, {
				"IndexType"
			},
		});
		put("IndexType", new Object[][] {
			{
				"OrdinalType"
			}
		});
		put("OrdinalType", new Object[][] {
			{
				"NewOrdinalType"
			}, {
				"IDENTIFIER"
			},
		});
		put("ComponentType", new Object[][] {
			{
				"TypeDenoter"
			}
		});
		put("RecordType", new Object[][] {
			{
				"record", "RecordSectionList", "end"
			}, {
				"record", "RecordSectionList", ";", "VariantPart", "end"
			}, {
				"record", "VariantPart", "end"
			},
		});
		put("RecordSectionList", new Object[][] {
			{
				"RecordSectionList", ";", "RecordSection"
			}, {
				"RecordSection"
			},
		});
		put("RecordSection", new Object[][] {
			{
				"IdentifierList", ":", "TypeDenoter"
			}
		});
		put("VariantPart", new Object[][] {
			{
				"case", "VariantSelector", "of", "VariantList", ";"
			}, {
				"case", "VariantSelector", "of", "VariantList"
			}, {},
		});
		put("VariantSelector", new Object[][] {
			{
				"TagField", ":", "TagType"
			}, {
				"TagType"
			},
		});
		put("VariantList", new Object[][] {
			{
				"VariantList", ";", "Variant"
			}, {
				"Variant"
			},
		});
		put("Variant", new Object[][] {
			{
				"CaseConstantList", ":", "(", "RecordSectionList", ")"
			},
			{
				"CaseConstantList", ":", "(", "RecordSectionList", ";",
				"VariantPart", ")"
			}, {
				"CaseConstantList", ":", "(", "VariantPart", ")"
			},
		});
		put("CaseConstantList", new Object[][] {
			{
				"CaseConstantList", ",", "CaseConstant"
			}, {
				"CaseConstant"
			},
		});
		put("CaseConstant", new Object[][] {
			{
				"Constant"
			}, {
				"Constant", "..", "Constant"
			},
		});
		put("TagField", new Object[][] {
			{
				"IDENTIFIER"
			}
		});
		put("TagType", new Object[][] {
			{
				"IDENTIFIER"
			}
		});
		put("SetType", new Object[][] {
			{
				"set", "of", "BaseType"
			}
		});
		put("BaseType", new Object[][] {
			{
				"OrdinalType"
			}
		});
		put("FileType", new Object[][] {
			{
				"file", "of", "ComponentType"
			}
		});
		put("NewPointerType", new Object[][] {
			{
				"^", "DomainType"
			}, {
				"->", "DomainType"
			},
		});
		put("DomainType", new Object[][] {
			{
				"IDENTIFIER"
			}
		});
		put("VariableDeclarationPart", new Object[][] {
			{
				"var", "VariableDeclarationList", ";"
			}, {},
		});
		put("VariableDeclarationList", new Object[][] {
			{
				"VariableDeclarationList", ";", "VariableDeclaration"
			}, {
				"VariableDeclaration"
			},
		});
		put("VariableDeclaration", new Object[][] {
			{
				"IdentifierList", ":", "TypeDenoter"
			}
		});
		put("ProcedureAndFunctionDeclarationPart", new Object[][] {
			{
				"ProcOrFuncDeclarationList", ";"
			}, {},
		});
		put("ProcOrFuncDeclarationList", new Object[][] {
			{
				"ProcOrFuncDeclarationList", ";", "ProcOrFuncDeclaration"
			}, {
				"ProcOrFuncDeclaration"
			},
		});
		put("ProcOrFuncDeclaration", new Object[][] {
			{
				"ProcedureDeclaration"
			}, {
				"FunctionDeclaration"
			},
		});
		put("ProcedureDeclaration", new Object[][] {
			{
				"ProcedureHeading", ";", "Directive"
			}, {
				"ProcedureHeading", ";", "ProcedureBlock"
			},
		});
		put("ProcedureHeading", new Object[][] {
			{
				"ProcedureIdentification"
			}, {
				"ProcedureIdentification", "FormalParameterList"
			},
		});
		put("Directive", new Object[][] {
			{
				"forward"
			}, {
				"external"
			}, {
				"extern"
			},
		});
		put("FormalParameterList", new Object[][] {
			{
				"(", "FormalParameterSectionList", ")"
			}
		});
		put("FormalParameterSectionList", new Object[][] {
			{
				"FormalParameterSectionList", ";", "FormalParameterSection"
			}, {
				"FormalParameterSection"
			},
		});
		put("FormalParameterSection", new Object[][] {
			{
				"ValueParameterSpecification"
			}, {
				"VariableParameterSpecification"
			}, {
				"ProceduralParameterSpecification"
			}, {
				"FunctionalParameterSpecification"
			},
		});
		put("ValueParameterSpecification", new Object[][] {
			{
				"IdentifierList", ":", "IDENTIFIER"
			}
		});
		put("VariableParameterSpecification", new Object[][] {
			{
				"var", "IdentifierList", ":", "IDENTIFIER"
			}
		});
		put("ProceduralParameterSpecification", new Object[][] {
			{
				"ProcedureHeading"
			}
		});
		put("FunctionalParameterSpecification", new Object[][] {
			{
				"FunctionHeading"
			}
		});
		put("ProcedureIdentification", new Object[][] {
			{
				"procedure", "IDENTIFIER"
			}
		});
		put("ProcedureBlock", new Object[][] {
			{
				"Block"
			}
		});
		put("FunctionDeclaration", new Object[][] {
			{
				"FunctionHeading", ";", "Directive"
			}, {
				"FunctionIdentification", ";", "FunctionBlock"
			}, {
				"FunctionHeading", ";", "FunctionBlock"
			},
		});
		put("FunctionHeading", new Object[][] {
			{
				"function", "IDENTIFIER", ":", "ResultType"
			},
			{
				"function", "IDENTIFIER", "FormalParameterList", ":",
				"ResultType"
			},
		});
		put("ResultType", new Object[][] {
			{
				"IDENTIFIER"
			}
		});
		put("FunctionIdentification", new Object[][] {
			{
				"function", "IDENTIFIER"
			}
		});
		put("FunctionBlock", new Object[][] {
			{
				"Block"
			}
		});
		put("StatementPart", new Object[][] {
			{
				"CompoundStatement"
			}
		});
		put("CompoundStatement", new Object[][] {
			{
				"begin", "StatementSequence", "end"
			}
		});
		put("StatementSequence", new Object[][] {
			{
				"StatementSequence", ";", "Statement"
			}, {
				"Statement"
			},
		});
		put("Statement", new Object[][] {
			{
				"OpenStatement"
			}, {
				"ClosedStatement"
			},
		});
		put("OpenStatement", new Object[][] {
			{
				"Label", ":", "NonLabeledOpenStatement"
			}, {
				"NonLabeledOpenStatement"
			},
		});
		put("ClosedStatement", new Object[][] {
			{
				"Label", ":", "NonLabeledClosedStatement"
			}, {
				"NonLabeledClosedStatement"
			},
		});
		put("NonLabeledClosedStatement", new Object[][] {
			{
				"AssignmentStatement"
			}, {
				"ProcedureStatement"
			}, {
				"GotoStatement"
			}, {
				"CompoundStatement"
			}, {
				"CaseStatement"
			}, {
				"RepeatStatement"
			}, {
				"ClosedWithStatement"
			}, {
				"ClosedIfStatement"
			}, {
				"ClosedWhileStatement"
			}, {
				"ClosedForStatement"
			}, {},
		});
		put("NonLabeledOpenStatement", new Object[][] {
			{
				"OpenWithStatement"
			}, {
				"OpenIfStatement"
			}, {
				"OpenWhileStatement"
			}, {
				"OpenForStatement"
			},
		});
		put("RepeatStatement", new Object[][] {
			{
				"repeat", "StatementSequence", "until", "BooleanExpression"
			}
		});
		put("OpenWhileStatement", new Object[][] {
			{
				"while", "BooleanExpression", "do", "OpenStatement"
			}
		});
		put("ClosedWhileStatement", new Object[][] {
			{
				"while", "BooleanExpression", "do", "ClosedStatement"
			}
		});
		put("OpenForStatement", new Object[][] {
			{
				"for", "ControlVariable", ":=", "InitialValue", "Direction",
				"FinalValue", "do", "OpenStatement"
			}
		});
		put("ClosedForStatement", new Object[][] {
			{
				"for", "ControlVariable", ":=", "InitialValue", "Direction",
				"FinalValue", "do", "ClosedStatement"
			}
		});
		put("OpenWithStatement", new Object[][] {
			{
				"with", "RecordVariableList", "do", "OpenStatement"
			}
		});
		put("ClosedWithStatement", new Object[][] {
			{
				"with", "RecordVariableList", "do", "ClosedStatement"
			}
		});
		put("OpenIfStatement", new Object[][] {
			{
				"if", "BooleanExpression", "then", "Statement"
			},
			{
				"if", "BooleanExpression", "then", "ClosedStatement", "else",
				"OpenStatement"
			},
		});
		put("ClosedIfStatement", new Object[][] {
			{
				"if", "BooleanExpression", "then", "ClosedStatement", "else",
				"ClosedStatement"
			}
		});
		put("AssignmentStatement", new Object[][] {
			{
				"VariableAccess", ":=", "Expression"
			}
		});
		put("VariableAccess", new Object[][] {
			{
				"IDENTIFIER"
			}, {
				"IndexedVariable"
			}, {
				"FieldDesignator"
			}, {
				"VariableAccess", "^"
			}, {
				"VariableAccess", "->"
			},
		});
		put("IndexedVariable", new Object[][] {
			{
				"VariableAccess", "[", "IndexExpressionList", "]"
			}
		});
		put("IndexExpressionList", new Object[][] {
			{
				"IndexExpressionList", ",", "IndexExpression"
			}, {
				"IndexExpression"
			},
		});
		put("IndexExpression", new Object[][] {
			{
				"Expression"
			}
		});
		put("FieldDesignator", new Object[][] {
			{
				"VariableAccess", ".", "IDENTIFIER"
			}
		});
		put("ProcedureStatement", new Object[][] {
			{
				"IDENTIFIER", "Params"
			}, {
				"IDENTIFIER"
			},
		});
		put("Params", new Object[][] {
			{
				"(", "ActualParameterList", ")"
			}
		});
		put("ActualParameterList", new Object[][] {
			{
				"ActualParameterList", ",", "ActualParameter"
			}, {
				"ActualParameter"
			},
		});
		put("ActualParameter", new Object[][] {
			{
				"Expression"
			}, {
				"Expression", ":", "Expression"
			}, {
				"Expression", ":", "Expression", ":", "Expression"
			},
		});
		put("GotoStatement", new Object[][] {
			{
				"goto", "Label"
			}
		});
		put("CaseStatement", new Object[][] {
			{
				"case", "CaseIndex", "of", "CaseListElementList", "end"
			},
			{
				"case", "CaseIndex", "of", "CaseListElementList", ";", "end"
			},
			{
				"case", "CaseIndex", "of", "CaseListElementList", ";",
				"Otherwisepart", "Statement", "end"
			},
			{
				"case", "CaseIndex", "of", "CaseListElementList", ";",
				"Otherwisepart", "Statement", ";", "end"
			},
		});
		put("CaseIndex", new Object[][] {
			{
				"Expression"
			}
		});
		put("CaseListElementList", new Object[][] {
			{
				"CaseListElementList", ";", "CaseListElement"
			}, {
				"CaseListElement"
			},
		});
		put("CaseListElement", new Object[][] {
			{
				"CaseConstantList", ":", "Statement"
			}
		});
		put("Otherwisepart", new Object[][] {
			{
				"otherwise"
			}, {
				"otherwise", ":"
			},
		});
		put("ControlVariable", new Object[][] {
			{
				"IDENTIFIER"
			}
		});
		put("InitialValue", new Object[][] {
			{
				"Expression"
			}
		});
		put("Direction", new Object[][] {
			{
				"to"
			}, {
				"downto"
			},
		});
		put("FinalValue", new Object[][] {
			{
				"Expression"
			}
		});
		put("RecordVariableList", new Object[][] {
			{
				"RecordVariableList", ",", "VariableAccess"
			}, {
				"VariableAccess"
			},
		});
		put("BooleanExpression", new Object[][] {
			{
				"Expression"
			}
		});
		put("Expression", new Object[][] {
			{
				"SimpleExpression"
			}, {
				"SimpleExpression", "Relop", "SimpleExpression"
			},
		});
		put("SimpleExpression", new Object[][] {
			{
				"Term"
			}, {
				"SimpleExpression", "Addop", "Term"
			},
		});
		put("Term", new Object[][] {
			{
				"Factor"
			}, {
				"Term", "Mulop", "Factor"
			},
		});
		put("Factor", new Object[][] {
			{
				"Sign", "Factor"
			}, {
				"Exponentiation"
			},
		});
		put("Exponentiation", new Object[][] {
			{
				"Primary"
			}, {
				"Primary", "**", "Exponentiation"
			},
		});
		put("Primary", new Object[][] {
			{
				"VariableAccess"
			}, {
				"UnsignedConstant"
			}, {
				"FunctionDesignator"
			}, {
				"SetConstructor"
			}, {
				"(", "Expression", ")"
			}, {
				"not", "Primary"
			},
		});
		put("UnsignedConstant", new Object[][] {
			{
				"UnsignedNumber"
			}, {
				"CHARACTER_STRING"
			}, {
				"nil"
			},
		});
		put("UnsignedNumber", new Object[][] {
			{
				"UnsignedInteger"
			}, {
				"UnsignedReal"
			},
		});
		put("UnsignedInteger", new Object[][] {
			{
				"DIGSEQ"
			}
		});
		put("UnsignedReal", new Object[][] {
			{
				"REALNUMBER"
			}
		});
		put("FunctionDesignator", new Object[][] {
			{
				"IDENTIFIER", "Params"
			}
		});
		put("SetConstructor", new Object[][] {
			{
				"[", "MemberDesignatorList", "]"
			}, {
				"[", "]"
			},
		});
		put("MemberDesignatorList", new Object[][] {
			{
				"MemberDesignatorList", ",", "MemberDesignator"
			}, {
				"MemberDesignator"
			},
		});
		put("MemberDesignator", new Object[][] {
			{
				"MemberDesignator", "..", "Expression"
			}, {
				"Expression"
			},
		});
		put("Addop", new Object[][] {
			{
				"+"
			}, {
				"-"
			}, {
				"or"
			},
		});
		put("Mulop", new Object[][] {
			{
				"*"
			}, {
				"/"
			}, {
				"div"
			}, {
				"mod"
			}, {
				"and"
			},
		});
		put("Relop", new Object[][] {
			{
				"="
			}, {
				"<>"
			}, {
				"<"
			}, {
				">"
			}, {
				"<="
			}, {
				">="
			}, {
				"in"
			},
		});
		
		debug = PARSE_TREE;
	}
}
