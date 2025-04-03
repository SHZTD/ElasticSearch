package org.example;

public class Main {
    public static void main(String[] args) {
        VerLogs vl = new VerLogs();
        // selecciona los 10 primeros campos
        vl.hacerQuery("FROM logs_server | LIMIT 10");

        // obtener solo las peticiones con errores (status >= 400) y las primeras 20
        vl.hacerQuery("FROM logs_server | WHERE status >= 400 | KEEP ip, timestamp, method, url, status | LIMIT 20");

        // selecciona las ips que han hecho mas requests
        vl.hacerQuery("FROM logs_server | STATS request_count = COUNT(*) BY ip | SORT request_count DESC | LIMIT 1");
    }
}