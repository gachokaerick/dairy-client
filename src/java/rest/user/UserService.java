/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.user;

import domain.User;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:UserService [rest]<br>
 * USAGE:
 * <pre>
        UserService client = new UserService();
        Object response = client.XXX(...);
        // do whatever with response
        client.close();
 </pre>
 *
 * @author enrico
 */
public class UserService {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:38164/dairy-server/webresources";

    public UserService() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest/user");
    }

    public Response getUser(Object requestEntity) throws ClientErrorException {
        return webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), Response.class);
    }

    public String saveUser(User user) {
        Response response = webTarget.request(MediaType.TEXT_PLAIN).post(Entity.json(user));
        String msg = response.readEntity(String.class);
        return msg;
    }

    public void putHtml(Object requestEntity) throws ClientErrorException {
        webTarget.request(MediaType.TEXT_HTML).put(Entity.entity(requestEntity, MediaType.TEXT_HTML));
    }

    public void close() {
        client.close();
    }

}
