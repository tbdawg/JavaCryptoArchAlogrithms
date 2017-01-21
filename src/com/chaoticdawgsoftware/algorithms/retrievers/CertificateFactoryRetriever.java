package com.chaoticdawgsoftware.algorithms.retrievers;

import java.util.ArrayList;

/**
 * CertificateFactoryRetriever.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class CertificateFactoryRetriever implements Retriever {
    public ArrayList<String> getAlgorithms() {
        return ProviderAlgorithmRetrievingFactory.getAlgorithmList("CertificateFactory");
    }
}
