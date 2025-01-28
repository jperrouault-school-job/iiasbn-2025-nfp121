public class DataSource {
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean secure;

    public DataSource() { }

    public DataSource(boolean secure, String username, int port, String host, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;

        // if (host.isEmpty()) {
        //     throw new RuntimeException("euh vide interdit nan mais oh");
        // }
    }

    public static DataSourceBuilderV1 builderV1() {
        return new DataSourceBuilderV1();
    }

    public static DataSourceBuilderV2 builderV2() {
        return new DataSourceBuilderV2();
    }

    public static class DataSourceBuilderV1 {
        private DataSource instance = new DataSource();

        public DataSource build() {
            return this.instance;
        }

        public DataSourceBuilderV1 host(String host) {
            if (host.isEmpty()) {
                throw new RuntimeException("euh vide interdit nan mais oh");
            }

            this.instance.host = host;
            return this;
        }

        public DataSourceBuilderV1 port(int port) {
            this.instance.port = port;
            return this;
        }
        public DataSourceBuilderV1 username(String username) {
            this.instance.username = username;
            return this;
        }

        public DataSourceBuilderV1 password(String password) {
            this.instance.password = password;
            return this;
        }

        public DataSourceBuilderV1 secure(boolean secure) {
            this.instance.secure = secure;
            return this;
        }
    }

    public static class DataSourceBuilderV2 {
        private String host;
        private int port;
        private String username;
        private String password;
        private boolean secure;

        public DataSource build() {
            return new DataSource(secure, username, port, host, password);
        }

        public DataSourceBuilderV2 host(String host) {
            if (host.isEmpty()) {
                throw new RuntimeException("euh vide interdit nan mais oh");
            }

            this.host = host;
            return this;
        }

        public DataSourceBuilderV2 port(int port) {
            this.port = port;
            return this;
        }
        public DataSourceBuilderV2 username(String username) {
            this.username = username;
            return this;
        }

        public DataSourceBuilderV2 password(String password) {
            this.password = password;
            return this;
        }

        public DataSourceBuilderV2 secure(boolean secure) {
            this.secure = secure;
            return this;
        }
    }
}
