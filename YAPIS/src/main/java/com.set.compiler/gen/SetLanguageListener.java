package com.set.compiler.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SetLanguageParser}.
 */
public interface SetLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SetLanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SetLanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SetLanguageParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SetLanguageParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SetLanguageParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SetLanguageParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#digit_expression}.
	 * @param ctx the parse tree
	 */
	void enterDigit_expression(SetLanguageParser.Digit_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#digit_expression}.
	 * @param ctx the parse tree
	 */
	void exitDigit_expression(SetLanguageParser.Digit_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#initialize_set}.
	 * @param ctx the parse tree
	 */
	void enterInitialize_set(SetLanguageParser.Initialize_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#initialize_set}.
	 * @param ctx the parse tree
	 */
	void exitInitialize_set(SetLanguageParser.Initialize_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#initialize_element}.
	 * @param ctx the parse tree
	 */
	void enterInitialize_element(SetLanguageParser.Initialize_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#initialize_element}.
	 * @param ctx the parse tree
	 */
	void exitInitialize_element(SetLanguageParser.Initialize_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#assign_var_method_invocation}.
	 * @param ctx the parse tree
	 */
	void enterAssign_var_method_invocation(SetLanguageParser.Assign_var_method_invocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#assign_var_method_invocation}.
	 * @param ctx the parse tree
	 */
	void exitAssign_var_method_invocation(SetLanguageParser.Assign_var_method_invocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#assign_set}.
	 * @param ctx the parse tree
	 */
	void enterAssign_set(SetLanguageParser.Assign_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#assign_set}.
	 * @param ctx the parse tree
	 */
	void exitAssign_set(SetLanguageParser.Assign_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#assign_var}.
	 * @param ctx the parse tree
	 */
	void enterAssign_var(SetLanguageParser.Assign_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#assign_var}.
	 * @param ctx the parse tree
	 */
	void exitAssign_var(SetLanguageParser.Assign_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(SetLanguageParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(SetLanguageParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#operations}.
	 * @param ctx the parse tree
	 */
	void enterOperations(SetLanguageParser.OperationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#operations}.
	 * @param ctx the parse tree
	 */
	void exitOperations(SetLanguageParser.OperationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#operations_with_set}.
	 * @param ctx the parse tree
	 */
	void enterOperations_with_set(SetLanguageParser.Operations_with_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#operations_with_set}.
	 * @param ctx the parse tree
	 */
	void exitOperations_with_set(SetLanguageParser.Operations_with_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#simple_compare}.
	 * @param ctx the parse tree
	 */
	void enterSimple_compare(SetLanguageParser.Simple_compareContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#simple_compare}.
	 * @param ctx the parse tree
	 */
	void exitSimple_compare(SetLanguageParser.Simple_compareContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#hard_compare}.
	 * @param ctx the parse tree
	 */
	void enterHard_compare(SetLanguageParser.Hard_compareContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#hard_compare}.
	 * @param ctx the parse tree
	 */
	void exitHard_compare(SetLanguageParser.Hard_compareContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#while_cycle}.
	 * @param ctx the parse tree
	 */
	void enterWhile_cycle(SetLanguageParser.While_cycleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#while_cycle}.
	 * @param ctx the parse tree
	 */
	void exitWhile_cycle(SetLanguageParser.While_cycleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#if_then}.
	 * @param ctx the parse tree
	 */
	void enterIf_then(SetLanguageParser.If_thenContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#if_then}.
	 * @param ctx the parse tree
	 */
	void exitIf_then(SetLanguageParser.If_thenContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#for_each}.
	 * @param ctx the parse tree
	 */
	void enterFor_each(SetLanguageParser.For_eachContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#for_each}.
	 * @param ctx the parse tree
	 */
	void exitFor_each(SetLanguageParser.For_eachContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#global_assign_set}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_assign_set(SetLanguageParser.Global_assign_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#global_assign_set}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_assign_set(SetLanguageParser.Global_assign_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#global_assign_var}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_assign_var(SetLanguageParser.Global_assign_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#global_assign_var}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_assign_var(SetLanguageParser.Global_assign_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SetLanguageParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SetLanguageParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#type_1}.
	 * @param ctx the parse tree
	 */
	void enterType_1(SetLanguageParser.Type_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#type_1}.
	 * @param ctx the parse tree
	 */
	void exitType_1(SetLanguageParser.Type_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(SetLanguageParser.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(SetLanguageParser.SignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#subprogram_return}.
	 * @param ctx the parse tree
	 */
	void enterSubprogram_return(SetLanguageParser.Subprogram_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#subprogram_return}.
	 * @param ctx the parse tree
	 */
	void exitSubprogram_return(SetLanguageParser.Subprogram_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#subprogram_non_return}.
	 * @param ctx the parse tree
	 */
	void enterSubprogram_non_return(SetLanguageParser.Subprogram_non_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#subprogram_non_return}.
	 * @param ctx the parse tree
	 */
	void exitSubprogram_non_return(SetLanguageParser.Subprogram_non_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 */
	void enterBlock_return(SetLanguageParser.Block_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 */
	void exitBlock_return(SetLanguageParser.Block_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#block_non_return}.
	 * @param ctx the parse tree
	 */
	void enterBlock_non_return(SetLanguageParser.Block_non_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#block_non_return}.
	 * @param ctx the parse tree
	 */
	void exitBlock_non_return(SetLanguageParser.Block_non_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#signature_method_invocation}.
	 * @param ctx the parse tree
	 */
	void enterSignature_method_invocation(SetLanguageParser.Signature_method_invocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#signature_method_invocation}.
	 * @param ctx the parse tree
	 */
	void exitSignature_method_invocation(SetLanguageParser.Signature_method_invocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#method_invocation}.
	 * @param ctx the parse tree
	 */
	void enterMethod_invocation(SetLanguageParser.Method_invocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#method_invocation}.
	 * @param ctx the parse tree
	 */
	void exitMethod_invocation(SetLanguageParser.Method_invocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetLanguageParser#global_program}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_program(SetLanguageParser.Global_programContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetLanguageParser#global_program}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_program(SetLanguageParser.Global_programContext ctx);
}