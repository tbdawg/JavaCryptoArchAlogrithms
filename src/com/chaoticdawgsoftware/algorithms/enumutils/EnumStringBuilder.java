package com.chaoticdawgsoftware.algorithms.enumutils;

import com.chaoticdawgsoftware.algorithms.CreateAlgorithmEnums;
import com.chaoticdawgsoftware.algorithms.errors.FilePathUnderflowException;
import com.chaoticdawgsoftware.algorithms.errors.PackageNameUnderflowException;

/**
 * EnumStringBuilder.java
 * Created by ChaoticDawg on 6/1/17.
 */

public class EnumStringBuilder {
    private static final String[] CLASS_NAME = CreateAlgorithmEnums.class.getName().split("\\.");
    private static String[] algorithmClassName = null;

    public static void setAlgorithmClassName(String[] algorithmClassName) {
        EnumStringBuilder.algorithmClassName = algorithmClassName;
    }

    public static String buildPathFromClassName() throws FilePathUnderflowException {
        StringBuilder filePath = new StringBuilder("src/");
        if(CLASS_NAME.length > 0)
            for (int i = 0; i < CLASS_NAME.length -1; ++i)
                filePath.append(CLASS_NAME[i]).append("/");
        else
            throw new FilePathUnderflowException();

        return filePath.append("enums/").toString();
    }

    public static String getPathPlusFileName() {
        return buildPathFromClassName() + getClassName() + "Algorithms.java";
    }

    static String getClassName() {
        int lastIndex = algorithmClassName.length - 1;
        int end = (algorithmClassName[lastIndex]).length() - "Retriever".length();
        return (algorithmClassName[lastIndex]).substring(0, end);
    }

    static String getPackageNameFromFullClassName() throws PackageNameUnderflowException {
        return algorithmClassName.length > 0 ? appendToPackageName() : throwPackageNameUnderFlow();
    }

    private static String throwPackageNameUnderFlow() throws PackageNameUnderflowException {
        throw new PackageNameUnderflowException();
    }

    private static String appendToPackageName() {
        StringBuilder packageName = new StringBuilder();
        for (int i = 0; i < algorithmClassName.length - 2; ++i) {
            packageName.append(algorithmClassName[i]);
            if (i < algorithmClassName.length - 3)
                packageName.append(".");
        }

        return packageName.toString();
    }

    static String prependUnderscoreIfFirstISDigit(String enumValue) {
        if (Character.isDigit(enumValue.charAt(0)))
            enumValue = "_" + enumValue;
        return enumValue;
    }

    static String replaceIllegalCharacters(String enumValue) {
        if (enumValue.contains(".") || enumValue.contains("/") || enumValue.contains("-")) {
            EnumWriter.needsAnonymous = true;
            enumValue = enumValue.replaceAll("[./-]", "_");
        }

        return enumValue;
    }


}
