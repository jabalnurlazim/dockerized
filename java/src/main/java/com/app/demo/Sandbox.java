package com.app.demo;

import com.app.demo.params.BlogParams;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Sandbox {

    public static void main(String[] args) {

        /** JSON to Object START **/
        System.out.println("JSON to Object");
        String blogJson = "{ \"title\" : \"Blog Title\", \"content\" : \"Blog Content\" }";
        try{
            ObjectMapper mapper = new ObjectMapper();

            BlogParams blogParams = mapper.readValue(blogJson, BlogParams.class);

            System.out.println(blogParams.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        /** JSON to Object END **/

        System.out.println();

        /** JSON to Map START **/
        System.out.println("JSON to Map");

        String blogJsonToMapCorrect = "{ \"title\" : \"Blog Title\", \"content\" : \"Blog Content\" }";
        String blogJsonToMapIncorrect = "{ \"title\" : \"Blog Title\", \"content\" : \"Blog Content\" ";
        try{
            Map<String,Object> result =
                    new ObjectMapper().readValue(blogJsonToMapIncorrect, HashMap.class);

            System.out.println(result.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        /** JSON to Map END **/

    }
}
