package dev.cutie.androidtoolbox.commands;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Permits the communication between a view and a controller.
 */
public class CommandManager {
    private static CommandManager instance;
    private List<Command> commands;
    private volatile boolean isExecuting;
    private List<Command> addList;
    private List<String> removeList;

    /**
     * Set up
     */
    private CommandManager()
    {
        this.commands = new ArrayList<>();
        this.removeList = new ArrayList<>();
        this.addList = new ArrayList<>();
    }

    /**
     *  Retourne l'instance unique du CommandManager.
     *  @return L'instance unique du CommandManager
     */
    public static CommandManager getInstance()
    {
        if(instance == null)
            instance = new CommandManager();
        return  instance;
    }

    /**
     Exécute toutes les commandes en boucle de manière asynchrone.
     */
    public void Execute()
    {
        this.commands.stream()
                .filter(Command::isConditionMet)
                .forEach(Command::execute);
    }

    /**
     Ajoute une liste de commandes associées à une vue.
     @param view La vue associée à la liste de commandes
     @param commands La liste de commandes à ajouter
     */
    public void attachCommand(View view, List<Command> commands) {
        commands.forEach(command -> command.setClassId(view.toString()));
        if(this.isExecuting)
        {
            this.addList.addAll(commands);
        }
        else {
            this.commands.addAll(commands);
        }
    }

    /**
     * Add a command to the list of command
     * @param view La vue associée à la liste de commandes
     * @param command a command to add

     */
    public void attachCommand(Object view, Command command) {
        command.setClassId(view.toString());
        if(this.isExecuting)
        {
            this.addList.add(command);
        }
        else {
            this.commands.add(command);
        }
    }


    /**
     *
     * @param view La vue dont on veut supprimer les commandes associées
     */
    public void detachCommand(View view)
    {
        if(this.isExecuting)
        {
            removeList.add(view.toString());
        }
        else {
            this.commands.removeIf(command -> command.checkIfCommandOfClass(view));
        }
    }

    /**
     * Marks a command to be removed from the commadn list
     */
    private void executeRemoveQueue()
    {
        if(!removeList.isEmpty()) {
            this.removeList.forEach(view -> commands.removeIf(command -> command.checkIfCommandOfClass(view)));
            removeList = new ArrayList<>();
        }
    }

    /**
     * Marks a command to be added to the command list
     */
    private void executeAddQueue()
    {
        if(!addList.isEmpty()) {
            commands.addAll(addList);
            addList = new ArrayList<>();
        }
    }

    /**
     * Launch the management of the commands on a new thread
     */
    public static void launch()
    {
        new Thread(new Runnable() {
            @Override public void run() {
                while (true)
                {
                    getInstance().executeAddQueue();
                    getInstance().executeRemoveQueue();
                    getInstance().isExecuting = true;
                    getInstance().Execute();
                    getInstance().isExecuting = false;
                }
            }
        }).start();
    }
}
