package com.github.adrienlauer.poss;

import com.google.inject.Inject;

@Service
public class SomeService {
    private final SomeOtherService someOtherService;

    @Inject
    public SomeService(SomeOtherService someOtherService) {
        this.someOtherService = someOtherService;
    }

    public SomeOtherService getSomeOtherService() {
        return someOtherService;
    }
}
