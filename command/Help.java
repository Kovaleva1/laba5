package commands;

import java.util.LinkedHashMap;


public class Help implements Command {
    private String[] commandArguments;
    private Invoker commands;

    public Help( Invoker commands) {
        this.commands = commands;
    }

    public String descr() {
        return "help — помощь, вывод справки по доступным командам и формату их использования";
    }

    public void execute() {
        for (Command c : Invoker.getCommandHashMap().values())
            System.out.println(c.descr());
    }
    public void getCommands(String[] arguments) {
        this.commandArguments = commandArguments;

    }
}

