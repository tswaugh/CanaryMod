import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract superclass for command handlers, like {@link PlayerCommands} and
 * {@link ServerConsoleCommands}.
 * @author 14mRh4X0r
 */
public abstract class CommandHandler {
    private final Map<String, BaseCommand> commands =
            new LinkedHashMap<String, BaseCommand>();

    /**
     * Add a command to the player list.
     *
     * @param name The name of the command
     * @param cmd The {@link BaseCommand} to add
     */
    public void add(String name, BaseCommand cmd) {
        if (name != null && cmd != null) {
            if (!commands.containsValue(cmd)) {
                etc.getInstance().addCommand("/" + name, cmd.tooltip);
            }
            commands.put(name, cmd);
        }
    }

    public void addAll(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Command.class)) {
                for (String command : field.getAnnotation(Command.class)
                        .value()) {
                    try {
                        this.add(command.equals("") ? field.getName() : command,
                                 (BaseCommand) field.get(null));
                    } catch (IllegalAccessException e) {
                    }
                }
            }
        } // impossible
    }

    /**
     * Searches for and returns a command.
     *
     * @param command The command to search for
     * @return The {@link BaseCommand} corresponding to <tt>command</tt> if
     * found, <tt>null</tt> otherwise
     */
    public BaseCommand getCommand(String command) {
        return commands.get(command);
    }

    /**
     * Remove a command from the player list.
     *
     * @param name The name of the command to remove
     */
    public void remove(String name) {
        if (name != null) {
            etc.getInstance().removeCommand("/" + name);
            commands.remove(name);
        }
    }

}
