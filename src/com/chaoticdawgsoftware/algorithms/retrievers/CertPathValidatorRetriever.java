package com.chaoticdawgsoftware.algorithms.retrievers;

import com.chaoticdawgsoftware.algorithms.providerutils.ProviderAlgorithmRetrievingFactory;

import java.util.ArrayList;

/**
 * CertPathValidatorRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class CertPathValidatorRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("CertPathValidator");
    }
}
