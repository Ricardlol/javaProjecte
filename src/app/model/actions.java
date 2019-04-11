/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.ResultSet;

/**
 *
 * @author ricard
 */
public interface actions {
    public Object search(String id);
    public void delete(String id);
}
