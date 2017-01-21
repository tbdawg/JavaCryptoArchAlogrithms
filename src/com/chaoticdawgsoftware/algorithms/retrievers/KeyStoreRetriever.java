package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * KeyStoreRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class KeyStoreRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("KeyStore");
    }
}
