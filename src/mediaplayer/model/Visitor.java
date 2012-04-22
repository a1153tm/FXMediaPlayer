/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer.model;

/**
 *
 * @author miyabetaiji
 */
public interface Visitor {
    public abstract void visit(DirectoryEntry entry);
    public abstract void visit(FileEntry entry);
}
