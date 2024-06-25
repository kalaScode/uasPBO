/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

/**
 *
 * @author Dell
 */
import java.util.List;

public interface ExportStrategy<T> {
    void export(List<T> data, String filePath) throws Exception;
}

