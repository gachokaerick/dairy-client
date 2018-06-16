/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.user.login;

import domain.LoginUser;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:LoginResource
 * [rest/user/login]<br>
 * USAGE:
 * <pre>
 *        LoginService client = new LoginService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author enrico
 */
public class LoginService {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:38164/dairy-server/webresources";

    public LoginService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest/user/login");
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String checkUser(LoginUser loginUser) throws ClientErrorException {
        Response response = webTarget.request(MediaType.TEXT_PLAIN).post(Entity.json(loginUser));
        String msg = response.readEntity(String.class);
        return msg;
    }

    public <T> T getJson(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }

}
