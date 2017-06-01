package com.chaoticdawgsoftware.algorithms;

/*
 * CreateAlgorithmEnums.java
 * Created by ChaoticDawg on 1/20/17.
 */

import com.chaoticdawgsoftware.algorithms.retrievers.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class CreateAlgorithmEnums {
    private static boolean needsAnonymous;
    private static final String[] CLASS_NAME = (CreateAlgorithmEnums.class.getName()).split("\\.");
    private static String[] algorithmClassName = null;
    private static Retriever[] retrievers = {
            new AlgorithmParameterGeneratorRetriever(),
            new AlgorithmParametersRetriever(),
            new CertificateFactoryRetriever(),
            new CertPathBuilderRetriever(),
            new CertPathRetriever(),
            new CertPathValidatorRetriever(),
            new CertStoreRetriever(),
            new CipherRetriever(),
            new KeyAgreementRetriever(),
            new KeyFactoryRetriever(),
            new KeyGeneratorRetriever(),
            new KeyManagerFactoryRetriever(),
            new KeyPairGeneratorRetriever(),
            new KeyStoreRetriever(),
            new MacRetriever(),
            new MessageDigestRetriever(),
            new SecretKeyFactoryRetriever(),
            new SecureRandomRetriever(),
            new SignatureRetriever(),
            new SSLContextRetriever(),
            new TrustManagerFactoryRetriever()
    };

    public static void create() {
        try {
            createEnumsDirectory();
            createEnums();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEnumsDirectory() throws FilePathUnderflowException, UnableToCreateDirectoryException {
        String path = buildPathFromClassName();
        File directory = new File(path);
        if(!(directory.exists()))
            if(!directory.mkdir())
                throw new UnableToCreateDirectoryException(directory.getAbsolutePath());
    }

    private static String buildPathFromClassName() throws FilePathUnderflowException {
        StringBuilder filePath = new StringBuilder("src/");
        if(CLASS_NAME.length > 0)
            for (int i = 0; i < CLASS_NAME.length -1; ++i)
                filePath.append(CLASS_NAME[i]).append("/");
        else
            throw new FilePathUnderflowException();

        return filePath.append("enums/").toString();
    }

    private static void createEnums() throws PackageNameUnderflowException {
        for (Retriever retriever: retrievers) {
            algorithmClassName = (retriever.getClass().getName()).split("\\.");
            checkIfFileExistsIfNotWriteFile(retriever);
        }
    }

    private static void checkIfFileExistsIfNotWriteFile(Retriever retriever) {
        if (!(new File(getPathPlusFileName()).exists())) {
            try (FileWriter out = new FileWriter(getPathPlusFileName())) {
                writeEnumFile(retriever, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getPathPlusFileName() {
        return buildPathFromClassName() + getClassName() + "Algorithms.java";
    }

    private static String getClassName() {
        int lastIndex = algorithmClassName.length - 1;
        int end = (algorithmClassName[lastIndex]).length() - "Retriever".length();
        return (algorithmClassName[lastIndex]).substring(0, end);
    }

    private static void writeEnumFile(Retriever retriever, FileWriter out) throws IOException {
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
        out.write("package " + getPackageNameFromFullClassName() + ".enums;\n\n");
        out.write("@SuppressWarnings({\"unused\", \"SpellCheckingInspection\"})\n");
        out.write("public enum " + getClassName() + "Algorithms {\n");
    }

    private static String getPackageNameFromFullClassName() throws PackageNameUnderflowException {
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

    private static void writeEnumValue(FileWriter out, String enumValue) throws IOException {
        enumValue = prependUnderscoreIfFirstISDigit(enumValue);
        enumValue = replaceIllegalCharacters(enumValue);
        out.write("\t" + enumValue);
    }

    private static String prependUnderscoreIfFirstISDigit(String enumValue) {
        if (Character.isDigit(enumValue.charAt(0)))
            enumValue = "_" + enumValue;
        return enumValue;
    }

    private static String replaceIllegalCharacters(String enumValue) {
        if (enumValue.contains(".") || enumValue.contains("/") || enumValue.contains("-")) {
            needsAnonymous = true;
            enumValue = enumValue.replaceAll("[./-]", "_");
        }

        return enumValue;
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

    private static class UnableToCreateDirectoryException extends Exception {
        UnableToCreateDirectoryException(String directory) {
            super("System was not able to create directory: " + directory);
        }
    }

    private static class FilePathUnderflowException extends ArrayIndexOutOfBoundsException {
        FilePathUnderflowException() {
            super("Index must be greater than zero.");
        }
    }

    private static class PackageNameUnderflowException extends ArrayIndexOutOfBoundsException {
        PackageNameUnderflowException() {
            super("Index must be greater than zero.");
        }
    }
}
