/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restex1;

import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Random;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author ejer
 */
@Path("quote")
public class QuoteResource {

    
    private static HashMap<Integer, String> quotes = new HashMap<Integer, String>() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
            put(4, "hallo from the other side");
        }
    };

    private Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QuoteResource
     */
    public QuoteResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.restex1.QuoteResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml(@PathParam("id") int id, Map<Integer, String> quo) {
        Gson gson = new com.google.gson.GsonBuilder().create();
        JsonObject quote = new JsonObject();
        int key = id;
        quote.addProperty("quote", quotes.get(key));
        String jsonRes = gson.toJson(quote);
        return jsonRes;
    }
    
    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom(@PathParam("random") int id, Map<Integer, String> quot) {
        Gson gson = new com.google.gson.GsonBuilder().create();
        JsonObject quote = new JsonObject();
        List<String> quoteList = new ArrayList<>();
        for (int i = 0; i < quotes.size()+1; i++) {
            quoteList.add(quotes.get(i));
        }
        int randomQuotes = new Random().nextInt(quoteList.size());
        String randomQuote = quoteList.get(randomQuotes);
        quote.addProperty("quote", randomQuote);
        String jsonRes = gson.toJson(quote);
        return jsonRes;
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postQuote(String content)
    {
        JsonObject jsonobject = new JsonParser().parse(content).getAsJsonObject();
        String quote = jsonobject.get("quote").getAsString();
        System.out.println("quote");
        quotes.put(quotes.size()+1, quote);
        JsonObject jquote = new JsonObject();
        jquote.addProperty("id", quotes.size());
        jquote.addProperty("quote", quotes.get(quotes.size()));
        String response = new Gson().toJson(jquote);
        return response;
    }
    
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String putQuote(@PathParam("id") int id, String content)
    {
        JsonObject jsonobject = new JsonParser().parse(content).getAsJsonObject();
        String quote = jsonobject.get("quote").getAsString();
        System.out.println(quote);
        quotes.put(id, quote);
        JsonObject jquote = new JsonObject();
        jquote.addProperty("id", id);
        jquote.addProperty("quote", quotes.get(id));
        String response = new Gson().toJson(jquote);
        return response;
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteQuote(@PathParam("id") int id)
    {
        quotes.remove(id);
        JsonObject jquote = new JsonObject();
        jquote.addProperty("id", id);
        jquote.addProperty("quote","");
        String response = new Gson().toJson(jquote);
        return response;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
   

    /**
     * PUT method for updating or creating an instance of QuoteResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }
}
