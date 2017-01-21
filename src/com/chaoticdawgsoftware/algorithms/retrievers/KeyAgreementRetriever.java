package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * KeyAgreementRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class KeyAgreementRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("KeyAgreement");
    }
}
