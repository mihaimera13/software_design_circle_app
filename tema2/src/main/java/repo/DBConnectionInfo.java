package repo;

public enum DBConnectionInfo {
    DRIVER("org.postgresql.Driver"),
    URL("jdbc:postgresql://localhost/circles"),
    USER("postgres"),
    PASSWORD("postgres");

    private final String value;

    DBConnectionInfo(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
