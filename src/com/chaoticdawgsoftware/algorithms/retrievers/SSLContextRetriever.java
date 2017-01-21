package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * SSLContextRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class SSLContextRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("SSLContext");
    }
}