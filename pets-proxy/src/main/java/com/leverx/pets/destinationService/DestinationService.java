package com.leverx.pets.destinationService;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public interface DestinationService {

    HttpEntity executeGetAll(String path) throws IOException;

    HttpEntity executePost(String path, StringEntity requestEntity) throws IOException;

    void executeDelete(String path) throws IOException;

}
