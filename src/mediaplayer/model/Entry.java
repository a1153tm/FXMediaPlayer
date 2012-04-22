/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer.model;

import java.io.File;
import java.util.Iterator;

/**
 *
 * @author miyabetaiji
 */
public abstract class Entry {
    File file;

    Entry(File file) {
        this.file = file;
    }

    public long length() {
        return file.length();
    }

    public long lastModified() {
        return file.lastModified();
    }

    public String getName() {
        return file.getName();
    }

    public String getPath() {
        return file.getPath();
    }

    public boolean isFile() {
        return file.isFile();
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public Iterator iterator() {
        return null;
    }
    public abstract void accept(Visitor v);
}
