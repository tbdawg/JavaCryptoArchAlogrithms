package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * SecureRandomRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class SecureRandomRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("SecureRandom");
    }
}
