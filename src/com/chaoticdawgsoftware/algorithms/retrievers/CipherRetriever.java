package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * CipherRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class CipherRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("Cipher");
    }
}
