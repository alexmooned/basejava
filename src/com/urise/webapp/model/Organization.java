package com.urise.webapp.model;

import java.util.Objects;

public class Organization {
    private final Period periods;
    private final String name;
    private final String website;

    public Organization(Period periods, String name, String website) {
        Objects.requireNonNull(name, "name must not be null");
        this.periods = periods;
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getWebSite() {
        return website;
    }

    @Override
    public String toString() {
        return "Organization(" + name + ',' + website + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!name.equals(that.name)) return false;
        return Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        return result;
    }
}
