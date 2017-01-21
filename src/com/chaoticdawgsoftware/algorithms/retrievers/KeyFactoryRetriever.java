package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * KeyFactoryRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class KeyFactoryRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("KeyFactory");
    }
}
