package fixture;

public class Apple {
    private int weight;
    private String color;

    private Apple(AppleBuilder builder) {
        this.weight = builder.weight;
        this.color = builder.color;
    }

    public static AppleBuilder builder() {
        return new AppleBuilder();
    }

    public int getWeight() {
        return this.weight;
    }

    public String getColor() {
        return this.color;
    }

    public static class AppleBuilder {
        private int weight;
        private String color;

        private AppleBuilder() {}

        public AppleBuilder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public AppleBuilder color(String color) {
            this.color = color;
            return this;
        }

        public Apple build() {
            return new Apple(this);
        }
    }

}
