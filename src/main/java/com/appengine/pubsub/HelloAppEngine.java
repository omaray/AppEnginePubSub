/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appengine.pubsub;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//PubSub
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.List;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.pubsub.*;
import com.google.api.services.pubsub.model.*;

public class HelloAppEngine extends HttpServlet {
    
    private static final String APPLICATION_NAME = "my application";
    private static final String PROJECT_ID = "YOUR_PROJECT_ID";
    private static final String TOPIC_NAME = "projects/" + PROJECT_ID + "/topics/" + "topicFromAppEngine";
    private static final Logger log = Logger.getLogger(HelloAppEngine.class.getName());
      
    private static String ALL_SCOPES[] = {
      "https://www.googleapis.com/auth/pubsub",
      "https://www.googleapis.com/auth/cloud-platform",
    };
      
    private Pubsub client;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Hello App Engine!");
        
        try {
            initialize();
            createTopic(TOPIC_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    // Initialize the client with Google APIs Client Library
    public void initialize() throws Exception {
        System.out.println("Initialize the Google APIs Client Library client");
        log.info("Initialize the Google APIs Client Library client with logger");
           
        // build the transport
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
       
        // get the credentials
        List<String> scopeList = Arrays.asList(ALL_SCOPES);
        GoogleCredential credential = GoogleCredential.getApplicationDefault();
        if (credential.createScopedRequired()) {
            credential = credential.createScoped(scopeList);
        }
       
        // create the client stub
        this.client = new Pubsub.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
   
    // Create a topic with Google APIs Client Library
    public void createTopic(String topicName) throws Exception {
        System.out.println("Create a topic with Google APIs Client Library");
        log.info("Create a topic with Google APIs Client Library with logger");
        Topic topic = new Topic().setName(topicName);
        this.client.projects().topics().create(topicName, topic).execute();
    }
}
