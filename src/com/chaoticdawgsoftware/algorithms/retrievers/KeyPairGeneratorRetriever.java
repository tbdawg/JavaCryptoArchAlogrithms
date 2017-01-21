package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * KeyPairGeneratorRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class KeyPairGeneratorRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("KeyPairGenerator");
    }
}
