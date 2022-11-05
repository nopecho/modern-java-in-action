@FunctionalInterface
public interface Command {
    void doCommand(Class<?> clazz) throws Exception;
}
