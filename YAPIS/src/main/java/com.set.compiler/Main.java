package com.set.compiler;

import com.set.compiler.core.SetLanguageParserV1;
import com.set.compiler.core.SetLanguageVisitorImplV1;
import com.set.compiler.gen.SetLanguageLexer;
import com.set.compiler.gen.SetLanguageVisitor;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String args[]) {
        try {
            ANTLRInputStream input = new ANTLRInputStream(
                    new FileInputStream("resources/test.set"));
            SetLanguageLexer lexer = new SetLanguageLexer(input);
            SetLanguageParserV1 parser = new SetLanguageParserV1(new CommonTokenStream(lexer));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ParseTree tree = parser.global_program();
            if (!byteArrayOutputStream.toString().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Lexer error: " + byteArrayOutputStream.toString());
                return;
            }
            SetLanguageVisitor<String> listVisitor = new SetLanguageVisitorImplV1(parser, "Result");
            String output = listVisitor.visit(tree);
            FileWriter fileWriter = new FileWriter("Result.java");
            fileWriter.write(output);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
