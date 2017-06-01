package com.chaoticdawgsoftware.algorithms.providerutils;

import java.security.Provider;
import java.security.Security;
import java.util.*;

/**
 * ProviderAlgorithmRetrievingFactory.java
 * Created by ChaoticDawg on 1/20/17.
 */

public class ProviderAlgorithmRetrievingFactory {
    private static Provider[] providers = Security.getProviders();

    public static ArrayList<String> getAlgorithmList(String algorithm) {
        ArrayList<String> AlgorithmList = buildAlgorithmList(algorithm);
        Collections.sort(AlgorithmList);
        return AlgorithmList;
    }

    private static ArrayList<String> buildAlgorithmList(String algorithm) {
        Set<String> keyNameSet = new HashSet<>();
        for (Provider provider : providers)
            ProviderKeyListCreator.getKeyList(algorithm, keyNameSet, provider);

        return new ArrayList<>(keyNameSet);
    }
}
