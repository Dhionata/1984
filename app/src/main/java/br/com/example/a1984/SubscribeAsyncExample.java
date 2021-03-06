package br.com.example.a1984;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SubscribeAsyncExample extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String projectId = "fasam-1984";
        String subscriptionId = "teste";

        try {
            subscribeAsyncExample(projectId, subscriptionId);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Cara, deu um probleminha:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void subscribeAsyncExample(String projectId,
                                      String subscriptionId) {
        TextView Tid = findViewById(R.id.textViewID);
        TextView Tmessage = findViewById(R.id.textViewMessage);
        try {
            ProjectSubscriptionName subscriptionName =
                    ProjectSubscriptionName.of(projectId, subscriptionId);

            // Instantiate an asynchronous message receiver.
            MessageReceiver receiver =
                    (PubsubMessage message, AckReplyConsumer consumer) -> {
                        // Handle incoming message, then ack the received message.
                        //System.out.println("Id: " + message.getMessageId());
                        //System.out.println("Data: " + message.getData().toStringUtf8());
                        Tid.setText(message.getMessageId());
                        Tmessage.setText(message.getMessageId());
                        consumer.ack();
                    };

            Subscriber subscriber = null;
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
            // Start the subscriber.
            subscriber.startAsync().awaitRunning();
            System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
            // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // Shut down the subscriber after 30s. Stop receiving messages.
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Cara, deu um probleminha 2:\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}