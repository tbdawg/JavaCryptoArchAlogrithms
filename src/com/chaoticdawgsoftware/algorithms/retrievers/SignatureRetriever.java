package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * SignatureRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class SignatureRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("Signature");
    }
}
