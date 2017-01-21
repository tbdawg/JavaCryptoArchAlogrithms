package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * CertPathBuilderRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class CertPathBuilderRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("CertPathBuilder");
    }
}
