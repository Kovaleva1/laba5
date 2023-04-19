package commands;

public interface Command {
     void execute();

    String descr();

     void getCommands(String[] commandArguments) ;

}
