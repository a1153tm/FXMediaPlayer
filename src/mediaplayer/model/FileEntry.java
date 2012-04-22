/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer.model;

import java.io.File;

/**
 *
 * @author miyabetaiji
 */
public class FileEntry extends Entry {
    
    public FileEntry(File file) {
        super(file);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
