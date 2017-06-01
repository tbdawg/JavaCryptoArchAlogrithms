package com.chaoticdawgsoftware.algorithms;

/*
 * CreateAlgorithmEnums.java
 * Created by ChaoticDawg on 1/20/17.
 */

import com.chaoticdawgsoftware.algorithms.retrievers.*;
import com.chaoticdawgsoftware.algorithms.errors.*;
import com.chaoticdawgsoftware.algorithms.enumutils.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
        try {
            createEnumsDirectory();
            createEnums();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEnumsDirectory() throws FilePathUnderflowException, UnableToCreateDirectoryException {
        String path = EnumStringBuilder.buildPathFromClassName();
        File directory = new File(path);
        if(!(directory.exists()))
            if(!directory.mkdir())
                throw new UnableToCreateDirectoryException(directory.getAbsolutePath());
    }

    private static void createEnums() throws PackageNameUnderflowException {
        for (Retriever retriever: retrievers) {
            EnumStringBuilder.setAlgorithmClassName((retriever.getClass().getName()).split("\\."));
            checkIfFileExistsIfNotWriteFile(retriever);
        }
    }

    private static void checkIfFileExistsIfNotWriteFile(Retriever retriever) {
        if (!(new File(EnumStringBuilder.getPathPlusFileName()).exists())) {
            try (FileWriter out = new FileWriter(EnumStringBuilder.getPathPlusFileName())) {
                EnumWriter.writeEnumFile(retriever, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
