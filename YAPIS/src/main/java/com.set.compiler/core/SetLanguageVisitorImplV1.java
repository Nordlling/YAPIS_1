package com.set.compiler.core;

import com.google.common.base.Preconditions;
import com.set.compiler.core.methods.Method;
import com.set.compiler.core.methods.MethodType;
import com.set.compiler.core.variables.Variable;
import com.set.compiler.core.variables.VariableType;
import com.set.compiler.gen.SetLanguageParser;
import com.set.compiler.gen.SetLanguageVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetLanguageVisitorImplV1 implements SetLanguageVisitor<String> {

    private final SetLanguageParserV1 parserV1;
    private final VariableAndMethodRegister register = new VariableAndMethodRegister();
    private final String name;

    public SetLanguageVisitorImplV1(SetLanguageParserV1 parserV1, String name) {
        this.parserV1 = parserV1;
        this.name = name;
    }

    @Override
    public String visitProgram(SetLanguageParser.ProgramContext ctx) {
        register.registerMethodInvocation();
        String out = CompilerFields.MAIN_METHOD + ctx.block().accept(this);
        register.registerMethodInvocationEnded();
        return out;
    }

    @Override
    public String visitBlock(SetLanguageParser.BlockContext ctx) {
        register.registerNew​​VisibilityArea();
        StringBuilder out = new StringBuilder(CompilerFields.BEGIN).append(invokeAllStatementsForBlock(ctx.statement()));
        out.append(CompilerFields.END);
        register.registerVisibilityAreaEnded();
        return out.toString();
    }

    private StringBuilder invokeAllStatementsForBlock(List<SetLanguageParser.StatementContext> list) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            out.append(list.get(i).accept(this));
        }
        return out;
    }

    @Override
    public String visitStatement(SetLanguageParser.StatementContext ctx) {
        Statement statement = Statement.findStatement(ctx);
        Preconditions.checkNotNull(statement);
        return statement.invoke(ctx, this);
    }

    @Override
    public String visitDigit_expression(SetLanguageParser.Digit_expressionContext ctx) {
        validateDigitExpression(ctx);
        return concatExpr(ctx) + CompilerFields.SEPARATOR;
    }

    @Override
    public String visitInitialize_set(SetLanguageParser.Initialize_setContext ctx) {
        String ID = ((SetLanguageParser.Assign_setContext) ctx.parent).ID().getText();
        return VariableType.SET.getOutName() + " " + ID + CompilerFields.ASSIGN + CompilerFields.NEW_SET + CompilerFields.SEPARATOR + handleSetInitialization(ctx.ID(), ID);
    }

    private String handleSetInitialization(List<TerminalNode> list, String rootID) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Variable variable = Preconditions.checkNotNull(register.getVariable(list.get(i).getText()));
            if (variable.getVariableType() != VariableType.ELEMENT) {
                throw new UnsupportedOperationException();
            }
            out.append(rootID).append(CompilerFields.DELIMITER).append(String.format(CompilerFields.ADD_NEW_ELEMENT, variable.getID())).append(CompilerFields.SEPARATOR);
        }
        return out.toString();
    }

    @Override
    public String visitInitialize_element(SetLanguageParser.Initialize_elementContext ctx) {
        return String.format(CompilerFields.NEW_ELEMENT, ctx.STRING()) + CompilerFields.SEPARATOR;
    }

    @Override
    public String visitAssign_var_method_invocation(SetLanguageParser.Assign_var_method_invocationContext ctx) {
        Variable variable = new Variable(ctx.ID().getText(), Preconditions.checkNotNull(VariableType.findByDisplayName(ctx.type().getText())), ctx.CONST() != null);
        Preconditions.checkState(register.registerVariable(variable));
        Method method = Preconditions.checkNotNull(register.getRegisteredMethod(ctx.method_invocation().ID().getText()));
        if (method.getMethodType() == MethodType.RETURN_OPTIONAL || method.getMethodType().getReturnedType() != variable.getVariableType()) {
            throw new UnsupportedOperationException();
        }
        return variable.getVariableType().getOutName() + " " + variable.getID() + CompilerFields.ASSIGN + ctx.method_invocation().accept(this);
    }

    private void validateDigitExpression(SetLanguageParser.Digit_expressionContext ctx) {
        if (ctx.ID() != null) {
            Variable variable = register.getVariable(ctx.ID().toString());
            if (variable == null || variable.getVariableType() != VariableType.INT) {
                throw new UnsupportedOperationException();
            }
        }
        List<SetLanguageParser.Digit_expressionContext> expr = ctx.digit_expression();
        for (int i = 0; i < expr.size(); i++) {
            validateDigitExpression(expr.get(i));
        }
    }

    @Override
    public String visitAssign_var(SetLanguageParser.Assign_varContext ctx) {
        return processAssignVar(ctx, false);
    }

    private String processAssignVar(SetLanguageParser.Assign_varContext ctx, boolean global) {
        VariableType variableType = Preconditions.checkNotNull(VariableType.findByDisplayName(ctx.type_1().getText()));
        String out = variableType.getOutName() + " " + ctx.ID() + CompilerFields.ASSIGN + variableType.invokeInitLine(ctx, this);
        if (!global) {
            Preconditions.checkState(register.registerVariable(new Variable(ctx.ID().toString(), variableType, ctx.CONST() != null)));
        } else {
            Preconditions.checkState(register.registerGlobalVariable(new Variable(ctx.ID().toString(), variableType, ctx.CONST() != null)));
        }
        return out;
    }

    @Override
    public String visitAssign_set(SetLanguageParser.Assign_setContext ctx) {
        return processAssignSet(ctx, false);
    }

    private String processAssignSet(SetLanguageParser.Assign_setContext ctx, boolean global) {
        StringBuilder out = new StringBuilder();
        Operation operation = Operation.findOperation(ctx.initialize_set());
        Variable varForRegister;
        if (operation == null) {
            out.append(VariableType.SET.invokeInitLine(ctx, this));
            varForRegister = new Variable(ctx.ID().toString(), VariableType.SET, ctx.CONST() != null);
        } else {
            for (int i = 1; i < 2; i++) {
                Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.initialize_set().ID(i).getText()));
                if (variable.getVariableType() != VariableType.SET) {
                    throw new UnsupportedOperationException();
                }
            }
            out.append(VariableType.SET.getOutName()).append(" ").append(ctx.ID()).append(CompilerFields.ASSIGN);
            out.append(String.format(operation.getOverrideSet(), ctx.initialize_set().ID(0), ctx.initialize_set().ID(1))).append(CompilerFields.SEPARATOR);
            varForRegister = new Variable(ctx.ID().toString(), VariableType.SET);
        }
        if (!global) {
            Preconditions.checkState(register.registerVariable(varForRegister));
        } else {
            Preconditions.checkState(register.registerGlobalVariable(varForRegister));
        }
        return out.toString();
    }

    @Override
    public String visitPrint(SetLanguageParser.PrintContext ctx) {
        if (ctx.ID() != null) {
            Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.ID().getText()));
            return String.format(CompilerFields.SOUT, variable.getID()) + CompilerFields.SEPARATOR;
        } else {
            return String.format(CompilerFields.SOUT, ctx.STRING() == null ? ctx.NUMBER() : ctx.STRING()) + CompilerFields.SEPARATOR;
        }
    }

    @Override
    public String visitOperations(SetLanguageParser.OperationsContext ctx) {
        Variable variable = register.getVariable(ctx.ID().toString());
        if (variable == null || variable.getVariableType() != VariableType.INT || variable.isConstant()) {
            throw new UnsupportedOperationException();
        }
        validateDigitExpression(ctx.digit_expression());
        return variable.getID() + CompilerFields.ASSIGN + concatExpr(ctx.digit_expression()) + CompilerFields.SEPARATOR;
    }

    @Override
    //TODO rework.
    public String visitOperations_with_set(SetLanguageParser.Operations_with_setContext ctx) {
        Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.ID(0).getText()));
        Variable el = Preconditions.checkNotNull(register.getVariable(ctx.ID(1).getText()));
        if (variable.getVariableType() != VariableType.SET || el.getVariableType() != VariableType.ELEMENT || variable.isConstant()) {
            throw new UnsupportedOperationException();
        }
        if (ctx.ADD() == null) {
            return variable.getID() + CompilerFields.DELIMITER + String.format(CompilerFields.REMOVE_NEW_ELEMENT, el.getID()) + CompilerFields.SEPARATOR;
        } else {
            return variable.getID() + CompilerFields.DELIMITER + String.format(CompilerFields.REMOVE_NEW_ELEMENT, el.getID()) + CompilerFields.SEPARATOR;
        }
    }

    @Override
    public String visitSimple_compare(SetLanguageParser.Simple_compareContext ctx) {
        List<SetLanguageParser.Digit_expressionContext> dctx = ctx.digit_expression();
        validateDigitExpression(dctx.get(0));
        validateDigitExpression(dctx.get(1));
        return concatExpr(ctx);
    }

    private String concatExpr(ParserRuleContext ctx) {
        StringBuilder out = new StringBuilder();
        List<Token> tokens = parserV1.getTokens(ctx.start, ctx.stop);
        for (int i = 0; i < tokens.size(); i++) {
            out.append(tokens.get(i).getText());
        }
        return out.toString();
    }

    @Override
    public String visitHard_compare(SetLanguageParser.Hard_compareContext ctx) {
        return ctx.NEGATION().getText() + ctx.O_BRACKET().getText() + visitSimple_compare(ctx.simple_compare()) + ctx.C_BRACKET().getText();
    }

    @Override
    public String visitWhile_cycle(SetLanguageParser.While_cycleContext ctx) {
        String out = String.format(CompilerFields.WHILE, handlerCompare(ctx.hard_compare(), ctx.simple_compare()));
        out += ctx.block().accept(this);
        return out;
    }

    private String handlerCompare(SetLanguageParser.Hard_compareContext htx, SetLanguageParser.Simple_compareContext stx) {
        return stx == null ? htx.accept(this) : stx.accept(this);
    }

    @Override
    public String visitIf_then(SetLanguageParser.If_thenContext ctx) {
        return String.format(CompilerFields.IF_ELSE, handlerCompare(ctx.hard_compare(), ctx.simple_compare()), ctx.block(0).accept(this), ctx.block(1).accept(this));
    }

    @Override
    public String visitFor_each(SetLanguageParser.For_eachContext ctx) {
        Variable variable = register.getVariable(ctx.ID(1).getText());
        if (variable == null || variable.getVariableType() != VariableType.SET) {
            throw new UnsupportedOperationException();
        }
        Preconditions.checkState(register.registerVariable(new Variable(ctx.ID(0).getText(), VariableType.ELEMENT)));
        return String.format(CompilerFields.FOR_EACH, CompilerFields.ELEMENT + " " + ctx.ID(0).getText(), variable.getID()) + ctx.block().accept(this);
    }

    @Override
    public String visitGlobal_assign_set(SetLanguageParser.Global_assign_setContext ctx) {
        return CompilerFields.GLOBAL_VAR + processAssignSet(ctx.assign_set(), true);
    }

    @Override
    public String visitGlobal_assign_var(SetLanguageParser.Global_assign_varContext ctx) {
        return CompilerFields.GLOBAL_VAR + processAssignVar(ctx.assign_var(), true);
    }

    @Override
    public String visitType(SetLanguageParser.TypeContext ctx) {
        VariableType variableType = VariableType.findByDisplayName(ctx.getText());
        return Preconditions.checkNotNull(variableType).getOutName();
    }

    @Override
    public String visitType_1(SetLanguageParser.Type_1Context ctx) {
        VariableType variableType = VariableType.findByDisplayName(ctx.getText());
        return Preconditions.checkNotNull(variableType).getOutName();
    }

    @Override
    public String visitSignature(SetLanguageParser.SignatureContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append(CompilerFields.OPEN_BRACKET);
        for (int i = 0; i < ctx.ID().size(); i++) {
            VariableType variableType = VariableType.findByDisplayName(ctx.type(i).getText());
            Preconditions.checkNotNull(variableType);
            register.registerVariable(new Variable(ctx.ID(i).getText(), variableType));
            builder.append(ctx.type(i).accept(this)).append(" ").append(ctx.ID(i));
            if (i != ctx.ID().size() - 1) {
                builder.append(CompilerFields.COMMA);
            }
        }
        builder.append(CompilerFields.CLOSE_BRACKET);
        return builder.toString();
    }

    @Override
    public String visitSubprogram_return(SetLanguageParser.Subprogram_returnContext ctx) {
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null || method.getMethodType() == MethodType.RETURN_OPTIONAL) {
            throw new UnsupportedOperationException();
        }
        register.registerMethodInvocation();
        //NPE checked before.
        String s = CompilerFields.SUB_METHOD + method.getMethodType().getReturnedType().getOutName()
                + " " + ctx.ID() + ctx.signature().accept(this) + ctx.block_return().accept(this);
        register.registerMethodInvocationEnded();
        return s;
    }

    @Override
    public String visitSubprogram_non_return(SetLanguageParser.Subprogram_non_returnContext ctx) {
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null || method.getMethodType() != MethodType.RETURN_OPTIONAL) {
            throw new UnsupportedOperationException();
        }
        register.registerMethodInvocation();
        String s = CompilerFields.SUB_METHOD + CompilerFields.VOID + " " + ctx.ID() + handleSignature(ctx.signature());
        s += ctx.block_non_return() == null ? ctx.block().accept(this) : ctx.block_non_return().accept(this);
        register.registerMethodInvocationEnded();
        return s;
    }

    private String handleSignature(SetLanguageParser.SignatureContext ctx) {
        return ctx == null ? CompilerFields.OPEN_BRACKET + CompilerFields.CLOSE_BRACKET : ctx.accept(this);
    }

    @Override
    public String visitBlock_return(SetLanguageParser.Block_returnContext ctx) {
        register.registerNew​​VisibilityArea();
        Method method = Preconditions.checkNotNull(register.getRegisteredMethod(((SetLanguageParser.Subprogram_returnContext) ctx.parent).ID().getText()));
        StringBuilder out = new StringBuilder(CompilerFields.BEGIN).append(invokeAllStatementsForBlock(ctx.statement()));
        out.append(CompilerFields.RETURN);
        Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.ID().getText()));
        if (variable.getVariableType() != method.getMethodType().getReturnedType()) {
            throw new UnsupportedOperationException();
        }
        out.append(" ").append(variable.getID()).append(CompilerFields.SEPARATOR).append(CompilerFields.END);
        register.registerVisibilityAreaEnded();
        return out.toString();
    }

    @Override
    public String visitBlock_non_return(SetLanguageParser.Block_non_returnContext ctx) {
        register.registerNew​​VisibilityArea();
        StringBuilder out = new StringBuilder(CompilerFields.BEGIN).append(invokeAllStatementsForBlock(ctx.statement()));
        out.append(CompilerFields.RETURN);
        out.append(CompilerFields.SEPARATOR);
        out.append(CompilerFields.END);
        register.registerVisibilityAreaEnded();
        return out.toString();
    }

    @Override
    public String visitSignature_method_invocation(SetLanguageParser.Signature_method_invocationContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append(CompilerFields.OPEN_BRACKET);
        Method method = register.getRegisteredMethod(((SetLanguageParser.Method_invocationContext) ctx.parent).ID().getText());
        Preconditions.checkNotNull(method);
        if (method.getArguments().size() != ctx.ID().size()) {
            throw new UnsupportedOperationException();
        }
        for (int i = 0; i < ctx.ID().size(); i++) {
            Variable variable = register.getVariable(ctx.ID(i).getText());
            if (variable == null || method.getArguments().get(i) != variable.getVariableType()) {
                throw new UnsupportedOperationException();
            }
            builder.append(variable.getID());
        }
        return builder.append(CompilerFields.CLOSE_BRACKET).toString();
    }

    @Override
    public String visitMethod_invocation(SetLanguageParser.Method_invocationContext ctx) {
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null) {
            throw new UnsupportedOperationException();
        }
        return method.getID() + " " + handleSignatureOfInvocation(ctx) + CompilerFields.SEPARATOR;
    }

    private String handleSignatureOfInvocation(SetLanguageParser.Method_invocationContext ctx) {
        if (ctx.signature_method_invocation() == null) {
            Method method = Preconditions.checkNotNull(register.getRegisteredMethod(ctx.ID().getText()));
            if (method.getArguments().size() != 0) {
                throw new UnsupportedOperationException();
            }
            return CompilerFields.OPEN_BRACKET + CompilerFields.CLOSE_BRACKET;
        }
        return ctx.signature_method_invocation().accept(this);
    }

    @Override
    public String visitGlobal_program(SetLanguageParser.Global_programContext ctx) {
        StringBuilder out = new StringBuilder();
        register.registerMethod(new Method(CompilerFields.MAIN_PROGRAM, MethodType.RETURN_OPTIONAL, Collections.emptyList()));
        List<SetLanguageParser.Subprogram_non_returnContext> non_returnContexts = ctx.subprogram_non_return();
        List<SetLanguageParser.Subprogram_returnContext> returnContexts = ctx.subprogram_return();
        for (SetLanguageParser.Global_assign_varContext ct : ctx.global_assign_var()) {
            out.append(ct.accept(this));
        }
        for (SetLanguageParser.Global_assign_setContext ct : ctx.global_assign_set()) {
            out.append(ct.accept(this));
        }
        for (SetLanguageParser.Subprogram_returnContext ct : returnContexts) {
            VariableType variableType = Preconditions.checkNotNull(VariableType.findByDisplayName(ct.type().getText()));
            register.registerMethod(new Method(ct.ID().toString(), Preconditions.checkNotNull(MethodType.findByReturnedType(variableType)), collectMethodArguments(ct.signature())));
            out.append(ct.accept(this));
        }
        for (SetLanguageParser.Subprogram_non_returnContext ct : non_returnContexts) {
            register.registerMethod(new Method(ct.ID().toString(), MethodType.RETURN_OPTIONAL, collectMethodArguments(ct.signature())));
            out.append(ct.accept(this));
        }
        out.append(ctx.program().accept(this));
        return out.toString();
    }

    private List<VariableType> collectMethodArguments(SetLanguageParser.SignatureContext ctx) {
        List<VariableType> variableTypes = new ArrayList<>();
        if (ctx == null) {
            return Collections.emptyList();
        }
        for (int i = 0; i < ctx.ID().size(); i++) {
            VariableType variableType = VariableType.findByDisplayName(ctx.type(i).getText());
            variableTypes.add(Preconditions.checkNotNull(variableType));
        }
        return variableTypes;
    }

    @Override
    public String visit(ParseTree parseTree) {
        String out = parseTree.accept(this);
        System.out.println(out);
        return String.format(CompilerFields.STATIC_CONTENT, name, out);
    }

    @Override
    public String visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public String visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public String visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
