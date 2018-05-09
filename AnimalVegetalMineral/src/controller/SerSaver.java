package controller;

/*
 * Imported necessary libraries
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class SerSaver.
 * Saves and loads objects into .ser archives.
 * 
 * @author Ramon Guimerà, Héctor De Armas
 * 
 */
public class SerSaver {

    /**
     * Instance of the class
     */
    private static SerSaver instance = null;

    /**
     * Saver constructor.
     * It only creates the object.
     */
    private SerSaver() {
        MyLogger.info("Serializable Saver created");
    }
    
    /**
     * Gets instance
     * @return instance of the class
     */
    public static SerSaver getInstance() {
        if (instance == null) {
            instance = new SerSaver();
        }
        return instance;
    }

    /**
     * Saves an object into a .ser extension archive.
     * @param object object to be saved
     */
    public final void save(final Object object, final String filename) {
        try {
            FileOutputStream fileToWrite = new FileOutputStream(filename + ".ser");
            ObjectOutputStream out       = new ObjectOutputStream(fileToWrite);
            out.writeObject(object);
            out.close();
            fileToWrite.close();
            MyLogger.info("Object saved as a .ser file");

        } catch (FileNotFoundException e) {
            MyLogger.severe("FileNotFoundException at serSaver while loadindg");
            MyLogger.severe(e.getMessage());

        } catch (IOException e) {
            MyLogger.severe("IOException at serSaver while loadindg");
            MyLogger.severe(e.getMessage());
        }
    }

    /**
     * Loads an object from a .ser file.
     * @return object loaded
     */
    public final Object load(final String filename) {
        Object obj = null;

        try {
            FileInputStream fileToRead = new FileInputStream(filename + ".ser");
            ObjectInputStream in       = new ObjectInputStream(fileToRead);
            obj = in.readObject();
            in.close();
            fileToRead.close();
            MyLogger.info("Object loaded from file .ser");
            return obj;

        } catch (ClassNotFoundException e) {
            MyLogger.severe("ClassNotFoundExcept. at serSaver while loadindg");
            MyLogger.severe(e.getMessage());

        } catch (IOException e) {
            MyLogger.severe("IOException at serSaver while loadindg");
            MyLogger.severe(e.getMessage());
        }

        return obj;
    }
}
