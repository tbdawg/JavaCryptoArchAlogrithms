package com.chaoticdawgsoftware.algorithms.retrievers;

import com.chaoticdawgsoftware.algorithms.providerutils.ProviderAlgorithmRetrievingFactory;

import java.util.ArrayList;

/**
 * CertPathRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class CertPathRetriever implements Retriever {
    // Encodings not found on my machine // Typically PKCS7 PkiPath
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("CertPath");
    }
}
