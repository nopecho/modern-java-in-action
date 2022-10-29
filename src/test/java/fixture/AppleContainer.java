package fixture;

import java.util.List;
import java.util.stream.Collectors;

public class AppleContainer {

    private final List<Apple> container;

    private AppleContainer(Apple... apple){
        this.container = List.of(apple);
    }

    public static AppleContainer of(Apple... apple) {
        return new AppleContainer(apple);
    }

    public String prettyPrintApple(AppleFormatter formatter) {
        return container.stream()
                .map(formatter::accept)
                .collect(Collectors.joining());
    }
}
