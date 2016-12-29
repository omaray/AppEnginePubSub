Basic App Engine app with Google APIs Client Library usage
==========================================================

This is a generated application from the appengine-skeleton archetype, from which extra dependencies and code were added to create a topic in Pub/Sub.

Step 1: run the steps from [Using Apache Maven and the App Engine Plugin](https://cloud.google.com/appengine/docs/java/tools/maven) to create the appengine-skeleton-archetype

Step 2: add the following entries in the pom.xml

		<!-- Added for PubSub -->
	    <dependency>
	      <groupId>com.google.apis</groupId>
	      <artifactId>google-api-services-pubsub</artifactId>
	      <version>v1-rev14-1.22.0</version>
	    </dependency>
	    <dependency>
	      <groupId>com.google.appengine</groupId>
	      <artifactId>appengine-api-1.0-sdk</artifactId>
	      <version>1.9.48</version>
	    </dependency>
	    <dependency>
	      <groupId>com.google.api-client</groupId>
	      <artifactId>google-api-client-appengine</artifactId>
	      <version>1.22.0</version>
	    </dependency>

Step 3: add the code in the HelloAppEngine.java to create a Pub/Sub topic

Step 4: run mvn appengine:devserver (for local) and mvn appengine:update (for GCP)
