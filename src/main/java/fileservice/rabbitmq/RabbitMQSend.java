package fileservice.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import fileservice.FileMetadata;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSend {

    private final static String QUEUE_NAME = "storedData";
    ConnectionFactory connectionFactory;

    public RabbitMQSend() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.12.97.13");
    }

    public void sendMessageToQueue(FileMetadata fileMetadata) throws IOException, TimeoutException {

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Message message = createMessage(fileMetadata);

        channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes("UTF-8"));

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }


    private Message createMessage(FileMetadata fileMetadata) {

        String nameID = fileMetadata.getName();
        String filename = fileMetadata.getFileName();
        String encryptionkey = fileMetadata.getEncryptionkey();
        String UUID = fileMetadata.getUUID();

        Message message = new Message(nameID, encryptionkey, filename, UUID);
        return message;

    }
}
