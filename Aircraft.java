public class Aircraft {
    private String aircraftId;
    private String model;
    private AircraftStatus status;

    public Aircraft(String aircraftId, String model) {
        this.aircraftId = aircraftId;
        this.model = model;
        status = AircraftStatus.STATIONED;
    }
    public enum AircraftStatus {
        IN_TRANSIT,
        STATIONED
    }
    public String getAircraftId(){
        return aircraftId;
    }
    public String getModel(){
        return model;
    }
    public void setAircraftStatus(AircraftStatus status) {
        this.status = status;
    }

}
