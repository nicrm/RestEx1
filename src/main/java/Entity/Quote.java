/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ejer
 */
public class Quote {
    int id;
    String quo;

    public Quote(int id, String quo) {
        this.id = id;
        this.quo = quo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuo() {
        return quo;
    }

    public void setQuo(String quote) {
        this.quo = quote;
    }
    
}
