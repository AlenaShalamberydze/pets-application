package com.leverx.pets.destinationService.impl;

import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.NoArgsConstructor;

import java.net.URI;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DestinationConstants {

    public static final String HEADER = "application/json";

    private static final String DESTINATION_NAME = "cf-proxy-pets";

    public static final HttpDestination DESTINATION = getDestination(DESTINATION_NAME).asHttp();

    public static final URI URI = DESTINATION.getUri();

}
