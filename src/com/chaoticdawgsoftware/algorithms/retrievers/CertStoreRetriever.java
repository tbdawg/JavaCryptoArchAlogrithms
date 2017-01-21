package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * CertStoreRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class CertStoreRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("CertStore");
    }
}
