package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * MacRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class MacRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("Mac");
    }
}
