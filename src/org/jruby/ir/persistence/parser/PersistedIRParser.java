package org.jruby.ir.persistence.parser;

import java.util.ArrayList;
import org.jruby.ir.operands.Variable;
import org.jruby.ir.Operation;
import org.jruby.ir.operands.KeyValuePair;
import org.jruby.util.KCode;
import org.jruby.ir.IRScopeType;
import org.jruby.ir.instructions.Instr;
import org.jruby.ir.operands.Label;
import beaver.*;
import org.jruby.util.RegexpOptions;
import org.jruby.ir.operands.Operand;
import org.jruby.ir.operands.CompoundString;
import org.jruby.parser.IRStaticScopeType;
import org.jcodings.Encoding;
import org.jruby.ir.IRScope;
import org.jruby.parser.IRStaticScope;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "persisted_ir.grammar".
 */
public class PersistedIRParser extends Parser {
	static public class Terminals {
		static public final short EOF = 0;
		static public final short ID = 1;
		static public final short LPAREN = 2;
		static public final short COLON = 3;
		static public final short LT = 4;
		static public final short BLOCK = 5;
		static public final short SYMBOL_LITERAL = 6;
		static public final short PERCENT = 7;
		static public final short SELF = 8;
		static public final short LBRACE = 9;
		static public final short BACKTICK = 10;
		static public final short COMPOUND_STRING_MARKER = 11;
		static public final short ARGS_PUSH_MARKER = 12;
		static public final short ARGS_CAT_MARKER = 13;
		static public final short SCOPE_MARKER = 14;
		static public final short MODULE_MARKER = 15;
		static public final short HASH = 16;
		static public final short REGEXP_MARKER = 17;
		static public final short BIGNUM_MARKER = 18;
		static public final short FIXNUM_MARKER = 19;
		static public final short FLOAT_MARKER = 20;
		static public final short IREXCEPTION_MARKER = 21;
		static public final short DOLLAR = 22;
		static public final short ARRAY_MARKER = 23;
		static public final short NIL = 24;
		static public final short UNEXECUTABLE_NIL = 25;
		static public final short TRUE = 26;
		static public final short FALSE = 27;
		static public final short OBJECT_CLASS = 28;
		static public final short UNKNOWN_SUPER_TARGET = 29;
		static public final short ASTERISK = 30;
		static public final short STANDARD_ERROR = 31;
		static public final short STRING_LITERAL = 32;
		static public final short SVALUE_MARKER = 33;
		static public final short UNDEFINED_VALUE = 34;
		static public final short EOLN = 35;
		static public final short FIXNUM = 36;
		static public final short RPAREN = 37;
		static public final short COMMA = 38;
		static public final short LBRACK = 39;
		static public final short RBRACK = 40;
		static public final short GT = 41;
		static public final short STRING = 42;
		static public final short STATIC_SCOPE = 43;
		static public final short RBRACE = 44;
		static public final short SCOPE = 45;
		static public final short DOT = 46;
		static public final short EQ = 47;
		static public final short BAR = 48;
		static public final short LEXICAL_PARENT_MARKER = 49;
		static public final short REGEXP_OPTIONS_MARKER = 50;
		static public final short KCODE_MARKER = 51;
		static public final short FLOAT = 52;
		static public final short GTE = 53;
		static public final short EXCLUSIVE = 54;
		static public final short INCLUSIVE = 55;
	}

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9pjc0buLSKLn$$llUIAqcgHggLgnOOaO91LCEohOOSGzhKah0BAeaXfRMjR1TnGKSGLHLP" +
		"51LVSK4H5H1H4HTpO2O8BKAsjtOw$UzyONWA8N#xZAtvVRhxVFMVEp3apS##ykMSYULUfdY" +
		"QgZyPfdkxJ8irLVxLKLrsW3kgXczHKVzPG3TU56gDPggzH6gsBTB4kqnpDq4pTfjjrfs9Q8" +
		"KyfoavAjDGWsYT9YbP1FrDZ#uUzOYkJ4kyahTCWlQfvPduCUygUrdcwItShJR$IcJfBbNMs" +
		"VgqgIbU6CbLLrNIEibHTvwg6QggMQgkEwkfo$K5$r9zqXGfqe$hf8JrijsaQutdIPYAVqCj" +
		"wmVwZ3ssghjDsBT6dz7oDVgD2xRIljOVIW90zlwYNR96EKtdRQr$e5VlSFbD5dQoVwpITOd" +
		"lqV5AUM39YX9ldKlrIfziMraAspbT3DLCBj5RAKMkrLNjrL6Vbghlor0XxDtjEFTLBlTNWc" +
		"9b$MOnZCyRYOsGSPJ6I7sFb$ttVOdzMNmtKO0tJ22qF$PqPgR6wH1CqIR$NNvGVCbwbaFvd" +
		"XFHl4jA$UKZ$DY7zsuNqxnJIlqj8$o4X$SU7zByqfFzlG$h$BgJ$NqFwtnhI$uuY$olrR2d" +
		"ydmwydoFxNqQ#7jLaNULi5SYIfsgAdoNhtc3lV4g$q3swMjVgUjsWwRfPjsY#JTQfMg25Uj" +
		"EswG7Thv$efpeHsuEsMoVeU9NJeyusMu$OftfSZ#aPhI9dVSsssrQzhhUqMckqJ#lrXjPgW" +
		"zxMEsJEx#ezVQID#a3lsx#qIHzhjpPhYxPgXxRPtxLBdzbNzczzhhtsJ$dN5$QD8imXnYac" +
		"bTaSHz$7CuRohl#JE4LK83EkI6vy2YCyZVbKyhDATh4pUVxVFRq#FBz#V2aoanWjagHeVeG" +
		"Ctgl$bunu4LP6HX13GOpg$6MJCpSaYsxCxjoKFBivcNPBCktMV3Tps37RikkrvmlQaTshCp" +
		"jGBb$KF7KZz#uHvDo364zzSdH$3PRdhEFBQi4zYwzj3RvVenXvRNgUmBSsWpsaAb$eSzaBm" +
		"lRSc#XfxczWS8$nzAiGBnrzU9AUMFWOOUUPpcyvZN5aCkEgZA1we6Ko4dzjP0Rt29dCQ6TF" +
		"OHUeHjPmhCo$B4PPZB8OFxuOuVVdHknLzO8zUM3eQESnfnhiQoFO3slndGaRhyo$pB$C$yV" +
		"lNwjK$d7llkJhqI3xhwCnpXPbdwdBtTyx8y7UwU#b7lTqSjs8owwYkWplAkon0yZDqj7oLQ" +
		"31s2AKVTk5rCLj$luNRrCpg8swN3KQr4GubQS7vGnAVkQSec7qaORkjysYnKYiiI3NImj6a" +
		"8xLRv5bNBJB89$VVqLmB28B5ESvSJQJbZZR9fWfUUUuycOdjy1Ms0uNmmxOAKNyyq4Xx8gt" +
		"y#fmsuFzHEWC5q0joFFhZoRqSIdakl5sGk$gz8xeqroRSR04khXo3vZWz5wm7lh2Lz37sVj" +
		"9qLEH0v3PoFxEVYNaEtqmJ8426EBwNNPWR9ne59ldTCOPxHlNOpp$wELEN#oaUnUn5OUUP#" +
		"phk8pEGb#DN8vSTw0#ketRD6mfiE7mpoks1bWFgSmvzSkupSj2$mPPZy9ivCbkp6xiNlO1V" +
		"wy#L82ABbxR4l577jodL$Fmu$4QmHdG5Cw6ivozNV5sOhmUQyhZlNfreJQsEILYdG$L6CDr" +
		"o0PGELZ$qaB8E4JV3Psi2hsX3pG6thtN19f1SsZfsh4Ml7ne0Qs1TUwn1hpXq0foe0CDvoB" +
		"R0ElH6mFZ8HSkWQxKqyRh0ZtH7qDEIXWJwz7BWudG7LYNNdzWpNe3usrIL$cIgRq6#mxufl" +
		"BRJDrxk6VzVN2jTUzlPSAxB2nDhDBr7wdivEd5xIbUWfwHq3wzHBiS9uULi4zsSdw2RoMeV" +
		"VYnn6OSq3MoHDsiXDWhI$h#iBcc5CPbgbVSdihlCZOTcNFa6Ad3d5nwS9qA4jfj$06n9fSe" +
		"pp#Ipz66CIIiGos8ozZ49CMs8xVHA7vl2MlFEw54$OZIzw$zAhhiVqx#5$OvVNT2$LGLk$Z" +
		"DhxBDjia#je$iG$l0thUDzfvji7Vj7NlRrjjRjixMsflsXgsnrssrBSBdrS3pvU2#miLvCR" +
		"WlJuZyGeB#V71VLbH#5fwnEmDzQN1$8hW$3knWzYWyOajiiJri3w4$MEJvWDrVf2#qUJRNv" +
		"jXzDhl8TaT2hxV2BN2ppN2Mss6wtMGt8c#mQNQziRVRlNQFtMMpx6wxDi7x6fXY2sn#iUTr" +
		"jJrfBwd4PRkiq7RQ3hHFIjPXst#mxVik#vjzUGZp97B02BbZZ2moHKEn3ANSZpFs4EGuyaV" +
		"M64J8vygHmqKqFY16RXWDyiC1bByZ7ri1iW3fPv730sASd2D53AOSnmjeWkreqClHbB7mJG" +
		"l#bzYRVaSZHoERCT$co1QCmQSH#aNKjSJU2Xzo8OYGYyV8AoCGuyGU8pEF9X0fHWwsniWsF" +
		"BUso7R84KYVzeSWZ$RTe3kwJm$qdj01luwCWzmC8kJrdiOYnr8t23f1PyfT81UzApBRqI30" +
		"WtA1z4y34xy5hGbzTm==");

	static final Action RETURN3 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 3];
		}
	};

	static final Action RETURN2 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 2];
		}
	};

	private final Action[] actions;

	public PersistedIRParser() {
		super(PARSING_TABLES);
		actions = new Action[] {
			Action.RETURN,	// [0] $goal = scopes
			RETURN3,	// [1] scopes = scope scopes scope; returns 'scope' although none is marked
			new Action() {	// [2] scope = scope_info.scope EOLN instructions.instrs EOLN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_scope = _symbols[offset + 1];
					final IRScope scope = (IRScope) _symbol_scope.value;
					final Symbol _symbol_instrs = _symbols[offset + 3];
					final Instr[] instrs = (Instr[]) _symbol_instrs.value;
					 return new Symbol( IRScopeFactory.INSTANCE.addToScope(scope, instrs) );
				}
			},
			Action.NONE,  	// [3] opt$lexical_parent = 
			Action.RETURN,	// [4] opt$lexical_parent = lexical_parent
			new Action() {	// [5] scope_info = SCOPE LPAREN scope_type.type COMMA FIXNUM.line RPAREN COLON LT STRING.name GT EOLN opt$lexical_parent.parent static_scope.ss
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_type = _symbols[offset + 3];
					final IRScopeType type = (IRScopeType) _symbol_type.value;
					final Symbol _symbol_line = _symbols[offset + 5];
					final String line = (String) _symbol_line.value;
					final Symbol _symbol_name = _symbols[offset + 9];
					final String name = (String) _symbol_name.value;
					final Symbol _symbol_parent = _symbols[offset + 12];
					final IRScope parent = (IRScope) _symbol_parent.value;
					final Symbol _symbol_ss = _symbols[offset + 13];
					final IRStaticScope ss = (IRStaticScope) _symbol_ss.value;
					 return new Symbol( IRScopeFactory.INSTANCE.createScope(type, parent, name, line, ss) );
				}
			},
			new Action() {	// [6] scope_type = ID.type
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_type = _symbols[offset + 1];
					final String type = (String) _symbol_type.value;
					 return new Symbol( IRScopeFactory.INSTANCE.createScopeType(type) );
				}
			},
			new Action() {	// [7] lexical_parent = LEXICAL_PARENT_MARKER LT STRING.name GT EOLN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_name = _symbols[offset + 3];
					final String name = (String) _symbol_name.value;
					 return new Symbol( IRScopeFactory.INSTANCE.findLexicalParent(name) );
				}
			},
			Action.NONE,  	// [8] opt$variables = 
			Action.RETURN,	// [9] opt$variables = variables
			new Action() {	// [10] static_scope = STATIC_SCOPE LPAREN static_scope_type.type RPAREN COLON LBRACK opt$variables.vars RBRACK
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_type = _symbols[offset + 3];
					final IRStaticScopeType type = (IRStaticScopeType) _symbol_type.value;
					final Symbol _symbol_vars = _symbols[offset + 7];
					final ArrayList _list_vars = (ArrayList) _symbol_vars.value;
					final String[] vars = _list_vars == null ? new String[0] : (String[]) _list_vars.toArray(new String[_list_vars.size()]);
					 return new Symbol( IRScopeFactory.INSTANCE.buildStaticScope(type, vars) );
				}
			},
			new Action() {	// [11] static_scope_type = ID.type
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_type = _symbols[offset + 1];
					final String type = (String) _symbol_type.value;
					 return new Symbol( IRScopeFactory.INSTANCE.createStaticScopeType(type) );
				}
			},
			new Action() {	// [12] variables = ID
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [13] variables = variables COMMA ID
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			RETURN2,	// [14] instructions = instruction EOLN; returns 'EOLN' although none is marked
			RETURN3,	// [15] instructions = instructions instruction EOLN; returns 'EOLN' although none is marked
			Action.RETURN,	// [16] instruction = label_instr
			Action.RETURN,	// [17] instruction = simple_instr
			Action.RETURN,	// [18] instruction = result_instr
			new Action() {	// [19] label_instr = ID.label COLON
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_label = _symbols[offset + 1];
					final String label = (String) _symbol_label.value;
					 return new Symbol( IRInstructionFactory.INSTANCE.createLabel(label) );
				}
			},
			new Action() {	// [20] simple_instr = operation.id
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_id = _symbols[offset + 1];
					final Operation id = (Operation) _symbol_id.value;
					 return new Symbol( IRInstructionFactory.INSTANCE.createTreadPoll() );
				}
			},
			new Action() {	// [21] simple_instr = operation.id label.target
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_id = _symbols[offset + 1];
					final Operation id = (Operation) _symbol_id.value;
					final Symbol _symbol_target = _symbols[offset + 2];
					final Label target = (Label) _symbol_target.value;
					 return new Symbol( IRInstructionFactory.INSTANCE.createJump(target) );
				}
			},
			new Action() {	// [22] operation = ID.name
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_name = _symbols[offset + 1];
					final String name = (String) _symbol_name.value;
					 return new Symbol( IRInstructionFactory.INSTANCE.createOperation(name) );
				}
			},
			new Action() {	// [23] param_list = param
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [24] param_list = param_list COMMA param
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			Action.RETURN,	// [25] param = operand
			new Action() {	// [26] param = FIXNUM.val
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_val = _symbols[offset + 1];
					final String val = (String) _symbol_val.value;
					 return new Symbol( Integer.valueOf(val) );
				}
			},
			Action.RETURN,	// [27] param = array
			new Action() {	// [28] result_instr = variable.var EQ rvalue.rv
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_var = _symbols[offset + 1];
					final Variable var = (Variable) _symbol_var.value;
					final Symbol _symbol_rv = _symbols[offset + 3];
					final Operand rv = (Operand) _symbol_rv.value;
					 return new Symbol( IRInstructionFactory.INSTANCE.createCopy(var, rv) );
				}
			},
			new Action() {	// [29] result_instr = variable.var EQ operation.instr
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_var = _symbols[offset + 1];
					final Variable var = (Variable) _symbol_var.value;
					final Symbol _symbol_instr = _symbols[offset + 3];
					final Operation instr = (Operation) _symbol_instr.value;
					 return new Symbol( IRInstructionFactory.INSTANCE.createReturnInstrWithNoParams(var, instr) );
				}
			},
			new Action() {	// [30] result_instr = variable.var EQ operation.instr LPAREN param_list.list RPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_var = _symbols[offset + 1];
					final Variable var = (Variable) _symbol_var.value;
					final Symbol _symbol_instr = _symbols[offset + 3];
					final Operation instr = (Operation) _symbol_instr.value;
					final Symbol _symbol_list = _symbols[offset + 5];
					final ArrayList _list_list = (ArrayList) _symbol_list.value;
					final Object[] list = _list_list == null ? new Object[0] : (Object[]) _list_list.toArray(new Object[_list_list.size()]);
					 return new Symbol( IRInstructionFactory.INSTANCE.createReturnInstrWithParams(var, instr, list) );
				}
			},
			new Action() {	// [31] result_instr = operation.instr LPAREN operand.o1 RPAREN EQ operand.o2
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_instr = _symbols[offset + 1];
					final Operation instr = (Operation) _symbol_instr.value;
					final Symbol _symbol_o1 = _symbols[offset + 3];
					final Operand o1 = (Operand) _symbol_o1.value;
					final Symbol _symbol_o2 = _symbols[offset + 6];
					final Operand o2 = (Operand) _symbol_o2.value;
					 return new Symbol( IRInstructionFactory.INSTANCE.createPutGlobalVar(o1, o2) );
				}
			},
			Action.RETURN,	// [32] rvalue = array_operand
			Action.RETURN,	// [33] rvalue = as_string
			Action.RETURN,	// [34] rvalue = backtick_string
			Action.RETURN,	// [35] rvalue = compound_array
			Action.RETURN,	// [36] rvalue = compound_string
			Action.RETURN,	// [37] rvalue = current_scope
			Action.RETURN,	// [38] rvalue = dynamic_symbol
			Action.RETURN,	// [39] rvalue = hash
			Action.RETURN,	// [40] rvalue = immutable_literal
			Action.RETURN,	// [41] rvalue = ir_exception
			Action.RETURN,	// [42] rvalue = method_handle
			Action.RETURN,	// [43] rvalue = object_class
			Action.RETURN,	// [44] rvalue = range
			Action.RETURN,	// [45] rvalue = reference
			Action.RETURN,	// [46] rvalue = regexp
			Action.RETURN,	// [47] rvalue = scope_module
			Action.RETURN,	// [48] rvalue = splat
			Action.RETURN,	// [49] rvalue = standard_error
			Action.RETURN,	// [50] rvalue = string_literal
			Action.RETURN,	// [51] rvalue = svalue
			Action.RETURN,	// [52] rvalue = undefined_value
			Action.RETURN,	// [53] rvalue = variable
			Action.RETURN,	// [54] operand = rvalue
			Action.RETURN,	// [55] operand = label
			new Action() {	// [56] array_operand = ARRAY_MARKER array.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 2];
					final Operand[] a = (Operand[]) _symbol_a.value;
					 return new Symbol( IROperandFactory.INSTANCE.createArray(a) );
				}
			},
			Action.NONE,  	// [57] opt$array_elements = 
			Action.RETURN,	// [58] opt$array_elements = array_elements
			RETURN2,	// [59] array = LBRACK opt$array_elements.els RBRACK
			new Action() {	// [60] array_elements = operand
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [61] array_elements = array_elements COMMA operand
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [62] as_string = HASH LBRACE operand.o RBRACE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_o = _symbols[offset + 3];
					final Operand o = (Operand) _symbol_o.value;
					 return new Symbol( IROperandFactory.INSTANCE.createAsString(o) );
				}
			},
			new Action() {	// [63] backtick_string = BACKTICK array.a BACKTICK
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 2];
					final Operand[] a = (Operand[]) _symbol_a.value;
					 return new Symbol( IROperandFactory.INSTANCE.createBacktickString(a) );
				}
			},
			new Action() {	// [64] compound_array = ARGS_PUSH_MARKER LBRACK operand.a1 COMMA operand.a2 RBRACK
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a1 = _symbols[offset + 3];
					final Operand a1 = (Operand) _symbol_a1.value;
					final Symbol _symbol_a2 = _symbols[offset + 5];
					final Operand a2 = (Operand) _symbol_a2.value;
					 return new Symbol( IROperandFactory.INSTANCE.createArgsPush(a1, a2) );
				}
			},
			new Action() {	// [65] compound_array = ARGS_CAT_MARKER LBRACK operand.a1 COMMA operand.a2 RBRACK
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a1 = _symbols[offset + 3];
					final Operand a1 = (Operand) _symbol_a1.value;
					final Symbol _symbol_a2 = _symbols[offset + 5];
					final Operand a2 = (Operand) _symbol_a2.value;
					 return new Symbol( IROperandFactory.INSTANCE.createArgsCat(a1, a2) );
				}
			},
			new Action() {	// [66] compound_string = COMPOUND_STRING_MARKER encoding.encoding array.pieces
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_encoding = _symbols[offset + 2];
					final Encoding encoding = (Encoding) _symbol_encoding.value;
					final Symbol _symbol_pieces = _symbols[offset + 3];
					final Operand[] pieces = (Operand[]) _symbol_pieces.value;
					 return new Symbol( IROperandFactory.INSTANCE.createCompoundString(encoding, pieces) );
				}
			},
			new Action() {	// [67] encoding = ID.encoding
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_encoding = _symbols[offset + 1];
					final String encoding = (String) _symbol_encoding.value;
					 return new Symbol( IROperandFactory.INSTANCE.createEncoding(encoding) );
				}
			},
			new Action() {	// [68] current_scope = SCOPE_MARKER LT STRING.name GT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_name = _symbols[offset + 3];
					final String name = (String) _symbol_name.value;
					 return new Symbol( IROperandFactory.INSTANCE.createCurrentScope(name) );
				}
			},
			new Action() {	// [69] dynamic_symbol = COLON compound_string.symbol_name
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_symbol_name = _symbols[offset + 2];
					final CompoundString symbol_name = (CompoundString) _symbol_symbol_name.value;
					 return new Symbol( IROperandFactory.INSTANCE.createDynamicSymbol(symbol_name) );
				}
			},
			Action.NONE,  	// [70] opt$key_value_pairs = 
			Action.RETURN,	// [71] opt$key_value_pairs = key_value_pairs
			new Action() {	// [72] hash = LBRACE opt$key_value_pairs.pairs RBRACE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_pairs = _symbols[offset + 2];
					final ArrayList _list_pairs = (ArrayList) _symbol_pairs.value;
					final KeyValuePair[] pairs = _list_pairs == null ? new KeyValuePair[0] : (KeyValuePair[]) _list_pairs.toArray(new KeyValuePair[_list_pairs.size()]);
					 return new Symbol( IROperandFactory.INSTANCE.createHash(pairs) );
				}
			},
			new Action() {	// [73] key_value_pairs = key_value_pair
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [74] key_value_pairs = key_value_pairs COMMA key_value_pair
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [75] key_value_pair = operand.key GTE operand.value
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_key = _symbols[offset + 1];
					final Operand key = (Operand) _symbol_key.value;
					final Symbol _symbol_value = _symbols[offset + 3];
					final Operand value = (Operand) _symbol_value.value;
					 return new Symbol( IROperandFactory.INSTANCE.createKeyValuePair(key, value) );
				}
			},
			new Action() {	// [76] immutable_literal = BIGNUM_MARKER FIXNUM.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_b = _symbols[offset + 2];
					final String b = (String) _symbol_b.value;
					 return new Symbol( IROperandFactory.INSTANCE.createBignum(b) );
				}
			},
			new Action() {	// [77] immutable_literal = FIXNUM_MARKER FIXNUM.n
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 2];
					final String n = (String) _symbol_n.value;
					 return new Symbol( IROperandFactory.INSTANCE.createFixnum(n) );
				}
			},
			new Action() {	// [78] immutable_literal = FLOAT_MARKER FLOAT.f
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_f = _symbols[offset + 2];
					final String f = (String) _symbol_f.value;
					 return new Symbol( IROperandFactory.INSTANCE.createFloat(f) );
				}
			},
			new Action() {	// [79] immutable_literal = NIL
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createNil() );
				}
			},
			new Action() {	// [80] immutable_literal = UNEXECUTABLE_NIL
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createUnexecutableNil() );
				}
			},
			new Action() {	// [81] immutable_literal = TRUE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createTrueLiteral() );
				}
			},
			new Action() {	// [82] immutable_literal = FALSE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createFalseLiteral() );
				}
			},
			new Action() {	// [83] ir_exception = IREXCEPTION_MARKER ID.reason
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_reason = _symbols[offset + 2];
					final String reason = (String) _symbol_reason.value;
					 return new Symbol( IROperandFactory.INSTANCE.createIRException(reason) );
				}
			},
			new Action() {	// [84] label = ID.label
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_label = _symbols[offset + 1];
					final String label = (String) _symbol_label.value;
					 return new Symbol( IROperandFactory.INSTANCE.createLabel(label) );
				}
			},
			new Action() {	// [85] method_handle = LT operand.receiver DOT operand.methodName GT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_receiver = _symbols[offset + 2];
					final Operand receiver = (Operand) _symbol_receiver.value;
					final Symbol _symbol_methodName = _symbols[offset + 4];
					final Operand methodName = (Operand) _symbol_methodName.value;
					 return new Symbol( IROperandFactory.INSTANCE.createMethodHandle(methodName, receiver) );
				}
			},
			new Action() {	// [86] object_class = OBJECT_CLASS
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createObjectClass() );
				}
			},
			new Action() {	// [87] range = LPAREN operand.b range_type.isExclusive operand.e RPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_b = _symbols[offset + 2];
					final Operand b = (Operand) _symbol_b.value;
					final Symbol _symbol_isExclusive = _symbols[offset + 3];
					final boolean isExclusive = (boolean) _symbol_isExclusive.value;
					final Symbol _symbol_e = _symbols[offset + 4];
					final Operand e = (Operand) _symbol_e.value;
					 return new Symbol( IROperandFactory.INSTANCE.createRange(b, e, isExclusive) );
				}
			},
			new Action() {	// [88] range_type = EXCLUSIVE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( true );
				}
			},
			new Action() {	// [89] range_type = INCLUSIVE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( false );
				}
			},
			Action.RETURN,	// [90] reference = backref
			Action.RETURN,	// [91] reference = global_variable
			Action.RETURN,	// [92] reference = meth_addr
			Action.RETURN,	// [93] reference = nth_ref
			Action.RETURN,	// [94] reference = symbol
			new Action() {	// [95] backref = DOLLAR SYMBOL_LITERAL.ch
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_ch = _symbols[offset + 2];
					final String ch = (String) _symbol_ch.value;
					 return new Symbol( IROperandFactory.INSTANCE.createBackref(ch) );
				}
			},
			new Action() {	// [96] global_variable = DOLLAR ID.name
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_name = _symbols[offset + 2];
					final String name = (String) _symbol_name.value;
					 return new Symbol( IROperandFactory.INSTANCE.createGlobalVariable(name) );
				}
			},
			new Action() {	// [97] meth_addr = SYMBOL_LITERAL.name
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_name = _symbols[offset + 1];
					final String name = (String) _symbol_name.value;
					 return new Symbol( IROperandFactory.INSTANCE.createMethAddr(name) );
				}
			},
			new Action() {	// [98] meth_addr = UNKNOWN_SUPER_TARGET
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createUnknownSuperTarget() );
				}
			},
			new Action() {	// [99] nth_ref = DOLLAR FIXNUM.num
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_num = _symbols[offset + 2];
					final String num = (String) _symbol_num.value;
					 return new Symbol( IROperandFactory.INSTANCE.createNthRef(num) );
				}
			},
			new Action() {	// [100] symbol = COLON SYMBOL_LITERAL.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					 return new Symbol( IROperandFactory.INSTANCE.createSymbol(s) );
				}
			},
			new Action() {	// [101] regexp = REGEXP_MARKER BAR operand.operand BAR regexp_options.options
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_operand = _symbols[offset + 3];
					final Operand operand = (Operand) _symbol_operand.value;
					final Symbol _symbol_options = _symbols[offset + 5];
					final RegexpOptions options = (RegexpOptions) _symbol_options.value;
					 return new Symbol( IROperandFactory.INSTANCE.createRegexp(operand, options) );
				}
			},
			new Action() {	// [102] scope_module = MODULE_MARKER LT STRING.name GT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_name = _symbols[offset + 3];
					final String name = (String) _symbol_name.value;
					 return new Symbol( IROperandFactory.INSTANCE.createScopeModule(name) );
				}
			},
			Action.NONE,  	// [103] opt$regexp_opt_options = 
			Action.RETURN,	// [104] opt$regexp_opt_options = regexp_opt_options
			new Action() {	// [105] regexp_options = REGEXP_OPTIONS_MARKER LPAREN KCODE_MARKER ID.kcode opt$regexp_opt_options.opt RPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_kcode = _symbols[offset + 4];
					final String kcode = (String) _symbol_kcode.value;
					final Symbol _symbol_opt = _symbols[offset + 5];
					final ArrayList _list_opt = (ArrayList) _symbol_opt.value;
					final String[] opt = _list_opt == null ? new String[0] : (String[]) _list_opt.toArray(new String[_list_opt.size()]);
					 return new Symbol( IROperandFactory.INSTANCE.createRegexpOptions(kcode, opt) );
				}
			},
			new Action() {	// [106] regexp_opt_options = regexp_opt_option
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [107] regexp_opt_options = regexp_opt_options regexp_opt_option
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
				}
			},
			RETURN2,	// [108] regexp_opt_option = COMMA ID.opt
			new Action() {	// [109] splat = ASTERISK operand.array
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_array = _symbols[offset + 2];
					final Operand array = (Operand) _symbol_array.value;
					 return new Symbol( IROperandFactory.INSTANCE.createSplat(array) );
				}
			},
			new Action() {	// [110] standard_error = STANDARD_ERROR
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createStandardError() );
				}
			},
			new Action() {	// [111] string_literal = STRING_LITERAL.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final String s = (String) _symbol_s.value;
					 return new Symbol( IROperandFactory.INSTANCE.createStringLiteral(s) );
				}
			},
			new Action() {	// [112] svalue = SVALUE_MARKER operand.array
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_array = _symbols[offset + 2];
					final Operand array = (Operand) _symbol_array.value;
					 return new Symbol( IROperandFactory.INSTANCE.createSValue(array) );
				}
			},
			new Action() {	// [113] undefined_value = UNDEFINED_VALUE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createUndefininedValue() );
				}
			},
			Action.RETURN,	// [114] variable = local_variable
			Action.RETURN,	// [115] variable = temporary_variable
			new Action() {	// [116] local_variable = SELF
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new Symbol( IROperandFactory.INSTANCE.createSelf() );
				}
			},
			new Action() {	// [117] local_variable = BLOCK.id LPAREN FIXNUM.scopeDepth COLON FIXNUM.location RPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_id = _symbols[offset + 1];
					final String id = (String) _symbol_id.value;
					final Symbol _symbol_scopeDepth = _symbols[offset + 3];
					final String scopeDepth = (String) _symbol_scopeDepth.value;
					final Symbol _symbol_location = _symbols[offset + 5];
					final String location = (String) _symbol_location.value;
					 return new Symbol( IROperandFactory.INSTANCE.createLocalVariable(id, scopeDepth, location) );
				}
			},
			new Action() {	// [118] local_variable = LT ID.id LPAREN FIXNUM.scopeDepth COLON FIXNUM.location RPAREN GT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_id = _symbols[offset + 2];
					final String id = (String) _symbol_id.value;
					final Symbol _symbol_scopeDepth = _symbols[offset + 4];
					final String scopeDepth = (String) _symbol_scopeDepth.value;
					final Symbol _symbol_location = _symbols[offset + 6];
					final String location = (String) _symbol_location.value;
					 return new Symbol( IROperandFactory.INSTANCE.createClosureLocalVariable(id, scopeDepth, location) );
				}
			},
			new Action() {	// [119] local_variable = ID.id LPAREN FIXNUM.scopeDepth COLON FIXNUM.location RPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_id = _symbols[offset + 1];
					final String id = (String) _symbol_id.value;
					final Symbol _symbol_scopeDepth = _symbols[offset + 3];
					final String scopeDepth = (String) _symbol_scopeDepth.value;
					final Symbol _symbol_location = _symbols[offset + 5];
					final String location = (String) _symbol_location.value;
					 return new Symbol( IROperandFactory.INSTANCE.createLocalVariable(id, scopeDepth, location) );
				}
			},
			new Action() {	// [120] temporary_variable = PERCENT ID.id
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_id = _symbols[offset + 2];
					final String id = (String) _symbol_id.value;
					 return new Symbol( IROperandFactory.INSTANCE.createTemporaryVariable(id) );
				}
			}
		};
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		return actions[rule_num].reduce(_symbols, offset);
	}
}