package com.set.compiler.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SetLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SetLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SetLanguageParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SetLanguageParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SetLanguageParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#digit_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit_expression(SetLanguageParser.Digit_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#initialize_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialize_set(SetLanguageParser.Initialize_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#initialize_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialize_element(SetLanguageParser.Initialize_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#assign_var_method_invocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_var_method_invocation(SetLanguageParser.Assign_var_method_invocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#assign_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_set(SetLanguageParser.Assign_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#assign_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_var(SetLanguageParser.Assign_varContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SetLanguageParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#operations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperations(SetLanguageParser.OperationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#operations_with_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperations_with_set(SetLanguageParser.Operations_with_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#simple_compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_compare(SetLanguageParser.Simple_compareContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#hard_compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHard_compare(SetLanguageParser.Hard_compareContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#while_cycle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_cycle(SetLanguageParser.While_cycleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#if_then}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then(SetLanguageParser.If_thenContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#for_each}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_each(SetLanguageParser.For_eachContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#global_assign_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_assign_set(SetLanguageParser.Global_assign_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#global_assign_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_assign_var(SetLanguageParser.Global_assign_varContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SetLanguageParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#type_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_1(SetLanguageParser.Type_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature(SetLanguageParser.SignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#subprogram_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogram_return(SetLanguageParser.Subprogram_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#subprogram_non_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogram_non_return(SetLanguageParser.Subprogram_non_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_return(SetLanguageParser.Block_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#block_non_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_non_return(SetLanguageParser.Block_non_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#signature_method_invocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature_method_invocation(SetLanguageParser.Signature_method_invocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#method_invocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_invocation(SetLanguageParser.Method_invocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetLanguageParser#global_program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_program(SetLanguageParser.Global_programContext ctx);
}