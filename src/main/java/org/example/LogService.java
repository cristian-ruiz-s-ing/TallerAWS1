package org.example;

import static spark.Spark.*;

public class LogService {

    public static void main(String [] args){
        port (5000);
        get("/logservicefacade", (req, res) -> {
            res.type("application/json");
            return "{\"logid1\":\"20-2-2024-Log inicial\"}";
        });

    }

}
