package com.example.demo;

import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPStreamingPublishEventHandler;

public class PrintingPubAckEventHandler implements JCSMPStreamingPublishEventHandler {
	 @Override
	    public void responseReceived(String messageID) {
	        System.out.println("Received acknowledgment for message ID: " + messageID);
	    }

	    @Override
	    public void handleError(String messageID, JCSMPException e, long timestamp) {
	        System.err.println("Error for message ID " + messageID + " - " + e);
	    }

	
}
