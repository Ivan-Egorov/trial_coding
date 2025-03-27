import java.io.Serializable;

public class Human implements TrialComparable<Human>, Serializable {
    private String gender;
    private int age;
    private String lastName;

    private Human(Builder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.lastName = builder.lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int compareTo(Human other) {
        if (this.age != other.age)
            return Integer.compare(this.age, other.age);
        if (!this.lastName.equalsIgnoreCase(other.lastName))
            return this.lastName.compareTo(other.lastName);
        return this.gender.compareTo(other.gender);
    }

    @Override
    public String toString() {
        return " Human - " +
                " Gender - " + gender +
                " LastName - " + lastName +
                " Age - " + age;
    }

    public static class Builder {
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