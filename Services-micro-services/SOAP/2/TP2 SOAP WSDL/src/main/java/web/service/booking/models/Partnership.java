package web.service.booking.models;

public class Partnership {
    private String idAgency;
    private String agencyName;
    private String loginId;
    private String password;
    private double discountRate;

    public Partnership() {
    }

    public Partnership(String idAgency, String agencyName, String loginId, String password, double discountRate) {
        this.idAgency = idAgency;
        this.agencyName = agencyName;
        this.loginId = loginId;
        this.password = password;
        this.discountRate = discountRate;
    }

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDiscountRate() {
        return discountRate;
    }



    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "Partnership {" +
                "idAgency='" + idAgency + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", discountRate=" + discountRate +
                '}';
    }
}
