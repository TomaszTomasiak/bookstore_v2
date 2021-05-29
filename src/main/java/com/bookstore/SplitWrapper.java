package com.bookstore;

import io.split.client.SplitClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SplitWrapper {
    @Value("${split.io.api.key}")
    private String splitApiKey;
    private final SplitClient splitClient;

    public SplitWrapper(SplitClient splitClient) {
        this.splitClient = splitClient;
    }

    public boolean isTreatmentOn(String treatmentName) {
        String treatment = splitClient.getTreatment(splitApiKey, treatmentName);
        if (treatment.equals("on")) {
            return true;
        } else if (treatment.equals("off")) {
            return false;
        } else {
            throw new RuntimeException("Error retrieving treatment from Split.io");
        }
    }
}
