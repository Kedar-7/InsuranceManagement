
package Entity;

import java.math.BigDecimal;

public class Policy {
    private int policyId;
    private String policyType;
    private String policyDescription;
    private BigDecimal coverageAmount;

    

    public Policy(int policyId, String policyType, String policyDescription, BigDecimal coverageAmount) {
        this.policyId = policyId;
        this.policyType = policyType;
        this.policyDescription = policyDescription;
        this.coverageAmount = coverageAmount;
    }

   

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription;
    }

    public BigDecimal getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(BigDecimal coverageAmount) {
        this.coverageAmount = coverageAmount;
    }


    @Override
    public String toString() {
        return "Policy{" +
                "policyId=" + policyId +
                ", policyType='" + policyType + '\'' +
                ", policyDescription='" + policyDescription + '\'' +
                ", coverageAmount=" + coverageAmount +
                '}';
    }
}

