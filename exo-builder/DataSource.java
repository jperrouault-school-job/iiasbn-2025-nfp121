public class DataSource {
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean secure;
    private String info1;
    private String info2;
    private String info3;
    private String info4;
    private String info5;
    private String info6;

    public DataSource(boolean secure, String username, int port, String host, String password, String info1, String info2, String info3, String info4, String info6, String info5) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;

        if (host.isEmpty()) {
            throw new RuntimeException("euh vide interdit nan mais oh");
        }

        this.info1 = info1;
        this.info2 = info2;
        this.info3 = info3;
        this.info4 = info4;
        this.info5 = info5;
        this.info6 = info6;
    }
}
