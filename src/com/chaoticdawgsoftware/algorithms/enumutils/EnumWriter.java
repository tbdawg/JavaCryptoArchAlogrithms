package com.chaoticdawgsoftware.algorithms.enumutils;

import com.chaoticdawgsoftware.algorithms.retrievers.Retriever;

import java.io.FileWriter;
import java.io.IOException;

/**
 * EnumWriter.java
 * Created by ChaoticDawg on 6/1/17.
 */

public class EnumWriter {
    static boolean needsAnonymous;

    public static void writeEnumFile(Retriever retriever, FileWriter out) throws IOException {
        writeHeaderToEnumFile(out);
        int count = retriever.getAlgorithms().size() - 1;
        for(String algorithm : retriever.getAlgorithms()) {
            needsAnonymous = false;
            writeEnumValue(out, algorithm);
            writeAnonymousToString(out, algorithm);
            writeEnumValueClosing(out, count--);
        }
        out.write("}");
    }

    private static void writeHeaderToEnumFile(FileWriter out) throws IOException {
        out.write("package " + EnumStringBuilder.getPackageNameFromFullClassName() + ".enums;\n\n");
        out.write("@SuppressWarnings({\"unused\", \"SpellCheckingInspection\"})\n");
        out.write("public enum " + EnumStringBuilder.getClassName() + "Algorithms {\n");
    }

    private static void writeEnumValue(FileWriter out, String enumValue) throws IOException {
        enumValue = EnumStringBuilder.prependUnderscoreIfFirstISDigit(enumValue);
        enumValue = EnumStringBuilder.replaceIllegalCharacters(enumValue);
        out.write("\t" + enumValue);
    }

    private static void writeAnonymousToString(FileWriter out, String enumValueToString) throws IOException {
        if (needsAnonymous) {
            out.write(" {\n\t\tpublic String toString() {\n\t\t\treturn \""
                    + enumValueToString + "\";\n\t\t}\n\t}");
        }
    }

    private static void writeEnumValueClosing(FileWriter out, int count) throws IOException {
        if (count != 0)
            out.write(",");
        out.write("\n");
    }
}
