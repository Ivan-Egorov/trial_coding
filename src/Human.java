class Human {
    private String gender;
    private int age;
    private String lastName;

    private Human(Builder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.lastName = builder.lastName;
    }

    public class Builder {
        private String gender;
        private int age;
        private String lastName;

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }
}