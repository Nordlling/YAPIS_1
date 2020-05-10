package com.set.compiler.core;

import com.set.compiler.gen.SetLanguageParser;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.annotation.Nullable;

public enum Operation {

    PLUS(SetLanguageParser.MULTIPLY, "Set.union(%s,%s)"),
    MINUS(SetLanguageParser.MINUS, "Set.diff(%s,%s)"),
    DIVIDE(SetLanguageParser.DIVIDE, "Set.simDiff(%s,%s)"),
    MULTIPLY(SetLanguageParser.MULTIPLY, "Set.intersection(%s,%s)"),;

    private final int type;
    private final String overrideSet;

    Operation(int type, String overrideSet) {
        this.type = type;
        this.overrideSet = overrideSet;
    }

    @Nullable
    public static Operation findOperation(ParserRuleContext ctx) {
        for (Operation operation : Operation.values()) {
            if (ctx.getToken(operation.type, 0) != null) {
                return operation;
            }
        }
        return null;
    }

    public String getOverrideSet() {
        return overrideSet;
    }

}
