package it.unife.ingsw202324.EventManager.services;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;


@SpringBootApplication
public class TemplateRestConsumer {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(TemplateRestConsumer.class);

    static String uriBaseMock = "http://localhost:3000/api/";

    public static String callREST(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();
        /*
        Creo uriBase per chiamare Mockoon se l'impostazione è useMock
         */
        if(useMock)
            uriBase = uriBaseMock;

        logger.info("Calling REST service: {}{}", uriBase, resourceName);


        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(String.class);
    }
}