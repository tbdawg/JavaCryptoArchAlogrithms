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

    private static void createEnums() {
        String fullClassName;
        String[] splitFullClassName;
        String packageName;
        String className;

        for (Retriever retriever: retrievers) {

            fullClassName = retriever.getClass().getName();
            splitFullClassName = fullClassName.split("\\.");
            packageName = getPackageNameFromFullClassName(splitFullClassName);
            className = splitFullClassName[splitFullClassName.length - 1];
            className = className.substring(0, className.length() - "Retriever".length());

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

    private static void createEnumsDirectory() {
        String fullClassName= CreateAlgorithmEnums.class.getName();
        String[] splitFullClassName = fullClassName.split("\\.");
        String path = buildPathFromClassName(splitFullClassName);
        File directory = new File(path);
        if(!(directory.exists()))
             if(!directory.mkdir())
                 // Change to throw an error
                 System.out.println("Error: unable to create directory");
    }

    private static String getPackageNameFromFullClassName(String[] splitFullClassName) {
        String packageName = "";
        if (splitFullClassName.length > 0) {
            for (int i = 0; i < 3; ++i) {
                packageName += splitFullClassName[i];
                if (i < 2) {
                    packageName += ".";
                }
            }
        } else {
            // PackageNameUnderflowException
            throw new ArrayIndexOutOfBoundsException("Index must be greater than zero.");
        }
        return packageName;
    }

    // "src/" + splitFullClassName[0] + "/" + splitFullClassName[1] + "/" + splitFullClassName[2] + "/";
    private static String buildPathFromClassName(String[] splitFullClassName) {
        String filePath = "src/";
        if (splitFullClassName.length > 0) {
            for (int i = 0; i < 3; ++i) {
                filePath += splitFullClassName[i] + "/";
            }
        } else {
            // FilePathUnderflowException
            throw new ArrayIndexOutOfBoundsException("Index must be greater than zero.");
        }
        return filePath + "enums/";
    }

}
