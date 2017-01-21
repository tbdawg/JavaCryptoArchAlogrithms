package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * KeyManagerFactoryRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class KeyManagerFactoryRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("KeyManagerFactory");
    }
}
