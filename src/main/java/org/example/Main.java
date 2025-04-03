package org.example;

public class Main {
    public static void main(String[] args) {
        VerLogs vl = new VerLogs();
        vl.hacerQuery(
                "FROM logs_server " +
                        "| KEEP ip, timestamp, url " +
                        "| LIMIT 5"
        );
    }
}
