package web.service.booking.client.models;

import java.util.List;

public class Agency {
    private static Agency instance;
    private String agencyName;
    private String city;
    private String AGENCY_DB_NAME;

    private List<Service> usedServices;
    private List<Service> allServices;
    private List<BookingAgency> allBoonking;
    private List<Client> agencyClients;



    private Agency() {}

    public static synchronized Agency getInstance() {
        if (instance == null) {
            instance = new Agency();
        }
        return instance;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAGENCY_DB_NAME() {
        this.AGENCY_DB_NAME = this.agencyName.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim()+
                this.city.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim();
    }

    public static void setInstance(Agency instance) {
        Agency.instance = instance;
    }

    public String getAGENCY_DB_NAME() {
        return AGENCY_DB_NAME;
    }

    public void setAGENCY_DB_NAME(String AGENCY_DB_NAME) {
        this.AGENCY_DB_NAME = AGENCY_DB_NAME;
    }

    public List<Service> getUsedServices() {
        return usedServices;
    }


    public void setUsedServices(List<Service> usedServices) {
        this.usedServices = usedServices;
    }

    public boolean addUsedServices(Service newUsedService) {
        if (this.usedServices.add(newUsedService))
            return true;
        else return false;
    }

    public boolean removeUsedServices(Service newUsedService) {
        if (this.usedServices.remove(newUsedService))
            return true;
        else return false;
    }


    public List<BookingAgency> getAllBoonking() {
        return allBoonking;
    }

    public List<Client> getAgencyClients() {
        return agencyClients;
    }

    public List<Service> getAllServices() {
        return allServices;
    }

    public void setAllServices(List<Service> allServices) {
        this.allServices = allServices;
    }

    public void setAllBoonking(List<BookingAgency> allBoonking) {
        this.allBoonking = allBoonking;
    }

    public void setAgencyClients(List<Client> agencyClients) {
        this.agencyClients = agencyClients;
    }
}
