package it.uniroma3.diadia.comandi;

import java.util.Set;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;


public class ComandoAiuto implements Comando {
    private IO ioconsole;

    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        //this.ioconsole.mostraMessaggio("Comandi disponibili 'vai' 'aiuto' 'fine' 'prendi' 'posa'.");

        // Replace with the package that contains your classes
        Reflections reflections = new Reflections(
            new ConfigurationBuilder()
            .forPackages("") // scan everything
            .addScanners(Scanners.SubTypes)
        );

        // Get all classes in that package
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        this.ioconsole.mostraMessaggio("classi trovate: " + allClasses.size());

        String msg = "Comandi disponibili: ";
        for (Class<?> clazz : allClasses) {
            if (clazz.getSimpleName().startsWith("Comando")) {
                System.out.println(clazz.getName());
                msg = msg + clazz.getName();
            }
        }
        this.ioconsole.mostraMessaggio(msg);
    }

    /*
    * impostazione del parametro (inutile, ma richiesto dalla interface)
    */
    @Override
    public void setParametro(String parametro) {
    }

    /*
    * impostazione della console
    */
    @Override
    public void setIOConsole(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
}
