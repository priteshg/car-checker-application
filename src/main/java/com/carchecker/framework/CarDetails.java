package com.carchecker.framework;

import java.util.Objects;

/**
 * Simple POJO of car details using builder pattern
 *
 */
public class CarDetails {

    private final String make;
    private final String model;
    private final String colour;
    private final Integer year;

    private CarDetails(Builder b) {
        this.make = b.make;
        this.model = b.model;
        this.colour = b.colour;
        this.year = b.year;
    }

    @Override
    public String toString() {
        return "com.carchecker.framework.CarDetails{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDetails that = (CarDetails) o;
        return Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(colour, that.colour) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, colour, year);
    }

    public static class Builder {
        public String make;
        public String model;
        public String colour;
        public Integer year;

        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder colour(String colour) {
            this.colour = colour;
            return this;
        }

        public Builder year(Integer year) {
            this.year = year;
            return this;
        }

        public Builder year(String year) {
            this.year = Integer.parseInt(year);
            return this;
        }

        public CarDetails build() {
            return new CarDetails(this);
        }

    }

}
