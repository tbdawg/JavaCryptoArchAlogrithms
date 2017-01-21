package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * AlgorithmParameterGeneratorRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class AlgorithmParameterGeneratorRetriever implements Retriever{
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("AlgorithmParameterGenerator");
    }
}
