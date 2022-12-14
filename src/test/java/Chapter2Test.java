import fixture.Apple;
import fixture.AppleContainer;
import fixture.AppleFormatter;
import fixture.DefaultAppleFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Chapter2Test {
    String DEFAULT_FORMAT = "red 80g";
    String DEFAULT_FORMAT2 = "blue 150g";
    String DEFAULT_FORMAT3 = "red 80g\nblue 150g\n";
    Apple TEST_DATA = Apple.builder().weight(80).color("red").build();
    Apple TEST_DATA2 = Apple.builder().weight(150).color("blue").build();

    @Test
    @DisplayName("동작 파라미터")
    void operating_parameters() {
        AppleContainer container = AppleContainer.of(TEST_DATA);
        AppleFormatter sut = new DefaultAppleFormatter();

        String actual = container.prettyPrintApple(sut);

        assertThat(actual).isEqualTo(DEFAULT_FORMAT);
    }

    @Test
    @DisplayName("동작 파라미터2")
    void operating_parameters2() {
        AppleContainer container = AppleContainer.of(TEST_DATA2);
        AppleFormatter sut = new DefaultAppleFormatter();

        String actual = container.prettyPrintApple(sut);

        assertThat(actual).isEqualTo(DEFAULT_FORMAT2);
    }

    @Test
    @DisplayName("동작 파라미터3")
    void operating_parameters3() {
        AppleContainer container = AppleContainer.of(TEST_DATA, TEST_DATA2);
        AppleFormatter sut = new DefaultAppleFormatter();

        String actual = container.prettyPrintApple(sut);

        assertThat(actual).isEqualTo(DEFAULT_FORMAT3);
    }

    @Test
    @DisplayName("Predicate")
    void predicate() {
        AppleContainer container = AppleContainer.of(TEST_DATA, TEST_DATA2);

        List<Apple> actual = container.filter((apple -> apple.getWeight() > 50));
        List<Apple> actual2 = container.filter((apple -> apple.getWeight() < 50));

        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual2).isEmpty();
    }

    private static class CommandRegister {
        private final List<Command> container = new ArrayList<>();

        public void registration (Command ...commands) {
            this.container.addAll(List.of(commands));
        }

        public List<Command> get() {
            return this.container;
        }
    }

    @Test
    void register() {
        CommandRegister register = new CommandRegister();
        register.registration((c) ->  c.getDeclaredMethods()[0].invoke(c));

        assertThat(register.get()).isNotEmpty();
    }

    @Test
    void test() {
        CommandRegister register = new CommandRegister();
        register.registration(
                (t) ->  t.getDeclaredMethods()[0].invoke(new Foo()),
                (t) -> t.getDeclaredMethods()[0].invoke(new Bar())
        );

        register.get().forEach(command -> {
            try {
                command.doCommand(Foo.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static class Foo {
        public void print() {
            System.out.println("foo");
        }
    }

    private static class Bar {

        private List<String> list = new ArrayList<>();

        public void add(String s) {
            list.add(s);
        }
    }
}
