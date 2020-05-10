package com.set.compiler.core;

import com.set.compiler.gen.SetLanguageParser;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import javax.annotation.Nullable;

public enum Statement {

    ASSIGN(SetLanguageParser.Assign_varContext.class, (ctx, visitor) -> {
        return ctx.assign_var().accept(visitor);
    }),
    ASSIGN_SET(SetLanguageParser.Assign_setContext.class, (ctx, visitor) -> {
        return ctx.assign_set().accept(visitor);
    }),
    PRINT(SetLanguageParser.PrintContext.class, (ctx, visitor) -> {
        return ctx.print().accept(visitor);
    }),
    OPERATION_INT(SetLanguageParser.OperationsContext.class, (ctx, visitor) -> {
        return ctx.operations().accept(visitor);
    }),
    WHILE_CICLE(SetLanguageParser.While_cycleContext.class, (ctx, visitor) -> {
        return ctx.while_cycle().accept(visitor);
    }),
    IF_THEN(SetLanguageParser.If_thenContext.class, (ctx, visitor) -> {
        return ctx.if_then().accept(visitor);
    }),
    METHOD_INVOCATION(SetLanguageParser.Method_invocationContext.class, (ctx, visitor) -> {
        return ctx.method_invocation().accept(visitor);
    }),
    METHOD_INVOCATION_AND_ASSIGN(SetLanguageParser.Assign_var_method_invocationContext.class, (ctx, visitor) -> {
        return ctx.assign_var_method_invocation().accept(visitor);
    }),
    SET_OPERATIONS(SetLanguageParser.Operations_with_setContext.class, (ctx, visitor) -> {
        return ctx.operations_with_set().accept(visitor);
    }),
    FOR_EACH(SetLanguageParser.For_eachContext.class, (ctx, visitor) -> {
        return ctx.for_each().accept(visitor);
    });

    private final Class clazz;
    private final InvocationAction<String> action;

    Statement(Class clazz, InvocationAction<String> action) {
        this.clazz = clazz;
        this.action = action;
    }

    @Nullable
    public static Statement findStatement(ParserRuleContext ctx) {
        for (Statement statement : Statement.values()) {
            if (ctx.getRuleContext(statement.clazz, 0) != null) {
                return statement;
            }
        }
        return null;
    }

    public String invoke(SetLanguageParser.StatementContext ctx, ParseTreeVisitor<String> visitor) {
        return action.invoke(ctx, visitor);
    }

    private interface InvocationAction<T> {

        T invoke(SetLanguageParser.StatementContext ctx, ParseTreeVisitor<T> visitor);

    }

}
