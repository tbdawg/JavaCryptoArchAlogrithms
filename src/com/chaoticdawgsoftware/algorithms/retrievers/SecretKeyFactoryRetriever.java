package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * SecretKeyFactoryRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class SecretKeyFactoryRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("SecretKeyFactory");
    }
}
