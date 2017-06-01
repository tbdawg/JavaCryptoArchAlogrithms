package com.chaoticdawgsoftware.algorithms;

/*
 * CreateAlgorithmEnums.java
 * Created by ChaoticDawg on 1/20/17.
 */

import com.chaoticdawgsoftware.algorithms.retrievers.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ListIterator;


public class CreateAlgorithmEnums {
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
        createEnumsDirectory();
        createEnums();
    }

    private static void createEnumsDirectory() {
        String path = buildPathFromClassName((CreateAlgorithmEnums.class.getName()).split("\\."));
        File directory = new File(path);
        if(!(directory.exists()))
            if(!directory.mkdir())
                // Change to throw an error
                System.out.println("Error: unable to create directory");
    }

    private static String buildPathFromClassName(String[] splitFullClassName) {
        StringBuilder filePath = new StringBuilder("src/");
        if (splitFullClassName.length > 0)
            for (int i = 0; i < 3; ++i)
                filePath.append(splitFullClassName[i]).append("/");
        else
            // FilePathUnderflowException
            throw new ArrayIndexOutOfBoundsException("Index must be greater than zero.");

        return filePath.append("enums/").toString();
    }

    private static void createEnums() {
        for (Retriever retriever: retrievers) {
            String[] splitFullClassName = (retriever.getClass().getName()).split("\\.");
            String packageName = getPackageNameFromFullClassName(splitFullClassName);
            String className = getClassName(splitFullClassName);

            FileWriter out = null;
            String filePath = buildPathFromClassName(splitFullClassName);
            String fileName = filePath + className + "Algorithms.java";

            if (!(new File(fileName).exists())) {
                try {
                    out = new FileWriter(fileName);
                    out.write("package " + packageName + ".enums;\n\n");
                    out.write("@SuppressWarnings({\"unused\", \"SpellCheckingInspection\"})\n");
                    out.write("public enum " + className + "Algorithms {\n");
                    ListIterator<String> iterator = retriever.getAlgorithms().listIterator();
                    while (iterator.hasNext()) {
                        String toString = iterator.next();
                        String temp = toString;
                        boolean needsAnonymous = false;
                        if (Character.isDigit(temp.charAt(0))) {
                            temp = "_" + temp;
                        }
                        if (temp.contains(".") || temp.contains("/") || temp.contains("-")) {
                            needsAnonymous = true;
                            temp = temp.replace(".","_");
                            temp = temp.replace("/", "_");
                            temp = temp.replace("-", "_");
                        }
                        out.write("\t" + temp);
                        if (needsAnonymous) {
                            out.write(" {\n\t\tpublic String toString() {\n\t\t\treturn \""
                                    + toString + "\";\n\t\t}\n\t}");
                        }
                        if (iterator.hasNext()) {
                            out.write(",");
                        }
                        out.write("\n");
                    }
                    out.write("}");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static String getClassName(String[] splitFullClassName) {
        String className;
        className = splitFullClassName[splitFullClassName.length - 1];
        className = className.substring(0, className.length() - "Retriever".length());
        return className;
    }

    private static String getPackageNameFromFullClassName(String[] splitFullClassName) {
        if (splitFullClassName.length > 0)
             return appendToPackageName(splitFullClassName);
        else
            // PackageNameUnderflowException
            throw new ArrayIndexOutOfBoundsException("Index must be greater than zero.");
    }

    private static String appendToPackageName(String[] splitFullClassName) {
        StringBuilder packageName = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            packageName.append(splitFullClassName[i]);
            if (i < 2)
                packageName.append(".");
        }
        return packageName.toString();
    }
}
