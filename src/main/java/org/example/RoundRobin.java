package org.example;


import static spark.Spark.*;

public class RoundRobin {
    private static final String LOG_SERVICE_URL = "http://localhost:5000/logservicefacade";
    public static void main(String[] args) {
        RemoteLogServiceInvoker invoker = new RemoteLogServiceInvoker(LOG_SERVICE_URL);
        System.out.println("Starting Server ...");
        port(getPort());
        get("/", (req, res)-> getPage());
        get("/logservicefacade", (req, res) -> {
            res.type("application/json");
            return invoker.Invoke(args);
        });
        
    }

    private static String getPage() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Form Example</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Form with GET</h1>\n" +
                "<form action=\"/logservicefacade\">\n" +
                "    <label for=\"msg\">Name:</label><br>\n" +
                "    <input type=\"text\" id=\"msg\" name=\"msg\" value=\"Message example...\"><br><br>\n" +
                "    <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "</form>\n" +
                "<div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "<script>\n" +
                "    function loadGetMsg() {\n" +
                "        let msg = document.getElementById(\"msg\").value;\n" +
                "        const xhttp = new XMLHttpRequest();\n" +
                "        xhttp.onload = function() {\n" +
                "            document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "            this.responseText;\n" +
                "        }\n" +
                "        xhttp.open(\"GET\", \"/logservicefacade?msg=\"+msg);\n" +
                "        xhttp.send();\n" +
                "    }\n" +
                "</script>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

    private static int getPort() {
        if (System.getenv("PORT")!= null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}