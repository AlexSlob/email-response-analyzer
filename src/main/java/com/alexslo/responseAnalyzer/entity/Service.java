package com.alexslo.responseAnalyzer.entity;

import java.util.Objects;

public class Service {

    private final Integer serviceId;
    private final Integer variationId;
    private static final String DOT_PATTERN = "\\.";
    private static final String ASTERISK = "*";

    public Service(Integer serviceId) {
        this(serviceId, null);
    }

    public Service(Integer serviceId, Integer variationId) {
        this.serviceId = serviceId;
        this.variationId = variationId;
    }

    public static Service fromString(String value) {
        Objects.requireNonNull(value);

        if (value.equals(ASTERISK)) {
            return new Service(null);
        }

        String[] parts = value.split(DOT_PATTERN);

        if (parts.length > 2) {
            throw new IllegalArgumentException();
        }

        return parts.length == 2 ?
                new Service(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])) :
                new Service(Integer.parseInt(parts[0]));
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public Integer getVariationId() {
        return variationId;
    }

    public boolean matches(Service o) {
        return matchesServiceId(o.getServiceId()) && matchesVariationId(o.getVariationId());
    }

    private boolean matchesServiceId(Integer serviceId) {
        return this.serviceId == null || serviceId == null || this.serviceId == serviceId;
    }

    private boolean matchesVariationId(Integer variationId) {
        return this.variationId == null || variationId == null || this.variationId == variationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(serviceId, service.serviceId) &&
                Objects.equals(variationId, service.variationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, variationId);
    }
}
