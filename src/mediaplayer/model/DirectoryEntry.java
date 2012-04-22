/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;

/**
 *
 * @author miyabetaiji
 */
public class DirectoryEntry extends Entry {

    List<Entry> entries = new ArrayList<Entry>();

    public DirectoryEntry(File file) {
        super(file);
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                entries.add(new FileEntry(f));
            } else if (f.isDirectory()) {
                entries.add(new DirectoryEntry(f));
            }
        }
    }

    @Override
    public Iterator iterator() {
        return entries.iterator();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
