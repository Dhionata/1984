package br.com.example.a1984;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import java.util.concurrent.TimeUnit;

public class Cliente extends AppCompatActivity {
    //ListView listView = new ListView(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente);
        String projectId = "fasam-1984";
        String topicId = "teste";

        try {
            postar(projectId, topicId);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "deu ruim" + e.getMessage()
                    , Toast.LENGTH_LONG).show();
        }
    }

    private void postar(String projectId, String topicId) throws InterruptedException {
        TopicName topicName = TopicName.of(projectId, topicId);

        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();

            String message = "Hello World!";
            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Published message ID: " + messageId);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "cara, entrou no catch" + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
                Toast.makeText(getApplicationContext(), "finally\nDeve ter sido postado, olha no site.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}