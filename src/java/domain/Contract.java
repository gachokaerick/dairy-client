package domain;

import java.io.Serializable;

/**
 *
 * @author enrico
 */
public class Contract implements Serializable {

    private Integer contractId;
    private int supplierId;
    private String startDate;
    private String endDate;
    private float amountPerDay;
    private float unitCost;
    private String status;

    public Contract() {
    }

    public Contract(int supplierId, String startDate, String endDate, float amountPerDay, float unitCost, String status) {
        this.supplierId = supplierId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountPerDay = amountPerDay;
        this.unitCost = unitCost;
        this.status = status;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public float getAmountPerDay() {
        return amountPerDay;
    }

    public void setAmountPerDay(float amountPerDay) {
        this.amountPerDay = amountPerDay;
    }

    public float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(float unitCost) {
        this.unitCost = unitCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contract{" + "contractId=" + contractId + ", supplierId=" + supplierId + ", startDate=" + startDate + ", endDate=" + endDate + ", amountPerDay=" + amountPerDay + ", unitCost=" + unitCost + ", status=" + status + '}';
    }

}
