/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.contract;

import domain.Contract;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:ContractResource
 * [rest/contract]<br>
 * USAGE:
 * <pre>
 *        ContractService client = new ContractService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author enrico
 */
public class ContractService {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:38164/dairy-server/webresources";
    private static final String PENDING_CONTRACTS_ENDPOINT = BASE_URI + "/rest/contract/pending";
    private static final String APPROVED_CONTRACTS_ENDPOINT = BASE_URI + "/rest/contract/approved";
    private static final String DENIED_CONTRACTS_ENDPOINT = BASE_URI + "/rest/contract/denied";

    public ContractService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest/contract");
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String saveContract(Contract contract) throws ClientErrorException {
        Response response = webTarget.request(MediaType.TEXT_PLAIN).post(Entity.json(contract));
        String msg = response.readEntity(String.class);
        return msg;
    }

    public List<Contract> getPendingContracts() throws ClientErrorException {
        WebTarget target = client.target(PENDING_CONTRACTS_ENDPOINT);
        return getList(target);
    }

    public List<Contract> getApprovedContracts() throws ClientErrorException {
        WebTarget target = client.target(APPROVED_CONTRACTS_ENDPOINT);
        return getList(target);
    }

    public List<Contract> getDeniedContracts() throws ClientErrorException {
        WebTarget target = client.target(DENIED_CONTRACTS_ENDPOINT);
        return getList(target);
    }

    public String editContractStatus(Contract contract) {
        WebTarget target = client.target(BASE_URI).path("rest/contract");
        Response response = target.request(MediaType.TEXT_PLAIN).put(Entity.json(contract));
        String msg = response.readEntity(String.class);
        return msg;
    }

    public String deleteContract(String contractId) {
        WebTarget target = client.target(BASE_URI).path("rest/contract/" + contractId);
        Response response = target.request(MediaType.TEXT_PLAIN).delete();
        String msg = response.readEntity(String.class);
        return msg;
    }

    private List<Contract> getList(WebTarget target) {
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        List<Contract> list = new ArrayList<>();
        JsonArray jsonArray = response.readEntity(JsonArray.class);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject contractJson = jsonArray.getJsonObject(i);
            int contractId = contractJson.getJsonNumber("contractId").intValue();
            int supplierId = contractJson.getJsonNumber("supplierId").intValue();
            String startDate = contractJson.getString("startDate");
            String endDate = contractJson.getString("endDate");
            float amountPerDay = (float) contractJson.getJsonNumber("amountPerDay").doubleValue();
            float unitCost = (float) contractJson.getJsonNumber("unitCost").doubleValue();
            String status = contractJson.getString("status");

            Contract contract = new Contract(supplierId, startDate, endDate, amountPerDay, unitCost, status);
            contract.setContractId(contractId);
            list.add(contract);
        }
        return Collections.unmodifiableList(list);
    }

    public void close() {
        client.close();
    }

}
