package fixture;

public class DefaultAppleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        StringBuilder buffer = new StringBuilder();
        return String.valueOf(buffer.append(apple.getColor()).append(" ").append(apple.getWeight()).append("g").append("\n"));
    }
}
