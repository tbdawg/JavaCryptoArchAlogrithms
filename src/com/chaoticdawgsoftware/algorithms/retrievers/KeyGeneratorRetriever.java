package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * KeyGeneratorRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class KeyGeneratorRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("KeyGenerator");
    }
}
